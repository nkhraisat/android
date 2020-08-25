package com.example.i_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class watches extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<String> arr;
    ArrayList<String> in;

    ArrayAdapter<String> arrayAdapter;
    ListView lv;
    private StorageReference mStorageRef;
    String imgri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watches);

        lv = findViewById(R.id.watcheslistview);
        arr = new ArrayList<>();
        in = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);

        EditText et = findViewById(R.id.search);
        et.addTextChangedListener(new TextWatcher() {
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

        ref = FirebaseDatabase.getInstance().getReference().child("database").child("products");
        FirebaseDatabase.getInstance().getReference().child("database").child("products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    product p = snapshot.getValue(product.class);
                    if (p.getCategory().equals("Watches")) {
                        arr.add("product name : " + p.getName() + "\n category :" + p.getCategory());

                        in.add(p.getId());
                    }
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(getApplicationContext(), productdeteals.class);
                            i.putExtra("name", in.get(position));
                            startActivity(i);
                        }
                    });
                }
                lv.setAdapter(arrayAdapter);
                Toast.makeText(getApplicationContext(), "data inserted" + in, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
