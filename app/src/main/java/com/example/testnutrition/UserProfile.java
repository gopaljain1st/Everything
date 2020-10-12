package com.example.testnutrition;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {
    TextView txtage,txtname,txtaddress,txtoccuption,txtweight,txtheight,txtgender,txtemail,txtmobile,location,designation;
    CircleImageView profile;
    ImageView edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        edit=findViewById(R.id.edit);
        profile=findViewById(R.id.profile);
        txtage=findViewById(R.id.age);
        txtname=findViewById(R.id.name);
        txtaddress=findViewById(R.id.address);
        txtoccuption=findViewById(R.id.occupation);
        txtweight=findViewById(R.id.weigth);
        txtheight=findViewById(R.id.height);
        txtgender=findViewById(R.id.gender);
        txtemail=findViewById(R.id.email);
        txtmobile=findViewById(R.id.mobileNumber);

        location=findViewById(R.id.location);
        designation=findViewById(R.id.designation);

        SharedPreferences prefs = getSharedPreferences("customer",MODE_PRIVATE);
        String name = prefs.getString("name", "UserName").toUpperCase();
        String email=prefs.getString("email","UserEmail");
        String mobile=prefs.getString("mobile","UserMobile");
        String address = prefs.getString("address", "UserAddress");
        String height=prefs.getString("height","UserHeight");
        String weight=prefs.getString("weight","Weight");
        String occupation = prefs.getString("occupation", "UserOccupation");
        String gender=prefs.getString("gender","UserGender");
        Picasso.get().load(prefs.getString("profileUrl",null)).resize(500,500).centerCrop().into(profile);


        location.setText(address);
        designation.setText("("+occupation+")");


        txtname.setText(name);
        txtemail.setText(email);
        txtmobile.setText(mobile);
        txtaddress.setText(address);
        txtheight.setText(height);
        txtweight.setText(weight);
        txtoccuption.setText(occupation);
        txtgender.setText(gender);
        txtage.setText(prefs.getString("age","userAge"));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this,UpdateProfile.class));
                finish();
            }
        });

    }
}
