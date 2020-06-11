package com.yajith.shopping.ui.home;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.yajith.shopping.Login;
import com.yajith.shopping.databasehelper;
import com.yajith.shopping.R;

public class view_cart extends Fragment {
    databasehelper databasehelper;
    int product=cart.productid;
    int price;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.view_cart,container,false);
        databasehelper=new databasehelper(getContext());
        Cursor cursor=databasehelper.getproduct(String.valueOf(product));
        ImageView imageView=root.findViewById(R.id.image);
        TextView Name=root.findViewById(R.id.name);
        TextView Color=root.findViewById(R.id.color);
        TextView Size=root.findViewById(R.id.size);
        TextView Price=root.findViewById(R.id.price);
        Button button=root.findViewById(R.id.button4);
        price=databasehelper.getprice(String.valueOf(product));
        while (cursor.moveToNext())
        {
            Name.setText(cursor.getString(cursor.getColumnIndex("NAME")));
            Color.setText(cursor.getString(cursor.getColumnIndex("COLOR")));
            Size.setText(cursor.getString(cursor.getColumnIndex("SIZE")));
            Price.setText(cursor.getString(cursor.getColumnIndex("PRICE")));
        }
        if(product==1)
        {
            imageView.setImageResource(R.drawable.chain);
        }
        else if(product==2)
        {
            imageView.setImageResource(R.drawable.chair);
        }
        else if(product==3)
        {
            imageView.setImageResource(R.drawable.phone);
        }
        else if(product==4)
        {
            imageView.setImageResource(R.drawable.t_shirt);
        }
        else if(product==5)
        {
            imageView.setImageResource(R.drawable.wedding);
        }
        else if(product==6)
        {
            imageView.setImageResource(R.drawable.shocks);
        }
        else if(product==7)
        {
            imageView.setImageResource(R.drawable.shampoo);
        }
        else if(product==8)
        {
            imageView.setImageResource(R.drawable.perfume);
        }
        else if(product==9)
        {
            imageView.setImageResource(R.drawable.kulla);
        }
        else if(product==10)
        {
            imageView.setImageResource(R.drawable.glass);
        }
        else if(product==11)
        {
            imageView.setImageResource(R.drawable.full_hand);
        }
        else if(product==12)
        {
            imageView.setImageResource(R.drawable.dress);
        }
        else if(product==13)
        {
            imageView.setImageResource(R.drawable.baby_dress);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag=databasehelper.removecart(String.valueOf(product));
                if(flag==true)
                {
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new cart());
                    ft.commit();
                }
                else
                {
                    Toast.makeText(getContext(),"Something went wrong!",Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new fails());
                    ft.commit();
                }
            }
        });
        return root;
    }
}
