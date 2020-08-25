package com.example.i_store;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class category extends AppCompatActivity  {
    NavigationView side;
    DrawerLayout d1;
    private AppBarConfiguration mAppBarConfiguration;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        side =(NavigationView)findViewById(R.id.side);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final String uid=getIntent().getStringExtra("nn");
        Toast.makeText(getApplicationContext(),uid,Toast.LENGTH_LONG).show();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_black_24dp);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(category.this,addproduct.class);
                i.putExtra("nn",uid);
                startActivity(i);
            }
        });
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        ListView lv = findViewById( R.id.category );
        ArrayList items = new ArrayList();
        items.add( "all products" );
        items.add( "Cars" );
        items.add( "Video Games" );
        items.add( "Phones" );
        items.add( "Watches" );
        items.add( "Computers" );

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (position==0)
                {
                    Intent intent = new Intent( getApplicationContext(),kids.class );
                    startActivity( intent );
                }
                if (position==1)
                {
                    Intent intent = new Intent( getApplicationContext(),cars.class );
                    startActivity( intent );
                }

                if (position==2)
                {
                    //same that video games but diffrent name in class
                    Intent intent = new Intent( getApplicationContext(),sports.class );
                    startActivity( intent );
                }
                if (position==3)
                {
                    Intent intent = new Intent( getApplicationContext(),phones.class );
                    startActivity( intent );
                }
                if (position==4)
                {
                    Intent intent = new Intent( getApplicationContext(),watches.class );
                    startActivity( intent );
                }
                if (position==5)
                {
                    Intent intent = new Intent( getApplicationContext(),computers.class );
                    startActivity( intent );
                }

            }
        } );
        EditText search = findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                arrayAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
