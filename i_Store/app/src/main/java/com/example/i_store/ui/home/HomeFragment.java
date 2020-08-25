package com.example.i_store.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.i_store.R;
import com.example.i_store.cars;
import com.example.i_store.computers;
import com.example.i_store.kids;
import com.example.i_store.phones;
import com.example.i_store.videogames;
import com.example.i_store.watches;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ArrayAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final ListView lv = root.findViewById(R.id.categorylistview);
        final EditText et = root.findViewById(R.id.search);
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
        ArrayList items = new ArrayList();
        items.add("All products");
        items.add("Cars");
        items.add("Video games");
        items.add("Phones");
        items.add("Watches");
        items.add("Computers");
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,items);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0)
                {
                    Intent intent = new Intent(getActivity() , kids.class);
                    startActivity(intent);
                }
                if (position==1)
                {
                    Intent intent = new Intent(getActivity(), cars.class);
                    startActivity(intent);
                }
                if (position==2)
                {
                    Intent intent = new Intent(getActivity() , videogames.class);
                    startActivity(intent);
                }
                if (position==3)
                {
                    Intent intent = new Intent(getActivity(), phones.class);
                    startActivity(intent);
                }
                if (position==4)
                {
                    Intent intent = new Intent(getActivity(), watches.class);
                    startActivity(intent);
                }
                if (position==5)
                {
                    Intent intent = new Intent(getActivity(), computers.class);
                    startActivity(intent);
                }
            }
        });
        return root;
    }
}
