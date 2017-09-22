package com.example.fcy.materialdesigndemo;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by fcy on 2017/9/6.
 */

public class DrawViewGrop extends FrameLayout {
    private ViewDragHelper viewDragHelper;
    private View mMenuView,mContentView;

    public DrawViewGrop(@NonNull Context context) {
        super(context);
        init();
    }
    public DrawViewGrop(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public DrawViewGrop(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
       viewDragHelper=ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return mContentView==child;//何时开始检查
            }

           @Override
           public int clampViewPositionHorizontal(View child, int left, int dx) {
               return left;
           }

           @Override
           public int clampViewPositionVertical(View child, int top, int dy) {
               return 0;
           }

           @Override
           public void onViewReleased(View releasedChild, float xvel, float yvel) {//拖动结束后调用
               super.onViewReleased(releasedChild, xvel, yvel);
               if(mContentView.getLeft()<500){
                   viewDragHelper.smoothSlideViewTo(mContentView,0,0);
                   ViewCompat.postInvalidateOnAnimation(DrawViewGrop.this);
               }else{
                   viewDragHelper.smoothSlideViewTo(mContentView,300,0);
                   ViewCompat.postInvalidateOnAnimation(DrawViewGrop.this);
               }
           }
       });
    }

    @Override
    public void computeScroll() {
        if(viewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(DrawViewGrop.this);
        }
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return viewDragHelper.shouldInterceptTouchEvent(event);//???
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);//将触摸事件传递给ViewDragHelper  必不可少
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView=getChildAt(0);
        mContentView=getChildAt(1);
    }
}
