package com.example.testnutrition.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testnutrition.R;
import com.example.testnutrition.models.Itemlist2;

import java.util.List;

public class CustomRecyclerAdapter1 extends RecyclerView.Adapter<CustomRecyclerAdapter1.ViewHolder> {

    private Context context;
    private List<Itemlist2> personUtils;

    public CustomRecyclerAdapter1(Context context, List personUtils) {
        this.context = context;
        this.personUtils = personUtils;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist1, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.itemView.setTag(personUtils.get(position));

        Itemlist2 pu = personUtils.get(position);
        holder.itemimage.setImageResource(pu.getItemimageid());
        holder.itemname.setText(pu.getItemname());
        holder.quantity.setText(pu.getItemquantity());
        holder.calories.setText(pu.getItemcalories());
    }

    @Override
    public int getItemCount() {
        return personUtils.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView itemimage;
        public TextView itemname;
        public TextView quantity;
        TextView calories;

        public ViewHolder(View itemView) {
            super(itemView);
            itemimage=itemView.findViewById(R.id.itemimage);
            itemname =  itemView.findViewById(R.id.itemname);
            quantity =  itemView.findViewById(R.id.quanity);
            calories=itemView.findViewById(R.id.calories);

        }
    }

}
