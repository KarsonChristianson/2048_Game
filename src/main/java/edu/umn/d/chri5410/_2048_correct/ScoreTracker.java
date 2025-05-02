package edu.umn.d.chri5410._2048_correct;

/**
 * Tracks and manages the player's score throughout the game.
 * Supports adding to score and resetting for new games.
 */


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

