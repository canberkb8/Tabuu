package com.canberkbbc.tabuu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.canberkbbc.tabuu.fragments.SplashFragment;
import com.canberkbbc.tabuu.local.Preferences;


public class MainActivity extends AppCompatActivity {
    public static Preferences myPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPreferences = new Preferences(this);

        MainActivity.this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame_layout, new SplashFragment())
                .commitAllowingStateLoss();
    }
}
