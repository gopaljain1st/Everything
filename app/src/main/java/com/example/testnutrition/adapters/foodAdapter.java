package com.example.testnutrition.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testnutrition.OrderFoodActivity;
import com.example.testnutrition.R;
import com.example.testnutrition.models.Food;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class foodAdapter extends RecyclerView.Adapter<foodAdapter.FoodViewHolder>
{
    Context context;
    ArrayList<Food> al;
    int qty=1;
    boolean check=false;

    public foodAdapter(Context context, ArrayList<Food> al) {
        this.context = context;
        this.al = al;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View v= LayoutInflater.from(context).inflate(R.layout.food_layout,parent,false);
        return new FoodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder holder, int position)
    {
           final Food f=al.get(position);
           if(getItemCount()-1==position)
               holder.breakfastSpecialLine.setVisibility(View.INVISIBLE);
           holder.breakfastPrice.setText(f.getPrice()+" /-");
          // holder.breakfastImg.setImageResource(Integer.parseInt(f.getImgUrl()));
        Picasso.get().load(f.getImgUrl()).resize(300,300).centerCrop().into(holder.breakfastImg);
        //LoadImage loadImage=new LoadImage(holder.breakfastImg);
        //loadImage.execute(f.getImgUrl());
        holder.breakfastName.setText(f.getName());
           holder.breakfastProtein.setText(f.getProtein());
           holder.breakfastStrikeTag.setText("\u20B9 "+(Integer.parseInt(f.getPrice())+40));
           holder.breakfastStrikeTag.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
           holder.breakfastImg.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v)
               {
                   Intent in=new Intent(context, OrderFoodActivity.class);
                   in.putExtra("Food",f);
                   in.putExtra("qty",""+qty);
                   in.putExtra("isAdd",""+check);
                   context.startActivity(in);
               }
           });
           holder.breakfastAdd.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   holder.breakfastAdd.setVisibility(View.GONE);
                   holder.hideLayout.setVisibility(View.VISIBLE);
                   check=true;
               }
           });
           holder.addThali.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  qty = Integer.parseInt(holder.qtyThali.getText().toString().trim());
                   qty+=1;
                   holder.qtyThali.setText(""+qty);
               }
           });
        holder.removeThali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 qty = Integer.parseInt(holder.qtyThali.getText().toString().trim());
                if(qty>1)
                {
                    qty-=1;
                    holder.qtyThali.setText(""+qty);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder
    {

        ImageView breakfastImg,addThali,removeThali;
        TextView qtyThali,breakfastName,breakfastPrice,breakfastProtein,breakfastStrikeTag,breakfastSpecialLine;
        Button breakfastAdd;
        LinearLayout hideLayout;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            breakfastAdd=itemView.findViewById(R.id.breakfastAdd);
            breakfastName=itemView.findViewById(R.id.breakfastName);
            breakfastImg=itemView.findViewById(R.id.breakfastImg);
            breakfastPrice=itemView.findViewById(R.id.breakfastPrice);
            breakfastProtein=itemView.findViewById(R.id.breakfastProtein);
            breakfastStrikeTag=itemView.findViewById(R.id.breakfastStrikePrice);
            breakfastSpecialLine=itemView.findViewById(R.id.BreakfastSpecialLine);
            addThali=itemView.findViewById(R.id.addThali);
            removeThali=itemView.findViewById(R.id.removeThali);
            qtyThali=itemView.findViewById(R.id.qtyThali);
            hideLayout=itemView.findViewById(R.id.hideLayout);
        }
    }
    /*class LoadImage extends AsyncTask<String,Void, Bitmap>
    {
        ImageView iv;
        public LoadImage(ImageView iv) {
            this.iv = iv;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlLink= strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new java.net.URL(urlLink).openStream();
                bitmap= BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            iv.setImageBitmap(bitmap);
        }
    }*/
}
