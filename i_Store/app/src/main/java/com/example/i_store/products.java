package com.example.i_store;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class products extends Fragment {
    DatabaseReference ref;
    ArrayList<String> arr;
    ArrayList<String> in;
    ArrayAdapter<String> arrayAdapter;
    ListView lv;
    String uid;
    EditText et;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_products, container, false);
         et = root.findViewById(R.id.search1);
        lv = root.findViewById( R.id.myproduct_listview );
        arr=new ArrayList<>();
        in=new ArrayList<>();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Toast.makeText(getContext(), user.getEmail(), Toast.LENGTH_SHORT).show();
        ref= FirebaseDatabase.getInstance().getReference().child("database").child("users");
        FirebaseDatabase.getInstance().getReference().child("database").child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    register r = snapshot.getValue(register.class);
                    if(r.getEmail().equals(user.getEmail()))
                    { uid=r.getUid();}
                    }}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});
        try {


            arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, arr);
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
        }catch (Exception e){
        }
            Toast.makeText(getContext() , uid, Toast.LENGTH_SHORT).show();
            ref= FirebaseDatabase.getInstance().getReference().child("database").child("products");
            FirebaseDatabase.getInstance().getReference().child("database").child("products").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        product p =snapshot.getValue(product.class);
                        if(p.getUid().equals(uid)) {
                            arr.add("product name : " + p.getName() + "\n category :" + p.getCategory());
                            in.add(p.getId());
                        }
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent i = new Intent (getContext(), productdeteals.class);
                                i.putExtra("name",in.get(position));
                                startActivity(i);
                            }
                        });
                    }
                    if (arr.isEmpty()) {
                        Toast.makeText(getContext(), "no item to show", Toast.LENGTH_SHORT).show();
                    }
                    lv.setAdapter(arrayAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        return root;
    }}

