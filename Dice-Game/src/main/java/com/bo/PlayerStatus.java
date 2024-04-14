package com.bo;

import java.util.ArrayList;

/**
 * Représente les trois lancers de chaque partie, est le meilleur score du joueur
 *
 *
 *
 */

public class PlayerStatus {

    private int bestScore;

    private ArrayList<Integer> attempts = new ArrayList<Integer>();

    public PlayerStatus(int bestScore) {
        this.bestScore = bestScore;
        int[] array = {0, 0, 0};
        for (int num : array) {
            this.attempts.add(num);
        }
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public ArrayList<Integer> getAttempts() {
        return attempts;
    }

    public void setAttempts(ArrayList<Integer> attempts) {
        this.attempts = attempts;
    }



}
