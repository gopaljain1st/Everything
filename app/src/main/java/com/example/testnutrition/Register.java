package com.example.testnutrition;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.google.android.material.textfield.TextInputEditText;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Register extends AppCompatActivity
{
    EditText name,phoneNo,age,weight,height,occupation,email,userPassword,address;
    TextView male,female,other;
    Button submit;
    String sex="",id;
    CircleImageView profile;
    SharedPreferences sp=null;
    Intent in;
    private String encodeImage=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_information);
        initComponent();
        in=getIntent();
        String check=in.getStringExtra("check");
        switch(check)
        {
            case "mobile":
                phoneNo.setText(in.getStringExtra("mobileNo"));
                id=""+System.currentTimeMillis();
                break;
            case "facebook" :
                 phoneNo.setText(in.getStringExtra("mobileNo"));
                 email.setText(in.getStringExtra("email"));
                 name.setText(in.getStringExtra("name"));
                 id=in.getStringExtra("id");
                 break;
            case "google" :
                email.setText(in.getStringExtra("email"));
                name.setText(in.getStringExtra("name"));
                id=in.getStringExtra("id");
                break;
        }
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setBackgroundResource(R.drawable.border2);
                male.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                female.setTextColor(getResources().getColor(R.color.transparent));
                other.setTextColor(getResources().getColor(R.color.transparent));
                female.setBackgroundResource(R.drawable.border1);
                other.setBackgroundResource(R.drawable.border1);
                sex="Male";
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setBackgroundResource(R.drawable.border2);
                female.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                male.setTextColor(getResources().getColor(R.color.transparent));
                other.setTextColor(getResources().getColor(R.color.transparent));
                male.setBackgroundResource(R.drawable.border1);
                other.setBackgroundResource(R.drawable.border1);
                sex="Female";
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                other.setBackgroundResource(R.drawable.border2);
                other.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                female.setTextColor(getResources().getColor(R.color.transparent));
                male.setTextColor(getResources().getColor(R.color.transparent));
                male.setBackgroundResource(R.drawable.border1);
                female.setBackgroundResource(R.drawable.border1);
                sex="Other";
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Dexter.withActivity(Register.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                Intent intent=new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Select Image"),1);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(name.getText().toString().trim().equals("") || phoneNo.getText().toString().trim().equals("") || age.getText().toString().trim().equals("") || weight.getText().toString().trim().equals("")
                 || height.getText().toString().trim().equals("") || occupation.getText().toString().trim().equals("") || userPassword.getText().toString().trim().equals("")
                 || address.getText().toString().trim().equals("") || sex.equals("") || email.getText().toString().trim().equals("") || encodeImage==null)
                    Toast.makeText(Register.this, "Please Fill All The Details", Toast.LENGTH_SHORT).show();
                else
                {
                    final ProgressDialog pd = new ProgressDialog(Register.this);
                    pd.setTitle("Uploading...");
                    pd.show();

                    String url="https://relishking.com/restrauntapp/customerregistration.php";
                    StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response)
                        {
                            //Toast.makeText(Register.this, ""+response, Toast.LENGTH_SHORT).show();

                            if(response.contains("register done"))
                            {
                                String arr[] = response.split(",");
                                pd.dismiss();
                                Toast.makeText(Register.this, "Registeration Done Successfully", Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor editor=sp.edit();
                                editor.putString("id",id);
                                editor.putString("name",name.getText().toString());
                                editor.putString("email",email.getText().toString());
                                editor.putString("mobile",phoneNo.getText().toString());
                                editor.putString("address",address.getText().toString());
                                editor.putString("height",height.getText().toString());
                                editor.putString("weight",weight.getText().toString());
                                editor.putString("occupation",occupation.getText().toString());
                                editor.putString("gender",sex);
                                editor.putString("password",userPassword.getText().toString());
                                editor.putString("age",age.getText().toString());
                                String s="https://relishking.com/restrauntapp/images/";
                                editor.putString("profileUrl",s+(arr[1].trim()));
                                editor.commit();
                                startActivity(new Intent(Register.this,MainActivity.class));
                                 finish();
                            }
                            else
                                Toast.makeText(Register.this, ""+response, Toast.LENGTH_SHORT).show();
                        }
                    },new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            pd.dismiss();
                            Toast.makeText(Register.this, ""+error, Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> hm=new HashMap<>();
                            hm.put("id",id);
                           // hm.put("profileurl",in.getStringExtra("profileUrl"));
                            hm.put("email",email.getText().toString());
                            hm.put("customername",name.getText().toString());
                            hm.put("gender",sex);
                            hm.put("mobilenumber",phoneNo.getText().toString());
                            hm.put("age",age.getText().toString());
                            hm.put("weight",weight.getText().toString());
                            hm.put("occupation",occupation.getText().toString());
                            hm.put("password",userPassword.getText().toString());
                            hm.put("address",address.getText().toString());
                            hm.put("height",height.getText().toString());
                            hm.put("profileurl",encodeImage);
                            return hm;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(Register.this);
                    queue.add(request);
                }
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null){

            Uri filepath=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                bitmap=getResizedBitmap(bitmap,1024);
                profile.setImageBitmap(bitmap);
                imageStore(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
    private void imageStore(Bitmap bitmap) {
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] imageBytes=stream.toByteArray();
        encodeImage=android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }


    private void initComponent()
    {
        name=findViewById(R.id.userName);
        phoneNo=findViewById(R.id.userMobile);
        age=findViewById(R.id.userAge);
        weight=findViewById(R.id.userWeight);
        height=findViewById(R.id.userHeight);
        occupation=findViewById(R.id.userOccupation);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        other=findViewById(R.id.other);
        submit=findViewById(R.id.submit);
        email=findViewById(R.id.userEmail);
        userPassword=findViewById(R.id.userPassword);
        sp=getSharedPreferences("customer",MODE_PRIVATE);
        profile=findViewById(R.id.profile);
        address=findViewById(R.id.userAddress);
    }
}
