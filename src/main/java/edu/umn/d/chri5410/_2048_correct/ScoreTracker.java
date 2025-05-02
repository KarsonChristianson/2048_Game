package edu.umn.d.chri5410._2048_correct;

public class ScoreTracker {
    private int score;

    public ScoreTracker() {
        this.score = 0;
    }

    public void reset() {
        score = 0;
    }

    public void add(int amount) {
        score += amount;
    }

    public int getScore() {
        return score;
    }
}

