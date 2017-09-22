package com.example.fcy.materialdesigndemo;

import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by fcy on 2017/9/4.
 */

public class FirstActivity extends AppCompatActivity {
    private Intent intent;
    private View oval,rect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        oval=findViewById(R.id.oval);
        rect=findViewById(R.id.rect);
        setListeners();
    }

    private void setListeners() {
        oval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Animator animator= ViewAnimationUtils.createCircularReveal(oval,oval.getWidth()/2,oval.getHeight()/2,0,oval.getWidth());
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(2000);
                animator.start();
            }
        });
        rect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator animator= ViewAnimationUtils.createCircularReveal(rect,0,0,0,(float) Math.hypot(rect.getWidth(),rect.getHeight()));
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(2000);
                animator.start();
            }
        });
    }

    public void  explode(View view){
        intent=new Intent(this,SecondActivity.class);
        intent.putExtra("flag",0);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
    public void  slide(View view){
        intent=new Intent(this,SecondActivity.class);
        intent.putExtra("flag",1);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
    public void  fade(View view){
        intent=new Intent(this,SecondActivity.class);
        intent.putExtra("flag",2);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
    public void  share(View view){
        View fab=findViewById(R.id.fab);
        intent=new Intent(this,SecondActivity.class);
        intent.putExtra("flag",3);

        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,view,"share").toBundle());
        //startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(view,"share"),Pair.create(fab,"fab")).toBundle());//创建多个共享元素
    }
}
