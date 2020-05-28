package com.canberkbbc.tabuu.fragments;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.canberkbbc.tabuu.R;
import com.canberkbbc.tabuu.databinding.FragmSplashBinding;
import com.canberkbbc.tabuu.models.TabuuAppData;
import com.canberkbbc.tabuu.models.Word;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SplashFragment extends BaseFragment{
    private static int splashTimeOut=3000;
    View view;
    private FragmSplashBinding splashBinding;
    int wordCount;
    ArrayList<Word> words;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        splashBinding = DataBindingUtil.inflate(inflater, R.layout.fragm_splash,container,false);
        view = splashBinding.getRoot();
        words = new ArrayList<>();
        parser();

        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        animation();
        return view;
    }

    void animation(){
        try {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    uniqueNumber();
                    activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out).replace(R.id.main_frame_layout, new HomePageFragment()).commitAllowingStateLoss();
                }
            },splashTimeOut);
            YoYo.with(Techniques.FadeIn).duration(splashTimeOut).repeat(1).playOn(splashBinding.imgSplash);

        } catch (Exception e) {
            e.printStackTrace();
        }
        YoYo.with(Techniques.FadeIn).duration(splashTimeOut).repeat(1).playOn(splashBinding.imgSplash);
    }

    private void parser() {
        XmlResourceParser parser = getResources().getXml(R.xml.words);
        try{
            processXMLData(parser);
        }catch(IOException e){
            e.printStackTrace();
        }catch (XmlPullParserException e){
            e.printStackTrace();
        }

    }

    protected void processXMLData(XmlResourceParser parser)throws IOException, XmlPullParserException {
        int eventType = -1;
        while(eventType!=parser.END_DOCUMENT){
            if(eventType == XmlResourceParser.START_TAG){
                String studentValue = parser.getName();
                if (studentValue.equals("kelimeler")){
                    String kelime = parser.getAttributeValue(null,"kelime");
                    String yasaklı1 = parser.getAttributeValue(null,"yasak1");
                    String yasaklı2 = parser.getAttributeValue(null,"yasak2");
                    String yasaklı3 = parser.getAttributeValue(null,"yasak3");
                    String yasaklı4 = parser.getAttributeValue(null,"yasak4");
                    String yasaklı5 = parser.getAttributeValue(null,"yasak5");
                    displayValues(kelime,yasaklı1,yasaklı2,yasaklı3,yasaklı4,yasaklı5);
                    wordCount++;
                }
            }
            eventType = parser.next();
        }
    }
    protected void displayValues(String kelime, String yasaklı1,String yasaklı2,String yasaklı3,String yasaklı4,String yasaklı5){
        Word word = new Word(kelime,yasaklı1,yasaklı2,yasaklı3,yasaklı4,yasaklı5);
        words.add(word);
        TabuuAppData.getInstance().setWords(words);
        TabuuAppData.getInstance().setwordCount(wordCount);
    }
    private void uniqueNumber(){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < wordCount; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        TabuuAppData.getInstance().setUniqueNumber(list);
    }
}
