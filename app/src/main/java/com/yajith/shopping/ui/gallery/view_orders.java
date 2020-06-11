package com.yajith.shopping.ui.gallery;
import com.yajith.shopping.Login;
import com.yajith.shopping.databasehelper;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.yajith.shopping.R;
import com.yajith.shopping.ui.home.Customeradapter;
import com.yajith.shopping.ui.home.HomeFragment;
import com.yajith.shopping.ui.home.Products;
import com.yajith.shopping.ui.home.cart;
import com.yajith.shopping.ui.home.emptycart;

public class view_orders extends Fragment {
    ListView listView;
    ListView list1=HomeFragment.listView;
    ListView listView1= cart.listView;
    databasehelper databasehelper;
    String customerid= String.valueOf(Login.customerid);
    RelativeLayout relativeLayout;
    Products products;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.view_orders,container,false);
        listView=root.findViewById(R.id.list);
        relativeLayout=root.findViewById(R.id.relative);
        databasehelper=new databasehelper(getContext());
        if(listView1!=null) {
            listView1.setVisibility(View.INVISIBLE);
        }
        if(list1!=null) {
            list1.setVisibility(View.INVISIBLE);
        }
        products=new Products();
        getdata();
        OnBackPressedCallback onBackPressedCallback=new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentManager fragmentManager=getParentFragmentManager();
                FragmentTransaction ft=fragmentManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment,new GalleryFragment());
                ft.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this,onBackPressedCallback);
        return root;
    }

    private void getdata() {
        Cursor cursor=databasehelper.vieworder(customerid);
        int count=cursor.getCount();
        if(count==0)
        {
            FragmentManager fragmentManager=getParentFragmentManager();
            FragmentTransaction ft=fragmentManager.beginTransaction();
            ft.replace(R.id.nav_host_fragment,new emptycart());
            ft.commit();
        }
        else
        {
            int i=0;
            int k=0;

            String[] product=new String[count];
            String[] pro=new String[count];
            while (cursor.moveToNext())
            {
                product[i]=cursor.getString(cursor.getColumnIndex("PRODUCTID"));
                i++;
            }
            String a=product[0];
            for(int l=1;l<i;l++)
            {
                a+=product[l];
            }
            String[] refuse=a.split(",");
            int co=refuse.length/2;
            String[] name=new String[co];
            String[] color=new String[co];
            String[] size=new String[co];
            String[] price=new String[co];
            Integer[] image=new Integer[co];
            for(int j=0;j<co;j++)
            {
                if(refuse[k].equals("1"))
                {
                    name[j]=products.product1();
                    size[j]=products.size1();
                    color[j]=products.color1();
                    price[j]=products.price1();
                    image[j]=R.drawable.chain;
                }
                else if(refuse[k].equals("2"))
                {
                    name[j]=products.product2();
                    size[j]=products.size2();
                    color[j]=products.color2();
                    price[j]=products.price2();
                    image[j]=R.drawable.chair;
                }
                else if(refuse[k].equals("3"))
                {
                    name[j]=products.product3();
                    size[j]=products.size3();
                    color[j]=products.color3();
                    price[j]=products.price3();
                    image[j]=R.drawable.phone;
                }
                else if(refuse[k].equals("4"))
                {
                    name[j]=products.product4();
                    size[j]=products.size4();
                    color[j]=products.color4();
                    price[j]=products.price4();
                    image[j]=R.drawable.t_shirt;
                }
                else if(refuse[k].equals("5"))
                {
                    name[j]=products.product5();
                    size[j]=products.size5();
                    color[j]=products.color5();
                    price[j]=products.price5();
                    image[j]=R.drawable.wedding;
                }
                else if(refuse[k].equals("6"))
                {
                    name[j]=products.product6();
                    size[j]=products.size6();
                    color[j]=products.color6();
                    price[j]=products.price6();
                    image[j]=R.drawable.shocks;
                }
                else if(refuse[k].equals("7"))
                {
                    name[j]=products.product7();
                    size[j]=products.size7();
                    color[j]=products.color7();
                    price[j]=products.price7();
                    image[j]=R.drawable.shampoo;
                }
                else if(refuse[k].equals("8"))
                {
                    name[j]=products.product8();
                    size[j]=products.size8();
                    color[j]=products.color8();
                    price[j]=products.price8();
                    image[j]=R.drawable.perfume;
                }
                else if(refuse[k].equals("9"))
                {
                    name[j]=products.product9();
                    size[j]=products.size9();
                    color[j]=products.color9();
                    price[j]=products.price9();
                    image[j]=R.drawable.kulla;
                }
                else if(refuse[k].equals("10"))
                {
                    name[j]=products.product10();
                    size[j]=products.size10();
                    color[j]=products.color10();
                    price[j]=products.price10();
                    image[j]=R.drawable.glass;
                }
                else if(refuse[k].equals("11"))
                {
                    name[j]=products.product11();
                    size[j]=products.size11();
                    color[j]=products.color11();
                    price[j]=products.price11();
                    image[j]=R.drawable.full_hand;
                }
                else if(refuse[k].equals("12"))
                {
                    name[j]=products.product12();
                    size[j]=products.size12();
                    color[j]=products.color12();
                    price[j]=products.price12();
                    image[j]=R.drawable.dress;
                }
                else if(refuse[k].equals("13"))
                {
                    name[j]=products.product13();
                    size[j]=products.size13();
                    color[j]=products.color13();
                    price[j]=products.price13();
                    image[j]=R.drawable.baby_dress;
                }
                k+=2;
            }
            Customeradapter customeradapter=new Customeradapter(getActivity(),name,color,price,size,image);
            listView.setAdapter(customeradapter);
        }
    }
}
