package com.yajith.shopping;
import com.yajith.shopping.ui.home.fails;
import com.yajith.shopping.ui.home.success;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yajith.shopping.ui.home.HomeFragment;

public class progress extends Fragment {
    databasehelper databasehelper;
    String cus=String.valueOf(Login.customerid);
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.activity_progress,container,false);
        databasehelper=new databasehelper(getContext());
        getvalue();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentManager fragmentManager=getParentFragmentManager();
                FragmentTransaction ft=fragmentManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment,new success());
                ft.commit();
            }
        },3000);
        return root;
    }
    private void getvalue() {
        Cursor s=databasehelper.car(cus);
        int count=s.getCount();
        String[] pro=new String[count];
        int i=0;
        while (s.moveToNext())
        {
            pro[i]=s.getString(s.getColumnIndex("PRODUCTID"));
            i++;
        }
        String a=pro[0]+ ",";
        for(int j=1;j<count;j++)
        {
            a+=pro[j]+",";
        }
        Cursor ca=databasehelper.cardid(cus);
        ca.moveToFirst();
        String cardid=ca.getString(ca.getColumnIndex("ID"));
        boolean flag=databasehelper.addorder(cardid,cus,a);
        if(flag==true)
        {
            return;
        }
        else
        {
            databasehelper.flushcart(cus);
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, new success());
                ft.commit();
        }
    }
}
