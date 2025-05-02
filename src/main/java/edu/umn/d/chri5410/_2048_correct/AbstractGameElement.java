package edu.umn.d.chri5410._2048_correct;

public abstract class AbstractGameElement {
    protected int x;
    protected int y;

    public AbstractGameElement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void reset();
    public abstract String render();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
