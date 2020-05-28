package com.canberkbbc.tabuu.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import com.canberkbbc.tabuu.MainActivity;
import com.canberkbbc.tabuu.R;
import com.canberkbbc.tabuu.databinding.FragmScoreBinding;
import com.canberkbbc.tabuu.db.DataSource;
import com.canberkbbc.tabuu.models.Scores;
import com.canberkbbc.tabuu.models.TabuuAppData;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class ScoreFragment extends BaseFragment {
    private View view;
    private FragmScoreBinding binding;
    private int truePoint,falsePoint,score,finishScore;
    private String winnerTeam;
    DataSource dataSource;
    private InterstitialAd mInterstitialAd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragm_score,container,false);
        view = binding.getRoot();


        MobileAds.initialize(activity,"ca-app-pub-5075751572546757~5878362427");
        mInterstitialAd = new InterstitialAd(activity);
        mInterstitialAd.setAdUnitId("ca-app-pub-5075751572546757/5686790732");
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.i("TAG", "onAdFailedToLoad: "+errorCode);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                super.onAdClosed();
            }
        });

        showInterstitialAd();


        dataSource = new DataSource(activity);
        dataSource.open();

        clicks();


        binding.txtTeamA.setText(TabuuAppData.getInstance().getTeamA());
        binding.txtTeamB.setText(TabuuAppData.getInstance().getTeamB());
        finishScore = Integer.parseInt(MainActivity.myPreferences.getScore());

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("tag", "keyCode: " + keyCode);
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    Log.i("tag", "onKey Back listener is working!!!");
                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            truePoint = bundle.getInt("truePoint");
            falsePoint = bundle.getInt("falsePoint");
        }

        score = Integer.parseInt(TabuuAppData.getInstance().getScore());
        if (TabuuAppData.getInstance().getPlayingTeam().equals(TabuuAppData.getInstance().getTeamA())){

            binding.txtTrueA.setText("Turdaki Doğru Sayısı : "+truePoint);
            binding.txtFalseA.setText("Turdaki Yanlış Sayısı : "+falsePoint);
            if (TabuuAppData.getInstance().getTeamAScore()!=null){
                score +=TabuuAppData.getInstance().getTeamAScore();
            }
            TabuuAppData.getInstance().setTeamAScore(score);
            binding.txtScoreA.setText("Toplam Skor : "+score);
            if (TabuuAppData.getInstance().getTeamBScore()!=null){
                binding.txtScoreB.setText("Toplam Skor : "+TabuuAppData.getInstance().getTeamBScore());
            }else{
                binding.txtScoreB.setText("Toplam Skor : "+0);
            }
        }else{
            binding.txtTrueB.setText("Turdaki Doğru Sayısı : "+truePoint);
            binding.txtFalseB.setText("Turdaki Yanlış Sayısı : "+falsePoint);
            if (TabuuAppData.getInstance().getTeamBScore()!=null){
                score += TabuuAppData.getInstance().getTeamBScore();
            }
            TabuuAppData.getInstance().setTeamBScore(score);
            binding.txtScoreB.setText("Toplam Skor : "+score);
            if (TabuuAppData.getInstance().getTeamAScore()!=null){
                binding.txtScoreA.setText("Toplam Skor : "+TabuuAppData.getInstance().getTeamAScore());
            }else{
                binding.txtScoreA.setText("Toplam Skor : "+0);
            }
        }

        if (TabuuAppData.getInstance().getTeamAScore()>=finishScore || TabuuAppData.getInstance().getTeamBScore()>=finishScore){
            String teamA=TabuuAppData.getInstance().getTeamA();
            String teamB=TabuuAppData.getInstance().getTeamB();
            int teamScoreA=TabuuAppData.getInstance().getTeamAScore();
            int teamScoreB=TabuuAppData.getInstance().getTeamBScore();
            Scores scores = new Scores(teamA,teamB,teamScoreA,teamScoreB);
            dataSource.createPreviousGame(scores);
            dataSource.close();
            alertDialog();

        }

        return view;
    }

    private void clicks() {
        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TabuuAppData.getInstance().getPlayingTeam().equals(TabuuAppData.getInstance().getTeamA())){
                    TabuuAppData.getInstance().setPlayingTeam(TabuuAppData.getInstance().getTeamB());
                }else{
                    TabuuAppData.getInstance().setPlayingTeam(TabuuAppData.getInstance().getTeamA());
                }
                if(score>=finishScore){
                    activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out).replace(R.id.main_frame_layout, new HomePageFragment()).addToBackStack(null).commitAllowingStateLoss();
                }else{
                    activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out).replace(R.id.main_frame_layout, new GameFragment()).addToBackStack(null).commitAllowingStateLoss();
                }
            }
        });
    }

    private void alertDialog(){
        if (TabuuAppData.getInstance().getTeamAScore()>=finishScore){
            winnerTeam=TabuuAppData.getInstance().getTeamA();
        }
        else{
            winnerTeam=TabuuAppData.getInstance().getTeamB();
        }
        ViewDialog alert = new ViewDialog();
        alert.showDialog(getActivity(), winnerTeam);
    }

    public void showInterstitialAd(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "Geçiş reklamı yuklenmedi");
        }
    }
}

class ViewDialog {
    public void showDialog(Activity activity,String winTeam){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);

        TextView textWinner = (TextView) dialog.findViewById(R.id.txt_winnerTeam);
        textWinner.setText(winTeam);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
