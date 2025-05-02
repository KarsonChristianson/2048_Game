package edu.umn.d.chri5410._2048_correct;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

    @Test
    public void testIsEmptyInitially() {
        Tile tile = new Tile(0, 0);
        assertTrue(tile.isEmpty(), "New tile should be empty");
    }

    @Test
    public void testMergeFlagResets() {
        Tile tile = new Tile(0, 0);
        tile.setMerged(true);
        tile.reset();
        assertFalse(tile.hasMerged(), "Merge flag should reset after calling reset()");
    }

    @Test
    public void testCanMergeWithSameValue() {
        Tile a = new Tile(0, 0);
        Tile b = new Tile(0, 1);
        a.setValue(2);
        b.setValue(2);
        assertTrue(a.canMergeWith(b), "Tiles with same value and not merged should be mergeable");
    }

    @Test
    public void testCannotMergeWithDifferentValue() {
        Tile a = new Tile(0, 0);
        Tile b = new Tile(0, 1);
        a.setValue(2);
        b.setValue(4);
        assertFalse(a.canMergeWith(b), "Tiles with different values should not be mergeable");
    }

    @Test
    public void testMergeResultValue() {
        Tile a = new Tile(0, 0);
        Tile b = new Tile(0, 1);
        a.setValue(4);
        b.setValue(4);
        int score = a.merge(b);
        assertEquals(8, a.getValue(), "Merging two 4s should result in 8");
        assertEquals(0, b.getValue(), "Source tile should be zeroed after merge");
        assertEquals(8, score, "Returned score should match new value");
    }
}
