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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.testnutrition.R;
import com.example.testnutrition.ViewPagerAdapter;
import com.example.testnutrition.adapters.OffersAdapter;
import com.example.testnutrition.models.Food;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.facebook.FacebookSdk.getApplicationContext;

public class OffersFragment extends Fragment
{
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    ViewPagerAdapter viewPagerAdapter;
    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.Adapter<OffersAdapter.FoodViewHolder>adapter;
    ArrayList<Food> al;
    Handler handler;
    Timer timer;
    final long DELAY_MS = 500;
    final long PERIOD_MS = 3000;
    int currentPage=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.offers_layout,container,false);
        viewPager =  view.findViewById(R.id.viewPager);

        sliderDotspanel =  view.findViewById(R.id.SliderDots);

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
       // Timer timer = new Timer();
        //timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 2000);
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


        rv=view.findViewById(R.id.offersRecylerview);
        linearLayoutManager=new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);
        al=new ArrayList<>();
        al.add(new Food("Gajar ka Halwa","100","210 Cal || Healthy Sweets","40",""+R.drawable.gajarhalwa));
        al.add(new Food("Rasgulla","20/-","186 Cal || light Weighted","20",""+R.drawable.rasgulla));
        al.add(new Food("Mango Shake","40","240 Cal with Sugar || High Protein","25",""+R.drawable.mangoshake));
        adapter=new OffersAdapter(getContext(),al);
        rv.setAdapter(adapter);

        return view;
    }
    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            int count=viewPager.getCurrentItem();
            if(count == dots.length-1)
                count=-1;
            viewPager.setCurrentItem(count+1);

        }
    }
}
