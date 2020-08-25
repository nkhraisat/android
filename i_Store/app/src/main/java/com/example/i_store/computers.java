package com.example.i_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class computers extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<String> arr;
    ArrayAdapter<String> arrayAdapter;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_computers );
        lv = findViewById( R.id.computerslistview );
        arr=new ArrayList<>();

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

        ref= FirebaseDatabase.getInstance().getReference().child("database").child("products");
        FirebaseDatabase.getInstance().getReference().child("database").child("products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    product p =snapshot.getValue(product.class);
                    if(p.getCategory().equals("Computers"))
                        arr.add("product name : "+p.getName()+ "\n category :"+p.getCategory());
                }
                lv.setAdapter(arrayAdapter);
                if(arr.isEmpty())
                    Toast.makeText(getApplicationContext(),"there is no item to show ",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        lv.setAdapter(arrayAdapter);
    }


}

