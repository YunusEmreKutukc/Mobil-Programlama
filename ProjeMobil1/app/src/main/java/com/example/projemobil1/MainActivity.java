package com.example.projemobil1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button tarih;

   //public KaloriVeri kaloriVeri = new KaloriVeri();

    EditText suMiktari;
    EditText alinanKalori;

    Spinner aktivite;
    Spinner harcananZaman;

    TextView yakilanKalori;
    TextView suYeterlilik;
    TextView kalSonuc;

    Button hesapla;
    Button sonucKaydet;
    Button vkiEkran;
    Button grafikEkran;

    String[] arraySpinnerakt = new String[]{"Kardiyo","Karışık","Ağırlık"};
    String[]  arraySpinnerzaman = new String[]{"20","30","40","50","60","90","120"};

    float su,suL,suMl;

    //Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatePicker();

        aktivite = findViewById(R.id.spinnerAktivite);
        harcananZaman = findViewById(R.id.spinnerAktiviteZaman);

        suMiktari = findViewById(R.id.editTextSuMiktari);
        alinanKalori = findViewById(R.id.editTextAlinanKal);
        //suYeterlilik.refreshDrawableState();

        yakilanKalori = findViewById(R.id.textViewKalMikSonuc);
        kalSonuc = findViewById(R.id.textViewSonucKalBilgi);
        suYeterlilik = findViewById(R.id.textViewSuYet);

        sonucKaydet = findViewById(R.id.buttonKaydet);
        vkiEkran = findViewById(R.id.buttonBMI);
        grafikEkran = findViewById(R.id.buttonGrafik);
        hesapla = findViewById(R.id.buttonHesap);
        tarih = findViewById(R.id.buttonTarih);
        tarih.setText(getTodaysDate());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinnerakt);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aktivite.setAdapter(adapter);

        ArrayAdapter<String> adapterB = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinnerzaman);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        harcananZaman.setAdapter(adapterB);


        //Hesaplaya Basıldıktan sonraki eylemler

        hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SuMik();
                    YakilanKal();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        vkiEkran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , ActivitySecond.class);
                startActivity(intent);
                //finish();
            }
        });

        grafikEkran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , ActivityThird.class);
                startActivity(intent);
            }
        });

        tarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TarihGoster();
            }
        });

        SQLIslemleri sqlIslemleri = new SQLIslemleri(this);
        sqlIslemleri.dbAc();

        sonucKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(kalSonuc.getText().toString() != ""){
                    KaloriVeri kaloriVeri = new KaloriVeri(1, kalSonuc.getText().toString(), tarih.getText().toString());
                    sqlIslemleri.veriEkle(kaloriVeri);}

            }
        });

    }


    public void YakilanKal(){

       String Akt = aktivite.getSelectedItem().toString();
      String Zam =harcananZaman.getSelectedItem().toString();

      /*String alinanKal = alinanKalori.getText().toString();
      float alKalori = Float.parseFloat(alinanKal);*/

      yakilanKalori.setText(Zam);
       int ZamHesap = Integer.parseInt(Zam);
       float Kalori;
       String sonKal;


      if(Akt == "Kardiyo"){
          Kalori = ZamHesap * 4;
          sonKal = Float.toString(Kalori);
          yakilanKalori.setText(sonKal);
          String alinanKal = alinanKalori.getText().toString();
          float kalDeger = Float.parseFloat(alinanKal);
          float toplmSonuc  = kalDeger - Kalori;
          String sonDeger = String.valueOf(toplmSonuc);
          kalSonuc.setText(sonDeger);
         // kaloriVeri.setKaloriSon(sonDeger);
      }
      else if(Akt == "Karışık"){
          Kalori = ZamHesap * 5;
          sonKal = Float.toString(Kalori);
          yakilanKalori.setText(sonKal);
          String alinanKal = alinanKalori.getText().toString();
          float kalDeger = Float.parseFloat(alinanKal);
          float toplmSonuc  = kalDeger - Kalori;
          String sonDeger = String.valueOf(toplmSonuc);
          kalSonuc.setText(sonDeger);
         // kaloriVeri.setKaloriSon(sonDeger);
      }
      else if(Akt == "Ağırlık"){
          Kalori = ZamHesap * 7;
          sonKal = Float.toString(Kalori);
          yakilanKalori.setText(sonKal);
          String alinanKal = alinanKalori.getText().toString();
          float kalDeger = Float.parseFloat(alinanKal);
          float toplmSonuc  = kalDeger - Kalori;
          String sonDeger = String.valueOf(toplmSonuc);
          kalSonuc.setText(sonDeger);
         // kaloriVeri.setKaloriSon(sonDeger);
      }
    }



      public void SuMik(){
             String deger ;
             deger = suMiktari.getText().toString();
              su = Float.parseFloat(deger);
          if(su >10){
              suMl = su / 1000;

              if(suMl > 1 && suMl <2){

                  suYeterlilik.setText("Neredeyse Yeterli");
              }
              else if(suMl>=2){
                  suYeterlilik.setText("Yeterli");
              }
              else if(suMl<=1){
                  suYeterlilik.setText("Daha Fazla Su İçin");
              }
          }
          else if(su<10){
              suL = su;
              if(suL > 1 && suL <2){
                  suYeterlilik.setText("Neredeyse Yeterli");
              }
              else if(suL>=2){
                  suYeterlilik.setText("Yeterli");
              }
              else if(suL<=1){
                  suYeterlilik.setText("Daha Fazla Su İçin");
              }
          }
      }

      //Tarih için gereken kodlar aşağıda

      ///////////////

    private String getTodaysDate(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month +1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return makeDateString(day, month, year );
    }


    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                tarih.setText(date);

            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog( this,style, dateSetListener, year, month, day);
       // datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year ){
        return day+" / "+getMonthFormat(month) + " / "+year;
    }

    private String getMonthFormat(int month){
        if(month == 1)
            return "Ocak";
        if(month == 2)
            return "Şubat";
        if(month == 3)
            return "Mart";
        if(month == 4)
            return "Nisan";
        if(month == 5)
            return "Mayıs";
        if(month == 6)
            return "Haziran";
        if(month == 7)
            return "Temmmuz";
        if(month == 8)
            return "Ağustos";
        if(month == 9)
            return "Eylül";
        if(month == 10)
            return "Ekim";
        if(month == 11)
            return "Kasım";
        if(month == 12)
            return "Aralık";

        return "Ocak";
    }

    public void TarihGoster(){
           datePickerDialog.show();
    }

    /////////////

}