package com.yajith.shopping.ui.home;
import com.yajith.shopping.Login;
import  com.yajith.shopping.databasehelper;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.yajith.shopping.R;

public class HomeFragment extends Fragment {
   public static ListView listView;
    RelativeLayout relativeLayout;
    public static String nam,colo,pric,siz;
    public static Integer im;
    public static int id;
    ListView listView1=cart.listView;
    public static Customeradapter customeradapter;
     databasehelper databasehelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        listView=root.findViewById(R.id.list);
        relativeLayout=root.findViewById(R.id.relative);
        databasehelper=new databasehelper(getContext());
        if(listView1!=null) {
            listView1.setVisibility(View.INVISIBLE);
        }
        getdatabase();
        OnBackPressedCallback onBackPressedCallback=new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this,onBackPressedCallback);
        return root;
    }

    private void getdatabase() {
        final String[] name={"Chain","Chair","Phone","TSHIRT","WEDDING SUIT","SHOCKS","SHAMPOO","PERFUME","KULLA","GLASS","FULL HAND","GOWN","BABY DRESS"};
        final String[] color={"Silver","Brown","Green","BLUE","WHITE","PINK","WHITE","VELVET","BLUE","BLACK","WHITE","BLACK","WHITE"};
        final String[] price={"1500","600","15000","1000","6000","100","900","300","500","1500","1000","1500","1500"};
        final String[] size={"XL","X","6","XL","X","6","100ML","500ML","X","XL","X","XXL","X"};
        final Integer[] imag={
                R.drawable.chain,R.drawable.chair,R.drawable.phone,R.drawable.t_shirt,R.drawable.wedding,R.drawable.shocks,R.drawable.shampoo,
                R.drawable.perfume,R.drawable.kulla,R.drawable.glass,R.drawable.full_hand,R.drawable.dress,R.drawable.baby_dress
        };
        databasehelper.pushcost(String.valueOf(Login.customerid),String.valueOf(0));
        customeradapter=new Customeradapter(getActivity(),name,color,price,size,imag);
        listView.setAdapter(customeradapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                {
                    id=1;
                    nam=name[i];
                    siz=size[i];
                    pric=price[i];
                    colo=color[i];
                    im=imag[i];
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new item_view());
                    ft.commit();
                }
                else if(i==1)
                {
                    id=2;
                    nam=name[i];
                    siz=size[i];
                    pric=price[i];
                    colo=color[i];
                    im=imag[i];
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new item_view());
                    ft.commit();
                }
                else if(i==2)
                {
                    id=3;
                    nam=name[i];
                    siz=size[i];
                    pric=price[i];
                    colo=color[i];
                    im=imag[i];
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new item_view());
                    ft.commit();
                }
                else if(i==3)
                {
                    id=4;
                    nam=name[i];
                    siz=size[i];
                    pric=price[i];
                    colo=color[i];
                    im=imag[i];
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new item_view());
                    ft.commit();
                }
                else if(i==4)
                {
                    id=5;
                    nam=name[i];
                    siz=size[i];
                    pric=price[i];
                    colo=color[i];
                    im=imag[i];
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new item_view());
                    ft.commit();
                }
                else if(i==5)
                {
                    id=6;
                    nam=name[i];
                    siz=size[i];
                    pric=price[i];
                    colo=color[i];
                    im=imag[i];
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new item_view());
                    ft.commit();
                }
                else if(i==6)
                {
                    id=7;
                    nam=name[i];
                    siz=size[i];
                    pric=price[i];
                    colo=color[i];
                    im=imag[i];
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new item_view());
                    ft.commit();
                }
                else if(i==7)
                {
                    id=8;
                    nam=name[i];
                    siz=size[i];
                    pric=price[i];
                    colo=color[i];
                    im=imag[i];
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new item_view());
                    ft.commit();
                }
                else if(i==8)
                {
                    id=9;
                    nam=name[i];
                    siz=size[i];
                    pric=price[i];
                    colo=color[i];
                    im=imag[i];
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new item_view());
                    ft.commit();
                }
                else if(i==9)
                {
                    id=10;
                    nam=name[i];
                    siz=size[i];
                    pric=price[i];
                    colo=color[i];
                    im=imag[i];
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new item_view());
                    ft.commit();
                }
                else if(i==10)
                {
                    id=11;
                    nam=name[i];
                    siz=size[i];
                    pric=price[i];
                    colo=color[i];
                    im=imag[i];
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new item_view());
                    ft.commit();
                }
                else if(i==11)
                {
                    id=12;
                    nam=name[i];
                    siz=size[i];
                    pric=price[i];
                    colo=color[i];
                    im=imag[i];
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new item_view());
                    ft.commit();
                }
                else if(i==12)
                {
                    id=13;
                    nam=name[i];
                    siz=size[i];
                    pric=price[i];
                    colo=color[i];
                    im=imag[i];
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new item_view());
                    ft.commit();
                }
                else if(i==13)
                {
                    id=14;
                    nam=name[i];
                    siz=size[i];
                    pric=price[i];
                    colo=color[i];
                    im=imag[i];
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new item_view());
                    ft.commit();
                }



            }
        });
    }
}
