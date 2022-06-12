package com.example.projemobil1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;


public class ActivitySecond extends AppCompatActivity {

    Spinner cinsiyet;

    EditText boy;
    EditText kilo;
    EditText belCevre;
    EditText boyunCevre;
    EditText yas;

    TextView bmiText;
    TextView idealKilo;
    TextView yagOran;
    TextView maxKal;

    Button hesapla;
    Button kaydet;
    Button anaMenu;
    Button grafik;

    String vBoy, vKilo, vBelCevre, vBoyunCevre, vYas;
    float sBoy, sKilo, sBelCevre, sBoyunCevre, sYas;


    String[] arraySpinnerCins = new String[]{"Erkek","Kadın"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        boy = findViewById(R.id.editTextBoy);
        kilo = findViewById(R.id.editTextKilo);
        belCevre = findViewById(R.id.editTextNumberBel);
        boyunCevre = findViewById(R.id.editTextNumberBoyun);
        yas  = findViewById(R.id.editTextNumberYas);

        bmiText = findViewById(R.id.textViewBMI);
        idealKilo =findViewById(R.id.textViewIdealKilo);
        yagOran  = findViewById(R.id.textViewYagOran);
        maxKal = findViewById(R.id.textViewAlMaxKal);

        cinsiyet = findViewById(R.id.spinnerCinsiyet);

        hesapla = findViewById(R.id.buttonHesapla);
        kaydet = findViewById(R.id.buttonKayit);
        anaMenu = findViewById(R.id.buttonAna);
        grafik = findViewById(R.id.buttonGraph);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinnerCins);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cinsiyet.setAdapter(adapter);

        hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BMIhesapla();
                    GunlukKalHesapla();
                    YagOrani();
                    IdealKilo();
                }
                catch (Exception e){
                  e.printStackTrace();
                }

            }
        });

        anaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitySecond.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        grafik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitySecond.this , ActivityThird.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void IdealKilo(){
        String getValue;
        String cinsiyetSecim;
        cinsiyetSecim = cinsiyet.getSelectedItem().toString();

        vBoy = boy.getText().toString();
        sBoy = Float.parseFloat(vBoy);

        vKilo = kilo.getText().toString();
        sKilo = Float.parseFloat(vKilo);

        double sonucKilo ;
       // 2,3 x [(Boyunuz(cm)/2,54) – 60]
        if(cinsiyetSecim == "Erkek"){
            sonucKilo = 50 + (2.3 * ((sBoy / 2.54)- 60)) ;
            getValue = String.valueOf(Math.ceil(sonucKilo));
            idealKilo.setText(getValue);
        }
        else if(cinsiyetSecim == "Kadın"){
            sonucKilo = 45.5 + (2.3 * ((sBoy / 2.54)- 60)) ;
            getValue = String.valueOf(Math.ceil(sonucKilo));
            idealKilo.setText(getValue);
        }
    }
    public void YagOrani(){
        String cinsiyetSecim;
        cinsiyetSecim = cinsiyet.getSelectedItem().toString();

        vBoy = boy.getText().toString();
        sBoy = Float.parseFloat(vBoy);

        vKilo = kilo.getText().toString();
        sKilo = Float.parseFloat(vKilo);

        vBelCevre = belCevre.getText().toString();
        sBelCevre = Float.parseFloat(vBelCevre);

        vBoyunCevre = boyunCevre.getText().toString();
        sBoyunCevre = Float.parseFloat(vBoyunCevre);

              float asBoy = sBoy / 100;
              float asCevre = (sBelCevre - sBoyunCevre);
              double a = Math.log10(asCevre);
              double b = Math.log10(asBoy);
              double ySonuc;
              String getValue;

//495 / (1.29579 - (0.35004 * a )+ ( 0.22100 * b)) - 450
// (495 / (1.29579-(0.35004 *a)+(0.22100 * b))) - 450

        //(495 / (1.0324 - 0.19077 * a) + 0.15456 * b)) - 450);
        if(cinsiyetSecim == "Erkek"){
            //Erkek
            ySonuc = ((495 / (1.0324 - 0.19077 * a) + 0.15456 * b) - 450) / 10;
            getValue = String.valueOf(Math.ceil(ySonuc));
            yagOran.setText(getValue);

        }
        else if(cinsiyetSecim == "Kadın"){
            //Kadın
            ySonuc = ((495 / (1.29579 - 0.35004 * a) + 0.22100 * b) - 450) / 10;
            getValue = String.valueOf(Math.ceil(ySonuc));
            yagOran.setText(getValue);
        }


    }

    public  void BMIhesapla(){
        String getValue;
       double vSonuc;

        vBoy = boy.getText().toString();
        sBoy = Float.parseFloat(vBoy);

        vKilo = kilo.getText().toString();
        sKilo = Float.parseFloat(vKilo);

        if(sBoy > 3){
            float nBoy = sBoy /100;
            vSonuc = sKilo / (nBoy * nBoy);

           getValue = String.valueOf(Math.floor(vSonuc));
           //getValue = String.valueOf(vSonuc);
            bmiText.setText(getValue);
        }

        else if(sBoy<3 && sBoy >0){
            vSonuc =sKilo / (sBoy * sBoy);
            getValue = String.valueOf(Math.ceil(vSonuc));
            bmiText.setText(getValue);
        }

    }

    public  void  GunlukKalHesapla(){
        double dBoy;
        double dKilo;
        String getValue;
        String cinsiyetSecim;
        cinsiyetSecim = cinsiyet.getSelectedItem().toString();

        vBoy = boy.getText().toString();
        sBoy = Float.parseFloat(vBoy);

        vKilo = kilo.getText().toString();
        sKilo = Float.parseFloat(vKilo);

        vYas = yas.getText().toString();
        sYas = Float.parseFloat(vYas);

       double aSonuc;//Yapılan işlemlerden elde edilen sonucu tutar

        if(cinsiyetSecim == "Erkek"){

            aSonuc = 66 + (13.7 * sKilo) + (5 * sBoy) - (6.8 * sYas);
            //getValue = String.valueOf(Math.ceil(aSonuc));
            getValue = String.valueOf(aSonuc);
            maxKal.setText(getValue);

        }
        else if(cinsiyetSecim == "Kadın"){

            aSonuc = 655 + (9.6 * sKilo) + (1.8 * sBoy) - (4.7 * sYas);
            getValue = String.valueOf(Math.ceil(aSonuc));
            maxKal.setText(getValue);
        }

    }

}