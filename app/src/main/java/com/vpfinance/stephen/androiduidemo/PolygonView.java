package com.vpfinance.stephen.androiduidemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Wang Gensheng on 2015/5/6.
 */
public class PolygonView extends View {

    private static final String DEBUG_TAG = "PolygonView";

    /**
     * @attr
     * 多边形的边数
     */
    private int edges;

    /**
     * @attr
     * 边的色值
     */
    private int strokeColor;

    private int mStrokeWidth = 2;

    private int mRadius;

    private Paint mPathPaint = new Paint();

    private Point[] mPolyVertex;
    private int mWalk = 0;

    private boolean isAnimate;
    private boolean indeterminate;
    private int mDuration;

    private int mWidth;
    private int mHeight;

    public PolygonView(Context context) {
        this(context, null);
    }

    public PolygonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PolygonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PolygonView);
        edges = a.getInteger(R.styleable.PolygonView_edges, 6);
        strokeColor = a.getColor(R.styleable.PolygonView_strokeColor, Color.BLACK);
        a.recycle();

        mStrokeWidth = 4;

        mPathPaint.setAntiAlias(true);
        mPathPaint.setColor(strokeColor);
        mPathPaint.setStyle(Paint.Style.STROKE);
        mPathPaint.setStrokeJoin(Paint.Join.BEVEL);
        mPathPaint.setStrokeWidth(mStrokeWidth);
        mPathPaint.setStrokeCap(Paint.Cap.SQUARE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isAnimate) {
            connectPoints(mPolyVertex, 0, mPolyVertex.length - 1, true, canvas);
        } else {
            Log.d(DEBUG_TAG, "mWalk:" + mWalk);
            if (mWalk != mPolyVertex.length - 1) {
                connectPoints(mPolyVertex, 0, mWalk, false, canvas);
                canvas.drawLine(mPolyVertex[mWalk].x, mPolyVertex[mWalk].y,
                        mPolyVertex[mWalk + 1].x, mPolyVertex[mWalk + 1].y, mPathPaint);
            } else {
                connectPoints(mPolyVertex, 0, mPolyVertex.length - 1, true, canvas);
            }
        }
    }

    private Runnable mWalkAnimator = new Runnable() {
        @Override
        public void run() {
            if (isAnimate && mPolyVertex != null && mPolyVertex.length != 0) {
                mWalk++;

                if (indeterminate) {
                    mWalk = (mWalk + mPolyVertex.length) % mPolyVertex.length;
                }

                if (indeterminate || mWalk < mPolyVertex.length) {
                    invalidate();

                    long delay;
                    if (indeterminate) {
                        delay = 100;
                    } else {
                        delay = mDuration / edges;
                    }

                    postDelayed(this, delay);
                }
            }
        }
    };

    public void reDraw(int duration) {
        if (duration <= 0) {
            isAnimate = false;
            invalidate();
        } else {
            mWalk = 0;
            isAnimate = true;
            mDuration = duration;
            post(mWalkAnimator);
        }
    }

    public void setIndeterminate(boolean infinite) {
        this.isAnimate = true;
        this.indeterminate = true;
        post(mWalkAnimator);
    }

    public void stopAnimate() {
        removeCallbacks(mWalkAnimator);
        this.isAnimate = false;
    }

    private void calcPoints(int n) {
        if (n < 3) {
            throw new IllegalArgumentException("多边形边数不少于3");
        }

        mPolyVertex = new Point[n];
        int centerX = mWidth / 2;
        int centerY = mHeight / 2;
        for (int i = 0; i < n; i++) {
            Point pt = new Point();
            pt.set((int) (centerX + mRadius * Math.cos(alphaAngle(i))), (int) (centerY - mRadius * Math.sin(alphaAngle(i))));
            mPolyVertex[i] = pt;
        }
    }

    private double alphaAngle(int pointIndex) {
        double alpha;
        final double theta = 2 * Math.PI / edges;
        if (edges % 2 != 0) {
            alpha = Math.PI / 2 + pointIndex * theta;
        } else {
            alpha = Math.PI / 2 - theta / 2 + pointIndex * theta;
        }
        return alpha;
    }

    /**
     *
     * @param points
     * @param start
     * @param end
     * @param canvas
     */
    private void connectPoints(Point[] points, int start, int end, boolean seal, Canvas canvas) {
        if (start < 0 || start > points.length - 1 || end < 0 || end > points.length - 1) {
            throw new IndexOutOfBoundsException("");
        }
        Path path = new Path();
        path.moveTo(points[start].x, points[start].y);
        for (int i = start + 1; i <= end; i++) {
            path.lineTo(points[i].x, points[i].y);
            if (i == end && seal) {
                path.lineTo(points[start].x, points[start].y);
            }
        }
        canvas.drawPath(path, mPathPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = mHeight = mWidth > mHeight ? mHeight : mWidth;
        setMeasuredDimension(mWidth, mHeight);

        mWidth = mWidth - getPaddingLeft() - getPaddingRight();
        mHeight = mHeight - getPaddingTop() - getPaddingBottom();
        this.mRadius = mWidth / 2 - this.mStrokeWidth;
        calcPoints(edges);
    }

    public int getEdges() {
        return edges;
    }

    public void setEdges(int edges) {
        this.edges = edges;
        invalidate();
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        invalidate();
    }

}
