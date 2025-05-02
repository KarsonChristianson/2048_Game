package edu.umn.d.chri5410._2048_correct;

/*
 * Represents the 2048 game board and manages all tile movement, merging, and game state.
 * Handles direction-based movement, spawn logic, and game-over detection.
 *
 * @author Karson
 * @version 1.0
 */


import java.util.Random;

public class GameBoard {
    private final int size;
    private final Tile[][] grid;
    private final Random random = new Random();

    public GameBoard(int size) {
        this.size = size;
        this.grid = new Tile[size][size];
        initializeTiles();
        spawnRandomTile();
        spawnRandomTile();
    }

    private void initializeTiles() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                grid[x][y] = new Tile(x, y);
            }
        }
    }

    public Tile getTile(int x, int y) {
        return grid[x][y];
    }

    public void resetMergeFlags() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                grid[x][y].reset();
            }
        }
    }

    /**
     * Moves all tiles on the board in the specified direction.
     * Handles merging logic and updates the score using ScoreTracker.
     *
     * @param direction the direction of the move (UP, DOWN, LEFT, RIGHT)
     * @param scoreTracker the score manager to update merge points
     * @return true if any tiles moved or merged; false otherwise
     */


    public boolean move(Direction direction, ScoreTracker scoreTracker) {
        boolean moved = false;
        resetMergeFlags();

        int dx = 0, dy = 0;
        switch (direction) {
            case UP -> dy = -1;
            case DOWN -> dy = 1;
            case LEFT -> dx = -1;
            case RIGHT -> dx = 1;
        }

        int startX = (dx == 1) ? size - 1 : 0;
        int endX = (dx == 1) ? -1 : size;
        int stepX = (dx == 1) ? -1 : 1;

        int startY = (dy == 1) ? size - 1 : 0;
        int endY = (dy == 1) ? -1 : size;
        int stepY = (dy == 1) ? -1 : 1;

        for (int x = startX; x != endX; x += stepX) {
            for (int y = startY; y != endY; y += stepY) {
                Tile tile = grid[x][y];
                if (!tile.isEmpty()) {
                    int currentX = x;
                    int currentY = y;

                    while (true) {
                        int nextX = currentX + dx;
                        int nextY = currentY + dy;

                        if (!isValid(nextX, nextY))
                            break;

                        Tile nextTile = grid[nextX][nextY];
                        if (nextTile.isEmpty()) {
                            nextTile.setValue(tile.getValue());
                            tile.setValue(0);
                            tile = nextTile;
                            currentX = nextX;
                            currentY = nextY;
                            moved = true;
                        } else if (tile.canMergeWith(nextTile)) {
                            int gained = nextTile.merge(tile);
                            scoreTracker.add(gained);
                            tile.setValue(0);
                            moved = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        if (moved) {
            spawnRandomTile();
        }

        return moved;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    public void spawnRandomTile() {
        int emptyCount = 0;
        for (Tile[] row : grid)
            for (Tile tile : row)
                if (tile.isEmpty()) emptyCount++;

        if (emptyCount == 0) return;

        int index = random.nextInt(emptyCount);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (grid[x][y].isEmpty()) {
                    if (index == 0) {
                        grid[x][y].setValue(random.nextDouble() < 0.9 ? 2 : 4);
                        return;
                    }
                    index--;
                }
            }
        }
    }

    public boolean isGameOver() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Tile tile = grid[x][y];
                if (tile.isEmpty()) return false;
                for (Direction dir : Direction.values()) {
                    int nx = x, ny = y;
                    switch (dir) {
                        case UP -> ny--;
                        case DOWN -> ny++;
                        case LEFT -> nx--;
                        case RIGHT -> nx++;
                    }
                    if (isValid(nx, ny) && tile.canMergeWith(grid[nx][ny])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int getSize() {
        return size;
    }
}
