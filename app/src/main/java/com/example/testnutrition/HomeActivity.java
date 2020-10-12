package com.example.testnutrition;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.testnutrition.adapters.OptionAdapter;
import com.example.testnutrition.fragments.OffersFragment;
import com.example.testnutrition.fragments.OrderFragment;
import com.example.testnutrition.fragments.PackageFragment;
import com.example.testnutrition.fragments.UpComingFragment;
import com.example.testnutrition.models.Option;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FragmentTransaction transaction;
    FragmentManager fragmentManager;
    SearchView searchView;
    SharedPreferences sp=null;
    LinearLayout navheaderBlock;
    CircleImageView profile;
    ImageView locationLogo;
    TextView name,email,currentLocation;
    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer_layout);
        sp=getSharedPreferences("customer",MODE_PRIVATE);
        searchView = findViewById(R.id.homeSearchView);
        locationLogo=findViewById(R.id.locationLogo);
        locationLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLocation.callOnClick();
            }
        });
        currentLocation=findViewById(R.id.location);
        currentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(HomeActivity.this,MapActivity.class));
            }
        });
        ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_button);
        searchIcon.setImageResource(R.drawable.search_icon);
        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }


        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");

        mDrawerLayout=findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
                this,mDrawerLayout,toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        NavigationView navigationView = findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        navheaderBlock=headerView.findViewById(R.id.navheaderBlock);
        profile=headerView.findViewById(R.id.profile);
        name=headerView.findViewById(R.id.userName);
        email=headerView.findViewById(R.id.userEmail);
        name.setText(sp.getString("name","Nutritang").toUpperCase());
        email.setText(sp.getString("email","nutritang@gmail.com"));
        Picasso.get().load(sp.getString("profileUrl",null)).resize(500,500).centerCrop().into(profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,UserProfile.class));
            }
        });
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        TabLayout tabLayout =findViewById(R.id.mainTablayout);
        final ViewPager viewPager=findViewById(R.id.mainViewPager);
        ViewPageAdapter viewPageAdapter=new ViewPageAdapter(getSupportFragmentManager());
        //just make as much you want fragment class
        viewPageAdapter.addFragment(new OrderFragment(),"Order");
        viewPageAdapter.addFragment(new UpComingFragment(),"Upcoming");
        viewPageAdapter.addFragment(new OffersFragment(),"Offers");
        viewPageAdapter.addFragment(new PackageFragment(),"Packages");
        //add adapter
        viewPager.setAdapter(viewPageAdapter);
        //add viewPager in tablayout
        tabLayout.setupWithViewPager(viewPager);

        fragmentManager=getSupportFragmentManager();
        transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment,new OrderFragment());
        transaction.commit();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                if(tab.getText().toString().equals("Order"))
                {
                    transaction=fragmentManager.beginTransaction();
                    transaction.replace(R.id.nav_host_fragment,new OrderFragment());
                    transaction.commit();
                }
                else if(tab.getText().toString().equals("Upcoming"))
                {

                    transaction=fragmentManager.beginTransaction();
                    transaction.replace(R.id.nav_host_fragment,new UpComingFragment());
                    transaction.commit();
                }
                else if(tab.getText().toString().equals("Offers"))
                {
                    transaction=fragmentManager.beginTransaction();
                    transaction.replace(R.id.nav_host_fragment,new OffersFragment());
                    transaction.commit();
                }
                else if(tab.getText().toString().equals("Packages"))
                {
                    transaction=fragmentManager.beginTransaction();
                    transaction.replace(R.id.nav_host_fragment,new PackageFragment());
                    transaction.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.logoutDrawer:
                SharedPreferences.Editor edit = sp.edit();
                edit.remove("id");
                edit.remove("name");
                edit.remove("mobile");
                edit.remove("email");
                edit.remove("height");
                edit.remove("weight");
                edit.remove("occupation");
                edit.remove("age");
                edit.remove("password");
                edit.remove("gender");
                edit.remove("address");
                edit.remove("profileUrl");
                edit.commit();
                startActivity(new Intent(HomeActivity.this, WelcomeActivity.class));
                finish();
                break;
            case R.id.profile :
                startActivity(new Intent(this,UserProfile.class));
                break;
        }

        return true;
    }

    /* RecyclerView homeRv;
    RecyclerView.LayoutManager manager;
    ArrayList<Option>al;
    RecyclerView.Adapter<OptionAdapter.OptionViewHolder>adapter;
    FragmentTransaction transaction;
    FragmentManager fragmentManager;
     homeRv=findViewById(R.id.homeRv);
        manager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        homeRv.setLayoutManager(manager);
        Toolbar toolbar=findViewById(R.id.homeToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        al=new ArrayList<>();
        al.add(new Option("Order",""+R.drawable.facebookbutton,""+R.color.colorPrimary));
        al.add(new Option("Upcoming",""+R.drawable.googlebutton,""+R.color.colorAccent));
        al.add(new Option("Offers",""+R.drawable.emailbutton,""+R.color.primary_500));
        al.add(new Option("Packages",""+R.drawable.emailbutton,""+R.color.colorPrimary));
        al.add(new Option("Trainner",""+R.drawable.emailbutton,""+R.color.colorAccent));
        adapter=new OptionAdapter(this,al);
        homeRv.setAdapter(adapter);
        fragmentManager=getSupportFragmentManager();
        transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.ll,new OrderFragment());
        transaction.commit();
    */
    class ViewPageAdapter extends FragmentPagerAdapter
    {

        private ArrayList<Fragment> fragments;
        private  ArrayList<String> titles;
        ViewPageAdapter(FragmentManager fragmentManager)
        {
            super(fragmentManager);
            this.fragments=new ArrayList<>();
            this.titles=new ArrayList<>();
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
        public void addFragment(Fragment fragment,String title)
        {
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
