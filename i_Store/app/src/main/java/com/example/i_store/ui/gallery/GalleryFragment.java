package com.example.i_store.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.i_store.R;
import com.example.i_store.product;
import com.example.i_store.register;
import com.example.i_store.update_password;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
EditText fn,ln,em,ph;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Button b,b1,update_pass;
String uid;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
            galleryViewModel =
                    ViewModelProviders.of(this).get(GalleryViewModel.class);
            View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        try {

            b = (Button) root.findViewById(R.id.done);
            b1 = (Button) root.findViewById(R.id.edit_profile);
            update_pass = (Button) root.findViewById(R.id.update_pass);
            update_pass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getContext(), update_password.class);
                    startActivity(i);

                }
            });
            fn = (EditText) root.findViewById(R.id.fn);
            ln = (EditText) root.findViewById(R.id.ln);
            em = (EditText) root.findViewById(R.id.em);
            ph = (EditText) root.findViewById(R.id.ph);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {


                        fn.setFocusable(true);
                        ln.setFocusable(true);
                        ph.setFocusable(true);
                        b.setVisibility(View.VISIBLE);
                        b1.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "you can edit your profile now", Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        Toast.makeText(getContext(), e + "", Toast.LENGTH_LONG).show();
                    }
                }


            });
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    myRef = FirebaseDatabase.getInstance().getReference("database").child("users").child(uid).child("first_name");
                    myRef.setValue(String.valueOf(fn.getText().toString()));
                    myRef = FirebaseDatabase.getInstance().getReference("database").child("users").child(uid).child("last_name");
                    myRef.setValue(String.valueOf(ln.getText().toString()));
                    myRef = FirebaseDatabase.getInstance().getReference("database").child("users").child(uid).child("num");
                    myRef.setValue(String.valueOf(ph.getText().toString()));
                    b.setVisibility(View.GONE);
                    b1.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "your profile is updated successfilly", Toast.LENGTH_LONG).show();


                }

            });
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            user.getEmail();
            myRef = FirebaseDatabase.getInstance().getReference().child("database").child("users");
            FirebaseDatabase.getInstance().getReference().child("database").child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        register r = snapshot.getValue(register.class);
                        if (r.getEmail().equals(user.getEmail())) {
                            fn.setText(r.getFirst_name());
                            ln.setText(r.getLast_name());
                            em.setText(r.getEmail());
                            ph.setText(r.getNum());
                            uid = r.getUid();

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }catch(Exception e){}
        return root;
    }


}


