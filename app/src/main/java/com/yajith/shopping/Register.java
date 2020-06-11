package com.yajith.shopping;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Register extends AppCompatActivity {
    public static String Name,Email,Mobile,Pass,Pass1;
    Button button;
    databasehelper databasehelper;
    EditText name,email,mobile,pass,pass1;
    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        databasehelper=new databasehelper(this);
        boolean f2=databasehelper.product("CHAIN","silver","XL","3","1500");
        boolean f3=databasehelper.product("CHAIR","brown","X","13","600");
        boolean f4=databasehelper.product("PHONE","Green","6","10","15000");
        boolean f5=databasehelper.product("TSHIRT","BLUE","XL","34","1000");
        boolean f6=databasehelper.product("WEDDING SUIT","WHITE","X","1","6000");
        boolean f7=databasehelper.product("SHOCKS","PINK","6","10","100");
        boolean f8=databasehelper.product("SHAMPOO","WHILE","100ML","23","900");
        boolean f9=databasehelper.product("PERFUME","VELVET","500ML","13","300");
        boolean f10=databasehelper.product("KULLA","BLUE","X","80","500");
        boolean f11=databasehelper.product("GLASS","BLACK","XL","34","1500");
        boolean f12=databasehelper.product("FULL HAND","WHITE","X","12","1000");
        boolean f13=databasehelper.product("GOWN","BLACK","XXL","60","1500");
        boolean f14=databasehelper.product("BABY DRESS","WHITE","X","4","1500");
        databasehelper.setprice(String.valueOf(1));
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        mobile=findViewById(R.id.mobile);
        pass=findViewById(R.id.password);
        pass1=findViewById(R.id.confirm);
        button=findViewById(R.id.regi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name=name.getText().toString();
                Email=email.getText().toString();
                Mobile=mobile.getText().toString();
                Pass=pass.getText().toString();
                Pass1=pass1.getText().toString();
                if(Name.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter the name",Toast.LENGTH_SHORT).show();return;
                }
                else if(Email.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter the Email",Toast.LENGTH_SHORT).show();return;
                }
                else if(Mobile.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter the Mobile",Toast.LENGTH_SHORT).show();return;
                }
                else if(Pass.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter the Password",Toast.LENGTH_SHORT).show();return;
                }
                else if(Pass1.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter the Confirm Password",Toast.LENGTH_SHORT).show();return;
                }
                else if(!Pass.equals(Pass1))
                {
                    Toast.makeText(getApplicationContext(),"Password MisMatch",Toast.LENGTH_SHORT).show();return;
                }
                else
                {
                    boolean flag=databasehelper.register(Name,Mobile,Email,Pass);

                    if(flag==true) {
                        Intent intent = new Intent(Register.this, Login.class);
                        finish();
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"SomeThing Went Wrong",Toast.LENGTH_SHORT).show();return;
                    }
                }


            }
        });

    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
