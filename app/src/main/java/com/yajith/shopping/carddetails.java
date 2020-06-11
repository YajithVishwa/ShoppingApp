package com.yajith.shopping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.yajith.shopping.ui.entercard;
import com.yajith.shopping.ui.gallery.random;
import com.yajith.shopping.ui.home.HomeFragment;
import com.yajith.shopping.ui.home.fails;
import com.yajith.shopping.ui.home.otp;

public class carddetails extends Fragment {
    Spinner spinner;
    Button button;
    String card;
    String customer=String.valueOf(Login.customerid);
    databasehelper databasehelper;
    int count=0;
    String cardtype=payment.payment.trim();
    public static String otp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.activity_carddetails,container,false);
        spinner=root.findViewById(R.id.spinner);
        button=root.findViewById(R.id.button2);
        databasehelper=new databasehelper(getContext());
        getdata();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                card=spinner.getSelectedItem().toString();
                if(card.equals("NO CARD"))
                {
                    FragmentManager fragmentManager=getParentFragmentManager();
                    FragmentTransaction ft=fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new entercard());
                    ft.commit();
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
                    ft.replace(R.id.nav_host_fragment,new com.yajith.shopping.ui.home.otp());
                    ft.commit();
                   //   ft.commit();
                }
            }
        });
        return root;
    }

    private void getdata() {
        Cursor c=databasehelper.displaycard(customer,cardtype);
        count=c.getCount();
        String a="NO CARD";
        if(count==0)
        {
            count++;
            String[] card=new String[count];
            card[0]="NO CARD";
            access(card);
            return;
        }
        String[] card=new String[count];
        if(count>=1)
        {
            int j=0;
            while (c.moveToNext())
            {
                card[j]=c.getString(c.getColumnIndex("CARDNAME"));
                j++;
            }

        }
        access(card);
        return;

    }
    public void access(String[] card)
    {
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,card);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

}
