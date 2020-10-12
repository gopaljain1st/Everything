package com.example.testnutrition;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

import static com.facebook.share.model.ShareMessengerMediaTemplateContent.MediaType.IMAGE;

public class FlipeerLayout extends AppCompatActivity
{
    int imgArray[]={R.drawable.im2,R.drawable.image5,R.drawable.image12a,R.drawable.image12b,R.drawable.image12c};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_image_flipper);
        FlipperLayout flipperLayout =  findViewById(R.id.flipperlayout);
        int num_of_pages = 3;
        for (int i = 0; i < imgArray.length; i++)
        {
            FlipperView view = new FlipperView(getBaseContext());

            flipperLayout.addFlipperView(view);
        }
    }
}
