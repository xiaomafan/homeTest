package com.xiaoma.shapeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.xiaoma.hometest.LogHelp;
import com.xiaoma.hometest.R;

/**
 * Created by xiaoma on 2017/3/31.
 */

public class CustomImgText extends View {
    
    private String mTitle;
    private int mTitleSize;
    private int mTitleColor;
    private Bitmap mImg;
    private int mScaleType;
    private Rect mTextRect;
    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private Rect mRect;
    
    private final static int IMAGE_SCALE_CENTER=0;
    private final static int IMAGE_SCALE_CENTERCROP=1;

    public CustomImgText(Context context) {
        this(context,null);
    }

    public CustomImgText(Context context, @Nullable AttributeSet attrs) {
      this(context,attrs,0);
    }

    public CustomImgText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ShapeImgText);
        int indexCount = ta.getIndexCount();
        for (int i=0;i<indexCount;i++){
            int index = ta.getIndex(i);
            switch (index){
                case R.styleable.ShapeImgText_imgtitle:
                    mTitle=ta.getString(index);
                    break;
                case R.styleable.ShapeImgText_imgtitlesize:
                    mTitleSize=ta.getDimensionPixelSize(index,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.ShapeImgText_imgtitlecolor:
                    mTitleColor=ta.getColor(index, Color.BLACK);
                    break;
                case R.styleable.ShapeImgText_img:
                    mImg= BitmapFactory.decodeResource(getResources(),ta.getResourceId(index,0));
                    break;
                case R.styleable.ShapeImgText_scaleType:
                    mScaleType=ta.getInt(index,IMAGE_SCALE_CENTER);
                    break;
            }
        }
        ta.recycle();

        mPaint = new Paint();
        mRect = new Rect();
        mTextRect = new Rect();
        mPaint.setTextSize(mTitleSize);
        mPaint.getTextBounds(mTitle,0,mTitle.length(), mTextRect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(widthMode==MeasureSpec.EXACTLY){
          mWidth =widthSize;  
        }else {
            int desireImg=getPaddingLeft()+getPaddingRight()+mImg.getWidth();
            int desireText=getPaddingLeft()+getPaddingRight()+mTextRect.width();
            if(widthMode==MeasureSpec.AT_MOST){
                int desire=Math.max(desireImg,desireText);
                mWidth =Math.min(desire,widthSize);
            }
        }
        
        if(heightMode==MeasureSpec.EXACTLY){
            mHeight =heightSize;
        }else {
            int desire=getPaddingTop()+getPaddingBottom()+mImg.getHeight()+mTextRect.height();
            if(heightMode==MeasureSpec.AT_MOST){
                mHeight =Math.min(desire,heightSize);
            }
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.RED);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        LogHelp.e("ondraw",getPaddingLeft()+"/"+mWidth+"/"+
                getPaddingRight()+"/["+mHeight+"/"+getPaddingTop()+"/"+
                getPaddingBottom());
        mRect.left=getPaddingLeft();
        mRect.right=mWidth-getPaddingRight();
        mRect.top=getPaddingTop();
        mRect.bottom=mHeight-getPaddingBottom();
        
        mPaint.setColor(mTitleColor);
        mPaint.setStyle(Paint.Style.FILL);
        
        //文字宽度大于mRect的宽度处理
        if(mTextRect.width()>mWidth){
            TextPaint textPaint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mTitle, textPaint, (float) mWidth - getPaddingLeft() - getPaddingRight(), TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg,getPaddingLeft(),mHeight-getPaddingBottom(),mPaint);
        }else {
            canvas.drawText(mTitle,mWidth/2-mTextRect.width()*1.0f/2,mHeight-getPaddingBottom(),mPaint);
        }

        mRect.bottom -= mTextRect.height();

        if (mScaleType == IMAGE_SCALE_CENTER)
        {
            canvas.drawBitmap(mImg, null, mRect, mPaint);
        } else
        {
            //计算居中的矩形范围  
            mRect.left = mWidth / 2 - mImg.getWidth() / 2;
            mRect.right = mWidth / 2 + mImg.getWidth() / 2;
            mRect.top = (mHeight - mTextRect.height()) / 2 - mImg.getHeight() / 2;
            mRect.bottom = (mHeight - mTextRect.height()) / 2 + mImg.getHeight() / 2;

            canvas.drawBitmap(mImg, null, mRect, mPaint);
        }
    }
}
