package com.yajith.shopping.ui.gallery;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.yajith.shopping.Login;
import com.yajith.shopping.R;
import com.yajith.shopping.databasehelper;
import com.yajith.shopping.progress;
import com.yajith.shopping.ui.home.HomeFragment;
import com.yajith.shopping.ui.home.fails;
import com.yajith.shopping.ui.home.cart;
public class view_card extends Fragment {
    String customer=String.valueOf(Login.customerid);
    Spinner spinner,spinner1,spinner2;
    EditText name,cardno,cvv;
    Button button;
    ListView listView= HomeFragment.listView;
    ListView listView1=cart.listView;
    public static String otp;
    public static String Name,Cardno,Cvv,Expiry_mon,Expiry_year,Cardtype;
    com.yajith.shopping.databasehelper databasehelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.view_card,container,false);
        spinner=root.findViewById(R.id.spinner1);
        String[] card=new String[3];
        listView1.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.INVISIBLE);
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
        Cursor cursor=databasehelper.viewcard(customer);
        if(cursor.getCount()!=0)
        {
            cursor.moveToFirst();
            while (cursor.moveToNext())
            {
                name.setText(cursor.getString(cursor.getColumnIndex("NAME")), TextView.BufferType.EDITABLE);
                cardno.setText(cursor.getString(cursor.getColumnIndex("CARDNUMBER")),TextView.BufferType.EDITABLE);
                cvv.setText(cursor.getString(cursor.getColumnIndex("CVV")),TextView.BufferType.EDITABLE);
                String cardtyp=cursor.getString(cursor.getColumnIndex("CARDTYPE"));
                String expidate=cursor.getString(cursor.getColumnIndex("EXPIRYDATE"));
                int pos=0;
                int posmon=0;
                int posyear=0;
                if(cardtyp.equals("CREDIT CARD"))
                {
                    pos=0;
                }
                else if(cardtyp.equals("DEBIT CARD"))
                {
                    pos=1;
                }
                else
                {
                    pos=2;
                }
                spinner.setSelection(pos);
                String[] ex=expidate.split("/");
                String date=ex[0];
                for(int i=0;i<day.length;i++)
                {
                    if(date.equals(day[i]))
                    {
                        posmon=i;
                        break;
                    }
                }
                spinner1.setSelection(posmon);
                String years=ex[1];
                for(int i=0;i<year.length;i++)
                {
                    if(years.equals(year[i]))
                    {
                        posyear=i;
                        break;
                    }
                }
                spinner2.setSelection(posyear);
            }
        }




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
                    random random=new random();
                    otp=String.valueOf(random.otp());
                    String phoneNo=databasehelper.mobile(customer);
                    if(phoneNo.equals(null))
                    {
                        Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager=getParentFragmentManager();
                        FragmentTransaction ft=fragmentManager.beginTransaction();
                        ft.replace(R.id.nav_host_fragment,new fails());
                        ft.commit();
                    }
                    String sms="Your Shopping App Code is : "+otp;
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new otp());
                    ft.commit();

                }
            }
        });
        return root;
    }
}
