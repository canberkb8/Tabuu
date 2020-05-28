package com.canberkbbc.tabuu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteLevel extends SQLiteOpenHelper {
    public SqliteLevel(Context c){
        super (c,"previousgame",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table previousgame ( teama text, teamb text, pointa integer, pointb integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int eski, int yeni) {
        db.execSQL("drop table if exists previousgame");
    }
}
