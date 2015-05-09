package com.vpfinance.stephen.androiduidemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Wang Gensheng on 2015/5/9.
 */
public class ScrawlBoard extends View {

    private int width;
    private int height;

    private Paint mPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ScrawlBoard(Context context) {
        this(context, null);
    }

    public ScrawlBoard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrawlBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = width / 2;
        int centerY = height / 2;
        int diameter = 132;
        int radius = diameter / 2;
        int gap = diameter * 2 / 3;

        mPathPaint.setStyle(Paint.Style.STROKE);
        mPathPaint.setStrokeWidth(2);
        mPathPaint.setColor(0x4cffffff);
        canvas.drawCircle(centerX, centerY, radius, mPathPaint);

        mPathPaint.setColor(0x7fffffff);
        mPathPaint.setStrokeWidth(4);
        mPathPaint.setShadowLayer(4,0,0,0xff89bec0);
        canvas.drawCircle(centerX + diameter + gap, centerY, radius, mPathPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);

    }
}
