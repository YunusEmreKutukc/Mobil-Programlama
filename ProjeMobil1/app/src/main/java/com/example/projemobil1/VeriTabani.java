package com.example.projemobil1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VeriTabani extends SQLiteOpenHelper {

  public VeriTabani(Context conSql){
      super(conSql,"kalori",null,1);
  }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

      String sql = "create table kalori (id integer primary key autoincrement"+
              ", toplamkalori text not null, tarih date not null) ";

      sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
         sqLiteDatabase.execSQL("drop table if exists kalori");
    }

    public void onDelete(SQLiteDatabase sqLiteDatabase){
      String sql ="DELETE FROM kalori WHERE id=? AND toplamkalori =? AND tarih = ?";
      sqLiteDatabase.execSQL(sql);
  }
    public void select(SQLiteDatabase sqLiteDatabase){
          String sql = "select * from kalori";
          sqLiteDatabase.execSQL(sql);
    }
}
