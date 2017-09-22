package com.example.fcy.materialdesigndemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fcy on 2017/9/7.
 */

public class ViewDemo1 extends View {
    public ViewDemo1(Context context) {
        super(context);

    }

    public ViewDemo1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewDemo1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(),measureHeight());
    }

    private int measureHeight() {
        return 4;
    }

    private int measureWidth() {
        return  6;
    }
}
