package com.example.fcy.materialdesigndemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * Created by fcy on 2017/9/5.
 * 图像处理
 */

public class ColorMatrixAcrivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    private SeekBar seekbarHue,seekbarLum,seekbarSaturation;
    private ImageView image;
    private float mHue,mSaturation,mLum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colormatrix);
        image=(ImageView)findViewById(R.id.image);
        seekbarHue=(SeekBar)findViewById(R.id.seekbarHue);//色调
        seekbarSaturation=(SeekBar)findViewById(R.id.seekbarSaturation);//饱和度
        seekbarLum=(SeekBar)findViewById(R.id.seekbarLum);//亮度
        seekbarHue.setOnSeekBarChangeListener(this);
        seekbarLum.setOnSeekBarChangeListener(this);
        seekbarSaturation.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.seekbarHue:
                mHue=(progress-20)*1.0F/20*180;
                break;
            case R.id.seekbarSaturation:
                mSaturation=progress*1.0F/30;
                break;
            case R.id.seekbarLum:
                mLum=progress*1.0F/30;
                break;
        }
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.jingpin);
        Bitmap bmp=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bmp);
        Paint paint=new Paint();

        ColorMatrix hueMatrix=new ColorMatrix();
        hueMatrix.setRotate(0,mHue);//0代表Green
        hueMatrix.setRotate(1,mHue);//1代表Green
        hueMatrix.setRotate(2,mHue);//2代表Green

        ColorMatrix saturationMatrix=new ColorMatrix();
        saturationMatrix.setSaturation(mSaturation);

        ColorMatrix lumMatrix=new ColorMatrix();
        lumMatrix.setScale(mLum,mLum,mLum,1);

        ColorMatrix imageMatrix=new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);//利用矩阵乘法将单独的效果混合叠加
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bmp, 0, 0, paint);
        image.setImageBitmap(bmp);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
