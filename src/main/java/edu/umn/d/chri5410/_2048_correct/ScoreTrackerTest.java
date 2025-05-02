package edu.umn.d.chri5410._2048_correct;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreTrackerTest {

    private ScoreTracker tracker;

    @BeforeEach
    public void setup() {
        tracker = new ScoreTracker();
    }

    @Test
    public void testInitialScoreIsZero() {
        assertEquals(0, tracker.getScore(), "Score should start at 0");
    }

    @Test
    public void testAddIncreasesScore() {
        tracker.add(8);
        tracker.add(16);
        assertEquals(24, tracker.getScore(), "Score should accumulate added points");
    }

    @Test
    public void testResetClearsScore() {
        tracker.add(32);
        tracker.reset();
        assertEquals(0, tracker.getScore(), "Score should be zero after reset");
    }
}
