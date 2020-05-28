package com.canberkbbc.tabuu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.canberkbbc.tabuu.MainActivity;
import com.canberkbbc.tabuu.R;
import com.canberkbbc.tabuu.databinding.FragmSettingsBinding;

public class SettingsFragment extends BaseFragment {

    private View view;
    private FragmSettingsBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragm_settings,container,false);
        view = binding.getRoot();
        seekBars();
        return view;
    }

    void seekBars(){
        //Time Seek Bar
        binding.seekBarTime.setMax(150);
        binding.seekBarTime.setProgress(Integer.parseInt(MainActivity.myPreferences.getTime())-30);
        binding.txtTime.setText("Oyun Süresi : "+MainActivity.myPreferences.getTime());
        binding.seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 150){
                    progress=150;
                    seekBar.setProgress(150);
                    MainActivity.myPreferences.setTime("180");
                }if (progress<150 && progress > 120){
                    progress=120;
                    seekBar.setProgress(120);
                    MainActivity.myPreferences.setTime("150");
                }if (progress<120 && progress > 90){
                    progress=90;
                    seekBar.setProgress(90);
                    MainActivity.myPreferences.setTime("120");
                }if (progress<90 && progress > 60){
                    progress=60;
                    seekBar.setProgress(60);
                    MainActivity.myPreferences.setTime("90");
                }if (progress<60 && progress > 30){
                    progress=30;
                    seekBar.setProgress(30);
                    MainActivity.myPreferences.setTime("60");
                }if (progress<30 ){
                    progress=0;
                    seekBar.setProgress(0);
                    MainActivity.myPreferences.setTime("30");
                }
                binding.txtTime.setText("Oyun Süresi : "+(progress+30));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Pass Seek Bar
        binding.seekBarPass.setMax(4);
        binding.seekBarPass.setProgress(Integer.parseInt(MainActivity.myPreferences.getPass())-1);
        binding.txtPass.setText("Pas Hakkı : "+MainActivity.myPreferences.getPass());
        binding.seekBarPass.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 4){
                    seekBar.setProgress(4);
                    MainActivity.myPreferences.setPass("5");
                }if (progress==3){
                    seekBar.setProgress(3);
                    MainActivity.myPreferences.setPass("4");
                }if (progress==2){
                    seekBar.setProgress(2);
                    MainActivity.myPreferences.setPass("3");
                }if (progress==1){
                    seekBar.setProgress(1);
                    MainActivity.myPreferences.setPass("2");
                }if (progress==0 ){
                    seekBar.setProgress(0);
                    MainActivity.myPreferences.setPass("1");
                }
                binding.txtPass.setText("Pas Hakkı : "+(progress+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        // Tabuu Seek Bar
        binding.seekBarTabuu.setMax(3);
        binding.seekBarTabuu.setProgress(Integer.parseInt(MainActivity.myPreferences.getTabuu())-1);
        binding.txtTabuu.setText("Tabuu : "+MainActivity.myPreferences.getTabuu());
        binding.seekBarTabuu.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress==3){
                    seekBar.setProgress(3);
                    MainActivity.myPreferences.setTabuu("4");
                }if (progress==2){
                    seekBar.setProgress(2);
                    MainActivity.myPreferences.setTabuu("3");
                }if (progress==1){
                    seekBar.setProgress(1);
                    MainActivity.myPreferences.setTabuu("2");
                }if (progress==0 ){
                    seekBar.setProgress(0);
                    MainActivity.myPreferences.setTabuu("1");
                }
                binding.txtTabuu.setText("Tabuu : "+(progress+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        // Score Seek Bar
        binding.seekBarWinpoint.setMax(125);
        binding.seekBarWinpoint.setProgress(Integer.parseInt(MainActivity.myPreferences.getScore())-25);
        binding.txtWinpoint.setText("Kazanma Puanı : "+MainActivity.myPreferences.getScore());
        binding.seekBarWinpoint.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 125){
                    progress=125;
                    seekBar.setProgress(125);
                    MainActivity.myPreferences.setScore("150");
                }if (progress<125 && progress > 100){
                    progress=100;
                    seekBar.setProgress(100);
                    MainActivity.myPreferences.setScore("125");
                }if (progress<100 && progress > 75){
                    progress=75;
                    seekBar.setProgress(75);
                    MainActivity.myPreferences.setScore("100");
                }if (progress<75 && progress > 50){
                    progress=50;
                    seekBar.setProgress(50);
                    MainActivity.myPreferences.setScore("75");
                }if (progress<50 && progress > 25){
                    progress=25;
                    seekBar.setProgress(25);
                    MainActivity.myPreferences.setScore("50");
                }if (progress<25 ){
                    progress=0;
                    seekBar.setProgress(0);
                    MainActivity.myPreferences.setScore("25");
                }
                binding.txtWinpoint.setText("Kazanma Puanı : "+(progress+25));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
