package com.canberkbbc.tabuu.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    //Ekranın sağ alt köşesindeki Device File Explorerden > data > data > (app ismini arat) > app isminin içinde shared_prefs
    private SharedPreferences sharedPreferences;    //SharedPreferences referansı, local'de data tutmak için kullanılır

    public Preferences(Context context){    //Dışardan tanımlayacağın constructor
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    // Bir tane arryList Tanımla Burda Tum oyanan oyunların skorlarını tut geçmiş oyun skorları sayfasına burdan çek.

    public void setTime(String time)  {
        sharedPreferences.edit().putString("time", time).apply();
    }
    public String getTime()
    {
        return sharedPreferences.getString("time","");
    }

    public void setPass(String pass)  {
        sharedPreferences.edit().putString("pass", pass).apply();
    }
    public String getPass()
    {
        return sharedPreferences.getString("pass","");
    }

    public void setTabuu(String tabuu)  {
        sharedPreferences.edit().putString("tabuu", tabuu).apply();
    }
    public String getTabuu()
    {
        return sharedPreferences.getString("tabuu","");
    }

    public void setScore(String score)  {
        sharedPreferences.edit().putString("score", score).apply();
    }
    public String getScore()
    {
        return sharedPreferences.getString("score","");
    }

    public void deletePreferencesValues() {
        sharedPreferences.edit().clear().apply();

    }
}
