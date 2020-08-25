package com.example.i_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class cars extends AppCompatActivity {
    DatabaseReference ref;
     ArrayList<String> arr;
     ArrayList<String> in;
    ArrayAdapter<String> arrayAdapter;
    ListView lv;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cars );
         et = findViewById(R.id.search);
         lv = findViewById( R.id.carslistview );
        arr=new ArrayList<>();
        in=new ArrayList<>();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
      try{  et.addTextChangedListener(new TextWatcher() {
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
        });}catch(Exception e){}

        ref= FirebaseDatabase.getInstance().getReference().child("database").child("products");
        FirebaseDatabase.getInstance().getReference().child("database").child("products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    product p =snapshot.getValue(product.class);
                    if(p.getCategory().equals("Cars")) {
                        arr.add("product name : " + p.getName() + "\n category :" + p.getCategory());
                        in.add(p.getId());
                    }
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent (cars.this, productdeteals.class);
                            i.putExtra("name",in.get(position));
                            startActivity(i);
                        }
                    });
                }
                lv.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
