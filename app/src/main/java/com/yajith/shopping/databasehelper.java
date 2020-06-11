package com.yajith.shopping;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import androidx.annotation.Nullable;
public class databasehelper extends SQLiteOpenHelper {
    public databasehelper(@Nullable Context context) {
        super(context,"DBMS.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE CUSTOMER(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(30),EMAIL VARCHAR(30),MOBILE NUMBER,PASS VARCHAR(20));");
        sqLiteDatabase.execSQL("CREATE TABLE PRODUCT(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCGAR(30),COLOR VARCHAR(20),SIZE NUMBER,QUANTITY NUMBER,PRICE NUMBER);");
        sqLiteDatabase.execSQL("CREATE TABLE CART(ID INTEGER PRIMARY KEY AUTOINCREMENT,PRODUCTID INTEGER,CUSTOMERID INTEGER,COST INTEGER,FOREIGN KEY(PRODUCTID) REFERENCES PRODUCT(ID),FOREIGN KEY(CUSTOMERID) REFERENCES CUSTOMER(ID));");
        sqLiteDatabase.execSQL("CREATE TABLE CARD(ID INTEGER PRIMARY KEY AUTOINCREMENT,CARDNAME VARCHAR(20),CARDTYPE VARCHAR(20),CARDNUMBER VARCHAR(30),EXPIRYDATE VARCHAR(20),CVV NUMBER,CUSTOMERID INTEGER,FOREIGN KEY(CUSTOMERID) REFERENCES CUSTOMER(ID));");
        sqLiteDatabase.execSQL("CREATE TABLE ORDERTABLE(ID INTEGER PRIMARY KEY AUTOINCREMENT,CARDID INTEGER,CUSTOMERID INTEGER,PRODUCTID INTEGER,FOREIGN KEY(CARDID) REFERENCES CARD(ID),FOREIGN KEY(CUSTOMERID) REFERENCES CUSTOMER(ID));");
        sqLiteDatabase.execSQL("CREATE TABLE COST(COST INTEGER,CUSTOMERID INTEGER,FOREIGN KEY(CUSTOMERID) REFERENCES CUSTOMER(ID));");
    }
    public int total(String customer)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT COST FROM CART WHERE CUSTOMERID="+customer,null);
        Integer[] total=new Integer[cursor.getCount()];
        int i=0;
        while (cursor.moveToNext())
        {
            total[i]=Integer.parseInt(cursor.getString(cursor.getColumnIndex("COST")));
            i++;
        }
        int sum=0;
        for(int j=0;j<total.length;j++)
        {
            sum+=total[j];
        }
        return sum;
    }
    public void setprice(String customer)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("COST",0);
        contentValues.put("CUSTOMERID",customer);
        sqLiteDatabase.insert("COST",null,contentValues);
    }
    public int getprice(String productid)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT PRICE FROM PRODUCT WHERE ID="+productid,null);
        int price=0;
        while (cursor.moveToNext())
        {
            price=cursor.getInt(cursor.getColumnIndex("PRICE"));
        }
        return price;
    }
    public void flushcart(String customer)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete("CART","CUSTOMERID=?",new String[]{customer});
    }
    public int retrivecost(String customer)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT COST FROM COST WHERE CUSTOMERID="+customer,null);
        int cost=0;
        while (cursor.moveToNext())
        {
            cost=Integer.parseInt(cursor.getString(cursor.getColumnIndex("COST")));
        }
        return cost;
    }
    public void pushcost(String customer,String cost)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE COST SET COST="+cost+" WHERE CUSTOMERID="+customer);

    }
    public boolean removecart(String productid)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete("CART","PRODUCTID=?",new String[]{productid})!=0;

    }
    public Cursor getproduct(String productid)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM PRODUCT WHERE ID="+productid,null);
        return cursor;
    }
    public String mobile(String customerid)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT MOBILE FROM CUSTOMER WHERE ID="+customerid,null);
        String mobile=null;
        while (cursor.moveToNext())
        {
            mobile=cursor.getString(cursor.getColumnIndex("MOBILE"));
        }
        return mobile;
    }
    public Cursor viewcard(String customerid)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM CARD WHERE CUSTOMERID="+customerid,null);
        return cursor;
    }
    public Cursor vieworder(String customerid)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT PRODUCTID FROM ORDERTABLE WHERE CUSTOMERID="+customerid,null);
        return cursor;
    }
    public String profile(String customerid)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cer=sqLiteDatabase.rawQuery("SELECT NAME FROM CUSTOMER WHERE ID="+customerid,null);
        String name=null;
        while(cer.moveToNext())
        {
            name=cer.getString(cer.getColumnIndex("NAME"));
        }
        return name;
    }
    public String email(String customerid)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cer=sqLiteDatabase.rawQuery("SELECT EMAIL FROM CUSTOMER WHERE ID="+customerid,null);
        String name="98888";
        while(cer.moveToNext())
        {
            name=cer.getString(cer.getColumnIndex("EMAIL"));
        }
        return name;
    }
    public String mobileGET(String customerid)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cer=sqLiteDatabase.rawQuery("SELECT MOBILE FROM CUSTOMER WHERE ID="+customerid,null);
        String name=null;
        while(cer.moveToNext())
        {
            name=cer.getString(cer.getColumnIndex("MOBILE"));
        }
        return name;
    }
    public void clear(String customer)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete("CART","CUSTOMERID="+customer,null);
        sqLiteDatabase.close();
    }
    public boolean addorder(String cardid,String customer,String product)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("CARDID",cardid);
        contentValues.put("CUSTOMERID",customer);
        contentValues.put("PRODUCTID",product);
        long flag=sqLiteDatabase.insert("ORDERTABLE",null,contentValues);
        if(flag==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor cardid(String customer)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor s=sqLiteDatabase.rawQuery("SELECT ID FROM CARD WHERE CUSTOMERID="+customer,null);
        return s;
    }
    public Cursor car(String cust)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor c=sqLiteDatabase.rawQuery("SELECT PRODUCTID FROM CART WHERE CUSTOMERID="+cust,null);
        return c;
    }
    public boolean addcard(String cardname,String cardtype,String cardnumber,String Expiry,String cvv,String cus)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("CARDNAME",cardname);
        contentValues.put("CARDTYPE",cardtype);
        contentValues.put("CARDNUMBER",cardnumber);
        contentValues.put("EXPIRYDATE",Expiry);
        contentValues.put("CVV",cvv);
        contentValues.put("CUSTOMERID",cus);
        long t=sqLiteDatabase.insert("CARD",null,contentValues);
        if(t==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public  boolean product(String name,String color,String size,String quantity,String price)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("COLOR",color);
        contentValues.put("SIZE",size);
        contentValues.put("QUANTITY",quantity);
        contentValues.put("PRICE",price);
        long r=sqLiteDatabase.insert("PRODUCT",null,contentValues);
        if(r==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor displaycard(String customerid,String type)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT CARDNAME FROM CARD WHERE CUSTOMERID="+customerid,null);
        return cursor;
    }
    public boolean addcart(int id,int customerid,String price)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("PRODUCTID",id);
        contentValues.put("CUSTOMERID",customerid);
        contentValues.put("COST",price);
        long r=sqLiteDatabase.insert("CART",null,contentValues);
        if(r==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean register(String name,String mobile,String email,String pass)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("MOBILE",mobile);
        contentValues.put("EMAIL",email);
        contentValues.put("PASS",pass);
        long re=sqLiteDatabase.insert("CUSTOMER",null,contentValues);
        if(re==-1)
            return false;
        else
            return true;
    }

    public String login(String email,String pass)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        try {
            Cursor res = sqLiteDatabase.rawQuery("SELECT MOBILE FROM CUSTOMER WHERE MOBILE=" + email + " AND PASS=" + pass + ";", null);
            res.moveToFirst();
            int count = res.getColumnCount();
            if (count == 0) {
                return null;
            } else {
                String mobile = res.getString(res.getColumnIndex("MOBILE"));
                return mobile;
            }
        }
        catch (Exception e)
        {
            return "3432423";
        }
    }
    public int customerreg(String email)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("SELECT ID FROM CUSTOMER WHERE MOBILE="+email+";",null);
        res.moveToFirst();
        int count=res.getColumnCount();
        if(count==0)
        {
            return 0;
        }
        else
        {
            int i=res.getInt(res.getColumnIndex("ID"));
            return i;
        }

    }
    public Cursor products(String product) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PRODUCT WHERE ID="+product+";", null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CUSTOMER");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PRODUCT");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CART");
        onCreate(sqLiteDatabase);
    }
    public Cursor cart(int customerid)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor c=sqLiteDatabase.rawQuery("SELECT * FROM CART WHERE CUSTOMERID="+customerid,null);
        return  c;
    }
}
