package com.example.testnutrition.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testnutrition.CategoryActivity;
import com.example.testnutrition.R;
import com.example.testnutrition.models.PackagesModel;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    ArrayList<PackagesModel> packagesModelList;
    public MyAdapter(Context context, ArrayList<PackagesModel> packagesModelList){
        this.context = context;
        this.packagesModelList = packagesModelList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.packages_grid_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PackagesModel packagesModel=packagesModelList.get(position);
        holder.gridIcon.setImageResource(packagesModel.getImages());
        holder.gridIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent in =new Intent(context,CategoryActivity.class);
                in.putExtra("package",packagesModel);
                context.startActivity(in);
            }
        });
        holder.txtPrice.setText(packagesModel.getPrice());
    }

    @Override
    public int getItemCount() {
        return packagesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtPrice;
        ImageView gridIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtPrice=itemView.findViewById(R.id.txtPrice);
            gridIcon=itemView.findViewById(R.id.imageView2);

          /*  itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });*/
        }
    }
}