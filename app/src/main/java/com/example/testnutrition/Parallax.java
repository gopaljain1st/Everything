package com.example.testnutrition;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class Parallax extends AppCompatActivity
{
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private boolean appBarExpanded =true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_pagel);
        Toolbar toolbar = findViewById(R.id.toolbar);
        appBarLayout=findViewById(R.id.appbar);
      //  setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout = findViewById(R.id.ctoolbar);
        collapsingToolbarLayout.setTitle("parallaax Slide Animation");
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.im2);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                int vibrantColor=palette.getVibrantColor(getResources().getColor(R.color.primary_500));
                collapsingToolbarLayout.setContentScrimColor(vibrantColor);
                collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.black_trans80));
            }
        });
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(Math.abs(verticalOffset)>200)
                {
                    appBarExpanded = false;
                    invalidateOptionsMenu();
                }
                else
                {
                  appBarExpanded =true;
                  invalidateOptionsMenu();
                }
            }
        });
    }
}
