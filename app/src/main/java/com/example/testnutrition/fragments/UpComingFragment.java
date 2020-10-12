package com.example.testnutrition.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.testnutrition.R;
import com.example.testnutrition.ViewPagerAdapter;
import com.example.testnutrition.adapters.OffersAdapter;
import com.example.testnutrition.adapters.UpComingAdapter;
import com.example.testnutrition.models.Food;
import com.example.testnutrition.models.UpComing;

import java.util.ArrayList;

public class UpComingFragment extends Fragment
{
    RecyclerView upcomingRv;
    RecyclerView.Adapter<UpComingAdapter.UpComingViewHolder>adapter;
    ArrayList<UpComing>al;
    LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.upcoming_activity,container,false);
        upcomingRv=view.findViewById(R.id.upcomingRv);
        linearLayoutManager=new LinearLayoutManager(getContext());
        upcomingRv.setLayoutManager(linearLayoutManager);

        al=new ArrayList<>();
        al.add(new UpComing("Fri, 15 May","Gazar Ka Halwa","Home made Halwa with delicious taste and full of protein",""+R.drawable.gajarhalwa));
        al.add(new UpComing("Sun, 17 May","Mango Shake","Mango Shake with rosated fruits and having delicious taste which child your mind",""+R.drawable.mangoshake));
        al.add(new UpComing("Mon, 18 May","Rasgulla","Pure Rasgulla with delcious taste people love to eat it",""+R.drawable.rasgulla));
        adapter=new UpComingAdapter(getContext(),al);
        upcomingRv.setAdapter(adapter);

        return view;
    }
}
