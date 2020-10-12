package com.example.testnutrition;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testnutrition.adapters.ThaliItemAdapter;
import com.example.testnutrition.adapters.foodAdapter;
import com.example.testnutrition.models.Food;
import com.example.testnutrition.models.Item;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThaliItemsActivity extends AppCompatActivity
{
    RecyclerView rv;
    LinearLayoutManager manager;
    RecyclerView.Adapter<ThaliItemAdapter.ThaliItemAdapterViewHolder>adapter;
    ArrayList<Item>al;
    String thaliId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thali_items_activity);
        thaliId=getIntent().getStringExtra("thaliId");
        Log.d("thaliId",thaliId);
        rv=findViewById(R.id.rv);
        manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        al=new ArrayList<>();

        final ProgressDialog pd = new ProgressDialog(ThaliItemsActivity.this);
        pd.setTitle("Nutritang");
        pd.setMessage("loading...");
        pd.show();
        String url="https://relishking.com/restrauntapp/customerfetchthaliitem.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Log.d("Response",response);
                try {
                JSONObject jsonObject = new JSONObject(response);
                String sucess = jsonObject.getString("success");
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                if (sucess.equals("1")) {
                    pd.dismiss();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String s="https://relishking.com/restrauntapp/images/";
                        s+=object.getString("item_image");
                        al.add(new Item(s,object.getString("item_name"),object.getString("item_weight"),object.getString("item_quantity")));
                    }
                    adapter=new ThaliItemAdapter(ThaliItemsActivity.this,al);
                    rv.setAdapter(adapter);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                pd.dismiss();
                Toast.makeText(ThaliItemsActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> hm=new HashMap<>();
                hm.put("thaliId",thaliId);
                return hm;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(ThaliItemsActivity.this);
        queue.add(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
