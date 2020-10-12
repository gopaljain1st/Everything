package com.example.testnutrition.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testnutrition.R;
import com.example.testnutrition.models.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThaliItemAdapter extends RecyclerView.Adapter<ThaliItemAdapter.ThaliItemAdapterViewHolder>
{
    Context context;
    ArrayList<Item>al;

    public ThaliItemAdapter(Context context, ArrayList<Item> al) {
        this.context = context;
        this.al = al;
    }

    @NonNull
    @Override
    public ThaliItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.view_thali_items_card,parent,false);
        return new ThaliItemAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThaliItemAdapterViewHolder holder, int position)
    {
        Item i=al.get(position);
        Picasso.get().load(i.getImageUrl()).resize(300,300).centerCrop().into(holder.image);
        holder.name.setText(i.getName());
        holder.quantity.setText("Quantity : "+i.getQuantity());
        holder.weight.setText("Weight : "+i.getWeight());
        if(position==al.size()-1)
            holder.SpecialLine.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class ThaliItemAdapterViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,weight,quantity,SpecialLine;
        public ThaliItemAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            weight=itemView.findViewById(R.id.weight);
            quantity=itemView.findViewById(R.id.quanity);
            SpecialLine=itemView.findViewById(R.id.SpecialLine);
        }
    }
}
