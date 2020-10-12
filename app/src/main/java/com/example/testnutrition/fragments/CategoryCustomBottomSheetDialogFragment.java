package com.example.testnutrition.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testnutrition.CartActivity;
import com.example.testnutrition.R;
import com.example.testnutrition.adapters.CustomRecyclerAdapter2;
import com.example.testnutrition.models.Add_On_Model;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryCustomBottomSheetDialogFragment extends BottomSheetDialogFragment {
    RecyclerView recyclerView;
    Button bottomAddToCart;
   // RecyclerView.Adapter mAdapter;
    //RecyclerView.LayoutManager layoutManager;
    List<Add_On_Model> personUtilsList;
    private static final String apiurl="https://relishking.com/restrauntapp/customerfetchaddon.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.sample, container,false);
        recyclerView=v.findViewById(R.id.rerere);
        recyclerView.setHasFixedSize(true);
        bottomAddToCart=v.findViewById(R.id.bottomAddToCart);
        personUtilsList = new ArrayList<>();
        bottomAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getContext(), CartActivity.class));
            }
        });
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setTitle("Nutritang");
        pd.setMessage("loading...");
        pd.show();
        StringRequest request = new StringRequest(Request.Method.GET, apiurl, new Response.Listener<String>() {
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
                            String s="https://relishking.com/restrauntapp/images/";
                            s+=object.getString("item_image");
                            Add_On_Model item = new Add_On_Model(object.getString("item_name"),s,object.getString("item_price"),object.getString("item_weight"),object.getString("item_quantity"),object.getString("item_type"),object.getString("item_category"),object.getString("item_type2"),object.getString("item_description"));
                            personUtilsList.add(item);
                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
                        recyclerView.setAdapter(new CustomRecyclerAdapter2(getContext(),personUtilsList));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();
                Log.d("error","error"+error);
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
/* //addon=findViewById(R.id.addbtn);
      // layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        personUtilsList = new ArrayList<>();
        personUtilsList.add(new Add_On_Model(R.drawable.juice,"Drink/Juice"));
        personUtilsList.add(new Add_On_Model(R.drawable.somasa,"Samosa"));
        personUtilsList.add(new Add_On_Model(R.drawable.burger,"Burger"));
        personUtilsList.add(new Add_On_Model(R.drawable.rusgula,"Rasgula"));
        personUtilsList.add(new Add_On_Model(R.drawable.namkeen,"Namkeen"));
       // mAdapter = new CustomRecyclerAdapter1(getContext(), personUtilsList);

        recyclerView.setAdapter(new CustomRecyclerAdapter2(getContext(),personUtilsList));
      */

         return v;
    }
}