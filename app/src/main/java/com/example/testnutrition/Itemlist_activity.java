package com.example.testnutrition;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testnutrition.adapters.CustomRecyclerAdapter1;
import com.example.testnutrition.models.Itemlist2;

import java.util.ArrayList;
import java.util.List;

public class Itemlist_activity extends AppCompatActivity {
RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Itemlist2> personUtilsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_activity);
        recyclerView=findViewById(R.id.recycler2);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        personUtilsList = new ArrayList<>();
        personUtilsList.add(new Itemlist2(R.drawable.thali,"Indian Thali","Quantity :5","Calories: 256cal"));
        personUtilsList.add(new Itemlist2(R.drawable.thali1,"Indian Thali","Quantity :5","Calories: 256cal"));
        personUtilsList.add(new Itemlist2(R.drawable.thali2,"Indian Thali","Quantity :5","Calories: 256cal"));
        personUtilsList.add(new Itemlist2(R.drawable.thali3,"Indian Thali","Quantity :5","Calories: 256cal"));
        personUtilsList.add(new Itemlist2(R.drawable.thali,"Indian Thali","Quantity :5","Calories: 256cal"));
        mAdapter = new CustomRecyclerAdapter1(this, personUtilsList);

        recyclerView.setAdapter(mAdapter);

    }
}
