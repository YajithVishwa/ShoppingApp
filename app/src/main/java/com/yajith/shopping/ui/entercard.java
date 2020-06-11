package com.yajith.shopping.ui;
import com.yajith.shopping.databasehelper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.yajith.shopping.Login;
import com.yajith.shopping.R;
import com.yajith.shopping.progress;
import com.yajith.shopping.ui.home.HomeFragment;

import java.util.Calendar;

public class entercard extends Fragment {
    String customer=String.valueOf(Login.customerid);
    Spinner spinner,spinner1,spinner2;
    EditText name,cardno,cvv;
    Button button;
    String Name,Cardno,Cvv,Expiry_mon,Expiry_year,Cardtype;
    databasehelper databasehelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.activity_entercard,container,false);
        spinner=root.findViewById(R.id.spinner1);
        String[] card=new String[3];
        card[0]="CREDIT CARD";
        card[1]="DEBIT CARD";
        card[2]="GIFT CARD";
        name=root.findViewById(R.id.cardname);
        cardno=root.findViewById(R.id.cardnum);
        cvv=root.findViewById(R.id.cvv);
        databasehelper=new databasehelper(getContext());
        spinner1=root.findViewById(R.id.spinner2);
        spinner2=root.findViewById(R.id.spinner3);
        button=root.findViewById(R.id.button3);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,card);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        int j=0;
        String[] year=new String[10];
        for(int i=2020;i<2030;i++)
        {
            year[j]=String.valueOf(i);
            j++;
        }
        String[] day=new String[]{"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,day);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter1);
        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,year);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name=name.getText().toString();
                Cardno=cardno.getText().toString();
                Cvv=cvv.getText().toString();
                Expiry_mon=spinner1.getSelectedItem().toString();
                Expiry_year=spinner2.getSelectedItem().toString();
                Cardtype=spinner.getSelectedItem().toString();
                if(Name.equals(""))
                {
                    Toast.makeText(getContext(), "Enter Name of the card", Toast.LENGTH_SHORT).show();return;
                }
                else if(Cardno.equals(""))
                {
                    Toast.makeText(getContext(), "Enter the Card Number", Toast.LENGTH_SHORT).show();return;
                }
                else if(Cvv.equals(""))
                {
                    Toast.makeText(getContext(), "Enter Cvv", Toast.LENGTH_SHORT).show();return;
                }
                else
                {
                    String ex=Expiry_mon+"/"+Expiry_year;
                    boolean flag=databasehelper.addcard(Name,Cardtype,Cardno,ex,Cvv,customer);
                    if(flag==false)
                    {
                        Toast.makeText(getContext(), "SomeThing Went wrong", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Card Successfully Added", Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager=getParentFragmentManager();
                        FragmentTransaction ft=fragmentManager.beginTransaction();
                        ft.replace(R.id.nav_host_fragment,new progress());
                        ft.commit();

                    }
                }
            }
        });
        return root;
    }
}
