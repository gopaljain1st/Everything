package com.example.testnutrition;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testnutrition.adapters.UpComingAdapter;
import com.example.testnutrition.models.UpComing;

import java.util.ArrayList;

public class UpcomingActivity extends AppCompatActivity
{
    RecyclerView upcomingRv;
    RecyclerView.Adapter<UpComingAdapter.UpComingViewHolder>adapter;
    ArrayList<UpComing>al;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upcoming_activity);
        upcomingRv=findViewById(R.id.upcomingRv);

        linearLayoutManager=new LinearLayoutManager(this);
        upcomingRv.setLayoutManager(linearLayoutManager);

        al=new ArrayList<>();
        al.add(new UpComing("Fri, 15 May","Gazar Ka Halwa","Home made Halwa with delicious taste and full of protein",""+R.drawable.gajarhalwa));
        al.add(new UpComing("Sun, 17 May","Mango Shake","Mango Shake with rosated fruits and having delicious taste which child your mind",""+R.drawable.mangoshake));
        al.add(new UpComing("Mon, 18 May","Rasgulla","Pure Rasgulla with delcious taste people love to eat it",""+R.drawable.rasgulla));
        adapter=new UpComingAdapter(this,al);
        upcomingRv.setAdapter(adapter);
    }
}
