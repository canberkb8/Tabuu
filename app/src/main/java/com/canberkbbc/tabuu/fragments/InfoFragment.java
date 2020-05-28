package com.canberkbbc.tabuu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.canberkbbc.tabuu.R;
import com.canberkbbc.tabuu.databinding.FragmInfoBinding;

public class InfoFragment extends BaseFragment {
    private View view;
    private FragmInfoBinding binding;
    int controller,controller1,controller2,txtHeight;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragm_info,container,false);
        view = binding.getRoot();
        controller=0;controller1=0;controller2=0;txtHeight=800;
        clicks();
        return view;
    }
    void clicks(){
        binding.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (controller==0){
                    Animation animation = new Animation() {
                        @Override
                        protected void applyTransformation(float interpolatedTime, Transformation t) {
                            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams)binding.btnInfo.getLayoutParams();
                            params.setMargins(0,64*4,0,0);
                            params.height = (int) ( 160+txtHeight*interpolatedTime);
                            binding.btnInfo.setLayoutParams(params);
                        }
                    };
                    animation.setDuration(500);
                    binding.btnInfo.startAnimation(animation);
                    binding.btnInfo.setText("Tabuu oyunu size verilen kartın birinci kelimesini , altındaki kelimeleri kullanmadan grup arkadaşlarınıza en kısa sürede anlatmanızı amaçlamaktadır. Eğerki yasaklı kelimeleri grup arkadaşlarınıza söyletebilirseniz o kelimeyi kullanabilirsiniz. Yasaklı kelime kullanırsanız Tabu olur ve puanınız eksilir. Ayarlar kısmında pas hakkı , tabu yapılınca düşülen puan , oyun süresi ve oyun bitiş puanını ayarlayabilirsiniz.");
                    binding.imgPlus.setVisibility(View.GONE);
                    binding.imgClose.setVisibility(View.VISIBLE);
                    controller=1;
                }else{
                    Animation animation = new Animation() {
                        @Override
                        protected void applyTransformation(float interpolatedTime, Transformation t) {
                            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams)binding.btnInfo.getLayoutParams();
                            params.setMargins(0,64*4,0,0);
                            params.height = (int) ( txtHeight+160-txtHeight*interpolatedTime);
                            binding.btnInfo.setLayoutParams(params);
                        }
                    };
                    animation.setDuration(500);
                    binding.btnInfo.startAnimation(animation);
                    binding.btnInfo.setText("Bu Oyun Nedir Ve Nasıl Oynanır ?");
                    binding.imgPlus.setVisibility(View.VISIBLE);
                    binding.imgClose.setVisibility(View.GONE);
                    controller=0;
                }
            }
        });
        binding.btnInfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (controller1==0){
                    Animation animation = new Animation() {
                        @Override
                        protected void applyTransformation(float interpolatedTime, Transformation t) {
                            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams)binding.btnInfo1.getLayoutParams();
                            params.setMargins(0,16*4,0,0);
                            params.height = (int) ( 160+txtHeight*interpolatedTime);
                            binding.btnInfo1.setLayoutParams(params);
                        }
                    };
                    animation.setDuration(500);
                    binding.btnInfo1.startAnimation(animation);
                    binding.btnInfo1.setText("Her doğru kelime size 1 puan kazandıracaktır. Her tabuda da ayarlar kısmında belirttiğiniz sayıda puan azaltıcaktır. Pas hiç bir şekilde puanlamaya dahil değildir.");
                    binding.imgPlus1.setVisibility(View.GONE);
                    binding.imgClose1.setVisibility(View.VISIBLE);
                    controller1=1;
                }else{
                    Animation animation = new Animation() {
                        @Override
                        protected void applyTransformation(float interpolatedTime, Transformation t) {
                            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams)binding.btnInfo1.getLayoutParams();
                            params.setMargins(0,16*4,0,0);
                            params.height = (int) ( txtHeight+160-txtHeight*interpolatedTime);
                            binding.btnInfo1.setLayoutParams(params);
                        }
                    };
                    animation.setDuration(500);
                    binding.btnInfo1.startAnimation(animation);
                    binding.btnInfo1.setText("Puanlama Sistemi Nasıldır ?");
                    binding.imgPlus1.setVisibility(View.VISIBLE);
                    binding.imgClose1.setVisibility(View.GONE);
                    controller1=0;
                }
            }
        });

    }
}
