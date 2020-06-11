package com.yajith.shopping.ui.home;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.yajith.shopping.Login;
import com.yajith.shopping.R;
import com.yajith.shopping.databasehelper;

public class item_view extends Fragment {
    int customerid= Login.customerid;
    com.yajith.shopping.databasehelper databasehelper;
    String name=HomeFragment.nam,color=HomeFragment.colo,size=HomeFragment.siz,price=HomeFragment.pric;
    Integer image=HomeFragment.im;
    int id=HomeFragment.id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_phone,container,false);
        ImageView imageView=root.findViewById(R.id.image);
        TextView Name=root.findViewById(R.id.name);
        TextView Color=root.findViewById(R.id.color);
        TextView Size=root.findViewById(R.id.size);
        TextView Price=root.findViewById(R.id.price);
        databasehelper=new databasehelper(getContext());
        Button button=root.findViewById(R.id.button);
        Name.setText(name);
        Color.setText(color);
        Size.setText(size);
        Price.setText(price);
        imageView.setImageResource(image);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag=databasehelper.addcart(id,customerid,price);
                if(flag==true)
                {

                    Toast.makeText(getContext(),"Added to Cart",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        OnBackPressedCallback onBackPressedCallback=new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentManager fragmentManager=getParentFragmentManager();
                FragmentTransaction ft=fragmentManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment,new HomeFragment());
                ft.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this,onBackPressedCallback);
        return root;
    }


}
