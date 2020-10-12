package com.example.testnutrition;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MobileLoginactivity extends AppCompatActivity
{
    EditText mobile,password;
    Button login;
    SharedPreferences sp=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initComponent();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mobile.getText().toString().trim().equals("") || password.getText().toString().trim().equals(""))
                    Toast.makeText(MobileLoginactivity.this, "Please Enter Details", Toast.LENGTH_SHORT).show();
                else
                {
                    final ProgressDialog pd = new ProgressDialog(MobileLoginactivity.this);
                    pd.setTitle("Nutritang");
                    pd.setMessage("Loading...");
                    pd.show();
                    String url = "https://relishking.com/restrauntapp/customerlogin.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response)
                        {
                            if(response.equals("login falied"))
                                Toast.makeText(MobileLoginactivity.this, "Wrong mobile or password", Toast.LENGTH_SHORT).show();
                            else
                            {
                                Toast.makeText(MobileLoginactivity.this, "Login Sucessfull", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(MobileLoginactivity.this, MainActivity.class);
                                String arr[] = response.split(",");
                                SharedPreferences.Editor editor=sp.edit();
                                Log.d("respone : ",response);
                                editor.putString("id",arr[0]);
                                editor.putString("name",arr[1]);
                                editor.putString("email",arr[2]);
                                editor.putString("mobile",arr[3]);
                                editor.putString("address",arr[4]);
                                editor.putString("height",arr[5]);
                                editor.putString("weight",arr[6]);
                                editor.putString("occupation",arr[7]);
                                editor.putString("gender",arr[8]);
                                String s="https://relishking.com/restrauntapp/images/";
                                editor.putString("profileUrl",s+(arr[9].trim()));
                                editor.putString("password",arr[10]);
                                editor.putString("age",arr[11]);
                                editor.commit();
                                startActivity(in);
                                finish();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pd.dismiss();
                            Toast.makeText(MobileLoginactivity.this, "" + error, Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> map = new HashMap<>();
                            map.put("mobile",mobile.getText().toString());
                            map.put("password",password.getText().toString());
                            return map;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(MobileLoginactivity.this, new HurlStack());
                    requestQueue.add(stringRequest);
                }
            }
        });
    }
    void initComponent()
    {
        mobile=findViewById(R.id.userMobile);
        password=findViewById(R.id.userPassword);
        login=findViewById(R.id.mobileLoginButton);
        sp=getSharedPreferences("customer",MODE_PRIVATE);
    }
}
