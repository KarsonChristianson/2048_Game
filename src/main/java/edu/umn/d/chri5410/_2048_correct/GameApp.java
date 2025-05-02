package edu.umn.d.chri5410._2048_correct;

/*
 * Main JavaFX application class that initializes the window,
 * listens for keyboard input, and updates the game view.

 * Entry point for the 2048 game.
 */


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameApp extends Application {
    private static final int BOARD_SIZE = 4;

    private GameEngine engine;
    private GameRenderer renderer;
    private Text statusText;
    private GridPane boardPane;
    private Text scoreText;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        engine = new GameEngine(BOARD_SIZE);

        BorderPane root = new BorderPane();
        boardPane = new GridPane();
        renderer = new GameRenderer(boardPane);

        statusText = new Text("Use Arrow Keys to Play");

        Button newGameBtn = new Button("New Game");
        newGameBtn.setOnAction(e -> startNewGame());
        newGameBtn.setFocusTraversable(false);

        scoreText = new Text("Score: 0");
        HBox topBar = new HBox(20, statusText, scoreText, newGameBtn);

        root.setTop(topBar);
        root.setCenter(boardPane);

        Scene scene = new Scene(root, 450, 500);
        renderer.render(engine.getBoard());

        scene.setOnKeyPressed(e -> {
            if (engine.isGameOver()) {
                statusText.setText("Game Over! Press 'New Game' to restart.");
            } else {
                Direction dir = keyToDirection(e.getCode());
                if (dir != null) {
                    boolean moved = engine.update(dir);
                    if (moved) {
                        renderer.render(engine.getBoard());
                        scoreText.setText("Score: " + engine.getScore());
                        PersistenceManager.save(engine.getBoard());
                    }
                }
            }
        });

        primaryStage.setTitle("2048 Game - JavaFX Edition");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        boardPane.requestFocus();

        if (PersistenceManager.saveFileExists()) {
            PersistenceManager.load(engine.getBoard());
            renderer.render(engine.getBoard());
        }
    }

    private Direction keyToDirection(KeyCode code) {
        return switch (code) {
            case UP -> Direction.UP;
            case DOWN -> Direction.DOWN;
            case LEFT -> Direction.LEFT;
            case RIGHT -> Direction.RIGHT;
            default -> null;
        };
    }

    private void startNewGame() {
        engine.restart();
        renderer.render(engine.getBoard());
        statusText.setText("New game started!");
        PersistenceManager.save(engine.getBoard());
        boardPane.requestFocus();
        scoreText.setText("Score: 0");
    }
}
