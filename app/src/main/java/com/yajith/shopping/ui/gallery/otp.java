package com.yajith.shopping.ui.gallery;

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

import com.yajith.shopping.Login;
import com.yajith.shopping.R;
import com.yajith.shopping.databasehelper;
public class otp extends Fragment {
    databasehelper databasehelper;
    EditText editText;
    Button button;
    String userotp=null;
    public static String Name=view_card.Name;
    public static String Cardtype=view_card.Cardtype;
    public static String Cardno=view_card.Cardno;
    public static String Cvv=view_card.Cvv;
    public static String customer= String.valueOf(Login.customerid);
    public static String Expiry_mon=view_card.Expiry_mon;
    public static String Expiry_year=view_card.Expiry_year;
    public static String otp=view_card.otp;
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

                    String ex = Expiry_mon + " / " + Expiry_year;
                    boolean flag = databasehelper.addcard(Name, Cardtype, Cardno, ex, Cvv, customer);
                    if (flag == false) {
                        Toast.makeText(getContext(), "SomeThing Went wrong", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Card Saved", Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager = getParentFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.nav_host_fragment, new GalleryFragment());
                        ft.commit();

                    }
                }
                else
                {
                    Toast.makeText(getContext(), "Wrong OTP!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }
}
