package com.example.testnutrition.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.testnutrition.R;
import com.example.testnutrition.adapters.PackagesAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PackageFragment extends Fragment
{
    FragmentTransaction transaction;
    FragmentManager manager;
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    Handler handler;
    Timer timer;
    final long DELAY_MS = 500;
    final long PERIOD_MS = 3000;
    int currentPage=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.package_layout,container,false);

        viewPager=view.findViewById(R.id.pakagesViewPager);
        sliderDotspanel =  view.findViewById(R.id.SliderDots);

        //this code for add slider adapter
        final PackagesAdapter packagesAdapter=new PackagesAdapter(getContext());
        viewPager.setAdapter(packagesAdapter);

        dotscount = packagesAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(getContext());

            dots[i].setImageResource(R.drawable.nonactive_dot);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }


        dots[0].setImageResource(R.drawable.active_dot);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageResource(R.drawable.nonactive_dot);
                }


                dots[position].setImageResource(R.drawable.active_dot);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // Timer timer = new Timer();
        //timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 2000);
        handler=new Handler();
        final  Runnable update=new Runnable() {
            @Override
            public void run() {
                if(currentPage == packagesAdapter.getCount())
                    currentPage=0;
                viewPager.setCurrentItem(currentPage++,true);
            }
        };
        timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_MS,PERIOD_MS);

        TabLayout tabLayout =view.findViewById(R.id.tablayout);
        ViewPager viewPager=view.findViewById(R.id.viewpager);

        ViewPageAdapter viewPageAdapter=new ViewPageAdapter(getmanager());


        //just make as much you want fragment class
        viewPageAdapter.addFragment(new PackageBreakfast(),"Breakfast");
        viewPageAdapter.addFragment(new PackageLunch(),"Lunch");
        viewPageAdapter.addFragment(new PackageDinner(),"Dinner");



        //add adapter
        viewPager.setAdapter(viewPageAdapter);
        //add viewPager in tablayout
        tabLayout.setupWithViewPager(viewPager);

        manager=getmanager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.Packagell,new PackageBreakfast());
        transaction.commit();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                if(tab.getText().toString().equals("Breakfast"))
                {
                    transaction=manager.beginTransaction();
                    transaction.replace(R.id.Packagell,new PackageBreakfast());
                    transaction.commit();
                }
                else if(tab.getText().toString().equals("Lunch"))
                {

                    transaction=manager.beginTransaction();
                    transaction.replace(R.id.Packagell,new PackageLunch());
                    transaction.commit();
                }
                else if(tab.getText().toString().equals("Dinner"))
                {
                    transaction=manager.beginTransaction();
                    transaction.replace(R.id.Packagell,new PackageDinner());
                    transaction.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {
            }
        });


        return view;
    }
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
    FragmentManager getmanager()
    {
        return getChildFragmentManager();
    }
}
