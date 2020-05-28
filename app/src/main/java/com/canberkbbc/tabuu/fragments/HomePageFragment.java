package com.canberkbbc.tabuu.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.canberkbbc.tabuu.MainActivity;
import com.canberkbbc.tabuu.R;
import com.canberkbbc.tabuu.databinding.FragmHomepageBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class HomePageFragment extends BaseFragment{

    private View view;
    private FragmHomepageBinding binding;
    private AdView mAdView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragm_homepage,container,false);
        view = binding.getRoot();

        prefControl();

        mAdView = binding.adView;
        AdRequest BannerAdRequest = new AdRequest.Builder().build();
        mAdView.loadAd(BannerAdRequest);

        killAppToBackPress();
        clicks();

        return view;
    }



    private void clicks() {
        binding.imgPlayBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out).replace(R.id.main_frame_layout, new TeamFragment()).addToBackStack(null).commitAllowingStateLoss();
            }
        });
        binding.imgScoreBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out).replace(R.id.main_frame_layout, new PreviousGames()).addToBackStack(null).commitAllowingStateLoss();
            }
        });binding.imgSettingsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out).replace(R.id.main_frame_layout, new SettingsFragment()).addToBackStack(null).commitAllowingStateLoss();
            }
        });
        binding.imgHelpBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out).replace(R.id.main_frame_layout, new InfoFragment()).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }
    void killAppToBackPress(){
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("tag", "keyCode: " + keyCode);
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    int pid = android.os.Process.myPid();
                    android.os.Process.killProcess(pid);
                    return false;
                }
                return false;
            }
        });
    }
    private void prefControl(){
        if (MainActivity.myPreferences.getTime().equals("")){
            MainActivity.myPreferences.setTime("120");
        }
        if (MainActivity.myPreferences.getPass().equals("")){
            MainActivity.myPreferences.setPass("2");
        }
        if (MainActivity.myPreferences.getTabuu().equals("")){
            MainActivity.myPreferences.setTabuu("1");
        }
        if (MainActivity.myPreferences.getScore().equals("")){
            MainActivity.myPreferences.setScore("50");
        }
    }
}
