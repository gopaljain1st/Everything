package com.example.testnutrition;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Animation extends AppCompatActivity
{
    Button translate,rotate,scale,alpha,set;
    ImageView img;
    android.view.animation.Animation animation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation);
        initComponent();
        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation= AnimationUtils.loadAnimation(Animation.this,R.anim.translate);
                img.startAnimation(animation);
            }
        });
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animation= AnimationUtils.loadAnimation(Animation.this,R.anim.rotate1);
                img.startAnimation(animation);
            }
        });
        scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                animation= AnimationUtils.loadAnimation(Animation.this,R.anim.scale);
                img.startAnimation(animation);
            }
        });
        alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animation= AnimationUtils.loadAnimation(Animation.this,R.anim.alpha);
                img.startAnimation(animation);
            }
        });
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation= AnimationUtils.loadAnimation(Animation.this,R.anim.rotate);
                img.startAnimation(animation);

            }
        });
    }

    void initComponent()
    {
        translate=findViewById(R.id.translate);
        rotate=findViewById(R.id.rotate);
        scale=findViewById(R.id.scale);
        alpha=findViewById(R.id.alpha);
        set=findViewById(R.id.set);
        img=findViewById(R.id.img);
    }
}
