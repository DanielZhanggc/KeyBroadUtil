package com.yitong.keyboarddemo.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;

/**
 * @Author Daniel Zhang
 * @Time 2019/2/23 12:11
 * @Description
 */
@SuppressWarnings("UnnecessaryLocalVariable")
@SuppressLint({"StaticFieldLeak"})
public class PanHideUtil implements OnGlobalLayoutListener {
    private static PanHideUtil panHideUtil;
    private View mChildOfContent;
    private int usableHeightPrevious;
    private FrameLayout.LayoutParams frameLayoutParams;

    private PanHideUtil() {
    }

    public static synchronized PanHideUtil getInstance() {
        if (panHideUtil == null) {
            synchronized (KeyBoardUtil.class) {
                if (panHideUtil == null) {
                    panHideUtil = new PanHideUtil();
                }
            }
        }
        return panHideUtil;
    }

    public void attachActivity(Activity activity) {
        FrameLayout content = activity.findViewById(android.R.id.content);
        this.mChildOfContent = content.getChildAt(0);
        this.mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(this);
        this.frameLayoutParams = (FrameLayout.LayoutParams) this.mChildOfContent.getLayoutParams();
    }

    public void onGlobalLayout() {
        if (null == this.mChildOfContent) {
            NullPointerException exception = new NullPointerException("The specified layout could not be found");
            throw exception;
        } else {
            Rect r = new Rect();
            this.mChildOfContent.getWindowVisibleDisplayFrame(r);
            int visibleHeight = r.height();
            if (visibleHeight != this.usableHeightPrevious) {
                int usableHeightSansKeyboard = this.mChildOfContent.getRootView().getHeight();
                int heightDifference = usableHeightSansKeyboard - visibleHeight;
                if (heightDifference > usableHeightSansKeyboard / 4) {
                    this.frameLayoutParams.height = usableHeightSansKeyboard - heightDifference;
                } else {
                    this.frameLayoutParams.height = usableHeightSansKeyboard;
                }

                this.mChildOfContent.requestLayout();
                this.usableHeightPrevious = visibleHeight;
            }

        }
    }
}
