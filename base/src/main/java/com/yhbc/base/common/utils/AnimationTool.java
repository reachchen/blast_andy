package com.yhbc.base.common.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;

import com.yhbc.base.R;

/**
 * Created by Administrator on 2017/3/15.
 */

public class AnimationTool {

    static ObjectAnimator invisToVis;
    static ObjectAnimator visToInvis;

    /**
     * 颜色渐变动画
     *
     * @param beforeColor 变化之前的颜色
     * @param afterColor  变化之后的颜色
     * @param listener    变化事件
     */
    public static void animationColorGradient(int beforeColor, int afterColor, final OnUpdateListener listener) {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), beforeColor, afterColor).setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                textView.setTextColor((Integer) animation.getAnimatedValue());
                listener.onUpdate((Integer) animation.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }

    /**
     * 卡片翻转动画
     *
     * @param beforeView
     * @param AfterView
     */
    public static void cardFilpAnimation(final View beforeView, final View AfterView) {
        Interpolator accelerator = new AccelerateInterpolator();
        Interpolator decelerator = new DecelerateInterpolator();
        if (beforeView.getVisibility() == View.GONE) {
            // 局部layout可达到字体翻转 背景不翻转
            invisToVis = ObjectAnimator.ofFloat(beforeView,
                    "rotationY", -90f, 0f);
            visToInvis = ObjectAnimator.ofFloat(AfterView,
                    "rotationY", 0f, 90f);
        } else if (AfterView.getVisibility() == View.GONE) {
            invisToVis = ObjectAnimator.ofFloat(AfterView,
                    "rotationY", -90f, 0f);
            visToInvis = ObjectAnimator.ofFloat(beforeView,
                    "rotationY", 0f, 90f);
        }

        visToInvis.setDuration(250);// 翻转速度
        visToInvis.setInterpolator(accelerator);// 在动画开始的地方速率改变比较慢，然后开始加速
        invisToVis.setDuration(250);
        invisToVis.setInterpolator(decelerator);
        visToInvis.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationEnd(Animator arg0) {
                if (beforeView.getVisibility() == View.GONE) {
                    AfterView.setVisibility(View.GONE);
                    invisToVis.start();
                    beforeView.setVisibility(View.VISIBLE);
                } else {
                    AfterView.setVisibility(View.GONE);
                    visToInvis.start();
                    beforeView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationCancel(Animator arg0) {

            }

            @Override
            public void onAnimationRepeat(Animator arg0) {

            }

            @Override
            public void onAnimationStart(Animator arg0) {

            }
        });
        visToInvis.start();
    }

    /**
     * 缩小动画
     *
     * @param view
     */
    public static void zoomIn(final View view, float scale, float dist) {
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, scale);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, scale);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", 0.0f, -dist);

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX);
        mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();
    }

    /**
     * 放大动画
     *
     * @param view
     */
    public static void zoomOut(final View view, float scale) {
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();

        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", scale, 1.0f);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", scale, 1.0f);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), 0);

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX);
        mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();
    }

    public static void ScaleUpDowm(View view) {
        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.RESTART);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(1200);
        view.startAnimation(animation);
    }

    public static void animateHeight(int start, int end, final View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();//根据时间因子的变化系数进行设置高度
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);//设置高度
            }
        });
        valueAnimator.start();
    }

    /**
     * 底部滑入
     *
     * @param view
     */
    public static void bottomIn(View view, Context context) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_bottom_in);
        /*if (null != listener) {
            animation.setAnimationListener(listener);
        }*/
        view.startAnimation(animation);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 底部滑出
     *
     * @param view
     */
    public static void bottomOut(View view, Context context) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_bottom_out);
        /*if (null != listener) {
            animation.setAnimationListener(listener);
        }*/
        view.startAnimation(animation);
        view.setVisibility(View.GONE);
    }

    public interface OnUpdateListener {
        void onUpdate(int intValue);
    }
}
