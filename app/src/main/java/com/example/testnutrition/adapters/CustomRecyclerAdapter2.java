package com.example.testnutrition.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testnutrition.R;
import com.example.testnutrition.addOnItemDescription;
import com.example.testnutrition.models.Add_On_Model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomRecyclerAdapter2 extends RecyclerView.Adapter<CustomRecyclerAdapter2.ViewHolder> {

    private Context context;
    private List<Add_On_Model> personUtils;

    public CustomRecyclerAdapter2(Context context, List personUtils) {
        this.context = context;
        this.personUtils = personUtils;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_on_menu_xml, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    public void onBindViewHolder(ViewHolder holder, int position) {


        final Add_On_Model pu = personUtils.get(position);
        Picasso.get().load(pu.getImagUrl()).resize(500,500).centerCrop().into(holder.itemimage);
        //holder.itemimage.setImageResource(pu.getAddonimage_Id());
        holder.itemname.setText(pu.getAdd_item_name());
        holder.itemimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, addOnItemDescription.class);
                intent.putExtra("item",pu);
                context.startActivity(intent);
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

        public ViewHolder(View itemView) {
            super(itemView);
            itemimage=itemView.findViewById(R.id.addonimage);
            itemname =  itemView.findViewById(R.id.add_on_name);
        }
    }

}
