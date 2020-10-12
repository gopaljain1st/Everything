package com.example.testnutrition;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testnutrition.adapters.CustomSpinneAdapter;
import com.example.testnutrition.models.CustomItemModel;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner customSpinner;
    ArrayList<CustomItemModel>customList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);
        customSpinner=findViewById(R.id.spinner);
        customList=getCustomList();
        CustomSpinneAdapter customSpinneAdapter=new CustomSpinneAdapter(this,customList);

        if(customSpinner!=null){
            customSpinner.setAdapter(customSpinneAdapter);
            customSpinner.setOnItemSelectedListener(this);
        }
    }

    private ArrayList<CustomItemModel>getCustomList(){
        customList=new ArrayList<>();
        customList.add(new CustomItemModel("Golden",R.drawable.ic_access_time_black_24dp));
        customList.add(new CustomItemModel("Silver",R.drawable.ic_access_time_black_24dp));
        customList.add(new CustomItemModel("Platinium",R.drawable.ic_access_time_black_24dp));
         return customList;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        CustomItemModel customItem=(CustomItemModel) adapterView.getSelectedItem();
        Toast.makeText(this, customItem.getSpinnerItemName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
