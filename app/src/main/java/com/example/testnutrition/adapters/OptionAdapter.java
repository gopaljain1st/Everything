package com.example.testnutrition.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testnutrition.R;
import com.example.testnutrition.fragments.OffersFragment;
import com.example.testnutrition.fragments.OrderFragment;
import com.example.testnutrition.fragments.PackageFragment;
import com.example.testnutrition.fragments.UpComingFragment;
import com.example.testnutrition.models.Option;

import java.util.ArrayList;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionViewHolder>
{
    FragmentTransaction transaction;
    FragmentManager manager;
    Context context;
    ArrayList<Option> al;

    public OptionAdapter(Context context, ArrayList<Option> al) {
        this.context = context;
        this.al = al;
    }

    @NonNull
    @Override

    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v= LayoutInflater.from(context).inflate(R.layout.horizontal_content,parent,false);
        return new OptionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final OptionViewHolder holder, int position)
    {
        final Option o= al.get(position);
        holder.horizontalText.setText(o.getName());
        manager=((AppCompatActivity)context).getSupportFragmentManager();
        holder.handleColor();
        holder.horizontalText.setOnClickListener(new View.OnClickListener()
        {   @Override
            public void onClick(View v)
            {
                holder.handleColor();
              if(o.getName().equals("Order"))
              {
                  holder.horizontalText.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                  holder.horizontalLinearLayout.setBackground(context.getResources().getDrawable(R.drawable.border_option2));

                  transaction=manager.beginTransaction();
                  transaction.replace(R.id.nav_host_fragment,new OrderFragment());
                  transaction.commit();
              }
              else if(o.getName().equals("Upcoming"))
              {
                  holder.horizontalText.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                  holder.horizontalLinearLayout.setBackground(context.getResources().getDrawable(R.drawable.border_option2));

                  transaction=manager.beginTransaction();
                  transaction.replace(R.id.nav_host_fragment,new UpComingFragment());
                  transaction.commit();
              }
              else if(o.getName().equals("Offers"))
              {
                  holder.horizontalText.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                  holder.horizontalLinearLayout.setBackground(context.getResources().getDrawable(R.drawable.border_option2));

                  transaction=manager.beginTransaction();
                  transaction.replace(R.id.nav_host_fragment,new OffersFragment());
                  transaction.commit();
              }
              else if(o.getName().equals("Packages"))
              {
                  holder.horizontalText.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                  holder.horizontalLinearLayout.setBackground(context.getResources().getDrawable(R.drawable.border_option2));

                  transaction=manager.beginTransaction();
                  transaction.replace(R.id.nav_host_fragment,new PackageFragment());
                  transaction.commit();
              }
              else if(o.getName().equals("Trainner"))
              {
                  holder.horizontalText.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                  holder.horizontalLinearLayout.setBackground(context.getResources().getDrawable(R.drawable.border_option2));

                  transaction=manager.beginTransaction();
                  transaction.replace(R.id.nav_host_fragment,new OrderFragment());
                  transaction.commit();
              }
              holder.handleColor();
            }
        });
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class OptionViewHolder extends RecyclerView.ViewHolder
    {
        TextView horizontalText;
        LinearLayout horizontalLinearLayout;
        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            horizontalText=itemView.findViewById(R.id.horizontalText);
            horizontalLinearLayout=itemView.findViewById(R.id.optionLinearLayout);
        }
        void handleColor()
        {
            horizontalText.setTextColor(context.getResources().getColor(R.color.black_trans80));
            horizontalLinearLayout.setBackground(context.getResources().getDrawable(R.drawable.border_option));

        }
    }
}
