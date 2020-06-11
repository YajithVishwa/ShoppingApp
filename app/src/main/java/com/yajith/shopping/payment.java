package com.yajith.shopping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.yajith.shopping.ui.gallery.random;

public class payment extends Fragment {
    RadioGroup radioGroup;
    Button button;
    databasehelper databasehelper;
    public static String payment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.activity_payment,container,false);
        radioGroup=root.findViewById(R.id.radio);
        databasehelper=new databasehelper(getContext());
        button=root.findViewById(R.id.button);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton=(RadioButton)radioGroup.findViewById(i);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectid=radioGroup.getCheckedRadioButtonId();
                if(selectid==-1)
                {
                    Toast.makeText(getContext(), "Select Anyone Type Of Payment", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    RadioButton radioButton=(RadioButton)radioGroup.findViewById(selectid);
                    payment=String.valueOf(radioButton.getText());
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new carddetails());
                    ft.commit();
                }
            }
        });
        return root;
    }
}
