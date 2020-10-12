package com.example.testnutrition.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.testnutrition.R;
import com.example.testnutrition.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class OrderFragment  extends Fragment
{
    FragmentTransaction transaction;
    FragmentManager manager;
    private int dotscount;
    private ImageView[] dots;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    Button eatNow;
    Handler handler;
    Timer timer;
    final long DELAY_MS = 500;
    final long PERIOD_MS = 3000;
    int currentPage=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.test_slider,container,false);
        viewPager =  view.findViewById(R.id.orderViewPager);

        sliderDotspanel =  view.findViewById(R.id.orderSliderDots);

        viewPagerAdapter = new ViewPagerAdapter(getContext());

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
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
        eatNow=view.findViewById(R.id.eatnow);
        eatNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CustomBottomSheetDialogFragment().show(getActivity().getSupportFragmentManager(),"Select");
            }
        });

          handler=new Handler();
        final  Runnable update=new Runnable() {
            @Override
            public void run() {
                if(currentPage == viewPagerAdapter.getCount())
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
        final ViewPager viewPager=view.findViewById(R.id.viewpager);
        setUpViewPager(viewPager);
        //add viewPager in tablayout
        tabLayout.setupWithViewPager(viewPager);

        manager=getmanager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.orderll,new Breakfast());
        transaction.commit();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                if(tab.getText().toString().equals("Breakfast"))
                {
                    transaction=manager.beginTransaction();
                    transaction.replace(R.id.orderll,new Breakfast());
                    transaction.commit();
                }
                else if(tab.getText().toString().equals("Lunch"))
                {

                    transaction=manager.beginTransaction();
                    transaction.replace(R.id.orderll,new Lunch());
                    transaction.commit();
                }
                else if(tab.getText().toString().equals("Dinner"))
                {
                    transaction=manager.beginTransaction();
                    transaction.replace(R.id.orderll,new Dinner());
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

        //here end

        return view;
    }
    /*public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            getParentFragment().getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    int count=viewPager.getCurrentItem();
                    if(count == viewPagerAdapter.getCount() -1)
                        count=-1;
                    viewPager.setCurrentItem(count+1);
                }
            });

        }
    }
    private Runnable sliderRunnable =new Runnable() {
        @Override
        public void run()
        {
            int count=viewPager.getCurrentItem();
            if(count == dots.length-1)
                count=-1;
            viewPager.setCurrentItem(count+1);
        }
    };
*/
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
    void setUpViewPager(ViewPager vp)
    {
        ViewPageAdapter viewPageAdapter=new ViewPageAdapter(getChildFragmentManager());
        viewPageAdapter.addFragment(new Breakfast(),"Breakfast");
        viewPageAdapter.addFragment(new Lunch(),"Lunch");
        viewPageAdapter.addFragment(new Dinner(),"Dinner");

        vp.setAdapter(viewPageAdapter);
    }
    FragmentManager getmanager()
    {
        return getChildFragmentManager();
    }
}
