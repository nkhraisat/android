package com.example.i_store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class videogames extends AppCompatActivity {
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videogames);
        ListView lv = findViewById(R.id.videogameslist);
        ArrayList items = new ArrayList();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,items);
        lv.setAdapter(adapter);
        EditText et = findViewById(R.id.search);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
