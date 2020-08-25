package com.example.i_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class phones extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<String> arr;
    ArrayList<String> in;
    ArrayAdapter<String> arrayAdapter;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_phones );
        lv = findViewById( R.id.listview);
        arr=new ArrayList<>();
        in=new ArrayList<>();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);

        ref= FirebaseDatabase.getInstance().getReference().child("database").child("products");
        FirebaseDatabase.getInstance().getReference().child("database").child("products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    product p =snapshot.getValue(product.class);
                    if(p.getCategory().equals("Phones"))
                        arr.add("product name : "+p.getName()+ "\n category :"+p.getCategory());
                    in.add(p.getId());

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent (getApplicationContext(), productdeteals.class);
                            i.putExtra("name",in.get(position));
                            startActivity(i);
                        }
                    });
                }
                lv.setAdapter(arrayAdapter);
                Toast.makeText(getApplicationContext(),"data inserted"+in,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
