package com.example.elenavlasceanu.traveljournal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Constants;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
private TextView mUsername;
private TextView mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
         mUsername = (TextView) headerView.findViewById(R.id.username_google);
         mEmail=(TextView) headerView.findViewById(R.id.email_google);
        initView();

    }
   private void initView() {
       FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
           @Override
           public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               if (firebaseAuth.getCurrentUser() == null) {
                   Toast.makeText(NavigationDrawerActivity.this, "User not exist", Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(NavigationDrawerActivity.this, "User exist", Toast.LENGTH_SHORT).show();
               }
           }
       };

      /*FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       if(user!=null) {
           String email = user.getEmail();
           String username = user.getDisplayName();
           Toast.makeText(NavigationDrawerActivity.this,"hei",Toast.LENGTH_SHORT).show();
           mUsername.setText(username);
           mEmail.setText(email);
       }*/

       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       if (user != null) {
           for (UserInfo profile : user.getProviderData()) {
               // Id of the provider (ex: google.com)
               String providerId = profile.getProviderId();

               // UID specific to the provider
               String uid = profile.getUid();

               // Name, email address, and profile photo Url
               String name = profile.getDisplayName();
               String email = profile.getEmail();
             mUsername.setText(name);
             mEmail.setText(email);
           }
       }
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //Fragment fragment=null;
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            NavigationDrawerActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.contentID,TripRecyclerViewActivity.newInstance()).commit();
        } else if (id == R.id.nav_favourite) {
            NavigationDrawerActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.contentID,FavouriteFragment.newInstance()).commit();
        } else if (id == R.id.nav_info) {

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
       /* if(fragment!=null){
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction ft=fragmentManager.beginTransaction();
            ft.replace(R.id.drawer_layout,fragment);
            ft.commit();
        }*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
    public void btnAddTripClick(View view) {
        Intent intent = new Intent(this, Manage_Trip.class);
        startActivity(intent);
    }


}
