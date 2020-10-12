package com.example.testnutrition;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testnutrition.fragments.OffersFragment;
import com.example.testnutrition.fragments.OrderFragment;
import com.example.testnutrition.fragments.PackageFragment;
import com.example.testnutrition.fragments.UpComingFragment;
import com.example.testnutrition.models.Option;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private AppBarConfiguration mAppBarConfiguration;
    FragmentTransaction transaction;
    FragmentManager fragmentManager;
    SearchView searchView;
    DrawerLayout drawer;
    SharedPreferences sp=null;
    ActionBarDrawerToggle actionBarDrawerToggle;
    LinearLayout navheaderBlock;
    CircleImageView profile;
    ImageView locationLogo;
    TextView name,email,currentLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                startActivity(new Intent(MainActivity.this,MapActivity.class));
            }
        });
        sp=getSharedPreferences("customer",MODE_PRIVATE);
        searchView.setQueryHint(" esxgfd ");

//change icon color
        ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_button);
        searchIcon.setImageResource(R.drawable.search_icon);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen(R.id.nav_home);

        View headerView =navigationView.getHeaderView(0);
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
                startActivity(new Intent(MainActivity.this,UserProfile.class));
            }
        });

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow , R.id.logoutDrawer)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
*/


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
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void displaySelectedScreen(int itemId) {

        switch (itemId) {
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
                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                finish();
                break;
            case R.id.profile :
                startActivity(new Intent(this,UserProfile.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
    }
    @Override
    protected void onResume() {
        super.onResume();
        String loc=sp.getString("currentLocation","0");
        if(!loc.equals("0"))
            currentLocation.setText(loc);
    }

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
