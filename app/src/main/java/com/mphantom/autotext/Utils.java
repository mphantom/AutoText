//package com.mphantom.autotext;
//
//import android.content.res.Resources;
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//
///**
// * Created by wushaorong on 16-10-17.
// */
//
//public class Utils {
//    public static void forceCustomPadding(View view, boolean additive) {
//        final Resources res = view.getResources();
//        final int paddingSide = res.getDimensionPixelSize(R.dimen.settings_side_margin);
//
//        final int paddingStart = paddingSide + (additive ? view.getPaddingStart() : 0);
//        final int paddingEnd = paddingSide + (additive ? view.getPaddingEnd() : 0);
//        final int paddingBottom = res.getDimensionPixelSize(
//                com.android.internal.R.dimen.preference_fragment_padding_bottom);
//
//        view.setPaddingRelative(paddingStart, 0, paddingEnd, paddingBottom);
//    }
//
//    public static void handleLoadingContainer(View loading, View doneLoading, boolean done,
//                                              boolean animate) {
//        setViewShown(loading, !done, animate);
//        setViewShown(doneLoading, done, animate);
//    }
//
//    private static void setViewShown(final View view, boolean shown, boolean animate) {
//        if (animate) {
//            Animation animation = AnimationUtils.loadAnimation(view.getContext(),
//                    shown ? android.R.anim.fade_in : android.R.anim.fade_out);
//            if (shown) {
//                view.setVisibility(View.VISIBLE);
//            } else {
//                animation.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//                        view.setVisibility(View.INVISIBLE);
//                    }
//                });
//            }
//            view.startAnimation(animation);
//        } else {
//            view.clearAnimation();
//            view.setVisibility(shown ? View.VISIBLE : View.INVISIBLE);
//        }
//    }
//}
