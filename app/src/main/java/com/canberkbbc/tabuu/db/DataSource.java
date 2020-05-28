package com.canberkbbc.tabuu.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.canberkbbc.tabuu.models.Scores;

import java.util.ArrayList;


public class DataSource {
    SQLiteDatabase db;
    SqliteLevel myDb;
    public DataSource(Context c){
        myDb = new SqliteLevel(c);
    }
    public void open(){
        db=myDb.getWritableDatabase();
    }
    public void close(){
        myDb.close();
    }

    public void createPreviousGame(Scores scores){
        ContentValues values = new ContentValues();
        values.put("teama", scores.getTeamNameA());
        values.put("teamb", scores.getTeamNameB());
        values.put("pointa", scores.getPointA());
        values.put("pointb", scores.getPointB());
        db.insert("previousgame",null,values);

    }
    public ArrayList<Scores> arrayList(){
        ArrayList<Scores> scoresArrayList = new ArrayList<>();
        String columns[] = {"teama","teamb","pointa","pointb"};
        Cursor cursor = db.query("previousgame",columns,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String teama = cursor.getString(0);
            String teamb = cursor.getString(1);
            int pointa = cursor.getInt(2);
            int pointb = cursor.getInt(3);
            Scores scores = new Scores(teama,teamb,pointa,pointb);
            scoresArrayList.add(scores);
            cursor.moveToNext();
        }
        cursor.close();
        return scoresArrayList;
    }
}
