package com.example.user.gunluk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Giris extends AppCompatActivity {

    DatabaseHelper db;

    TextView txtkullaniciGirisi,txtParola;

    EditText edtxKullaniciGiris,edtxParolaGiris;

    Button btnGiris,btnKayitOl;

    ImageView resim2;

    Uri imageUri;

    Toolbar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        db=new DatabaseHelper(this);


        idAtama();

        setSupportActionBar(actionBar);



        btnKayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent kayitOl=new Intent(Giris.this,Kayit_Ol.class);
                startActivity(kayitOl);

            }
        });

        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kullaniciAdiii=edtxKullaniciGiris.getText().toString();
                String parolaa=edtxParolaGiris.getText().toString();

                Boolean chkKullaniciAdiParola=db.chkKullaniciAdiParola(kullaniciAdiii,parolaa);
                if (chkKullaniciAdiParola==true)
                {
                    Toast.makeText(getApplicationContext(),"Giriş Başarılı",Toast.LENGTH_SHORT).show();
                    Intent giris=new Intent(Giris.this,Secim_Ekrani.class);
                    startActivity(giris);
                }else
                    Toast.makeText(getApplicationContext(),"Parola veya Kullanıcı Adı Yanlış",Toast.LENGTH_SHORT).show();

            }
        });


        resim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galery_int=new Intent(Intent.ACTION_GET_CONTENT);
                galery_int.setType("image/*");
                // galery_int.setAction(Intent.ACTION_GET_CONTENT);
                // galery_int.addCategory(Intent.CATEGORY_OPENABLE);

                startActivityForResult(galery_int,45);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_settings:
                startActivity(new Intent(this,Kayit_Ol.class));
                return true;

        }
        return super.onOptionsItemSelected(item);

    }

    public void idAtama()
    {
        txtkullaniciGirisi=findViewById(R.id.txtKullaniciAdi);
        txtParola=findViewById(R.id.txtKullaniciAdi);

        edtxKullaniciGiris=findViewById(R.id.edtxKullaniciAdiGiris);
        edtxParolaGiris=findViewById(R.id.edtxSifreGiris);

        btnGiris=(Button)findViewById(R.id.btnGiris);
        btnKayitOl=(Button)findViewById(R.id.btnKayitOl);

        resim2=(ImageView)findViewById(R.id.imgvGirisResim);

        actionBar=(Toolbar)findViewById(R.id.action_Barr);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        /*if(requestCode==44)
        {

            Bitmap image=(Bitmap)data.getExtras().get("data");
            ImageView resim=(ImageView)findViewById(R.id.imageView);

            resim.setImageBitmap(image);


        }*/
        if(requestCode==45 && resultCode==RESULT_OK)
        {


            imageUri=data.getData();
            resim2.setImageURI(imageUri);
        }


        //super.onActivityResult(requestCode, resultCode, data);
    }
}
