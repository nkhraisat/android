package com.example.i_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class productdeteals extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    TextView pname ,sub,tvsuv,city, pmodel,pcolor,pdes,uname,pcat,trans,fu,st,brand,tvname,tvmode,tvcolor,tvtrans,tvfuel,tvdes,tvcat,tvbrand,tvstatus,wtype,tvwtype,cam,tvcam,size,tvsize,gen,tvgen,cpu,tvcpu,ram,tvram,gpu,tvgpu,storage,tvstorage;
    ImageView pimg;
    Spinner s;
    Uri imguri;
    int id;
    DatabaseReference ref;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdeteals);
        final String pr = getIntent().getStringExtra("name");
        wtype=(TextView)findViewById(R.id.WT);
        tvwtype=(TextView)findViewById(R.id.TVWT);
        cam=(TextView)findViewById(R.id.camera);
        sub=(TextView)findViewById(R.id.SUBcat);
        tvsuv=(TextView)findViewById(R.id.TVSUB);

        tvcam=(TextView)findViewById(R.id.tvcamera);
        size=(TextView)findViewById(R.id.SIZE);
        tvsize=(TextView)findViewById(R.id.TVSIZE);
        ram=(TextView)findViewById(R.id.RAM);
        tvram=(TextView)findViewById(R.id.TVram);
        cpu=(TextView)findViewById(R.id.CPU);
        tvcpu=(TextView)findViewById(R.id.TVCpu);
        gen=(TextView)findViewById(R.id.GEN);
        tvgen=(TextView)findViewById(R.id.TVgen);
        gpu=(TextView)findViewById(R.id.GPU);
        tvgpu=(TextView)findViewById(R.id.TVGpu);
        storage=(TextView)findViewById(R.id.STorage);
        tvstorage=(TextView)findViewById(R.id.TVstorage);
        city=(TextView)findViewById(R.id.CIty);



        pimg=(ImageView)findViewById(R.id.primage);
         pname = (TextView) findViewById(R.id.prname);
         uname=(TextView) findViewById(R.id.user);
         pmodel = (TextView) findViewById(R.id.prmodel);
         pcat = (TextView) findViewById(R.id.prcategory);
         pcolor = (TextView) findViewById(R.id.pcolor);
         pdes = (TextView) findViewById(R.id.prdes);
         trans = (TextView) findViewById(R.id.tr);
         fu = (TextView) findViewById(R.id.fu);
         st = (TextView) findViewById(R.id.st);
        brand=(TextView)findViewById(R.id.bran);
         tvbrand = (TextView) findViewById(R.id.tvpbrand);
         tvcat = (TextView) findViewById(R.id.tvpcat);
         tvcolor = (TextView) findViewById(R.id.tvpcolor);
         tvname = (TextView) findViewById(R.id.tvpname);
         tvmode = (TextView) findViewById(R.id.tvpmodel);
         tvtrans = (TextView) findViewById(R.id.tvptrans);
         tvfuel = (TextView) findViewById(R.id.tvpfuel);
         tvstatus = (TextView) findViewById(R.id.tvpstatus);
         tvdes = (TextView) findViewById(R.id.tvpdes);
        Toast.makeText(getApplicationContext(),  pr+"",Toast.LENGTH_LONG).show();




        ref= FirebaseDatabase.getInstance().getReference().child("database").child("products");
        FirebaseDatabase.getInstance().getReference().child("database").child("products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    product p = snapshot.getValue(product.class);
                    try {


                        if (p.getId().equals(pr)) {
                            city.setText(p.getCity());
                            if(p.getCategory().equals("Cars"))
                            {
                                pname.setVisibility(View.VISIBLE);
                                pmodel.setVisibility(View.VISIBLE);
                                pcat.setVisibility(View.VISIBLE);
                                pcolor.setVisibility(View.VISIBLE);
                                trans.setVisibility(View.VISIBLE);
                                fu.setVisibility(View.VISIBLE);
                                brand.setVisibility(View.VISIBLE);
                                st.setVisibility(View.VISIBLE);
                                tvbrand.setVisibility(View.VISIBLE);
                                tvcat.setVisibility(View.VISIBLE);
                                tvcolor.setVisibility(View.VISIBLE);
                                tvdes.setVisibility(View.VISIBLE);
                                tvfuel.setVisibility(View.VISIBLE);
                                tvmode.setVisibility(View.VISIBLE);
                                pimg.setVisibility(View.VISIBLE);
                                tvname.setVisibility(View.VISIBLE);
                                tvstatus.setVisibility(View.VISIBLE);
                                tvtrans.setVisibility(View.VISIBLE);
                                wtype.setVisibility(View.GONE);
                                tvwtype.setVisibility(View.GONE);
                                cam.setVisibility(View.GONE);
                                tvcam.setVisibility(View.GONE);
                                size.setVisibility(View.GONE);
                                tvsize.setVisibility(View.GONE);
                                ram.setVisibility(View.GONE);
                                tvram.setVisibility(View.GONE);
                                cpu.setVisibility(View.GONE);
                                tvcpu.setVisibility(View.GONE);
                                gen.setVisibility(View.GONE);
                                tvgen.setVisibility(View.GONE);
                                gpu.setVisibility(View.GONE);
                                tvgpu.setVisibility(View.GONE);
                                storage.setVisibility(View.GONE);
                                tvstorage.setVisibility(View.GONE);
                                sub.setVisibility(View.VISIBLE);
                                tvsuv.setVisibility(View.VISIBLE);
                                pname.setText(p.getName());
                                pmodel.setText(p.getModel());
                                pcat.setText(p.getCategory());
                                pcolor.setText(p.getColor());
                                trans.setText(p.getTrans());
                                fu.setText(p.getFuel());
                                brand.setText(p.getBrand());
                                st.setText(p.getStatus());
                                pdes.setText(p.getDesc());
                                sub.setText(p.getSubcategory());
                                uid = p.getUid();
                                imguri = Uri.parse(p.getImage());
                                Picasso.get().load(imguri).into(pimg);
                            }
                            else if(p.getCategory().equals("Watches")){

                                pname.setVisibility(View.VISIBLE);
                                pmodel.setVisibility(View.GONE);
                                pcat.setVisibility(View.VISIBLE);
                                pcolor.setVisibility(View.VISIBLE);
                                trans.setVisibility(View.GONE);
                                fu.setVisibility(View.GONE);
                                brand.setVisibility(View.VISIBLE);
                                st.setVisibility(View.VISIBLE);
                                tvbrand.setVisibility(View.VISIBLE);
                                tvcat.setVisibility(View.VISIBLE);
                                tvcolor.setVisibility(View.VISIBLE);
                                tvdes.setVisibility(View.VISIBLE);
                                tvfuel.setVisibility(View.GONE);
                                tvmode.setVisibility(View.GONE);
                                pimg.setVisibility(View.GONE);
                                tvname.setVisibility(View.VISIBLE);
                                tvstatus.setVisibility(View.VISIBLE);
                                tvtrans.setVisibility(View.GONE);
                                wtype.setVisibility(View.VISIBLE);
                                tvwtype.setVisibility(View.VISIBLE);
                                cam.setVisibility(View.GONE);
                                tvcam.setVisibility(View.GONE);
                                size.setVisibility(View.GONE);
                                tvsize.setVisibility(View.GONE);
                                ram.setVisibility(View.GONE);
                                tvram.setVisibility(View.GONE);
                                cpu.setVisibility(View.GONE);
                                tvcpu.setVisibility(View.GONE);
                                gen.setVisibility(View.GONE);
                                tvgen.setVisibility(View.GONE);
                                gpu.setVisibility(View.GONE);
                                tvgpu.setVisibility(View.GONE);
                                storage.setVisibility(View.GONE);
                                tvstorage.setVisibility(View.GONE);
                                pname.setText(p.getName());
                                wtype.setText(p.getWtype());
                                pcat.setText(p.getCategory());
                                brand.setText(p.getBrand());
                                st.setText(p.getStatus());
                                pdes.setText(p.getDesc());
                                pcolor.setText(p.getColor());
                                uid = p.getUid();
                                imguri = Uri.parse(p.getImage());
                                Picasso.get().load(imguri).into(pimg);

                            }else if (p.getCategory().equals("Video Games"))
                            {
                                pname.setVisibility(View.VISIBLE);
                                pmodel.setVisibility(View.GONE);
                                pcat.setVisibility(View.VISIBLE);
                                pcolor.setVisibility(View.VISIBLE);
                                trans.setVisibility(View.GONE);
                                fu.setVisibility(View.GONE);
                                brand.setVisibility(View.VISIBLE);
                                st.setVisibility(View.VISIBLE);
                                tvbrand.setVisibility(View.VISIBLE);
                                tvcat.setVisibility(View.VISIBLE);
                                tvcolor.setVisibility(View.VISIBLE);
                                tvdes.setVisibility(View.VISIBLE);
                                tvfuel.setVisibility(View.GONE);
                                tvmode.setVisibility(View.GONE);
                                pimg.setVisibility(View.GONE);
                                tvname.setVisibility(View.VISIBLE);
                                tvstatus.setVisibility(View.VISIBLE);
                                tvtrans.setVisibility(View.GONE);
                                wtype.setVisibility(View.GONE);
                                tvwtype.setVisibility(View.GONE);
                                cam.setVisibility(View.GONE);
                                tvcam.setVisibility(View.GONE);
                                size.setVisibility(View.GONE);
                                tvsize.setVisibility(View.GONE);
                                ram.setVisibility(View.GONE);
                                tvram.setVisibility(View.GONE);
                                cpu.setVisibility(View.GONE);
                                tvcpu.setVisibility(View.GONE);
                                gen.setVisibility(View.GONE);
                                tvgen.setVisibility(View.GONE);
                                gpu.setVisibility(View.GONE);
                                tvgpu.setVisibility(View.GONE);
                                storage.setVisibility(View.VISIBLE);
                                tvstorage.setVisibility(View.VISIBLE);

                                pname.setText(p.getName());
                                pmodel.setText(p.getModel());
                                pcat.setText(p.getCategory());
                                pcolor.setText(p.getColor());
                                brand.setText(p.getBrand());
                                st.setText(p.getStatus());
                                pdes.setText(p.getDesc());
                                uid = p.getUid();
                                sub.setText(p.getSubcategory());
                                imguri = Uri.parse(p.getImage());
                                Picasso.get().load(imguri).into(pimg);
                            }
                            else if (p.getCategory().equals("Phones")){

                                pname.setVisibility(View.VISIBLE);
                                pmodel.setVisibility(View.VISIBLE);
                                pcat.setVisibility(View.VISIBLE);
                                pcolor.setVisibility(View.VISIBLE);
                                trans.setVisibility(View.GONE);
                                fu.setVisibility(View.GONE);
                                brand.setVisibility(View.VISIBLE);
                                st.setVisibility(View.VISIBLE);
                                tvbrand.setVisibility(View.VISIBLE);
                                tvcat.setVisibility(View.VISIBLE);
                                tvcolor.setVisibility(View.VISIBLE);
                                tvdes.setVisibility(View.VISIBLE);
                                tvfuel.setVisibility(View.GONE);
                                tvmode.setVisibility(View.VISIBLE);
                                pimg.setVisibility(View.GONE);
                                tvname.setVisibility(View.VISIBLE);
                                tvstatus.setVisibility(View.VISIBLE);
                                tvtrans.setVisibility(View.GONE);
                                wtype.setVisibility(View.VISIBLE);
                                tvwtype.setVisibility(View.VISIBLE);
                                cam.setVisibility(View.VISIBLE);
                                tvcam.setVisibility(View.VISIBLE);
                                size.setVisibility(View.VISIBLE);
                                tvsize.setVisibility(View.VISIBLE);
                                ram.setVisibility(View.GONE);
                                tvram.setVisibility(View.GONE);
                                cpu.setVisibility(View.GONE);
                                tvcpu.setVisibility(View.GONE);
                                gen.setVisibility(View.GONE);
                                tvgen.setVisibility(View.GONE);
                                gpu.setVisibility(View.GONE);
                                tvgpu.setVisibility(View.GONE);
                                storage.setVisibility(View.VISIBLE);
                                tvstorage.setVisibility(View.VISIBLE);
                                pname.setText(p.getName());
                                cam.setText(p.getCam());
                                pcat.setText(p.getCategory());
                                brand.setText(p.getBrand());
                                st.setText(p.getStatus());
                                pdes.setText(p.getDesc());
                                pcolor.setText(p.getColor());
                                size.setText(p.getSize());
                                storage.setText(p.getStorage());
                                uid = p.getUid();
                                imguri = Uri.parse(p.getImage());
                                Picasso.get().load(imguri).into(pimg);

                            }else if(p.getCategory().equals("Computers")){

                                pname.setVisibility(View.VISIBLE);
                                pmodel.setVisibility(View.GONE);
                                pcat.setVisibility(View.VISIBLE);
                                pcolor.setVisibility(View.GONE);
                                trans.setVisibility(View.GONE);
                                fu.setVisibility(View.GONE);
                                brand.setVisibility(View.VISIBLE);
                                st.setVisibility(View.VISIBLE);
                                tvbrand.setVisibility(View.VISIBLE);
                                tvcat.setVisibility(View.VISIBLE);
                                tvcolor.setVisibility(View.VISIBLE);
                                tvdes.setVisibility(View.VISIBLE);
                                tvfuel.setVisibility(View.GONE);
                                tvmode.setVisibility(View.GONE);
                                pimg.setVisibility(View.GONE);
                                tvname.setVisibility(View.VISIBLE);
                                tvstatus.setVisibility(View.VISIBLE);
                                tvtrans.setVisibility(View.GONE);
                                wtype.setVisibility(View.VISIBLE);
                                tvwtype.setVisibility(View.VISIBLE);
                                cam.setVisibility(View.GONE);
                                tvcam.setVisibility(View.GONE);
                                size.setVisibility(View.GONE);
                                tvsize.setVisibility(View.GONE);
                                ram.setVisibility(View.VISIBLE);
                                tvram.setVisibility(View.VISIBLE);
                                cpu.setVisibility(View.VISIBLE);
                                tvcpu.setVisibility(View.VISIBLE);
                                gen.setVisibility(View.VISIBLE);
                                tvgen.setVisibility(View.VISIBLE);
                                gpu.setVisibility(View.VISIBLE);
                                tvgpu.setVisibility(View.VISIBLE);
                                storage.setVisibility(View.VISIBLE);
                                tvstorage.setVisibility(View.GONE);
                                pname.setText(p.getName());
                                cpu.setText(p.getCpu());
                                pcat.setText(p.getCategory());
                                brand.setText(p.getBrand());
                                st.setText(p.getStatus());
                                pdes.setText(p.getDesc());
                                pcolor.setText(p.getColor());
                                ram.setText(p.getRam());
                                gpu.setText(p.getGpu());
                                gen.setText(p.getGeneration());
                                uid = p.getUid();
                                imguri = Uri.parse(p.getImage());
                                Picasso.get().load(imguri).into(pimg);

                            }

                        }
                    }catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),  e+"",Toast.LENGTH_LONG).show();

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref= FirebaseDatabase.getInstance().getReference().child("database").child("users");
        FirebaseDatabase.getInstance().getReference().child("database").child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              try {
                  for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                      register p =snapshot.getValue(register.class);
                     try{ if(p.getUid().equals(uid))
                      {
                          uname.setText(p.getFirst_name()+" "+p.getLast_name());
                      }}catch (Exception e)
                     {
                         Toast.makeText(getApplicationContext(),  e+"",Toast.LENGTH_LONG).show();

                     }





                  }
              }catch (Exception e)
              {
                  Toast.makeText(getApplicationContext(),e+"",Toast.LENGTH_SHORT).show();

              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
