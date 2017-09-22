package com.example.fcy.materialdesigndemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by fcy on 2017/9/16.
 */

public class ProgressView extends View {

    private Paint paint;
    private Path path;
    private Paint paintText;

    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
       paint=new Paint();
        paint.setColor(Color.RED);  //设置画笔颜色
        paint.setStyle(Paint.Style.STROKE);//填充样式改为描边
        paint.setStrokeWidth(5);//设置画笔宽度
        path=new Path();
        paintText=new Paint();
        paintText.setColor(Color.GREEN);
        paintText.setTextSize(35);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("TAG","画弧");
      /*  RectF rect1 = new RectF(100, 10, 300, 100);
        canvas.drawArc(rect1, 0, 180, true, paint);

        RectF rect2 = new RectF(400, 10, 600, 100);
        canvas.drawArc(rect2, 0, 90, false, paint);*/
       /* path.moveTo(10,10);
        path.lineTo(10,100);
        path.lineTo(300,100);
        path.close();
        canvas.drawPath(path,paint);*///直线路径
        /*RectF rect1 =  new RectF(50, 50, 240, 200);
        path.addRect(rect1, Path.Direction.CW);
        canvas.drawPath(path,paint);
        String text="文字的行走方向依据路径的生成方向";
        canvas.drawTextOnPath(text,path,0,20,paintText);*///矩形路径
        canvas.drawText("欢迎光临我的小店",10,100,paint);
    }



}
