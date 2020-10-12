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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateProfile extends AppCompatActivity
{
    EditText name,phoneNo,age,weight,height,occupation,location,email,userPassword;
    TextView male,female,other;
    Button submit;
    String sex="";
    CircleImageView profile;
    SharedPreferences sp=null;
    Intent in;
    private String encodeImage=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);
        initComponent();
        sex=sp.getString("gender","");
        switch (sex)
        {
            case "Male" :
                male.setBackgroundResource(R.drawable.border2);
                male.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case "Female" :
                female.setBackgroundResource(R.drawable.border2);
                female.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case "Other" :
                other.setBackgroundResource(R.drawable.border2);
                other.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
        }
        Picasso.get().load(sp.getString("profileUrl",null)).resize(500,500).centerCrop().into(profile);

        name.setText(sp.getString("name", "UserName"));
        email.setText(sp.getString("email","UserEmail"));
        phoneNo.setText(sp.getString("mobile","UserMobile"));
        location.setText(sp.getString("address", "UserAddress"));
        height.setText(sp.getString("height", "UserHeight"));
        weight.setText(sp.getString("weight", "UserWeight"));
        occupation.setText(sp.getString("occupation", "UserOccupation"));
        age.setText(sp.getString("age","userAge"));
        userPassword.setText(sp.getString("password","userPassword"));

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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pd = new ProgressDialog(UpdateProfile.this);
                pd.setTitle("Uploading...");
                pd.show();

                String url="https://relishking.com/restrauntapp/updateCustomerProfile.php";
                StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        if(response.contains("Profile Updated Sucessfully"))
                        {
                            pd.dismiss();
                            Toast.makeText(UpdateProfile.this, "Profile Updated Sucessfully", Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor editor=sp.edit();
                            editor.putString("id",sp.getString("id","0"));
                            editor.putString("name",name.getText().toString());
                            editor.putString("email",email.getText().toString());
                            editor.putString("mobile",phoneNo.getText().toString());
                            editor.putString("address",location.getText().toString());
                            editor.putString("height",height.getText().toString());
                            editor.putString("weight",weight.getText().toString());
                            editor.putString("occupation",occupation.getText().toString());
                            editor.putString("gender",sex);
                            editor.putString("password",userPassword.getText().toString());
                            editor.putString("age",age.getText().toString());

                            if(response.contains(","))
                            {
                                String arr[] = response.split(",");
                                String s="https://relishking.com/restrauntapp/images/";
                                editor.putString("profileUrl",s+(arr[0].trim()));
                            }
                            editor.commit();
                            startActivity(new Intent(UpdateProfile.this,UserProfile.class));
                            finish();
                        }
                        else
                            Toast.makeText(UpdateProfile.this, ""+response, Toast.LENGTH_SHORT).show();
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        pd.dismiss();
                        Toast.makeText(UpdateProfile.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> hm=new HashMap<>();
                        hm.put("id",sp.getString("id","0"));
                        hm.put("email",email.getText().toString());
                        hm.put("customername",name.getText().toString());
                        hm.put("gender",sex);
                        hm.put("mobilenumber",phoneNo.getText().toString());
                        hm.put("age",age.getText().toString());
                        hm.put("weight",weight.getText().toString());
                        hm.put("occupation",occupation.getText().toString());
                        hm.put("password",userPassword.getText().toString());
                        hm.put("address",location.getText().toString());
                        hm.put("height",height.getText().toString());
                        if(encodeImage!=null)
                        hm.put("profileurl",encodeImage);
                        return hm;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(UpdateProfile.this);
                queue.add(request);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Dexter.withActivity(UpdateProfile.this)
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
        location=findViewById(R.id.userLocation);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        other=findViewById(R.id.other);
        submit=findViewById(R.id.submit);
        email=findViewById(R.id.userEmail);
        userPassword=findViewById(R.id.userPassword);
        sp=getSharedPreferences("customer",MODE_PRIVATE);
        profile=findViewById(R.id.profile);
    }
}
