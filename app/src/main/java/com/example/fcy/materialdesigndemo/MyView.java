package com.example.fcy.materialdesigndemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by fcy on 2017/9/16.
 */

public class MyView extends View {
    private final Paint paint;
    Context m_context;
    public MyView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub

        m_context=context;
    paint=new Paint();
        paint.setAntiAlias(true);//抗锯齿功能
        paint.setColor (Color.RED);  //设置画笔颜色
        paint.setStyle(Paint.Style.FILL);//设置填充样式   Style.FILL/Style.FILL_AND_STROKE/Style.STROKE
        paint.setStrokeWidth(5);//设置画笔宽度
        paint.setShadowLayer(10, 15, 15, Color.GREEN);//设置阴影
    }

    //重写OnDraw（）函数，在每次重绘时自主实现绘图
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画布背景颜色
        canvas.drawRGB(255, 255,255);
        //画圆
        //canvas.drawCircle(190, 200, 150, paint);
        RectF rect1 = new RectF(100, 10, 300, 100);
        canvas.drawArc(rect1, 0, 180, true, paint);
    }
}
