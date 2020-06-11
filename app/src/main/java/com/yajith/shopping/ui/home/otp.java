package com.yajith.shopping.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.yajith.shopping.R;
import com.yajith.shopping.carddetails;
import com.yajith.shopping.databasehelper;
import com.yajith.shopping.progress;
import com.yajith.shopping.ui.gallery.GalleryFragment;

public class otp extends Fragment {
    databasehelper databasehelper;
    EditText editText;
    Button button;
    String userotp=null;
    String otp= carddetails.otp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_otp,container,false);
        databasehelper =new databasehelper(getContext());
        editText=root.findViewById(R.id.otp);
        button=root.findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userotp=editText.getText().toString();
                if(userotp.equals(String.valueOf(otp))) {
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new progress());
                    ft.commit();
                }
                else
                {
                    Toast.makeText(getContext(), "Wrong OTP!", Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new fails());
                    ft.commit();
                }
            }
        });

        return root;
    }
}