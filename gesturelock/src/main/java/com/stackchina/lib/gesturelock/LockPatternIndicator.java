package com.stackchina.lib.gesturelock;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Wang Gensheng on 2015/5/8.
 */
public class LockPatternIndicator extends View implements LockPatternIndicatorInterface {

    private LockPatternView mPatternView;

    public LockPatternIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mPatternView == null) {
            return;
        }
    }

    @Override
    public void setPatternView(LockPatternView view) {
        this.mPatternView = view;
    }

    @Override
    public void onPatternDraw() {

    }
}
