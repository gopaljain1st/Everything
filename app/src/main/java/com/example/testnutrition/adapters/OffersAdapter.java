package com.example.testnutrition.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testnutrition.R;
import com.example.testnutrition.models.Food;

import java.util.ArrayList;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.FoodViewHolder>
{
    Context context;
    ArrayList<Food> al;

    public OffersAdapter(Context context, ArrayList<Food> al) {
        this.context = context;
        this.al = al;
    }

    @NonNull
    @Override
    public OffersAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v= LayoutInflater.from(context).inflate(R.layout.offers_content,parent,false);
        return new OffersAdapter.FoodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OffersAdapter.FoodViewHolder holder, int position)
    {
        Food f=al.get(position);
        holder.offersPrice.setText(f.getPrice()+" /-");
        holder.offersImg.setImageResource(Integer.parseInt(f.getImgUrl()));
        holder.offersName.setText(f.getName());
        holder.offersProtein.setText(f.getProtein());
        holder.offersDiscount.setText(f.getOffers()+"% Off");
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder
    {

        ImageView offersImg;
        TextView offersName,offersPrice,offersProtein,offersDiscount;
        ToggleButton offersAdd;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            offersAdd=itemView.findViewById(R.id.offersAdd);
            offersName=itemView.findViewById(R.id.offersName);
            offersImg=itemView.findViewById(R.id.offersImg);
            offersPrice=itemView.findViewById(R.id.offersPrice);
            offersProtein=itemView.findViewById(R.id.offersProtein);
            offersDiscount=itemView.findViewById(R.id.offersDiscount);
        }
    }
}
