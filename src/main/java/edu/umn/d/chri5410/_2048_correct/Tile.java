package edu.umn.d.chri5410._2048_correct;

/**
 * Represents a single tile on the 2048 game board.
 * Stores its current value and merge state.

 * Used by GameBoard to evaluate moves and merging logic.
 */

public class Tile extends AbstractGameElement {
    private int value;
    private boolean merged;

    public Tile(int x, int y) {
        super(x, y);
        this.value = 0; // Empty by default
        this.merged = false;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean hasMerged() {
        return merged;
    }

    public void setMerged(boolean merged) {
        this.merged = merged;
    }

    public boolean canMergeWith(Tile other) {
        return other != null && this.value == other.value && !this.merged && !other.merged;
    }

    /**
     * Merges this tile with another tile if they have the same value.
     * This tile's value is doubled and the other tile is cleared.
     *
     * @param other the tile to merge into this tile
     * @return the score gained from the merge (new value) or 0 if merge fails
     */

    public int merge(Tile other) {
        if (canMergeWith(other)) {
            this.value *= 2;
            this.merged = true;
            int gained = this.value;
            other.setValue(0);
            return gained;
        }
        return 0;
    }


    @Override
    public void reset() {
        merged = false;
    }

    @Override
    public String render() {
        return isEmpty() ? "." : Integer.toString(value);
    }
}
