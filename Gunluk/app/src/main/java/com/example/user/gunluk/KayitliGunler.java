package com.example.user.gunluk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KayitliGunler extends AppCompatActivity {

    Button duzenle,kaydet,cikis,göster;

    ScrollView scrlview;

    EditText yaziEkrani;

    TextView kayitliGunTarihi;

    Spinner spinner;

     public int visiblee=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayitli_gunler);



        idAtama();

        getDateName();



        göster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedDate= String.valueOf(spinner.getSelectedItem());
                try {
                    String data=getData(selectedDate);
                    yaziEkrani.setText(data);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            private String getData(String selectedDate) throws IOException {
                String value="";
                FileInputStream fis=openFileInput(selectedDate);
                byte[] input=new byte[fis.available()];
                while(fis.read(input)!=-1)
                {
                    value += new String(input);
                }
                fis.close();
                return  value;
            }

        });

        duzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(visiblee==1) {

                    duzenle.setVisibility(View.INVISIBLE);
                    kaydet.setVisibility(View.VISIBLE);
                    visiblee=0;
                    yaziEkrani.setEnabled(true);
                }
            }
        });
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(visiblee==0) {

                    kaydet.setVisibility(View.INVISIBLE);
                    duzenle.setVisibility(View.VISIBLE);
                    visiblee=1;
                    yaziEkrani.setEnabled(false);

                }
            }
        });

        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cikis=new Intent(KayitliGunler.this,Giris.class);
                startActivity(cikis);

            }
        });


    }

    private void getDateName() {
        String[] dateName = getApplicationContext().fileList(); // created an array
        List<String> list = new ArrayList<String>(); //transformed to an array list
        for(int i=0; i<dateName.length; i++) //the loop is for spinner
        {
            list.add(dateName[i]); //add to list
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        spinner.setAdapter(adapter); //to transform data to spinner

    }

    public void idAtama()
    {

        duzenle=(Button)findViewById(R.id.btnDuzenleKayitliGunler);
        kaydet=(Button)findViewById(R.id.btnKaydetKayitliGunler);
        cikis=(Button)findViewById(R.id.btnKayitliGunlerCikis);
        göster=(Button)findViewById(R.id.show);

        scrlview=(ScrollView)findViewById(R.id.scrlcwKayitliGunler);

        yaziEkrani=(EditText)findViewById(R.id.edtxKayitliGunler);

        kayitliGunTarihi=(TextView)findViewById(R.id.txtKayitliGunler);

        spinner=(Spinner)findViewById(R.id.spinner);

    }

}
