package com.yajith.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button button,button1;
    EditText Email,Pass;
    String email,pass;
    boolean flag=true;
    databasehelper databasehelper;
    public static int customerid=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkPermission(Manifest.permission.SEND_SMS,100);
        button=findViewById(R.id.regi);
        Email=findViewById(R.id.email);
        Pass=findViewById(R.id.password);
        databasehelper=new databasehelper(this);
        button1=findViewById(R.id.sign);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=Email.getText().toString();
                if(email.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter the Email Id",Toast.LENGTH_SHORT).show();return;
                }
                pass=Pass.getText().toString();
                if(pass.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter the Password",Toast.LENGTH_SHORT).show();return;
                }
                String f=databasehelper.login(email,pass);
                if(f.equals(email))
                {
                    customerid=databasehelper.customerreg(email);
                    Intent intent=new Intent(Login.this,MainScreen.class);
                    intent.putExtra("MOBILE",email);
                    if(flag==true) {
                        finish();
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(Login.this, "Accept To Continue", Toast.LENGTH_SHORT).show();
                        checkPermission(Manifest.permission.SEND_SMS,100);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect Login",Toast.LENGTH_SHORT).show();return;
                }

            }
        });
    }

    private void checkPermission(String sendSms, int i) {
        if(ContextCompat.checkSelfPermission(this,sendSms)== PackageManager.PERMISSION_DENIED)
        {
            flag=false;
            ActivityCompat.requestPermissions(this,new String[]{sendSms},i);
        }
        else
        {
            flag=true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
