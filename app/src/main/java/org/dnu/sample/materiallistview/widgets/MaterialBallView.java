package org.dnu.sample.materiallistview.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by isskj on 3/28/15.
 */
public class MaterialBallView extends View
    implements View.OnTouchListener {

    private boolean mIsScrolling;

    private OnClickListener mClickListener;

    private boolean mDrawingStarting;

    private boolean mDrawingFinishing;

    private int mPaintAlpha;

    private float[] mTouchDown = new float[2];

    private float[] mDistanceOfMove = new float[2];

    private float mCircleRadius;

    private float mMaxRadius;

    private Paint mPaint;

    private static final int DEFAULT_ALPHA = 100;

    public MaterialBallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MaterialBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MaterialBallView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        super.setOnTouchListener(this);
        mPaintAlpha = DEFAULT_ALPHA;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setAlpha(mPaintAlpha);

        ColorDrawable d = (ColorDrawable)getBackground();
    }

    public void setOnScrolling(boolean isScrolling) {
        mIsScrolling = isScrolling;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMaxRadius = Math.max(w, h) ;
    }

    public void setOnClickEndAnimationListener(OnClickListener listener) {
        mClickListener = listener;
    }

    @Override
    public boolean onTouch(final View v, MotionEvent e) {
        mTouchDown[0] = e.getX();
        mTouchDown[1] = e.getY();
        if (mIsScrolling) {
            return true;
        }
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDistanceOfMove[0] = e.getX();
                mDistanceOfMove[1] = e.getY();
                startDrawing();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (mClickListener != null) {
                    if (Math.abs(mDistanceOfMove[0] - mTouchDown[0]) > 200 ||
                            Math.abs(mDistanceOfMove[1] - mTouchDown[1]) > 200) {
                        break;
                    }
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mClickListener.onClick(v);
                        }
                    }, 200);
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);

        if (mDrawingStarting) {
            c.drawCircle(mTouchDown[0], mTouchDown[1], mCircleRadius, mPaint);
            invalidate();
            mCircleRadius += 80;

            if (mCircleRadius > mMaxRadius) {
                mDrawingStarting = false;
                mDrawingFinishing = true;
                invalidate();
                return;
            }
        }

        if (mDrawingFinishing) {
            c.drawColor(mPaint.getColor());
            mPaintAlpha -= 5;
            mPaint.setAlpha(mPaintAlpha);

            invalidate();

            if (mPaintAlpha <= 0) {
                mDrawingFinishing = false;
            }
        }
    }

    private void startDrawing() {
        mDrawingStarting = true;
        mDrawingFinishing = false;
        mPaintAlpha = DEFAULT_ALPHA;
        mCircleRadius = 0;
        mPaint.setAlpha(mPaintAlpha);
        invalidate();
    }
}
