package com.yitong.keyboarddemo.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

/**
 * @Author Daniel Zhang
 * @Time 2019/2/23 12:13
 * @Description
 */
@SuppressWarnings("UnnecessaryLocalVariable")
@SuppressLint({"StaticFieldLeak"})
public class KeyBoardUtil implements OnGlobalLayoutListener {
    private static KeyBoardUtil keyBoardUtil;
    private View rootView;
    private int rootViewVisibleHeight;
    private KeyBoardUtil.OnKeyBoardPopListener onKeyBoardPopListener;

    private KeyBoardUtil() {
    }

    public static synchronized KeyBoardUtil getInstance() {
        if (keyBoardUtil == null) {
            synchronized(KeyBoardUtil.class) {
                if (keyBoardUtil == null) {
                    keyBoardUtil = new KeyBoardUtil();
                }
            }
        }

        return keyBoardUtil;
    }

    public void setOnKeyBoardPopListener(Activity activity, KeyBoardUtil.OnKeyBoardPopListener onKeyBoardPopListener) {
        this.onKeyBoardPopListener = onKeyBoardPopListener;
        this.rootView = activity.getWindow().getDecorView();
        this.rootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    public void onGlobalLayout() {
        if (null == this.rootView) {
            NullPointerException exception = new NullPointerException("The specified layout could not be found");
            throw exception;
        } else {
            Rect r = new Rect();
            this.rootView.getWindowVisibleDisplayFrame(r);
            int visibleHeight = r.height();
            if (this.rootViewVisibleHeight == 0) {
                this.rootViewVisibleHeight = visibleHeight;
            } else if (this.rootViewVisibleHeight != visibleHeight) {
                if (this.rootViewVisibleHeight - visibleHeight > 200) {
                    if (this.onKeyBoardPopListener != null) {
                        this.onKeyBoardPopListener.keyBoardShow(this.rootViewVisibleHeight - visibleHeight);
                    }

                    this.rootViewVisibleHeight = visibleHeight;
                } else {
                    if (visibleHeight - this.rootViewVisibleHeight > 200) {
                        if (this.onKeyBoardPopListener != null) {
                            this.onKeyBoardPopListener.keyBoardHide(visibleHeight - this.rootViewVisibleHeight);
                        }

                        this.rootViewVisibleHeight = visibleHeight;
                    }

                }
            }
        }
    }

    public interface OnKeyBoardPopListener {
        void keyBoardShow(int var1);

        void keyBoardHide(int var1);
    }
}

