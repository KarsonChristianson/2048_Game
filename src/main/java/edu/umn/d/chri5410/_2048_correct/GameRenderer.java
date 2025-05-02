package edu.umn.d.chri5410._2048_correct;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;

public class GameRenderer {
    private static final int TILE_SIZE = 100;
    private static final int GAP = 5;

    private final GridPane gridPane;

    public GameRenderer(GridPane gridPane) {
        this.gridPane = gridPane;
        gridPane.setHgap(GAP);
        gridPane.setVgap(GAP);
    }

    public void render(GameBoard board) {
        gridPane.getChildren().clear();
        int size = board.getSize();

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Tile tile = board.getTile(x, y);
                StackPane tilePane = renderTile(tile);
                gridPane.add(tilePane, x, y);
            }
        }
    }

    private StackPane renderTile(Tile tile) {
        Rectangle rect = new Rectangle(TILE_SIZE, TILE_SIZE);
        int value = tile.getValue();

        Color color = switch (value) {
            case 0 -> Color.LIGHTGRAY;
            case 2 -> Color.BEIGE;
            case 4 -> Color.LIGHTYELLOW;
            case 8 -> Color.GOLD;
            case 16 -> Color.ORANGE;
            case 32 -> Color.ORANGERED;
            case 64 -> Color.RED;
            case 128 -> Color.LIGHTBLUE;
            case 256 -> Color.DEEPSKYBLUE;
            case 512 -> Color.MEDIUMBLUE;
            case 1024 -> Color.DARKBLUE;
            case 2048 -> Color.GREEN;
            default -> Color.BLACK;
        };

        rect.setFill(color);
        rect.setArcWidth(15);
        rect.setArcHeight(15);

        Text text = new Text(value == 0 ? "" : Integer.toString(value));
        text.setStyle("-fx-font: 24 arial; -fx-font-weight: bold;");

        StackPane stack = new StackPane();
        stack.getChildren().addAll(rect, text);
        return stack;
    }
}
