package edu.umn.d.chri5410._2048_correct;

public class GameEngine {
    private final GameBoard board;
    private final ScoreTracker scoreTracker = new ScoreTracker();
    private boolean gameOver;

    public GameEngine(int size) {
        this.board = new GameBoard(size);
        this.gameOver = false;
    }

    public boolean update(Direction dir) {
        if (gameOver) return false;

        boolean moved = board.move(dir, scoreTracker);
        if (moved) {
            if (board.isGameOver()) {
                gameOver = true;
            }
        }
        return moved;
    }


    public GameBoard getBoard() {
        return board;
    }

    public int getScore() {
        return scoreTracker.getScore();
    }

    public void restart() {
        gameOver = false;
        scoreTracker.reset();
        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {
                board.getTile(x, y).setValue(0);
            }
        }
        board.spawnRandomTile();
        board.spawnRandomTile();
    }



    public boolean isGameOver() {
        return gameOver;
    }
}
