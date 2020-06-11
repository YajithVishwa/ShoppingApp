package com.yajith.shopping.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.yajith.shopping.payment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.yajith.shopping.Login;
import com.yajith.shopping.MainScreen;
import com.yajith.shopping.R;
import com.yajith.shopping.databasehelper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class cart extends Fragment {
    public static ListView listView;
    Products products;
    TextView textView;
    public static int sum=0;
    int customerid= Login.customerid;
    RelativeLayout relativeLayout;
    databasehelper databasehelper;
    HomeFragment homeFragment;
    Button button;
    public static int productid=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_cart,container,false);
        listView=root.findViewById(R.id.list);
        relativeLayout=root.findViewById(R.id.relative);
        databasehelper=new databasehelper(getContext());
        products=new Products();
        button=root.findViewById(R.id.button);
        homeFragment=new HomeFragment();
        textView=root.findViewById(R.id.cost);
        ListView listView1=HomeFragment.listView;
        listView1.setVisibility(View.INVISIBLE);
        getdatabase();
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getParentFragmentManager();
                FragmentTransaction ft=fragmentManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment,new payment());
                ft.commit();

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                if(pos==0)
                {
                    productid=1;
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new view_cart());
                    ft.commit();
                }
                else if(pos==1)
                {
                    productid=2;
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new view_cart());
                    ft.commit();
                }
                else if(pos==2)
                {
                    productid=3;
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new view_cart());
                    ft.commit();
                }
                else if(pos==3)
                {
                    productid=4;
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new view_cart());
                    ft.commit();
                }
                else if(pos==4)
                {
                    productid=5;
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new view_cart());
                    ft.commit();
                }
                else if(pos==5)
                {
                    productid=6;
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new view_cart());
                    ft.commit();
                }
                else if(pos==6)
                {
                    productid=7;
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new view_cart());
                    ft.commit();
                }
                else if(pos==7)
                {
                    productid=8;
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new view_cart());
                    ft.commit();
                }
                else if(pos==8)
                {
                    productid=9;
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new view_cart());
                    ft.commit();
                }
                else if(pos==9)
                {
                    productid=10;
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new view_cart());
                    ft.commit();
                }
                else if(pos==10)
                {
                    productid=11;
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new view_cart());
                    ft.commit();
                }
                else if(pos==11)
                {
                    productid=12;
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new view_cart());
                    ft.commit();
                }
                else if(pos==12)
                {
                    productid=13;
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new view_cart());
                    ft.commit();
                }
            }
        });
        return root;
    }
    private void getdatabase() {
       /* String[] name={"Chain","Chair","Phone"};
        String[] color={"Silver","Brown","Green"};
        String[] price={"1500","600","15000"};
        String[] size={"XL","X","10"};
        Integer[] imag={
                R.drawable.chain,R.drawable.chair,R.drawable.phone
        };*/
       Cursor c =databasehelper.cart(customerid);
       if(c==null)
       {
           FragmentManager fragmentManager=getParentFragmentManager();
           FragmentTransaction ft=fragmentManager.beginTransaction();
           ft.replace(R.id.nav_host_fragment,new emptycart());
           ft.commit();
       }
       else {
           int count=c.getCount();
           String[] customer=new String[count];
           String[] product=new String[count];
           int i=0;
           while (c.moveToNext())
           {
               customer[i]=c.getString(c.getColumnIndex("CUSTOMERID"));
               product[i]=c.getString(c.getColumnIndex("PRODUCTID"));
               i++;
           }
           String[] name=new String[count];
           String[] color=new String[count];
           String[] size=new String[count];
           String[] price=new String[count];
           Integer[] image=new Integer[count];
           for(int j=0;j<count;j++)
           {
               Cursor cursor=databasehelper.products(product[j]);
               if(cursor.getCount()==0)
               {
                   return;
               }
               if(product[j].equals("1"))
               {
                   name[j]=products.product1();
                   size[j]=products.size1();
                   color[j]=products.color1();
                   price[j]=products.price1();
                   image[j]=R.drawable.chain;
               }
               else if(product[j].equals("2"))
               {
                   name[j]=products.product2();
                   size[j]=products.size2();
                   color[j]=products.color2();
                   price[j]=products.price2();
                   image[j]=R.drawable.chair;
               }
               else if(product[j].equals("3"))
               {
                   name[j]=products.product3();
                   size[j]=products.size3();
                   color[j]=products.color3();
                   price[j]=products.price3();
                   image[j]=R.drawable.phone;
               }
               else if(product[j].equals("4"))
               {
                   name[j]=products.product4();
                   size[j]=products.size4();
                   color[j]=products.color4();
                   price[j]=products.price4();
                   image[j]=R.drawable.t_shirt;
               }
               else if(product[j].equals("5"))
               {
                   name[j]=products.product5();
                   size[j]=products.size5();
                   color[j]=products.color5();
                   price[j]=products.price5();
                   image[j]=R.drawable.wedding;
               }
               else if(product[j].equals("6"))
               {
                   name[j]=products.product6();
                   size[j]=products.size6();
                   color[j]=products.color6();
                   price[j]=products.price6();
                   image[j]=R.drawable.shocks;
               }
               else if(product[j].equals("7"))
               {
                   name[j]=products.product7();
                   size[j]=products.size7();
                   color[j]=products.color7();
                   price[j]=products.price7();
                   image[j]=R.drawable.shampoo;
               }
               else if(product[j].equals("8"))
               {
                   name[j]=products.product8();
                   size[j]=products.size8();
                   color[j]=products.color8();
                   price[j]=products.price8();
                   image[j]=R.drawable.perfume;
               }
               else if(product[j].equals("9"))
               {
                   name[j]=products.product9();
                   size[j]=products.size9();
                   color[j]=products.color9();
                   price[j]=products.price9();
                   image[j]=R.drawable.kulla;
               }
               else if(product[j].equals("10"))
               {
                   name[j]=products.product10();
                   size[j]=products.size10();
                   color[j]=products.color10();
                   price[j]=products.price10();
                   image[j]=R.drawable.glass;
               }
               else if(product[j].equals("11"))
               {
                   name[j]=products.product11();
                   size[j]=products.size11();
                   color[j]=products.color11();
                   price[j]=products.price11();
                   image[j]=R.drawable.full_hand;
               }
               else if(product[j].equals("12"))
               {
                   name[j]=products.product12();
                   size[j]=products.size12();
                   color[j]=products.color12();
                   price[j]=products.price12();
                   image[j]=R.drawable.dress;
               }
               else if(product[j].equals("13"))
               {
                   name[j]=products.product13();
                   size[j]=products.size13();
                   color[j]=products.color13();
                   price[j]=products.price13();
                   image[j]=R.drawable.baby_dress;
               }
               continue;
           }
           sum=databasehelper.total(String.valueOf(customerid));
           //databasehelper.pushcost(String.valueOf(customerid),String.valueOf(sum));
           textView.setText(String.valueOf(sum));
           Customeradapter customeradapter=new Customeradapter(getActivity(),name,color,price,size,image);
           listView.setAdapter(customeradapter);
       }
    }
}
