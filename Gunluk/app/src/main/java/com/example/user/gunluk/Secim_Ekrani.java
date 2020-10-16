package com.example.user.gunluk;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import static com.example.user.gunluk.NotificationHatırlatma.Channel1;


public class Secim_Ekrani extends AppCompatActivity {

    private final long[] pattern={100,300,300,300};

    private NotificationManagerCompat notificationManagerCompat;

    Notification notification1= new Notification();

    private EditText editTextBildirim;

    Button yeniGunKayit,kayitliGun,btnBildirim;


    TextView txtBildirimTarih;

    EditText edtxHatırlatmaSaat,edtxHatırlatmaDk,edtxHatırlatmaSaniye;

    String saatTutucu,dakikaTutucu,saniyetutucu,bildirimSaati;

    String tarihKontrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secim__ekrani);
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
                                SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
                                String dateString =  df.format(date);
                                tarihKontrol=dateString;
                                txtBildirimTarih.setText(dateString);
                                if(tarihKontrol.equals(bildirimSaati )) {
                                    notificationManagerCompat.notify(1, notification1);
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();

        notificationManagerCompat=NotificationManagerCompat.from(this);

        yeniGunKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent yenigun=new Intent(Secim_Ekrani.this,GunlukYazmaEkrani.class);
                startActivity(yenigun);

            }
        });

        kayitliGun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kayitligun=new Intent(Secim_Ekrani.this,KayitliGunler.class);
                startActivity(kayitligun);
            }
        });
    }

    public void idAtama()
    {

        yeniGunKayit=(Button)findViewById(R.id.btnYeniGunKaydiSecimEkrani);
        kayitliGun=(Button)findViewById(R.id.btnKayıtlıGunSecimEkrani);
        btnBildirim=(Button)findViewById(R.id.btnBildirim);

        editTextBildirim=(EditText) findViewById(R.id.edtxHatirlatmaSaat);
        edtxHatırlatmaSaat=(EditText) findViewById(R.id.edtxHatirlatmaSaat);
        edtxHatırlatmaDk=(EditText) findViewById(R.id.edtxDakikaHatırlatma);
        edtxHatırlatmaSaniye=(EditText) findViewById(R.id.edtxSaniyeHatırlatma);

        txtBildirimTarih=(TextView) findViewById(R.id.txtGununtarihiBildirim);

    }

    public void sendOnChannel1(View v)
    {   String message=editTextBildirim.getText().toString();
        Notification notification= new NotificationCompat.Builder(this, Channel1)
                .setSmallIcon(R.drawable.ic_hatirlatma)
                .setContentTitle("Hatırlatma")
                .setContentText("Gününü Kaydetmeyi Unutma")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setVibrate(pattern)
                .build();

        /*if(edtxHatırlatmaSaat.getText().equals("")||edtxHatırlatmaSaat.getText().equals(null))
        {
            Toast.makeText(getApplicationContext(),"Saati giriniz",Toast.LENGTH_SHORT).show();
        }else*/
        saatTutucu=edtxHatırlatmaSaat.getText().toString();
        dakikaTutucu=edtxHatırlatmaDk.getText().toString();
        saniyetutucu=edtxHatırlatmaSaniye.getText().toString();
        bildirimSaati=saatTutucu+":"+dakikaTutucu+":"+saniyetutucu;
        //.setAutoCancel(true)
        notification1=notification;
      //  notificationManagerCompat.notify(1, notification);

    }

}
