package com.xiaoma.shapeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.xiaoma.hometest.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by xiaoma on 2017/3/30.
 */

public class ShapeView extends View {

    private String mTitle;
    private int mTitleSize;
    private int mTitleColor;

    private Paint paint;
    private Rect rect;

    public ShapeView(Context context) {
      
        this(context,null);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeView);
        int attr = typedArray.getIndexCount();
        for (int i = 0; i <attr; i++)
        {
            int index = typedArray.getIndex(i);
            switch (index){
                case R.styleable.ShapeView_shapetitle:
                    mTitle=typedArray.getString(index);
                    break;
                case R.styleable.ShapeView_shapetitlesize:
                    mTitleSize=typedArray.getDimensionPixelSize(index,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.ShapeView_shapetitlecolor:
                    mTitleColor=typedArray.getColor(index,Color.BLACK);
                    break;
            }
        }
            
         typedArray.recycle();

        paint = new Paint();
        paint.setTextSize(mTitleSize);
        rect = new Rect();
        paint.getTextBounds(mTitle, 0, mTitle.length(), rect);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitle = randomText();
                postInvalidate();
            }
        });
    }

    private String randomText()
    {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }

        return sb.toString();
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        
        //宽度是准确的宽度时
        if(widthMode==MeasureSpec.EXACTLY){
            width=widthSize;
        }else {
            paint.setTextSize(mTitleSize);
            paint.getTextBounds(mTitle,0,mTitle.length(),rect);
            int textWidth = rect.width();
            int desired=getPaddingLeft()+getPaddingRight()+textWidth;
            width=desired;
        }
        
        if(heightMode==MeasureSpec.EXACTLY){
            height=heightSize;
        }else {
            paint.setTextSize(mTitleSize);
            paint.getTextBounds(mTitle, 0, mTitle.length(), rect);
            float textHeight = rect.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }
        
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        Log.e("xiaoma/getMeasuredWidth",getMeasuredWidth()+"/"+getMeasuredHeight());
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

        paint.setColor(mTitleColor);
        Log.e("xiaoma/getWidth",getWidth()+"/"+getHeight()+"["+rect.width()+"/"+rect.height());
        canvas.drawText(mTitle, getWidth() / 2 - rect.width() / 2, getHeight() / 2 + rect.height() / 2, paint);
    }
}
