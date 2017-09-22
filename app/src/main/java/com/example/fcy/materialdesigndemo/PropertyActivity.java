package com.example.fcy.materialdesigndemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import static android.animation.ObjectAnimator.ofFloat;

/**
 * Created by fcy on 2017/9/5.
 */

public class PropertyActivity extends AppCompatActivity {
    private View target;
    private LinearLayout ll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        target=findViewById(R.id.target);
        ll=(LinearLayout)findViewById(R.id.ll);
        ScaleAnimation scaleAnimation=new ScaleAnimation(0,1,0,1);
        scaleAnimation.setDuration(2000);
        LayoutAnimationController layoutAnimationController=new LayoutAnimationController(scaleAnimation,0.2f);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_REVERSE);
       // ll.setLayoutAnimation(layoutAnimationController);
//ll.addView(new MyView(this));
       // ll.addView(new ProgressView(this));
        findViewById(R.id.translate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*PropertyValuesHolder pvh1=PropertyValuesHolder.ofFloat("translationX",300);
                PropertyValuesHolder pvh2=ofFloat("translationY",300);
                ObjectAnimator objectAnimator=ObjectAnimator.ofPropertyValuesHolder(target,pvh1,pvh2).setDuration(1000);
                objectAnimator.addListener(new AnimatorListenerAdapter() {//提供了默认实现
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //super.onAnimationEnd(animation);
                        //// TODO: 2017/9/5
                    }
                });
                objectAnimator.start();*/

                ObjectAnimator animator1= ofFloat(target,"translationX",300);
                ObjectAnimator animator2= ofFloat(target,"translationY",300);
                AnimatorSet animatorSet=new AnimatorSet();
                animatorSet.setDuration(1000);
                //animatorSet.playTogether(animator1,animator2);//一起执行
                //animatorSet.playSequentially(animator1,animator2);//顺序执行
                animatorSet.play(animator1).after(animator2);//after with before
                animatorSet.start();
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });
              //  target.animate().alpha()
            }
        });
        findViewById(R.id.scale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                target.animate().scaleX(-2);
                target.animate().scaleY(-2);
            }
        });
    }
}
