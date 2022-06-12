package com.example.projemobil1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class SQLIslemleri {

    SQLiteDatabase db;
    VeriTabani veriTabani;

    public  SQLIslemleri(Context conIslem){
        veriTabani = new VeriTabani(conIslem);
    }

    public void dbAc(){
        db = veriTabani.getWritableDatabase();
    }

    public  void  dbKapat(){
        veriTabani.close();
    }

    public void veriEkle(KaloriVeri kaloriVeri){

        ContentValues contentValues = new ContentValues();
        contentValues.put("toplamkalori", kaloriVeri.getKaloriSon());
        contentValues.put("tarih", kaloriVeri.getTarih());
        db.insert("kalori",null , contentValues);
    }

    public void veriGuncelle(KaloriVeri kaloriVeri){
        ContentValues contentValues = new ContentValues();
        contentValues.put("toplamkalori", kaloriVeri.getKaloriSon());
      //  contentValues.put("tarih", kaloriVeri.getTarih());
        db.update("kalori", contentValues, "tarih = ?", null);

    }

    public void veriSil(KaloriVeri kaloriVeri){
     int id = kaloriVeri.getId();
     db.delete("kalori", "id="+id, null);
   }

    public List<String> listele(ListView listView){
        List<String> veriler = new ArrayList<String>();
        String kolonlar [] = {"id","toplamkalori","tarih"};
        Cursor cursor = db.query("kalori", kolonlar, null, null,null , null, null);
        while (cursor.moveToNext()){
            veriler.add(cursor.getInt(0)+" - "+cursor.getString(1)+" - "+cursor.getString(2));
        }
        return veriler;
    }
}
