package com.example.testnutrition.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testnutrition.R;
import com.example.testnutrition.adapters.MyAdapter;
import com.example.testnutrition.models.PackagesModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PackageLunch extends Fragment
{
    private static final String apiurl="https://relishking.com/restrauntapp/customerfetchpackage.php";
    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.Adapter<MyAdapter.ViewHolder>adapter;
    ArrayList<PackagesModel> al;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.grid_layout,container,false);
        rv=view.findViewById(R.id.lunchRecyclerview);
        al=new ArrayList<>();
        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setTitle("Nutritang");
        pd.setMessage("loading...");
        pd.show();
        StringRequest request=new StringRequest(Request.Method.POST, apiurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String sucess = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if (sucess.equals("1")) {
                        pd.dismiss();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            al.add(new PackagesModel(R.drawable.image3,object.getString("package_id"),object.getString("restrauntid"),object.getString("package_name"),object.getString("package_category"),object.getString("package_type"),object.getString("package_date"),object.getString("package_time"),object.getString("package_price"),object.getString("package_type2"),object.getString("package_description")));
                        }
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2, GridLayoutManager.VERTICAL,false);
                        rv.setLayoutManager(gridLayoutManager);
                        adapter=new MyAdapter(getContext(),al);
                        rv.setAdapter(adapter);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("type","Lunch");
                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(request);
        return view;
    }
}
