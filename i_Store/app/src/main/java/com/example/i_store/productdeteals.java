package com.example.i_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class productdeteals extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    TextView pname, sub, tvsuv, city, pmodel, pcolor, pdes, uname, pcat, trans, fu, st, brand, tvname, tvmode, tvcolor, tvtrans, tvfuel, tvdes, tvcat, tvbrand, tvstatus, wtype, tvwtype, cam, tvcam, size, tvsize, gen, tvgen, cpu, tvcpu, ram, tvram, gpu, tvgpu, storage, tvstorage;
    ImageView pimg;
    TextView price;
    Spinner s;
    String imguri;
    int id;
    DatabaseReference ref;
    String uid;
    RatingBar rate;
    Button submit;
    TextView phoneN;
    double user_rate;
    double rated_by;
    String Email;
    String user_id, product_id;
    Button remove, call;
    String phone_number;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdeteals);
        remove = (Button) findViewById(R.id.remove);

        call = (Button) findViewById(R.id.call);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Email = user.getEmail();
        final String pr = getIntent().getStringExtra("name");
        rate = (RatingBar) findViewById(R.id.star_rate);
        submit = (Button) findViewById(R.id.submit);
        phoneN = (TextView) findViewById(R.id.number);
        wtype = (TextView) findViewById(R.id.WT);
        tvwtype = (TextView) findViewById(R.id.TVWT);
        cam = (TextView) findViewById(R.id.camera);
        sub = (TextView) findViewById(R.id.SUBcat);
        tvsuv = (TextView) findViewById(R.id.TVSUB);
        price = (TextView) findViewById(R.id.price);

        tvcam = (TextView) findViewById(R.id.tvcamera);
        size = (TextView) findViewById(R.id.SIZE);
        tvsize = (TextView) findViewById(R.id.TVSIZE);
        ram = (TextView) findViewById(R.id.RAM);
        tvram = (TextView) findViewById(R.id.TVram);
        cpu = (TextView) findViewById(R.id.CPU);
        tvcpu = (TextView) findViewById(R.id.TVCpu);
        gen = (TextView) findViewById(R.id.GEN);
        tvgen = (TextView) findViewById(R.id.TVgen);
        gpu = (TextView) findViewById(R.id.GPU);
        tvgpu = (TextView) findViewById(R.id.TVGpu);
        storage = (TextView) findViewById(R.id.STorage);
        tvstorage = (TextView) findViewById(R.id.TVstorage);
        city = (TextView) findViewById(R.id.CIty);


        pimg = (ImageView) findViewById(R.id.primage);
        pname = (TextView) findViewById(R.id.prname);
        uname = (TextView) findViewById(R.id.user);
        pmodel = (TextView) findViewById(R.id.prmodel);
        pcat = (TextView) findViewById(R.id.prcategory);
        pcolor = (TextView) findViewById(R.id.pcolor);
        pdes = (TextView) findViewById(R.id.prdes);
        trans = (TextView) findViewById(R.id.tr);
        fu = (TextView) findViewById(R.id.fu);
        st = (TextView) findViewById(R.id.st);
        brand = (TextView) findViewById(R.id.bran);
        tvbrand = (TextView) findViewById(R.id.tvpbrand);
        tvcat = (TextView) findViewById(R.id.tvpcat);
        tvcolor = (TextView) findViewById(R.id.tvpcolor);
        tvname = (TextView) findViewById(R.id.tvpname);
        tvmode = (TextView) findViewById(R.id.tvpmodel);
        tvtrans = (TextView) findViewById(R.id.tvptrans);
        tvfuel = (TextView) findViewById(R.id.tvpfuel);
        tvstatus = (TextView) findViewById(R.id.tvpstatus);
        tvdes = (TextView) findViewById(R.id.tvpdes);


        ref = FirebaseDatabase.getInstance().getReference().child("database").child("products");
        FirebaseDatabase.getInstance().getReference().child("database").child("products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    product p = snapshot.getValue(product.class);
                    try {


                        if (p.getId().equals(pr)) {
                            city.setText(p.getCity());
                            if (p.getCategory().equals("Cars")) {
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
                                pdes.setVisibility(View.VISIBLE);
                                pname.setText(p.getName());
                                pmodel.setText(p.getModel());
                                pcat.setText(p.getCategory());
                                pcolor.setText(p.getColor());
                                trans.setText(p.getTrans());
                                fu.setText(p.getFuel());
                                brand.setText(p.getBrand());
                                st.setText(p.getStatus());
                                pdes.setText(p.getDesc());
                                product_id = p.getId();
                                sub.setText(p.getSubcategory());
                                uid = p.getUid();
                                imguri = p.getImage();
                                price.setText(p.getPrice());

                                price.setText(p.getPrice());
                                mStorageRef = FirebaseStorage.getInstance().getReference("images");
                                StorageReference sr = mStorageRef.child(imguri);
                                sr.getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                    @Override
                                    public void onSuccess(byte[] bytes) {
                                        Bitmap bt = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                        pimg.setImageBitmap(bt);

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                            } else if (p.getCategory().equals("Watches")) {

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
                                pimg.setVisibility(View.VISIBLE);
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
                                pdes.setVisibility(View.VISIBLE);

                                pname.setText(p.getName());
                                wtype.setText(p.getWtype());
                                pcat.setText(p.getCategory());
                                brand.setText(p.getBrand());
                                st.setText(p.getStatus());
                                product_id = p.getId();
                                price.setText(p.getPrice());

                                pdes.setText(p.getDesc());
                                pcolor.setText(p.getColor());
                                uid = p.getUid();
                                imguri = p.getImage();
                                StorageReference sr = null;


                                mStorageRef = FirebaseStorage.getInstance().getReference("images");

                                sr = mStorageRef.child(imguri);
                                sr.getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                    @Override
                                    public void onSuccess(byte[] bytes) {
                                        try {
                                            Bitmap bt = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                                            pimg.setImageBitmap(bt);

                                        } catch (Exception e) {
                                            Toast.makeText(productdeteals.this, "      " + e, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            } else if (p.getCategory().equals("Video Games")) {
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
                                pimg.setVisibility(View.VISIBLE);
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
                                product_id = p.getId();
                                pdes.setVisibility(View.VISIBLE);
                                price.setText(p.getPrice());

                                pname.setText(p.getName());
                                pmodel.setText(p.getModel());
                                pcat.setText(p.getCategory());
                                pcolor.setText(p.getColor());
                                brand.setText(p.getBrand());
                                st.setText(p.getStatus());
                                pdes.setText(p.getDesc());
                                uid = p.getUid();
                                sub.setText(p.getSubcategory());
                                mStorageRef = FirebaseStorage.getInstance().getReference("images");
                                StorageReference sr = mStorageRef.child(imguri);
                                sr.getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                    @Override
                                    public void onSuccess(byte[] bytes) {
                                        Bitmap bt = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                        pimg.setImageBitmap(bt);

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                            } else if (p.getCategory().equals("Phones")) {

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
                                pimg.setVisibility(View.VISIBLE);
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
                                product_id = p.getId();
                                pdes.setVisibility(View.VISIBLE);
                                price.setText(p.getPrice());

                                storage.setText(p.getStorage());
                                uid = p.getUid();
                                mStorageRef = FirebaseStorage.getInstance().getReference("images");
                                StorageReference sr = mStorageRef.child(imguri);
                                sr.getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                    @Override
                                    public void onSuccess(byte[] bytes) {
                                        Bitmap bt = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                        pimg.setImageBitmap(bt);

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });

                            } else if (p.getCategory().equals("Computers")) {

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
                                pimg.setVisibility(View.VISIBLE);
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
                                product_id = p.getId();
                                pdes.setVisibility(View.VISIBLE);
                                price.setText(p.getPrice());

                                gpu.setText(p.getGpu());
                                gen.setText(p.getGeneration());
                                uid = p.getUid();
                                mStorageRef = FirebaseStorage.getInstance().getReference("images");
                                StorageReference sr = mStorageRef.child(imguri);
                                sr.getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                    @Override
                                    public void onSuccess(byte[] bytes) {
                                        Bitmap bt = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                        pimg.setImageBitmap(bt);

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                    }
                                });

                            }

                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        ref = FirebaseDatabase.getInstance().getReference().child("database").child("users");
        FirebaseDatabase.getInstance().getReference().child("database").child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        register p = snapshot.getValue(register.class);
                        try {
                            if (p.getEmail().equals(Email)) {
                                user_id = p.getUid();


                            }
                        } catch (Exception e) {

                        }


                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        ref = FirebaseDatabase.getInstance().getReference().child("database").child("users");
        FirebaseDatabase.getInstance().getReference().child("database").child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        register p = snapshot.getValue(register.class);

                        try {
                            if (p.getUid().equals(uid)) {
                                uname.setText(p.getFirst_name() + " " + p.getLast_name());
                                phoneN.setText(p.getNum());
                                user_rate = Double.parseDouble(p.getRate());
                                rated_by = Integer.parseInt(p.getRated_by());
                                phone_number = p.getNum();


                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);

                        }
                        if (uid.equals(user_id)) {
                            remove.setVisibility(View.VISIBLE);
                            rate.setVisibility(View.GONE);
                            submit.setVisibility(View.GONE);
                        }else
                        {
                            call.setVisibility(View.VISIBLE);
                        }


                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void submit(View view) {
        double star_rate = (double) rate.getRating();
        rated_by += 1;
        try {

            user_rate = (user_rate + star_rate) / rated_by;
            myRef = FirebaseDatabase.getInstance().getReference("database").child("users").child(uid).child("rate");
            myRef.setValue(String.valueOf(user_rate));
            myRef = FirebaseDatabase.getInstance().getReference("database").child("users").child(uid).child("rated_by");
            myRef.setValue(String.valueOf(rated_by));

        } catch (Exception e) {
        }
        Toast.makeText(getApplicationContext(), star_rate + " stars", Toast.LENGTH_LONG).show();
    }

    public void remove1(View view) {
        try {
            myRef = FirebaseDatabase.getInstance().getReference("database").child("products").child(product_id);

            myRef.removeValue();
            Intent i = new Intent(getApplicationContext(), category.class);
            startActivity(i);
            Toast.makeText(this, "product romoved successfully", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "erorr" + e, Toast.LENGTH_SHORT).show();
        }

    }

    public void call(View view) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:123456789"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        startActivity(callIntent);
    }

    public void onclickimg(View view) {
        Intent i = new Intent(this , image.class);
        i.putExtra("image",imguri);
        startActivity(i);
        finish();
    }

    public void report(View view) {
        Intent i = new Intent(this, report.class);
        startActivity(i);
    }
}
