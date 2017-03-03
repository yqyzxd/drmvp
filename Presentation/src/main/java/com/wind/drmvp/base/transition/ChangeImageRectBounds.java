package com.wind.drmvp.base.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

import com.wind.base.utils.AnimUtils;

/**
 * Created by wind on 2017/3/3.
 */

@TargetApi(Build.VERSION_CODES.KITKAT)
public class ChangeImageRectBounds extends Transition {

    private static final String PROP_BOUNDS = "marryu:ChangeImageRectBounds:bounds";

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    private void captureValues(TransitionValues transitionValues) {
        final View view = transitionValues.view;
        if (view == null || view.getWidth() <= 0 || view.getHeight() <= 0) return;

        transitionValues.values.put(PROP_BOUNDS, new Rect(view.getLeft(), view.getTop(),
                view.getRight(), view.getBottom()));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {

        if (startValues == null || endValues == null) {
            return null;
        }
        final Rect startBounds = (Rect) startValues.values.get(PROP_BOUNDS);
        final Rect endBounds = (Rect) endValues.values.get(PROP_BOUNDS);

        final View view = endValues.view;
        ObjectAnimator animatorWidth = ObjectAnimator.ofInt(new ViewWrapper(view), "width", startBounds.width(), endBounds.width());
        ObjectAnimator animatorHeight = ObjectAnimator.ofInt(new ViewWrapper(view), "height", startBounds.height(), endBounds.height());
        final int translationX = startBounds.centerX() - endBounds.centerX();
        final int translationY = startBounds.centerY() - endBounds.centerY();

        final Animator translate = ObjectAnimator.ofFloat(
                view,
                View.TRANSLATION_X,
                View.TRANSLATION_Y,
                getPathMotion().getPath(translationX, translationY, 0, 0)
                      );
        AnimatorSet transition = new AnimatorSet();
        transition.playTogether(animatorWidth, animatorHeight, translate);
        transition.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // Clean up
                view.getOverlay().clear();
            }
        });
        return new AnimUtils.NoPauseAnimator(transition);
    }


    private class ViewWrapper {

        private View real;

        public ViewWrapper(View v) {
            real = v;
        }

        public void setWidth(int width) {
            ViewGroup.LayoutParams params = real.getLayoutParams();
            params.width = width;
        }

        public void setHeight(int height) {
            ViewGroup.LayoutParams params = real.getLayoutParams();
            params.height = height;
        }

        public int getWidth() {
            return real.getLayoutParams().width;
        }

        public int getHeight() {
            return real.getLayoutParams().height;
        }

    }
}
