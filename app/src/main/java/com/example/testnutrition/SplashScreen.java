package com.example.testnutrition;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity
{
    Animation rotation;
    ImageView iv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        rotation= AnimationUtils.loadAnimation(this,R.anim.rotate);
        iv=findViewById(R.id.logo);
        iv.startAnimation(rotation);
    }
}
