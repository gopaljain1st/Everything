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
import com.example.testnutrition.models.UpComing;

import java.util.ArrayList;

public class BreakfastSpecialOffers extends RecyclerView.Adapter<BreakfastSpecialOffers.BreakfastSpecialViewHolder>
{
    Context context;
    ArrayList<UpComing> al;

    public BreakfastSpecialOffers(Context context, ArrayList<UpComing> al) {
        this.context = context;
        this.al = al;
    }

    @NonNull
    @Override
    public BreakfastSpecialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.breakfast_special_item_view,parent,false);
        return new BreakfastSpecialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreakfastSpecialViewHolder holder, int position)
    {
        UpComing u=al.get(position);
        if(position==al.size()-1)
            holder.BreakfastSpecialLine.setVisibility(View.INVISIBLE);
        holder.BreakfastSpecialDesc.setText(u.getDesc());
        holder.BreakfastSpecialName.setText(u.getName());
        holder.BreakfastSpecialImage.setImageResource(Integer.parseInt(u.getImgUrl()));
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class BreakfastSpecialViewHolder extends RecyclerView.ViewHolder
    {

        ImageView BreakfastSpecialImage;
        TextView BreakfastSpecialName,BreakfastSpecialDesc,BreakfastSpecialLine;
        public BreakfastSpecialViewHolder(@NonNull View itemView) {
            super(itemView);
            BreakfastSpecialImage=itemView.findViewById(R.id.BreakfastSpecialImage);
            BreakfastSpecialName=itemView.findViewById(R.id.BreakfastSpecialName);
            BreakfastSpecialDesc=itemView.findViewById(R.id.BreakfastSpecialDesc);
            BreakfastSpecialLine=itemView.findViewById(R.id.BreakfastSpecialLine);
        }
    }
}
