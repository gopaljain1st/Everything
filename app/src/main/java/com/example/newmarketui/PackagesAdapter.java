package com.example.newmarketui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class PackagesAdapter extends PagerAdapter
{
    Context context;
    LayoutInflater layoutInflater;
    Integer[] images={R.drawable.image12a,R.drawable.image12b,R.drawable.image12c};

    public PackagesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View view=layoutInflater.inflate(R.layout.custom_package,null);

        ImageView imageView=view.findViewById(R.id.imageView);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==0){
                    Toast.makeText(context, "slider 1 clicked", Toast.LENGTH_SHORT).show();
                }else if(position==1){
                    Toast.makeText(context, "slider 2 clicked", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "slider 3 clicked", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imageView.setImageResource(images[position]);
        ViewPager vp=(ViewPager)container;
        vp.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp=(ViewPager)container;
        View view=(View)object;
        vp.removeView(view);
    }
}
