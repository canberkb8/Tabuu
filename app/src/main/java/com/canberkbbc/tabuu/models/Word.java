package com.canberkbbc.tabuu.models;

public class Word {
    String Word,tabu1,tabu2,tabu3,tabu4,tabu5;

    public Word(String word, String tabu1, String tabu2, String tabu3, String tabu4, String tabu5) {
        Word = word;
        this.tabu1 = tabu1;
        this.tabu2 = tabu2;
        this.tabu3 = tabu3;
        this.tabu4 = tabu4;
        this.tabu5 = tabu5;
    }

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }

    public String getTabu1() {
        return tabu1;
    }

    public void setTabu1(String tabu1) {
        this.tabu1 = tabu1;
    }

    public String getTabu2() {
        return tabu2;
    }

    public void setTabu2(String tabu2) {
        this.tabu2 = tabu2;
    }

    public String getTabu3() {
        return tabu3;
    }

    public void setTabu3(String tabu3) {
        this.tabu3 = tabu3;
    }

    public String getTabu4() {
        return tabu4;
    }

    public void setTabu4(String tabu4) {
        this.tabu4 = tabu4;
    }

    public String getTabu5() {
        return tabu5;
    }

    public void setTabu5(String tabu5) {
        this.tabu5 = tabu5;
    }
}
