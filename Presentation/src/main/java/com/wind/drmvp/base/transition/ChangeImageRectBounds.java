package com.wind.drmvp.base.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wind.base.utils.AnimUtils;

/**
 * Created by wind on 2017/3/3.
 */

@TargetApi(Build.VERSION_CODES.KITKAT)
public class ChangeImageRectBounds extends Transition {

    private static final String PROP_BOUNDS = "marryu:ChangeImageRectBounds:bounds";
    private static final String PROP_GLOBAL_OFFSET = "marryu:ChangeImageRectBounds:global_offset";
    private static final String PROP_VIEW_BOUNDS = "marryu:ChangeImageRectBounds:viewbounds";
    private static final String PROP_VIEW_CONTENT_BOUNDS = "marryu:ChangeImageRectBounds:viewcontentbounds";
    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
        /*final View view = transitionValues.view;
        if (view == null || view.getWidth() <= 0 || view.getHeight() <= 0) return;
        Point globalOffset = new Point();


        Rect result=new Rect();
         transitionValues.view.getGlobalVisibleRect(result, globalOffset);
        transitionValues.values.put(PROP_GLOBAL_OFFSET, globalOffset);
        transitionValues.values.put(PROP_BOUNDS, result);*/
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
       /* final View view = transitionValues.view;
        if (view == null || view.getWidth() <= 0 || view.getHeight() <= 0) return;
        Point globalOffset = new Point();


        Rect result=new Rect();
        if (transitionValues.view instanceof ImageView){
            Rect viewRect=new Rect();
            transitionValues.view.getGlobalVisibleRect(viewRect, globalOffset);
            transitionValues.values.put(PROP_VIEW_BOUNDS, viewRect);
            ImageView iv= (ImageView) transitionValues.view;
            ImageRect imageRect=new ImageRect(iv);
            RectF bounds=imageRect.getImageRect();

            result.set((int) bounds.left, (int) bounds.top, (int) bounds.right, (int) bounds.bottom);
        }else {
            transitionValues.view.getGlobalVisibleRect(result, globalOffset);
            transitionValues.values.put(PROP_VIEW_BOUNDS, result);
        }
        transitionValues.values.put(PROP_GLOBAL_OFFSET, globalOffset);
        transitionValues.values.put(PROP_BOUNDS, result);*/
    }

    private void captureValues(TransitionValues transitionValues) {
        final View view = transitionValues.view;
        if (view == null || view.getWidth() <= 0 || view.getHeight() <= 0) return;
        Point globalOffset = new Point();


        Rect result=new Rect();
       // transitionValues.view.getGlobalVisibleRect(result, globalOffset);
        if (transitionValues.view instanceof ImageView){
            Rect viewRect=new Rect();
            transitionValues.view.getGlobalVisibleRect(viewRect, globalOffset);
            transitionValues.values.put(PROP_VIEW_BOUNDS, viewRect);
            ImageView iv= (ImageView) transitionValues.view;
            ImageRect imageRect=new ImageRect(iv);
            Rect bounds=imageRect.getImageRect();
            transitionValues.values.put(PROP_VIEW_CONTENT_BOUNDS, bounds);
            result.set((int) bounds.left+viewRect.left, (int) bounds.top+viewRect.top,
                    (int) bounds.right+viewRect.right, (int) bounds.bottom+viewRect.bottom);
        }else {
            transitionValues.view.getGlobalVisibleRect(result, globalOffset);
            transitionValues.values.put(PROP_VIEW_BOUNDS, result);
        }

        transitionValues.values.put(PROP_GLOBAL_OFFSET, globalOffset);
        transitionValues.values.put(PROP_BOUNDS, result);
    }
    float startScale;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {

        if (startValues == null || endValues == null) {
            return null;
        }
        Rect startBounds = (Rect) startValues.values.get(PROP_BOUNDS);
        Rect startContentBounds= (Rect) startValues.values.get(PROP_VIEW_CONTENT_BOUNDS);
        Rect finalBounds = (Rect) endValues.values.get(PROP_BOUNDS);
        Rect finalContentBounds= (Rect) endValues.values.get(PROP_VIEW_CONTENT_BOUNDS);

        final View endView = endValues.view;


        boolean fromThumbnail = finalBounds.width() > startBounds.width();
        final Point globalOffset = (Point) endValues.values.get(PROP_GLOBAL_OFFSET);


        if (fromThumbnail) {
            endView.setPivotX(0.5f);
            endView.setPivotY(0.5f);
            Rect finalViewBounds = (Rect) endValues.values.get(PROP_VIEW_BOUNDS);
            startBounds.offset(-globalOffset.x, -globalOffset.y);
            finalViewBounds.offset(-globalOffset.x, -globalOffset.y);

            float[] ratios=calculateRatios(startBounds,finalViewBounds);

            final AnimatorSet enter = new AnimatorSet();
            enter.play(ObjectAnimator.ofFloat(endView, View.X, startBounds.left, finalViewBounds.left))
                    .with(ObjectAnimator.ofFloat(endView, View.Y, startBounds.top, finalViewBounds.top))
                    .with(ObjectAnimator.ofFloat(endView, View.SCALE_X, ratios[0], 1f))
                    .with(ObjectAnimator.ofFloat(endView, View.SCALE_Y, ratios[1], 1f));

            return new AnimUtils.NoPauseAnimator(enter);
        } else {
            startValues.view.setPivotX(0.5f);
            startValues.view.setPivotY(0.5f);
            endValues.view.setPivotX(0.5f);
            endValues.view.setPivotY(0.5f);
            Rect finalViewBounds = (Rect) endValues.values.get(PROP_VIEW_BOUNDS);
            Rect startViewBounds = (Rect) startValues.values.get(PROP_VIEW_BOUNDS);
            float[] ratios=calculateRatios(finalViewBounds,startViewBounds);

            int relativeLeft=startBounds.left-startViewBounds.left;
            int relativeTop=startBounds.top-startViewBounds.top;
            //垂直方向的位移
            int deltaHeight = (int) (relativeTop* ratios[1]);
            //水平方向的位移
            int deltaWidth = (int) (relativeLeft* ratios[0]);
            final AnimatorSet exit = new AnimatorSet();
            ratios=calculateRatios(startContentBounds,finalContentBounds);
            exit.play(ObjectAnimator.ofFloat(endView, View.X, startBounds.left,finalBounds.left-deltaWidth))
                    .with(ObjectAnimator.ofFloat(endView, View.Y, startBounds.top,finalBounds.top-deltaHeight))
                    .with(ObjectAnimator.ofFloat(endView, View.SCALE_X, ratios[0],1f))
                    .with(ObjectAnimator.ofFloat(endView, View.SCALE_Y, ratios[1],1f));



            return new AnimUtils.NoPauseAnimator(exit);
        }
    }
    private float[] calculateRatios(Rect startBounds, Rect finalBounds) {
        //startBounds：点击的View的绘制区域（小图）
        //finalBounds：最终展示的View的绘制区域（大图）
        float[] result = new float[2];
        float widthRatio = startBounds.width() * 1.0f / finalBounds.width() * 1.0f;
        float heightRatio = startBounds.height() * 1.0f / finalBounds.height() * 1.0f;
        result[0] = widthRatio;
        result[1] = heightRatio;
        return result;
    }
    public static class ImageRect {
        private Rect rect;

        public ImageRect(ImageView imageview) {
            rect = new Rect();
            if (imageview != null&&imageview.getDrawable()!=null) {
                //得到drawable的边界
                Rect drawableRect = imageview.getDrawable().getBounds();
                //得到图片的矩阵
                Matrix imgMatrix = imageview.getImageMatrix();
                float[] matrixValues = new float[9];
                imgMatrix.getValues(matrixValues);
                //图片的左边界（相对于imageview）
                rect.left =(int) matrixValues[Matrix.MTRANS_X];
                //图片的顶边界（相对于imageview）
                rect.top = (int) matrixValues[Matrix.MTRANS_Y];
                //图片的右边界（相对于imageview），计算方法：左边界+图片宽*X方向的缩放
                rect.right =(int) ( rect.left + drawableRect.width() * matrixValues[Matrix.MSCALE_X]);
                //图片的右边界（相对于imageview），计算方法：上边界+图片高*Y方向的缩放
                rect.bottom = (int)(rect.top + drawableRect.height() * matrixValues[Matrix.MSCALE_Y]);
            }
        }

        public Rect getImageRect() {
            return rect;
        }
    }
}
