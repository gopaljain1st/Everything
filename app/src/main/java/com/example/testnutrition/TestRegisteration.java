package com.example.testnutrition;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class TestRegisteration extends AppCompatActivity
{
    Button submit;
    final String api="https://shankaryogshala.com/sagarjain/register.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_information);
         submit=findViewById(R.id.submit);
         submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v)
             {
                     StringRequest request=new StringRequest(Request.Method.POST, api, new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response)
                     {
                         Toast.makeText(TestRegisteration.this, ""+response, Toast.LENGTH_SHORT).show();

                     }
                 },new Response.ErrorListener() {


                     @Override
                     public void onErrorResponse(VolleyError error)
                     {

                     }
                 }){
                     @Override
                     protected Map<String, String> getParams() throws AuthFailureError {
                         Map<String,String> hm=new HashMap<>();
                         hm.put("name","gopal");
                         hm.put("mobile","7692898351");
                         hm.put("password","test");
                         hm.put("email","male");
                         return hm;
                     }
                 };
                 RequestQueue queue = Volley.newRequestQueue(TestRegisteration.this);
                 queue.add(request);
             }
         });
    }
}
