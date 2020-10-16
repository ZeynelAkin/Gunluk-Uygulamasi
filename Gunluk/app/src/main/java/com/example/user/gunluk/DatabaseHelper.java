package com.example.user.gunluk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "Login.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table user(id integer primary key autoincrement,name text,kullaniciAdi text,password text)");
        db.execSQL("Create table gunler(id integer primary key autoincrement , kullaniciId integer  ,gunYazi text,resim blob,tarih text, foreign key (kullaniciId) references user(id))");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists user");
    }

    public boolean insert(String name,String kullaniciAdi ,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("name",name);
        contentValues.put("kullaniciAdi",kullaniciAdi);
        contentValues.put("password",password);
        long ins = db.insert("user",null,contentValues);
        if (ins==1) return false;
        else return true;
    }

    public  Boolean chkKullaniciAdi(String kullaniciAdii)
    {
            SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from user where kullaniciAdi=?",new String[]{kullaniciAdii});
        if(cursor.getCount()>0)return false;
        else return true;
    }

    public  Boolean chkKullaniciAdiParola(String kullaniciAdiiii,String parolaaa)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from user where kullaniciAdi=? and password=?",new String[]{kullaniciAdiiii,parolaaa});
        if (cursor.getCount()>0) return true;
        else return  false;

    }

    public boolean insertGun(String name,String kullaniciAdi ,String password)
    {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("name",name);
        contentValues.put("kullaniciAdi",kullaniciAdi);
        contentValues.put("password",password);
        long ins = db1.insert("user",null,contentValues);
        if (ins==1) return false;
        else return true;
    }

}
