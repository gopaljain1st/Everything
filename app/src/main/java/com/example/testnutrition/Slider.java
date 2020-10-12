package com.example.testnutrition;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;


import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.testnutrition.fragments.Breakfast;
import com.example.testnutrition.fragments.Dinner;
import com.example.testnutrition.fragments.Lunch;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class Slider extends AppCompatActivity
{
    LinearLayout sliderDotspanel;

    Button eatNow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_slider);
       /*
       *  flipper=findViewById(R.id.flipper);
        sliderDotspanel = findViewById(R.id.SliderDots);


        eatNow=findViewById(R.id.eatnow);
          eatNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            new CustomBottomSheetDialogFragment().show(getSupportFragmentManager(),"Select");
            }
        });

          //here find tablayout and viewpager
        TabLayout tabLayout =findViewById(R.id.tablayout);
        ViewPager viewPager=findViewById(R.id.viewpager);

        ViewPageAdapter viewPageAdapter=new ViewPageAdapter(getSupportFragmentManager());

        //just make as much you want fragment class
        viewPageAdapter.addFragment(new Breakfast(),"Breakfast");
        viewPageAdapter.addFragment(new Lunch(),"Lunch");
        viewPageAdapter.addFragment(new Dinner(),"Dinner");

        //add adapter
        viewPager.setAdapter(viewPageAdapter);
        //add viewPager in tablayout
        tabLayout.setupWithViewPager(viewPager);

        //here end

        arrayList=new ArrayList<>();
        arrayList.add(new SliderItem(R.drawable.mainlogo));
        arrayList.add(new SliderItem(R.drawable.im2));
        arrayList.add(new SliderItem(R.drawable.image12a));
        arrayList.add(new SliderItem(R.drawable.image12b));
        arrayList.add(new SliderItem(R.drawable.image12c));
        flipper.setAdapter(new SliderAdapterExample(flipper,arrayList));
        flipper.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,2000);
            }
        });


    }
    //This class is for open fragments
    class ViewPageAdapter extends FragmentPagerAdapter
    {

        private ArrayList<Fragment> fragments;
        private  ArrayList<String> titles;
        ViewPageAdapter(FragmentManager fragmentManager)
        {
            super(fragmentManager);
            this.fragments=new ArrayList<>();
            this.titles=new ArrayList<>();
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
        public void addFragment(Fragment fragment,String title)
        {
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
    //here is end of class


    private Runnable sliderRunnable =new Runnable() {
        @Override
        public void run()
        {
            int count=flipper.getCurrentItem();
            if(count == arrayList.size()-1)
                count=-1;
                flipper.setCurrentItem(count+1);
        }
    };
*/}
}
