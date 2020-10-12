package com.example.testnutrition;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.testnutrition.adapters.OffersAdapter;
import com.example.testnutrition.adapters.foodAdapter;
import com.example.testnutrition.models.Food;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Offers extends AppCompatActivity
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offers_layout);

        viewPager =  findViewById(R.id.viewPager);

        sliderDotspanel =  findViewById(R.id.SliderDots);

         viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 2000);

        rv=findViewById(R.id.offersRecylerview);
        linearLayoutManager=new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        al=new ArrayList<>();
        al.add(new Food("Gajar ka Halwa","100","210 Cal || Healthy Sweets","40",""+R.drawable.gajarhalwa));
        al.add(new Food("Rasgulla","20/-","186 Cal || light Weighted","20",""+R.drawable.rasgulla));
        al.add(new Food("Mango Shake","40","240 Cal with Sugar || High Protein","25",""+R.drawable.mangoshake));
        adapter=new OffersAdapter(this,al);
        rv.setAdapter(adapter);

    }
    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            Offers.this.runOnUiThread(new Runnable() {
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


}
