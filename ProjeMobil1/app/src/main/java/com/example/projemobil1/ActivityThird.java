package com.example.projemobil1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivityThird extends AppCompatActivity {

    ListView listView ;
    Button buttonListele;
    Button anaMenu;
    Button VkiMenu;
    Button sil;
    EditText txtid;

    SQLIslemleri sqlIslemleri = new SQLIslemleri(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        sqlIslemleri.dbAc();

        listView = findViewById(R.id.listView);
        buttonListele = findViewById(R.id.buttonListele);
        anaMenu = findViewById(R.id.buttonAmenu);
        VkiMenu = findViewById(R.id.buttonVkiHsap);
        sil = findViewById(R.id.buttonSil);
        txtid = findViewById(R.id.txtID);
        List<String> vVeriler = sqlIslemleri.listele(listView);
        ArrayAdapter<String> arrayAdapter =  new ArrayAdapter<String>(ActivityThird.this, android.R.layout.simple_list_item_1,android.R.id.text1,vVeriler);

        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idText = txtid.getText().toString();
                int intID = Integer.parseInt(idText);
                KaloriVeri kaloriVeri = new KaloriVeri(intID, null,null );
                sqlIslemleri.veriSil(kaloriVeri);
                arrayAdapter.clear();
                arrayAdapter.addAll(vVeriler);
                arrayAdapter.notifyDataSetChanged();

            }
        });
        buttonListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listView.setAdapter(arrayAdapter);

            }
        });

        anaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityThird.this , MainActivity.class);
                startActivity(intent);
            }
        });

        VkiMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityThird.this , ActivitySecond.class);
                startActivity(intent);
                finish();
            }
        });

    }


}