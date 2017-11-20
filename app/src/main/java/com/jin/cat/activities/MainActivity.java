package com.jin.cat.activities;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.jin.cat.R;
import com.jin.cat.fragments.TapFragment;
import com.jin.cat.utils.FirebaseUtils;
import com.squareup.picasso.Picasso;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    private FirebaseAuth mAuth;

    private static NavigationView navigationView;
    private static View header;
    private static TextView profileName;
    private static ImageView profileImg;
    private static TextView profileEmail;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new TapFragment();
        fragmentTransaction.replace(R.id.main_container_wrapper, fragment);
        fragmentTransaction.commit();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        header = navigationView.inflateHeaderView(R.layout.nav_header_cat);

        profileImg = (ImageView) header.findViewById(R.id.nav_profile_img);
        profileName = (TextView) header.findViewById(R.id.profile_name);
        profileEmail = (TextView) header.findViewById(R.id.profile_email);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_like) {
                    startActivity(new Intent(MainActivity.this, LikeCatListActivity.class));
                    //fragment = new TapFragment();
                } else if (id == R.id.nav_comment) {


                } else if (id == R.id.nav_my_post_list) {
                   // Intent intent = new Intent(MainActivity.this, MyPostActivity.class);
                   // startActivity(intent);

                } else if (id == R.id.nav_sound_cound) {

                }else if (id == R.id.nav_raw_folder) {

                }

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container_wrapper, fragment);
                transaction.commit();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                assert drawer != null;
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {

        if(FirebaseUtils.getCurrentUser()!=null){

            Picasso.with(profileImg.getContext()).load(FirebaseUtils.getCurrentUser().getPhotoUrl()).into(profileImg);
            profileName.setText(FirebaseUtils.getCurrentUser().getDisplayName());
            profileEmail.setText(FirebaseUtils.getCurrentUser().getEmail());

        }else{
            profileImg.setImageResource(R.drawable.icon_user);
            profileName.setText("Guest");
            profileEmail.setText("Guest");
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.menu_login:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                return true;

            case R.id.menu_logout:
                mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();

                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if(FirebaseUtils.getCurrentUser()!=null){
            menu.getItem(0).setVisible(false);
            menu.getItem(1).setVisible(true);
        }else{
            menu.getItem(0).setVisible(true);
            menu.getItem(1).setVisible(false);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
