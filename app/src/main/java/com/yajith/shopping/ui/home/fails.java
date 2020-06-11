package com.yajith.shopping.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yajith.shopping.R;

import pl.droidsonroids.gif.GifImageView;

public class fails extends Fragment {
    GifImageView gifImageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View root=inflater.inflate(R.layout.activity_fails,container,false);
    gifImageView=root.findViewById(R.id.gif);
        new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            FragmentManager fragmentManager=getParentFragmentManager();
            FragmentTransaction ft=fragmentManager.beginTransaction();
            ft.replace(R.id.nav_host_fragment,new HomeFragment());
            ft.commit();
        }
    },2000);
        return root;
        }
}
