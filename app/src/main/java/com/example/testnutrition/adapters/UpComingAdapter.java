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

public class UpComingAdapter extends RecyclerView.Adapter<UpComingAdapter.UpComingViewHolder>
{
    Context context;
    ArrayList<UpComing>al;

    public UpComingAdapter(Context context, ArrayList<UpComing> al) {
        this.context = context;
        this.al = al;
    }

    @NonNull
    @Override
    public UpComingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.upcoming_food,parent,false);
        return new UpComingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpComingViewHolder holder, int position)
    {
            UpComing u=al.get(position);
            if(position==al.size()-1)
                holder.upcomingLine.setVisibility(View.INVISIBLE);
            holder.upcomingDesc.setText(u.getDesc());
            holder.upcomingName.setText(u.getName());
            holder.upcomingDate.setText(u.getDate());
            holder.upcomingImage.setImageResource(Integer.parseInt(u.getImgUrl()));
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class UpComingViewHolder extends RecyclerView.ViewHolder
    {

        ImageView upcomingImage;
        TextView upcomingDate,upcomingName,upcomingDesc,upcomingLine;
        public UpComingViewHolder(@NonNull View itemView) {
            super(itemView);
            upcomingImage=itemView.findViewById(R.id.upcomingImage);
            upcomingDate=itemView.findViewById(R.id.upcomingDate);
            upcomingName=itemView.findViewById(R.id.upcomingName);
            upcomingDesc=itemView.findViewById(R.id.upcomingDesc);
            upcomingLine=itemView.findViewById(R.id.upcomingLine);
        }
    }
}
