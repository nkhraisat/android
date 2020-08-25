package com.example.i_store;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class addproduct extends AppCompatActivity {
    private StorageReference mStorageRef;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText pname , pmodel,pcolor,pdes,storage,subcat,price,brand,city,cam,size,ram,gpu;
    Spinner s,stat,tran,fue,wtype,cpu,gen;
    TextView tvs,tvname,tvprice,tvtrans,tvfuel,tvsub,tvdesc,tvcolor,tvbrand,tvmodel,tvstorage,tvcam,tvsize,tvwtype,tvram,tvcpu,tvgpu,tvgen;
    DatabaseReference ref;
    String uid;
    int pid;
    final static int p=1;
    Uri imaguri;
    ImageView iv;
    String Imageurl;
    String email;
String imageName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);

        Imageurl="";
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("database").child("products");
        ram=(EditText)findViewById(R.id.ram);
        gpu=(EditText)findViewById(R.id.gpu);
        cpu=(Spinner)findViewById(R.id.Cpu);
        gen=(Spinner)findViewById(R.id.gen);
        tvram=(TextView)findViewById(R.id.tvram);
        tvcpu=(TextView)findViewById(R.id.tvcpu);
        tvgpu=(TextView)findViewById(R.id.tvgpu);
        tvgen=(TextView)findViewById(R.id.tvgen);

        pname = (EditText) findViewById(R.id.prname);
        cam=(EditText)findViewById(R.id.cam);
        tvcam=(TextView)findViewById(R.id.tvcam);
        size=(EditText)findViewById(R.id.size);
        tvsize=(TextView)findViewById(R.id.tvsize);
        wtype=(Spinner)findViewById(R.id.wtype);
        tvwtype=(TextView)findViewById(R.id.tvwtype);

        price = (EditText) findViewById(R.id.price);
        pmodel = (EditText) findViewById(R.id.model);
        tvs = (TextView) findViewById(R.id.tvstatus);
        tvbrand = (TextView) findViewById(R.id.tvbrand);
        tvcolor = (TextView) findViewById(R.id.tvcolor);
        tvdesc = (TextView) findViewById(R.id.tvdesc);
        tvfuel = (TextView) findViewById(R.id.tvfuel);
        tvmodel = (TextView) findViewById(R.id.tvmodel);
        tvname = (TextView) findViewById(R.id.tvname);
        tvprice = (TextView) findViewById(R.id.tvprice);
        tvstorage = (TextView) findViewById(R.id.tvstorage);
        tvsub = (TextView) findViewById(R.id.tvsubcat);
        tvtrans = (TextView) findViewById(R.id.tvtrans);
        city = (EditText) findViewById(R.id.city);

        pcolor = (EditText) findViewById(R.id.pcolor);
        pdes = (EditText) findViewById(R.id.desc);
        mAuth = FirebaseAuth.getInstance();
        iv = (ImageView) findViewById(R.id.photo);
        storage = (EditText) findViewById(R.id.storage);
        brand = (EditText) findViewById(R.id.brand);
        subcat = (EditText) findViewById(R.id.subcategory);

        uid = getIntent().getStringExtra("nn");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
         email=user.getEmail();

        ArrayList items = new ArrayList();

        items.add("Cars");
        items.add("video Games");
        items.add("Phones");
        items.add("Watches");

        items.add("Computers");
        ArrayList status = new ArrayList();

        status.add("used");
        status.add("new");

        ArrayList fuel = new ArrayList();

        fuel.add("fuel");
        fuel.add("hybrid");
        fuel.add("Electricity");

        ArrayList trans = new ArrayList();

        trans.add("Automatic");
        trans.add("manual");

        ArrayList watch = new ArrayList();

        watch.add("Analog");
        watch.add("Digital");
        ArrayList prc = new ArrayList();

        prc.add("core-i7");
        prc.add("core-i5");
        prc.add("core-i3");
        prc.add("Celeron");

        ArrayAdapter<String> pra = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, prc);
        pra.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cpu.setAdapter(pra);
        ArrayList gi = new ArrayList();

        gi.add("1st");
        gi.add("2nd");
        gi.add("3rd");
        gi.add("5th");
        gi.add("6th");
        gi.add("7th");
        gi.add("8th");
        gi.add("9th");
        gi.add("10th");

        ArrayAdapter<String> asd = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, gi);
        asd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gen.setAdapter(asd);
        ArrayAdapter<String> wty = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, watch);
        wty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wtype.setAdapter(wty);
        s= (Spinner)findViewById(R.id.sp);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        stat = (Spinner) findViewById(R.id.status);
        ArrayAdapter<String> a = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, status);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stat.setAdapter(a);


        tran = (Spinner) findViewById(R.id.trans);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, trans);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tran.setAdapter(ad);
        fue = (Spinner) findViewById(R.id.fuel);

        ArrayAdapter<String> ada = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, fuel);
        ada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fue.setAdapter(ada);
        ref = FirebaseDatabase.getInstance().getReference().child("database").child("products");
        FirebaseDatabase.getInstance().getReference().child("database").child("products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    product p = snapshot.getValue(product.class);
                    pid = 0;
                    if (Integer.parseInt(p.getId()) >= pid)
                        pid = Integer.parseInt(p.getId()) + 1;


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //getting user id
        FirebaseDatabase.getInstance().getReference().child("database").child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    register p = snapshot.getValue(register.class);

                    if (email.equals(p.getEmail()))
                    {uid=p.getUid();}


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //visabillity
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position==0)
                {
                    //car
                    try {


                        tran.setVisibility(View.VISIBLE);
                        brand.setVisibility(View.VISIBLE);
                        pname.setVisibility(View.VISIBLE);
                        subcat.setVisibility(View.VISIBLE);
                        fue.setVisibility(View.VISIBLE);
                        pmodel.setVisibility(View.VISIBLE);
                        price.setVisibility(View.VISIBLE);
                        stat.setVisibility(View.VISIBLE);
                        storage.setVisibility(View.GONE);
                        pcolor.setVisibility(View.VISIBLE);
                        pdes.setVisibility(View.VISIBLE);
                        tvbrand.setVisibility(View.VISIBLE);
                        tvtrans.setVisibility(View.VISIBLE);
                        tvname.setVisibility(View.VISIBLE);
                        tvsub.setVisibility(View.VISIBLE);
                        tvfuel.setVisibility(View.VISIBLE);
                        tvcolor.setVisibility(View.VISIBLE);
                        tvmodel.setVisibility(View.VISIBLE);
                        tvprice.setVisibility(View.VISIBLE);
                        tvstorage.setVisibility(View.GONE);
                        tvs.setVisibility(View.VISIBLE);
                        tvdesc.setVisibility(View.VISIBLE);
                        cam.setVisibility(View.GONE);
                        size.setVisibility(View.GONE);
                        tvcam.setVisibility(View.GONE);
                        tvsize.setVisibility(View.GONE);

                        wtype.setVisibility(View.GONE);
                        tvwtype.setVisibility(View.GONE);
                        ram.setVisibility(View.GONE);
                        tvram.setVisibility(View.GONE);
                        cpu.setVisibility(View.GONE);
                        tvcpu.setVisibility(View.GONE);
                        gen.setVisibility(View.GONE);
                        tvgen.setVisibility(View.GONE);
                        gpu.setVisibility(View.GONE);
                        tvgpu.setVisibility(View.GONE);
                    }catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();
                    }

                } else if (position==1)
                {
                    //video games


                        tran.setVisibility(View.GONE);
                        brand.setVisibility(View.VISIBLE);
                        pname.setVisibility(View.VISIBLE);
                        subcat.setVisibility(View.VISIBLE);
                        fue.setVisibility(View.GONE);
                        pmodel.setVisibility(View.GONE);
                        price.setVisibility(View.VISIBLE);
                        stat.setVisibility(View.VISIBLE);
                        storage.setVisibility(View.VISIBLE);
                        pcolor.setVisibility(View.VISIBLE);
                        pdes.setVisibility(View.VISIBLE);
                        tvbrand.setVisibility(View.VISIBLE);
                        tvtrans.setVisibility(View.GONE);
                        tvname.setVisibility(View.VISIBLE);
                        tvsub.setVisibility(View.VISIBLE);
                        tvfuel.setVisibility(View.GONE);
                        tvcolor.setVisibility(View.VISIBLE);
                        tvmodel.setVisibility(View.GONE);
                        tvprice.setVisibility(View.VISIBLE);
                        tvstorage.setVisibility(View.VISIBLE);
                        tvs.setVisibility(View.VISIBLE);
                        tvdesc.setVisibility(View.VISIBLE);
                        cam.setVisibility(View.GONE);
                        size.setVisibility(View.GONE);
                        tvcam.setVisibility(View.GONE);
                        tvsize.setVisibility(View.GONE);

                        wtype.setVisibility(View.GONE);
                        tvwtype.setVisibility(View.GONE);
                    ram.setVisibility(View.GONE);
                    tvram.setVisibility(View.GONE);
                    cpu.setVisibility(View.GONE);
                    tvcpu.setVisibility(View.GONE);
                    gen.setVisibility(View.GONE);
                    tvgen.setVisibility(View.GONE);
                    gpu.setVisibility(View.GONE);
                    tvgpu.setVisibility(View.GONE);


                    }


          else if (position==2)
                {
                    //phones
                    try {


                        tran.setVisibility(View.GONE);
                        brand.setVisibility(View.VISIBLE);
                        pname.setVisibility(View.VISIBLE);
                        subcat.setVisibility(View.VISIBLE);
                        fue.setVisibility(View.GONE);
                        pmodel.setVisibility(View.VISIBLE);
                        price.setVisibility(View.VISIBLE);
                        stat.setVisibility(View.VISIBLE);
                        storage.setVisibility(View.VISIBLE);
                        pcolor.setVisibility(View.VISIBLE);
                        pdes.setVisibility(View.VISIBLE);
                        tvbrand.setVisibility(View.VISIBLE);
                        tvtrans.setVisibility(View.GONE);
                        tvname.setVisibility(View.VISIBLE);
                        tvsub.setVisibility(View.VISIBLE);
                        tvfuel.setVisibility(View.GONE);
                        tvcolor.setVisibility(View.VISIBLE);
                        tvmodel.setVisibility(View.VISIBLE);
                        tvprice.setVisibility(View.VISIBLE);
                        tvstorage.setVisibility(View.VISIBLE);
                        tvs.setVisibility(View.VISIBLE);
                        tvdesc.setVisibility(View.VISIBLE);
                        cam.setVisibility(View.VISIBLE);
                        size.setVisibility(View.VISIBLE);
                        tvcam.setVisibility(View.VISIBLE);
                        tvsize.setVisibility(View.VISIBLE);
                        wtype.setVisibility(View.GONE);
                        tvwtype.setVisibility(View.GONE);
                        ram.setVisibility(View.GONE);
                        tvram.setVisibility(View.GONE);
                        cpu.setVisibility(View.GONE);
                        tvcpu.setVisibility(View.GONE);
                        gen.setVisibility(View.GONE);
                        tvgen.setVisibility(View.GONE);
                        gpu.setVisibility(View.GONE);
                        tvgpu.setVisibility(View.GONE);
                    }catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();
                    }
                }else if (position==3) {
                    //watches

                  try {
                      tran.setVisibility(View.GONE);

                      brand.setVisibility(View.VISIBLE);
                      pname.setVisibility(View.VISIBLE);
                      subcat.setVisibility(View.GONE);
                      fue.setVisibility(View.GONE);
                      pmodel.setVisibility(View.GONE);
                      price.setVisibility(View.VISIBLE);
                      stat.setVisibility(View.VISIBLE);
                      storage.setVisibility(View.GONE);
                      pcolor.setVisibility(View.VISIBLE);
                      pdes.setVisibility(View.VISIBLE);
                      tvbrand.setVisibility(View.VISIBLE);
                      tvtrans.setVisibility(View.GONE);
                      tvname.setVisibility(View.VISIBLE);
                      tvsub.setVisibility(View.GONE);
                      tvfuel.setVisibility(View.GONE);
                      tvcolor.setVisibility(View.VISIBLE);
                      tvmodel.setVisibility(View.GONE);
                      tvprice.setVisibility(View.VISIBLE);
                      tvstorage.setVisibility(View.GONE);
                      tvs.setVisibility(View.VISIBLE);
                      tvdesc.setVisibility(View.VISIBLE);
                      cam.setVisibility(View.GONE);
                      size.setVisibility(View.GONE);
                      tvcam.setVisibility(View.GONE);
                      tvsize.setVisibility(View.GONE);
                      wtype.setVisibility(View.VISIBLE);
                      tvwtype.setVisibility(View.VISIBLE);
                      ram.setVisibility(View.GONE);
                      tvram.setVisibility(View.GONE);
                      cpu.setVisibility(View.GONE);
                      tvcpu.setVisibility(View.GONE);
                      gen.setVisibility(View.GONE);
                      tvgen.setVisibility(View.GONE);
                      gpu.setVisibility(View.GONE);
                      tvgpu.setVisibility(View.GONE);
                  }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();
                }

            }
          else if (position==4) {
                    //watches
try {


                    tran.setVisibility(View.GONE);
                    brand.setVisibility(View.VISIBLE);
                    pname.setVisibility(View.VISIBLE);
                    subcat.setVisibility(View.GONE);
                    fue.setVisibility(View.GONE);
                    pmodel.setVisibility(View.GONE);
                    price.setVisibility(View.VISIBLE);
                    stat.setVisibility(View.VISIBLE);
                    storage.setVisibility(View.VISIBLE);
                    pcolor.setVisibility(View.GONE);
                    pdes.setVisibility(View.VISIBLE);
                    tvbrand.setVisibility(View.VISIBLE);
                    tvtrans.setVisibility(View.GONE);
                    tvname.setVisibility(View.VISIBLE);
                    tvsub.setVisibility(View.GONE);
                    tvfuel.setVisibility(View.GONE);
                    tvcolor.setVisibility(View.GONE);
                    tvmodel.setVisibility(View.GONE);
                    tvprice.setVisibility(View.VISIBLE);
                    tvstorage.setVisibility(View.VISIBLE);
                    tvs.setVisibility(View.VISIBLE);
                    tvdesc.setVisibility(View.VISIBLE);
                    cam.setVisibility(View.GONE);
                    size.setVisibility(View.VISIBLE);
                    tvcam.setVisibility(View.GONE);
                    tvsize.setVisibility(View.VISIBLE);
                    wtype.setVisibility(View.GONE);
                    tvwtype.setVisibility(View.GONE);
                    ram.setVisibility(View.VISIBLE);
                    tvram.setVisibility(View.VISIBLE);
                    cpu.setVisibility(View.VISIBLE);
                    tvcpu.setVisibility(View.VISIBLE);
                    gen.setVisibility(View.VISIBLE);
                    tvgen.setVisibility(View.VISIBLE);
                    gpu.setVisibility(View.VISIBLE);
                    tvgpu.setVisibility(View.VISIBLE);
                    }catch (Exception e) {
    Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();
}


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



    }
    public void add(View view) {
        String name , model, color,des;
        name= pname.getText().toString();
        model=pmodel.getText().toString();
        color=pcolor.getText().toString();
        des=pdes.getText().toString();
        String cat =s.getSelectedItem().toString();
upload();

            if (s.getSelectedItem().equals("Cars")) {
                if(name.isEmpty())
                {
                    pname.setError(" please fill the product name !");
                }
                if(model.isEmpty())
                {
                    pmodel.setError(" please fill the model !");
                }
                if(city.getText().toString().isEmpty())
                {
                    city.setError(" please fill the  city !");
                }
                if(brand.getText().toString().isEmpty())
                {
                    brand.setError(" please fill the product brand !");
                }
                if(subcat.getText().toString().isEmpty())
                {
                    subcat.setError(" please fill the product name !");
                }
                if(price.getText().toString().isEmpty())
                {
                    price.setError(" please fill the product priec !");
                }
                if(!name.isEmpty()&&!model.isEmpty()&&!city.getText().toString().isEmpty()&&!brand.getText().toString().isEmpty()&&!price.getText().toString().isEmpty()&&!subcat.getText().toString().isEmpty()) {
                    product p = new product(cat, name, model, color, des,  city.getText().toString(),uid, brand.getText().toString(), price.getText().toString(), stat.getSelectedItem().toString(), subcat.getText().toString(), tran.getSelectedItem().toString(), fue.getSelectedItem().toString(), imageName, String.valueOf(pid));
                    myRef.child(String.valueOf(pid)).setValue(p);
                    Toast.makeText(getApplicationContext(),"product added successfully",Toast.LENGTH_LONG).show();
                    Intent i= new Intent(getApplicationContext(),category.class);
                    startActivity(i);
                    finish();
                }
            }
            else if (s.getSelectedItem().equals("Video games"))
            {
                product p = new product( cat,  name,  color,  des,  uid,  city.getText().toString(),  brand.getText().toString(),  storage.getText().toString(),  price.getText().toString(),  stat.getSelectedItem().toString(),  subcat.getText().toString(),  imageName,  String.valueOf(pid)) ;
                myRef.child(String.valueOf(pid)).setValue(p);
                Toast.makeText(getApplicationContext(),"product added successfully",Toast.LENGTH_LONG).show();
                Intent i= new Intent(getApplicationContext(),category.class);
                startActivity(i);
                finish();


            }else if (s.getSelectedItem().equals("Phones"))
            {
                product p = new product( cat,  name,  model,  color,  des,  uid,  city.getText().toString(),  brand.getText().toString(),  storage.getText().toString(),  price.getText().toString(),  stat.getSelectedItem().toString(),  subcat.getText().toString(),  imageName,  size.getText().toString(),  cam.getText().toString(), String.valueOf( pid),ram.getText().toString());
                myRef.child(String.valueOf(pid)).setValue(p);
                Toast.makeText(getApplicationContext(),"product added successfully",Toast.LENGTH_LONG).show();
                Intent i= new Intent(getApplicationContext(),category.class);
                startActivity(i);
                finish();

            }else if (s.getSelectedItem().equals("Watches"))
            {
                product p = new product( cat,  name,  color,  des,  uid,  city.getText().toString(),  brand.getText().toString(),  price.getText().toString(),  stat.getSelectedItem().toString(), imageName,  wtype.getSelectedItem().toString(), String.valueOf(pid));
                myRef.child(String.valueOf(pid)).setValue(p);
                Toast.makeText(getApplicationContext(),"product added successfully",Toast.LENGTH_LONG).show();
                Intent i= new Intent(getApplicationContext(),category.class);
                startActivity(i);
                finish();

            }else if (s.getSelectedItem().equals("Computers"))

            {
                product p = new product( cat,  name,  des, uid,  city.getText().toString(),  brand.getText().toString(),  storage.getText().toString(),  price.getText().toString(),  stat.getSelectedItem().toString(),  imageName,  size.getText().toString(),  ram.getText().toString(),  cpu.getSelectedItem().toString(),  gpu.getText().toString(),  gen.getSelectedItem().toString(), String.valueOf(pid));
                myRef.child(String.valueOf(pid)).setValue(p);
                Toast.makeText(getApplicationContext(),"product added successfully",Toast.LENGTH_LONG).show();
                Intent i= new Intent(getApplicationContext(),category.class);
                startActivity(i);
                finish();


            }

        }




    public void chose(View view) {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,p);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == p && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imaguri = data.getData();
            Picasso.get().load(imaguri).into(iv);

        }
    }
    private String getExteintion(Uri uri)
    {
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(cr.getType(uri));
        }
        private void upload()
        {
            if(imaguri!=null){
                mStorageRef = FirebaseStorage.getInstance().getReference("images");
                imageName=System.currentTimeMillis()+"."+getExteintion(imaguri);
               StorageReference ref = mStorageRef.child(imageName);
                ref.putFile(imaguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Imageurl =taskSnapshot.getUploadSessionUri().toString().trim();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"error while uploading image",Toast.LENGTH_SHORT).show();

                    }
                });

        }
}
}