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
import com.example.testnutrition.adapters.BreakfastSpecialOffers;
import com.example.testnutrition.adapters.foodAdapter;
import com.example.testnutrition.models.Food;
import com.example.testnutrition.models.UpComing;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dinner extends Fragment
{
    RecyclerView dinnerLayoutrv,dinnerLayoutRecylerviewSpecialOffer,dinnerLayoutRecylerviewAddon,dinnerLayouttestRecyclerView;
    LinearLayoutManager linearLayoutManager,linearLayoutManager2,linearLayoutManager3,testLinearLayout;
    RecyclerView.Adapter<foodAdapter.FoodViewHolder>adapter,testAdapter;
    RecyclerView.Adapter<BreakfastSpecialOffers.BreakfastSpecialViewHolder>adapter2,adapter3;
    ArrayList<Food>al,testal;
    ArrayList<UpComing>al2,al3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dinner_layout,container,false);
        dinnerLayoutrv=view.findViewById(R.id.dinnerLayoutRecyclerview);
        dinnerLayoutRecylerviewSpecialOffer=view.findViewById(R.id.dinnerLayoutRecyclerviewSpecialOffer);
        dinnerLayoutRecylerviewAddon=view.findViewById(R.id.dinnerLayoutRecyclerviewAddon);
        dinnerLayouttestRecyclerView=view.findViewById(R.id.dinnerLayouttestRecylerView);

        dinnerLayouttestRecyclerView.setNestedScrollingEnabled(false);
        dinnerLayoutrv.setNestedScrollingEnabled(false);
        dinnerLayoutRecylerviewAddon.setNestedScrollingEnabled(false);
        dinnerLayoutRecylerviewSpecialOffer.setNestedScrollingEnabled(false);

        linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager2=new LinearLayoutManager(getContext());
        linearLayoutManager3=new LinearLayoutManager(getContext());
        testLinearLayout=new LinearLayoutManager(getContext());

        dinnerLayouttestRecyclerView.setLayoutManager(testLinearLayout);
        dinnerLayoutrv.setLayoutManager(linearLayoutManager);
        dinnerLayoutRecylerviewAddon.setLayoutManager(linearLayoutManager2);
        dinnerLayoutRecylerviewSpecialOffer.setLayoutManager(linearLayoutManager3);


        /*testal=new ArrayList<>();
        testal.add(new Food("Silver Package","140","220 Cal || light Weighted",""+R.drawable.thali));
        testal.add(new Food("Golden Package","200","300 Cal || light Weighted",""+R.drawable.thali1));
        testAdapter=new foodAdapter(getContext(),testal);
        dinnerLayouttestRecyclerView.setAdapter(testAdapter);
        al.add(new Food("Platinum Package","240","400 Cal || High Protein",""+R.drawable.thali2));
        al.add(new Food("Special Package","180","280 Cal || High Protein",""+R.drawable.thali3));
        adapter=new foodAdapter(getContext(),al);
        dinnerLayoutrv.setAdapter(adapter);
        */
        al=new ArrayList<>();
        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setTitle("Nutritang");
        pd.setMessage("loading...");
        pd.show();
        String apiurl="https://relishking.com/restrauntapp/customerfetchthali.php";
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
                            String s="https://relishking.com/restrauntapp/images/";
                            s+=object.getString("thali_image");
                            al.add(new Food(object.getString("thali_name"),object.getString("thali_price"),"220 Cal || light Weighted",s,object.getString("thali_type"),object.getString("thali_place_order_time"),object.getString("thali_item_count"),object.getString("thali_id")));
                        }
                        adapter=new foodAdapter(getContext(),al);
                        dinnerLayoutrv.setAdapter(adapter);
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
                map.put("type","Dinner");
                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(request);



        al2=new ArrayList<>();
        al2.add(new UpComing("Gazar Ka Halwa","Home made Halwa with delicious taste and full of protein",""+R.drawable.gajarhalwa));
        al2.add(new UpComing("Mango Shake","Mango Shake with rosated fruits and having delicious taste which child your mind",""+R.drawable.mangoshake));
        al2.add(new UpComing("Rasgulla","Pure Rasgulla with delcious taste people love to eat it",""+R.drawable.rasgulla));
        al2.add(new UpComing("Rasgulla","Pure Rasgulla with delcious taste people love to eat it",""+R.drawable.rasgulla));
        al2.add(new UpComing("Rasgulla","Pure Rasgulla with delcious taste people love to eat it",""+R.drawable.rasgulla));
        adapter2=new BreakfastSpecialOffers(getContext(),al2);
        dinnerLayoutRecylerviewSpecialOffer.setAdapter(adapter2);


        al3=new ArrayList<>();
        al3.add(new UpComing("Gazar Ka Halwa","Home made Halwa with delicious taste and full of protein",""+R.drawable.gajarhalwa));
        al3.add(new UpComing("Mango Shake","Mango Shake with rosated fruits and having delicious taste which child your mind",""+R.drawable.mangoshake));
        al3.add(new UpComing("Rasgulla","Pure Rasgulla with delcious taste people love to eat it",""+R.drawable.rasgulla));
        al3.add(new UpComing("Rasgulla","Pure Rasgulla with delcious taste people love to eat it",""+R.drawable.rasgulla));
        al3.add(new UpComing("Rasgulla","Pure Rasgulla with delcious taste people love to eat it",""+R.drawable.rasgulla));
        adapter3=new BreakfastSpecialOffers(getContext(),al3);
        dinnerLayoutRecylerviewAddon.setAdapter(adapter3);
        return view;
    }
}
