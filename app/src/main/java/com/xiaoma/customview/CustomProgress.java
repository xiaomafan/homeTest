package com.xiaoma.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.xiaoma.hometest.R;

/**
 * Created by xiaoma on 2017/4/1.
 */

public class CustomProgress extends View {

    private int mFristColor;
    private int mSecondColor;
    private int mProgressWidth;
    private int mSpeed;
    private Paint mPaint;
    private int mProgress;
    private boolean isNext;

    public CustomProgress(Context context) {
        this(context, null);
    }

    public CustomProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomProgress);
        int indexCount = ta.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = ta.getIndex(i);
            switch (index) {
                case R.styleable.CustomProgress_firstColor:
                    mFristColor = ta.getColor(R.styleable.CustomProgress_firstColor, Color.RED);
                    break;
                case R.styleable.CustomProgress_secondColor:
                    mSecondColor = ta.getColor(R.styleable.CustomProgress_secondColor, Color.GRAY);
                    break;
                case R.styleable.CustomProgress_progressWidth:
                    mProgressWidth = ta.getDimensionPixelOffset(R.styleable.CustomProgress_progressWidth, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomProgress_mySpeed:
                    mSpeed = ta.getInt(R.styleable.CustomProgress_mySpeed, 20);
                    break;
            }
        }
        ta.recycle();

        mPaint = new Paint();
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    mProgress=mProgress+8;
                    if (mProgress >= 360) {
                        mProgress = 0;
                        if (!isNext) {
                            isNext = true;
                        } else {
                            isNext = false;
                        }
                    } else {
                        postInvalidate();
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int center = getWidth() / 2;
        int radius = center - mProgressWidth / 2;
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mProgressWidth);
        RectF rectF = new RectF(center - radius, center - radius, center + radius, center + radius);
        if(!isNext){
            //第一圈在跑
            mPaint.setColor(mFristColor);
            canvas.drawCircle(center,center,radius,mPaint);
            mPaint.setColor(mSecondColor);
            canvas.drawArc(rectF,-90,mProgress,false,mPaint);
        }else {
            mPaint.setColor(mSecondColor);
            canvas.drawCircle(center,center,radius,mPaint);
            mPaint.setColor(mFristColor);
            canvas.drawArc(rectF,-90,mProgress,false,mPaint);
        }


    }
}
