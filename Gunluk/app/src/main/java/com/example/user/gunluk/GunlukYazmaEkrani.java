package com.example.user.gunluk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GunlukYazmaEkrani extends AppCompatActivity {


    Button kaydetGun,cikis;

    EditText edtxgunlukYazma;

    TextView gununTarihi;

    ScrollView scrolView;

    Date simdikiZaman = new Date();

    String dateName;
    String concent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gunluk_yazma_ekrani);

        idAtama();


        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                long date = System.currentTimeMillis();
        SimpleDateFormat  df = new SimpleDateFormat("MMM dd yyyy \nhh-mm-ss a");
                                String dateString =  df.format(date);
                                gununTarihi.setText(dateString);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();



        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cikis=new Intent(GunlukYazmaEkrani.this,Giris.class);
                startActivity(cikis);

            }
        });

        kaydetGun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dateName = gununTarihi.getText().toString();
                concent= edtxgunlukYazma.getText().toString();

                try {
                    FileOutputStream fos= openFileOutput(dateName, Context.MODE_PRIVATE);
                    fos.write(concent.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),"Gün Kaydedildi",Toast.LENGTH_SHORT).show();
                Intent kaydetveSecimeGit=new Intent(GunlukYazmaEkrani.this,Secim_Ekrani.class);

                startActivity(kaydetveSecimeGit);
            }
        });

    }

    public void idAtama()
    {

        kaydetGun=(Button)findViewById(R.id.btnKaydetGunlukYazmaEkrani);
        cikis=(Button)findViewById(R.id.btnCikisGunlukYazmaEkrani);

        edtxgunlukYazma=(EditText)findViewById(R.id.edtxGunlukYazmaEkrani);

        gununTarihi=(TextView)findViewById(R.id.txtGunTarihiGunlukYazmaEkrani);

        scrolView=(ScrollView)findViewById(R.id.scrlvwGunlukYazmaEkranı);

    }

}
