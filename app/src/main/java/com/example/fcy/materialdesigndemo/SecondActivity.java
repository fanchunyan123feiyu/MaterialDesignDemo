package com.example.fcy.materialdesigndemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by fcy on 2017/9/4.
 */

public class SecondActivity extends AppCompatActivity {
    private int flag;
    private int downX,downY;
    private int moveX,moveY;
    private int offsetx,offsety;
    private Scroller scroller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        flag=getIntent().getIntExtra("flag",-1);
        switch (flag){
            case 0:
                getWindow().setEnterTransition(new Explode());
                break;
            case 1:
                getWindow().setEnterTransition(new Slide());
                break;
            case 2:
                getWindow().setEnterTransition(new Fade());
                getWindow().setExitTransition(new Fade());
                break;
        }
        setContentView(R.layout.activity_second);
        DragView drawView = (DragView) findViewById(R.id.drawView);
        Log.e("TAG","getLeft():"+drawView.getLeft()+"getX():"+drawView.getX());
        Log.e("TAG","getTop():"+drawView.getTop()+"getY():"+drawView.getY());
        Log.e("TAG","getRight():"+drawView.getRight());
        Log.e("TAG","getBottom():"+drawView.getBottom());

        Log.e("TAG","移动之前-----------------");

       // drawView=(DragView)findViewById(R.id.drawView);
        drawView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = (int) event.getRawX();
                        downY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        moveX = (int) event.getRawX();
                        moveY = (int) event.getRawY();
                        offsetx = moveX - downX;
                        offsety = moveY - downY;
                        RelativeLayout.LayoutParams layoutParams=(RelativeLayout.LayoutParams)v.getLayoutParams();
                        layoutParams.leftMargin=v.getLeft()+offsetx;
                        layoutParams.topMargin=v.getTop()+offsety;
                        v.setLayoutParams(layoutParams);
                        downX = moveX;
                        downY = moveY;
                        break;
                    case MotionEvent.ACTION_UP:
                       /* View viewGroup=(View) v.getParent();
                        scroller.startScroll(viewGroup.getScrollX(),viewGroup.getScrollY(),-viewGroup.getScrollX(),-viewGroup.getScrollY());
*/ Log.e("TAG","移动之后-----------------");
                        Log.e("TAG","getLeft():"+v.getLeft()+"getX():"+v.getX());
                        Log.e("TAG","getTop():"+v.getTop()+"getY():"+v.getY());
                        Log.e("TAG","getRight():"+v.getRight());
                        Log.e("TAG","getBottom():"+v.getBottom());

                        break;
                }
                return true;
            }
        });



    }

}
