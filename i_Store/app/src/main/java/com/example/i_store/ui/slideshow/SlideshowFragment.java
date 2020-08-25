package com.example.i_store.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.i_store.MainActivity;
import com.example.i_store.R;
import com.google.firebase.auth.FirebaseAuth;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
      try {
          mAuth.getInstance()
                  .signOut();

        Intent i = new Intent(getContext(), MainActivity.class);
          startActivity(i);

        }catch (Exception e){
          Toast.makeText(getContext(),e+"",Toast.LENGTH_SHORT).show();
      }


        return root;
    }
}
