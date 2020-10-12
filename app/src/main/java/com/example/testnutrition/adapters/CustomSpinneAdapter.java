package com.example.testnutrition.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testnutrition.R;
import com.example.testnutrition.models.CustomItemModel;

import java.util.ArrayList;

public class CustomSpinneAdapter extends ArrayAdapter<CustomItemModel> {
    public CustomSpinneAdapter(@NonNull Context context, ArrayList<CustomItemModel> customItemList) {
        super(context,0, customItemList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_layout,parent,false);
        }
        CustomItemModel item=getItem(position);
        ImageView spinnerIV=convertView.findViewById(R.id.ivSpinnerLayout);
        TextView spinnnerTV=convertView.findViewById(R.id.tvSpinnerLayout);
        if (item!=null) {
            spinnerIV.setImageResource(item.getSpinnerItemImage());
            spinnnerTV.setText(item.getSpinnerItemName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_drop_down_layout,parent,false);
        }
        CustomItemModel item=getItem(position);
        ImageView dropdownIV=convertView.findViewById(R.id.ivDropDown);
        TextView dropdownTV=convertView.findViewById(R.id.tvDropDown);
        if (item!=null) {
            dropdownIV.setImageResource(item.getSpinnerItemImage());
            dropdownTV.setText(item.getSpinnerItemName());
        }
        return convertView;
    }
}
