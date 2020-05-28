package com.canberkbbc.tabuu.models;

public class Scores {
    String teamNameA;
    String teamNameB;
    int pointA;
    int pointB;

    public Scores(String teamNameA, String teamNameB, int pointA, int pointB) {
        this.teamNameA = teamNameA;
        this.teamNameB = teamNameB;
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public String getTeamNameA() {
        return teamNameA;
    }

    public void setTeamNameA(String teamNameA) {
        this.teamNameA = teamNameA;
    }

    public String getTeamNameB() {
        return teamNameB;
    }

    public void setTeamNameB(String teamNameB) {
        this.teamNameB = teamNameB;
    }

    public int getPointA() {
        return pointA;
    }

    public void setPointA(int pointA) {
        this.pointA = pointA;
    }

    public int getPointB() {
        return pointB;
    }

    public void setPointB(int pointB) {
        this.pointB = pointB;
    }
}
