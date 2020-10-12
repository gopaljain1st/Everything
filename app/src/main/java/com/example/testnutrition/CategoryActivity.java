package com.example.testnutrition;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testnutrition.adapters.CustomRecyclerAdapter;
import com.example.testnutrition.adapters.MyAdapter;
import com.example.testnutrition.adapters.ViewpagerAdapter;
import com.example.testnutrition.fragments.CategoryCustomBottomSheetDialogFragment;
import com.example.testnutrition.models.ItemList;
import com.example.testnutrition.models.Itemlist2;
import com.example.testnutrition.models.PackagesModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryActivity extends AppCompatActivity {

    private static final String apiurl="https://relishking.com/restrauntapp/fetchPackageItems.php";
    ViewPager viewPager;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    Button categoryAddbtn;
    ArrayList<Itemlist2> al;
    List<ItemList> personUtilsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        recyclerView=findViewById(R.id.recycler);
        categoryAddbtn=findViewById(R.id.CategoryAddbtn);
        recyclerView.setNestedScrollingEnabled(false);
        viewPager=findViewById(R.id.viewpager);
        ViewpagerAdapter viewpagerAdapter=new ViewpagerAdapter(this);
        viewPager.setAdapter(viewpagerAdapter);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Intent in =getIntent();
        final PackagesModel p = (PackagesModel) in.getSerializableExtra("package");
        al=new ArrayList<>();
        final ProgressDialog pd = new ProgressDialog(this);
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
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Itemlist2 item=new Itemlist2(R.drawable.thali,object.getString("id"),object.getString("package_id"),object.getString("item_name"),object.getString("item_price"),object.getString("item_weight"),object.getString("item_quantity"),object.getString("item_type"));
                            al.add(item);
                        }
                        mAdapter = new CustomRecyclerAdapter(CategoryActivity.this, al);
                        recyclerView.setAdapter(mAdapter);
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
                Toast.makeText(CategoryActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("package_id",p.getId());
                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(CategoryActivity.this);
        requestQueue.add(request);

       /* personUtilsList = new ArrayList<>();
        personUtilsList.add(new ItemList(R.drawable.thali,"Indian Thali","Package: Silver"));
        personUtilsList.add(new ItemList(R.drawable.thali1,"South Indian Thali","Package: Gold"));
        personUtilsList.add(new ItemList(R.drawable.thali2,"North Indian Thali","Package: Platinium"));
        personUtilsList.add(new ItemList(R.drawable.thali3," Instant Thali","Package: Gold"));
        personUtilsList.add(new ItemList(R.drawable.thali," Indian Thali","Package: Silver"));
        */
        categoryAddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CategoryCustomBottomSheetDialogFragment().show(getSupportFragmentManager(),"Dialog");
            }
        });
    }
}
