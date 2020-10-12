package com.example.testnutrition;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapterExample extends
        RecyclerView.Adapter<SliderAdapterExample.SliderAdapterVH> {

    ViewPager2 viewPager2;
    private List<SliderItem> mSliderItems;

    public SliderAdapterExample(ViewPager2 viewPager2, ArrayList<SliderItem> mSliderItems) {

        this.viewPager2 = viewPager2;
        this.mSliderItems=mSliderItems;
    }
    @NonNull
    @Override
    public SliderAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderAdapterVH(
          LayoutInflater.from(parent.getContext()).inflate
                  (R.layout.slider_view,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
      viewHolder.setImage(mSliderItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mSliderItems.size();
    }


    class SliderAdapterVH extends RecyclerView.ViewHolder {

        ImageView iv;

        public SliderAdapterVH(View itemView) {
            super(itemView);
           iv=itemView.findViewById(R.id.iv);
        }
        void setImage(SliderItem sliderItem)
        {
            iv.setImageResource(sliderItem.getImage());
        }
    }

}
