package com.example.fcy.materialdesigndemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by fcy on 2017/9/6.
 */

public class DragView extends View {
    private  Scroller scroller;
    private int  downX,downY;
    private int moveX,moveY;
    private int offsetx=0;
    private int offsety=0;

    public DragView(Context context) {
        super(context);
        scroller= new Scroller(context);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        scroller= new Scroller(context);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller= new Scroller(context);
        Log.e("TAG",scroller+"scroller");
    }
    @Override
    public void computeScroll() {//重新他让滑动更加流畅真是而不是一瞬间完成
        super.computeScroll();
        Log.e("TAG",scroller+"computeScroll");
        if(scroller.computeScrollOffset()){//判断滑动是否完成
            ((View)getParent()).scrollTo(scroller.getCurrX(),scroller.getCurrY());
            invalidate();//通过此方法才能间接调用到computeScroll
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX=(int) event.getRawX();
                downY=(int)event.getRawY();
                Log.e("TAG",event+"down");
                break;
            case MotionEvent.ACTION_MOVE:
                moveX=(int)event.getRawX();
                moveY=(int)event.getRawY();
                 offsetx= moveX-downX;
                offsety=moveY-downY;
                //layout(getLeft()+offetx,getTop()+offsety,getRight()+offetx,getBottom()+offsety);//法1
               /* offsetLeftAndRight(offetx);
                offsetTopAndBottom(offsety);法2*/
                ((View)getParent()).scrollBy(-offsetx,-offsety);//注意是负数 法3
                Log.e("TAG",event+"move");
                downX=moveX;
                downY = moveY;
                break;
            case MotionEvent.ACTION_UP:
               /* View view=(View)getParent();
                scroller.startScroll(view.getScrollX(),view.getScrollY(),-view.getScrollX(),-view.getScrollY(),2000);
                invalidate();*/
                break;
        }
        return true;
    }


}
