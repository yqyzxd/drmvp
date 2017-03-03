package com.wind.drmvp.base.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

import com.wind.base.utils.AnimUtils;

import static android.view.View.MeasureSpec.makeMeasureSpec;

/**
 * Created by wind on 2017/3/2.
 */

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class CardToFullTransition extends Transition {

    private static final String PROP_BOUNDS = "marryu:cardToFullTransform:bounds";

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    private void captureValues(TransitionValues transitionValues) {
        final View view = transitionValues.view;
        if (view == null || view.getWidth() <= 0 || view.getHeight() <= 0) return;

        transitionValues.values.put(PROP_BOUNDS, new Rect(view.getLeft(), view.getTop(),
                view.getRight(), view.getBottom()));
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {

        if (startValues == null || endValues == null) return null;

        final Rect startBounds = (Rect) startValues.values.get(PROP_BOUNDS);
        final Rect endBounds = (Rect) endValues.values.get(PROP_BOUNDS);

        final boolean fromCard = endBounds.width() > startBounds.width();
        final View view = endValues.view;
        final Rect cardBounds = fromCard ? endBounds : startBounds;
        final Rect fullBounds = fromCard ? startBounds : endBounds;
        final Interpolator fastOutSlowInInterpolator =
                AnimUtils.getFastOutSlowInInterpolator(sceneRoot.getContext());
        final long duration = getDuration();


        if (!fromCard) {
            // Force measure / layout the dialog back to it's original bounds
            view.measure(
                    makeMeasureSpec(startBounds.width(), View.MeasureSpec.EXACTLY),
                    makeMeasureSpec(startBounds.height(), View.MeasureSpec.EXACTLY));
            view.layout(startBounds.left, startBounds.top, startBounds.right, startBounds.bottom);
        }


        final int translationX = startBounds.centerX() - endBounds.centerX();
        final int translationY = startBounds.centerY() - endBounds.centerY();
        if (fromCard) {
            view.setTranslationX(translationX);
            view.setTranslationY(translationY);
        }
        final Animator scaleXAnimator;
        final Animator scaleYAnimator;


        float scaleX = endBounds.width() / (float) startBounds.width();
        float scaleY = endBounds.height() / (float) startBounds.height();
        scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, scaleX);
        scaleYAnimator=ObjectAnimator.ofFloat(view, "scaleY", 1.0f, scaleY);


        // Translate to end position along an arc
        final Animator translate = ObjectAnimator.ofFloat(
                view,
                View.TRANSLATION_X,
                View.TRANSLATION_Y,
                fromCard ? getPathMotion().getPath(translationX, translationY, 0, 0)
                        : getPathMotion().getPath(0, 0, -translationX, -translationY));
        translate.setDuration(duration);
        translate.setInterpolator(fastOutSlowInInterpolator);

        final AnimatorSet transition = new AnimatorSet();
        transition.playTogether(scaleXAnimator,scaleYAnimator, translate);
        if (fromCard) {
            transition.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    // Clean up
                    view.getOverlay().clear();
                }
            });
        }
        return new AnimUtils.NoPauseAnimator(transition);
    }
}
