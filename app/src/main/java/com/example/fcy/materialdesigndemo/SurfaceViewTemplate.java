package com.example.fcy.materialdesigndemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by fcy on 2017/9/6.
 * SurfaceView使用模板
 */

public class SurfaceViewTemplate extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder mHolder;
    private boolean mIsDrawing;//子线程标志位
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;
private int x=0,y=0;
    public SurfaceViewTemplate(Context context) {
        super(context, null);
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mHolder = getHolder();
        mHolder.addCallback(this);//注册回调
        setFocusable(true);//可以获得焦点
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        mPath=new Path();
        mPaint=new Paint();
       // mPaint.setColor(Color.RED);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            draw();
            x+=1;
            y=(int)(100*Math.sin(x*2*Math.PI/180)+400);
            mPath.lineTo(x,y);
        }

    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            //// TODO: 2017/9/6
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath,mPaint);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);//对画布内容进行提交
            }
        }
    }
}
