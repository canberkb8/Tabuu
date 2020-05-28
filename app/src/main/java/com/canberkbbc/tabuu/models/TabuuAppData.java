package com.canberkbbc.tabuu.models;

import java.util.ArrayList;

public class TabuuAppData {
    private static final TabuuAppData ourInstance = new TabuuAppData();
    public static TabuuAppData getInstance(){
        return ourInstance;
    }

    public TabuuAppData() {

    }

    private ArrayList<Word> words;

    public ArrayList<Word> getWords(){
        return words;
    }

    public void setWords(ArrayList<Word> words){
        this.words = words;
    }

    private ArrayList<Integer> uniqueNumbers;

    public ArrayList<Integer> getUniqueNumber(){
        return uniqueNumbers;
    }

    public void setUniqueNumber(ArrayList<Integer> uniqueNumbers){
        this.uniqueNumbers = uniqueNumbers;
    }

    private int wordCount;

    public int getwordCount() {
        return wordCount;
    }

    public int setwordCount(int wordCount) {
        this.wordCount = wordCount;
        return wordCount;
    }

    private String score;

    public String getScore() {
        return score;
    }

    public String setScore(String score) {
        this.score = score;
        return score;
    }
    private String teamA;

    public String getTeamA() {
        return teamA;
    }

    public String setTeamA(String teamA) {
        this.teamA = teamA;
        return teamA;
    }

    private Integer teamAScore;

    public Integer getTeamAScore() {
        return teamAScore;
    }

    public Integer setTeamAScore(Integer teamAScore) {
        this.teamAScore = teamAScore;
        return teamAScore;
    }

    private String teamB;

    public String getTeamB() {
        return teamB;
    }

    public String setTeamB(String teamB) {
        this.teamB = teamB;
        return teamB;
    }

    private Integer teamBScore;

    public Integer getTeamBScore() {
        return teamBScore;
    }

    public Integer setTeamBScore(Integer teamBScore) {
        this.teamBScore = teamBScore;
        return teamBScore;
    }

    private String playingTeam;

    public String getPlayingTeam() {
        return playingTeam;
    }

    public String setPlayingTeam(String playingTeam) {
        this.playingTeam = playingTeam;
        return playingTeam;
    }

    private Integer arrCount;

    public Integer getArrCount() {
        return arrCount;
    }

    public Integer setArrCount(Integer arrCount) {
        this.arrCount = arrCount;
        return arrCount;
    }
}
