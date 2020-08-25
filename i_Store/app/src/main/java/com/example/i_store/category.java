package com.example.i_store;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class category extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final String uid=getIntent().getStringExtra("nn");

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_black_24dp);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(category.this,addproduct.class);
                i.putExtra("nn",uid);
                startActivity(i);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;



            }
        });
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
       try {
           mAppBarConfiguration = new AppBarConfiguration.Builder(
                   R.id.nav_home, R.id.nav_gallery,R.id.nav_slideshow, R.id.products)
                   .setDrawerLayout(drawer)
                   .build();
       }catch (Exception e){                        Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();
       }

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
               try {
                   int id=destination.getId();




                switch (id)
                {

                    case R.id.products:
                    fab.hide();
                    break;
                    default:
                        fab.show();
                        break;
                }}catch (Exception e){  Toast.makeText(getApplicationContext(), " "+e, Toast.LENGTH_SHORT).show();}

               }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.category, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
