package com.xiaoma.study;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by xiaoma on 2017/5/8.
 */

public class MyView extends ViewGroup {

    private Scroller mScroller;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        int scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller = new Scroller(context);
        

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       
        VelocityTracker velocityTracker = VelocityTracker.obtain();
        velocityTracker.addMovement(event);
        velocityTracker.computeCurrentVelocity(1000);
        float xVelocity = velocityTracker.getXVelocity();
        float yVelocity = velocityTracker.getYVelocity();
        
        velocityTracker.clear();
        velocityTracker.recycle();

        
        return super.onTouchEvent(event);
    }
    
    public void smoothScroollTo(int destX,int destY){
        int scrollX = getScrollX();
        int deltaX=destX-scrollX;
        mScroller.startScroll(scrollX,0,deltaX,0,1000);
        
        invalidate();
        
        
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
        
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
    
    //onTouchListener>onTouchEvent>onClickListener
    
    

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        
    }


}
