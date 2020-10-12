package com.example.testnutrition.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testnutrition.Itemlist_activity;
import com.example.testnutrition.R;
import com.example.testnutrition.models.ItemList;
import com.example.testnutrition.models.Itemlist2;

import java.util.List;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Itemlist2> personUtils;

    public CustomRecyclerAdapter(Context context, List personUtils) {
        this.context = context;
        this.personUtils = personUtils;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.itemView.setTag(personUtils.get(position));

        Itemlist2 pu = personUtils.get(position);
        holder.itemimage.setImageResource(pu.getItemimageid());
        holder.itemname.setText(pu.getItemname());
        holder.packname.setText(pu.getItemname());
        holder.itemimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Itemlist_activity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return personUtils.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
          ImageView itemimage;
        public TextView itemname;
        public TextView packname;

        public ViewHolder(View itemView) {
            super(itemView);
            itemimage=itemView.findViewById(R.id.itemimage);
            itemname =  itemView.findViewById(R.id.itemname);
            packname =  itemView.findViewById(R.id.packagename);

        }
    }

}
