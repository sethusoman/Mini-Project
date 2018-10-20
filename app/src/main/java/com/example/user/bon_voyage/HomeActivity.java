package com.example.user.bon_voyage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class HomeActivity extends AppCompatActivity {

    Button btnLogOut;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(I);
            }
        });

        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.open, R.string.close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.nav_mytrips:
                        Toast.makeText(HomeActivity.this, "My Trips",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_settings:
                        Toast.makeText(HomeActivity.this, "Settings",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_myfriends:
                        Intent In = new Intent(HomeActivity.this, MapsActivity.class);
                        startActivity(In);
                        break;
                    case R.id.nav_signout:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                       // Intent I = new Intent(HomeActivity.this, LoginActivity.class);
                        //startActivity(I);
                        break;
                    default:
                        return true;
                }


             return true;

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
