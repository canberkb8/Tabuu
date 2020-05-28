package com.canberkbbc.tabuu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.canberkbbc.tabuu.models.TabuuAppData;
import com.canberkbbc.tabuu.R;
import com.canberkbbc.tabuu.databinding.FragmTeamBinding;

public class TeamFragment extends BaseFragment {

    private View view;
    private FragmTeamBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragm_team,container,false);
        view = binding.getRoot();



        clicks();

        return view;
    }

    void clicks() {
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabuuAppData.getInstance().setTeamAScore(0);
                TabuuAppData.getInstance().setTeamBScore(0);
                if (binding.edtTxtTeama.getText().toString().equals("")){
                    TabuuAppData.getInstance().setTeamA("Takım A");
                }else{
                    TabuuAppData.getInstance().setTeamA(binding.edtTxtTeama.getText().toString());
                }
                if (binding.edtTxtTeamb.getText().toString().equals("")){
                    TabuuAppData.getInstance().setTeamB("Takım B");
                }else{
                    TabuuAppData.getInstance().setTeamB(binding.edtTxtTeamb.getText().toString());
                }
                TabuuAppData.getInstance().setPlayingTeam(TabuuAppData.getInstance().getTeamA());
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out).replace(R.id.main_frame_layout, new GameFragment()).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }

}
