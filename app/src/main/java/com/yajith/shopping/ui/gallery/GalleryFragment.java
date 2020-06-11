package com.yajith.shopping.ui.gallery;
import com.yajith.shopping.carddetails;
import com.yajith.shopping.databasehelper;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.yajith.shopping.Login;
import com.yajith.shopping.R;
import com.yajith.shopping.ui.home.HomeFragment;

public class GalleryFragment extends Fragment {
    String customer=String.valueOf(Login.customerid);
    TextView textView1,textView2,textView3;
    Button button1,button2;
    ListView listView= HomeFragment.listView;
    databasehelper databasehelper;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        textView1=root.findViewById(R.id.name);
        textView2=root.findViewById(R.id.email);
        textView3=root.findViewById(R.id.mobile);
        button1=root.findViewById(R.id.buttonview);
        databasehelper=new databasehelper(getContext());
        button2=root.findViewById(R.id.buttonCard);
        listView.setVisibility(View.INVISIBLE);
        String name=databasehelper.profile(customer);
        String email=databasehelper.email(customer);
        String mobile=databasehelper.mobileGET(customer);
        textView1.setText(name);
        textView2.setText(email);
        textView3.setText(mobile);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getParentFragmentManager();
                FragmentTransaction ft=fragmentManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment,new view_orders());
                ft.commit();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getParentFragmentManager();
                FragmentTransaction ft=fragmentManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment,new view_card());
                ft.commit();
            }
        });
        return root;
    }
}
