package com.canberkbbc.tabuu.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.canberkbbc.tabuu.MainActivity;
import com.canberkbbc.tabuu.models.TabuuAppData;
import com.canberkbbc.tabuu.R;
import com.canberkbbc.tabuu.databinding.FragmGameBinding;

public class GameFragment extends BaseFragment {

    private View view;
    private FragmGameBinding binding;
    CountDownTimer countDownTimer;
    private int progress,score,truePoint,falsePoint,pass,tabuu,arrCount,scoreFinish,checkStop,stopTime,stopProgress;
    long time;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragm_game,container,false);
        view = binding.getRoot();

        enableBackPress();

        if (TabuuAppData.getInstance().getPlayingTeam().equals(TabuuAppData.getInstance().getTeamA())){
            binding.txtTeam.setText(TabuuAppData.getInstance().getTeamA());
        }else{
            binding.txtTeam.setText(TabuuAppData.getInstance().getTeamB());
        }

        pass=Integer.parseInt(MainActivity.myPreferences.getPass());
        tabuu=Integer.parseInt(MainActivity.myPreferences.getTabuu());
        scoreFinish=Integer.parseInt(MainActivity.myPreferences.getScore());

        binding.txtPass.setText(String.valueOf(pass));

        getWord();
        timer();
        clicks();

        return view;
    }
    @Override
    public void onStop() {
        try{
            countDownTimer.cancel();
            binding.btnTabu.setEnabled(false);
            binding.btnTrue.setEnabled(false);
            binding.btnPass.setEnabled(false);
            binding.btnStop.setEnabled(false);
        }catch (NullPointerException ignored){
        }
        super.onStop();
    }
    @Override
    public void onDestroy() {
        try{
            countDownTimer.cancel();
            binding.btnTabu.setEnabled(false);
            binding.btnTrue.setEnabled(false);
            binding.btnPass.setEnabled(false);
            binding.btnStop.setEnabled(false);
        }catch (NullPointerException ignored){
        }
        super.onDestroy();
    }
    void enableBackPress(){
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    return true;
                }
                return false;
            }
        });
    }
    void clicks() {
        binding.btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score++;
                truePoint++;
                binding.txtScore.setText("Skor : "+score);
                getWord();
            }
        });
        binding.btnTabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score-=tabuu;
                falsePoint++;
                binding.txtScore.setText("Skor : "+score);
                getWord();
            }
        });
        binding.btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pass>0){
                    getWord();
                    pass--;
                    binding.txtPass.setText(String.valueOf(pass));
                }else{
                    Toast.makeText(activity,"Pas Hakkınız Doldu",Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkStop==0){
                    binding.btnStop.setText("Devam");
                    binding.txtTabu1.setVisibility(View.INVISIBLE);
                    binding.txtTabu2.setVisibility(View.INVISIBLE);
                    binding.txtTabu3.setVisibility(View.INVISIBLE);
                    binding.txtTabu4.setVisibility(View.INVISIBLE);
                    binding.txtTabu5.setVisibility(View.INVISIBLE);
                    binding.txtWord.setVisibility(View.INVISIBLE);
                    binding.btnTabu.setEnabled(false);
                    binding.btnTrue.setEnabled(false);
                    binding.btnPass.setEnabled(false);
                    binding.btnHome.setVisibility(View.VISIBLE);
                    countDownTimer.cancel();
                    checkStop=1;
                }else{
                    binding.btnStop.setText("Dur");
                    binding.txtTabu1.setVisibility(View.VISIBLE);
                    binding.txtTabu2.setVisibility(View.VISIBLE);
                    binding.txtTabu3.setVisibility(View.VISIBLE);
                    binding.txtTabu4.setVisibility(View.VISIBLE);
                    binding.txtTabu5.setVisibility(View.VISIBLE);
                    binding.txtWord.setVisibility(View.VISIBLE);
                    binding.btnHome.setVisibility(View.INVISIBLE);
                    binding.btnPass.setEnabled(true);
                    binding.btnTabu.setEnabled(true);
                    binding.btnTrue.setEnabled(true);
                    timer();
                    checkStop=0;
                }
            }
        });
        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out).replace(R.id.main_frame_layout, new HomePageFragment()).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }
    private void timer(){
        progress=0;
        binding.progressTimer.setMax(Integer.parseInt(MainActivity.myPreferences.getTime()));
        if (checkStop==0){
            time = Integer.parseInt(MainActivity.myPreferences.getTime())*1000;
            progress=0;
        }else{
            progress = stopProgress;
            time = stopTime;
        }
        countDownTimer = new CountDownTimer(time, 1000) {
            public void onTick(long millisUntilFinished) {
                binding.txtTime.setText(String.valueOf(millisUntilFinished / 1000));
                stopTime = Integer.parseInt(String.valueOf(millisUntilFinished));
                progress++;
                stopProgress = Integer.parseInt(String.valueOf(Integer.parseInt(MainActivity.myPreferences.getTime())*1000-stopTime))/1000;
                binding.progressTimer.setProgress(progress);
            }

            public void onFinish() {
                TabuuAppData.getInstance().setScore(String.valueOf(score));
                ScoreFragment scoreFragment = new ScoreFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("truePoint",truePoint );
                bundle.putInt("falsePoint", falsePoint);
                scoreFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out).replace(R.id.main_frame_layout, scoreFragment).addToBackStack(null).commitAllowingStateLoss();
            }
        }.start();
    }
    private void getWord(){
        if (TabuuAppData.getInstance().getArrCount()!=null){
            arrCount = TabuuAppData.getInstance().getArrCount();
        }else{
            arrCount=0;
        }
        binding.txtWord.setText(TabuuAppData.getInstance().getWords().get(TabuuAppData.getInstance().getUniqueNumber().get(arrCount)).getWord());
        binding.txtTabu1.setText(TabuuAppData.getInstance().getWords().get(TabuuAppData.getInstance().getUniqueNumber().get(arrCount)).getTabu1());
        binding.txtTabu2.setText(TabuuAppData.getInstance().getWords().get(TabuuAppData.getInstance().getUniqueNumber().get(arrCount)).getTabu2());
        binding.txtTabu3.setText(TabuuAppData.getInstance().getWords().get(TabuuAppData.getInstance().getUniqueNumber().get(arrCount)).getTabu3());
        binding.txtTabu4.setText(TabuuAppData.getInstance().getWords().get(TabuuAppData.getInstance().getUniqueNumber().get(arrCount)).getTabu4());
        binding.txtTabu5.setText(TabuuAppData.getInstance().getWords().get(TabuuAppData.getInstance().getUniqueNumber().get(arrCount)).getTabu5());
        arrCount++;
        TabuuAppData.getInstance().setArrCount(arrCount);
    }
}
