package com.example.user.gunluk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Kayit_Ol extends AppCompatActivity {

    DatabaseHelper db;

    Toolbar tbkayitt;

    Button kaydetKayit;

    EditText isimKayit,kullaniciAdiKayit,sifreKayit,sifreTekrariKayit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit__ol);
        db=new DatabaseHelper(this);

        idAtama();


/**/
        kaydetKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String namee=isimKayit.getText().toString();
                String kullaniciAdiKayitt=kullaniciAdiKayit.getText().toString();
                String sifre1=sifreKayit.getText().toString();
                String sifre2=sifreTekrariKayit.getText().toString();

                if(namee.equals("")||kullaniciAdiKayitt.equals("")||sifre1.equals("")||sifre2.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Boş Alan Bırakmayınız",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (sifre1.equals(sifre2)) {
                            Boolean chkKullaniciAdii=db.chkKullaniciAdi(kullaniciAdiKayitt);
                            if (chkKullaniciAdii==true)
                            {
                                Boolean insert=db.insert(namee,kullaniciAdiKayitt,sifre1);
                                if (insert==true)
                                {
                                    Toast.makeText(getApplicationContext(),"Kayıt Başarılı",Toast.LENGTH_LONG).show();
                                    Intent kaydetCik =new Intent(Kayit_Ol.this,Giris.class);

                                    startActivity(kaydetCik);
                                }
                            }else {
                                Toast.makeText(getApplicationContext(),"Bu Kullanıcı Mevcut",Toast.LENGTH_SHORT).show();
                            }
                    }else
                    Toast.makeText(getApplicationContext(),"Parola Eşleşmedi",Toast.LENGTH_SHORT).show();
                }





            }
        });

    }


    public void idAtama()
    {



        kaydetKayit=(Button)findViewById(R.id.btnKaydetKayitOl);

        isimKayit=(EditText)findViewById(R.id.edtxIsimKayitOl);
        kullaniciAdiKayit=(EditText)findViewById(R.id.edtxKullaniciAdiKayitOl);
        sifreKayit=(EditText)findViewById(R.id.edtxSifreKayitOl);
        sifreTekrariKayit=(EditText)findViewById(R.id.edtxSifreTekrariKayitOl);

    }


}
