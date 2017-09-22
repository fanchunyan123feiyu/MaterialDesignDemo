package com.example.fcy.materialdesigndemo;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private MainActivity activity;
    private TextView tvZ;
    private  boolean flag=true;
    private ImageView tint;

    /**
     * TessBaseAPI初始化用到的第一个参数，是个目录。
     */
    private static final String DATAPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    /**
     * 在DATAPATH中新建这个目录，TessBaseAPI初始化要求必须有这个目录。
     */
    private static final String tessdata = DATAPATH + File.separator + "tessdata";
    /**
     * TessBaseAPI初始化测第二个参数，就是识别库的名字不要后缀名。
     */
    private static final String DEFAULT_LANGUAGE = "chi_sim";
    /**
     * assets中的文件名
     */
    private static final String DEFAULT_LANGUAGE_NAME = DEFAULT_LANGUAGE + ".traineddata";
    /**
     * 保存到SD卡中的完整文件名
     */
    private static final String LANGUAGE_PATH = tessdata + File.separator + DEFAULT_LANGUAGE_NAME;

    /**
     * 权限请求值
     */
    private static final int PERMISSION_REQUEST_CODE=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=this;
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.jingpin);//机器人不行
        Palette.generateAsync(bitmap,new Palette.PaletteAsyncListener(){

            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getDarkVibrantSwatch();//色调
                Log.e("TAG","---"+activity.getActionBar());//null
                //getActionBar().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
                Window window=getWindow();
                Log.e("TAG","---"+window);
                Log.e("TAG","---"+vibrant);//null
                window.setStatusBarColor(vibrant.getRgb());
            }
        });
        setContentView(R.layout.activity_main);
        Log.e("TAG","---999"+activity.getActionBar());
        tint=(ImageView)findViewById(R.id.tint);
        tint.setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);
        tint.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
       /* TessBaseAPI tessBaseAPI = new TessBaseAPI();
        tessBaseAPI.init(DATAPATH, DEFAULT_LANGUAGE);//参数后面有说明。
        tessBaseAPI.setImage(bitmap);
        String text = tessBaseAPI.getUTF8Text();*/
        tvZ=(TextView)findViewById(R.id.tvZ);
        findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG","change");
                if(flag){
                    tvZ.animate().translationZ(10);
                    flag=false;
                }else{
                    tvZ.animate().translationZ(0);
                    flag=true;
                }

            }
        });

        ViewOutlineProvider viewOutlineProvider1=new ViewOutlineProvider(){

            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0,0,view.getWidth(),view.getHeight(),30);
            }
        };
        ViewOutlineProvider viewOutlineProvider2=new ViewOutlineProvider(){

            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0,0,view.getWidth(),view.getHeight());
            }
        };
        View tv1=findViewById(R.id.tv1);
        View tv2=findViewById(R.id.tv2);
        tv1.setOutlineProvider(viewOutlineProvider1);
        tv2.setOutlineProvider(viewOutlineProvider2);

        findViewById(R.id.animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity,FirstActivity.class));
            }
        });
        findViewById(R.id.propertyanimation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity,PropertyActivity.class));
            }
        });
        findViewById(R.id.colorMatrix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity,ColorMatrixAcrivity.class));
            }
        });
    }
}
