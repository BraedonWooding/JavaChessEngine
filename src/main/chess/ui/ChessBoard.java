package main.chess.ui;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import main.chess.ChessState;
import main.chess.Main;
import main.chess.pieces.Piece;

public class ChessBoard extends GridPane {
    private Label[][] squares = new Label[Main.CHESS_DIMENSIONS][Main.CHESS_DIMENSIONS];
    private Map<String, Image> images = new HashMap<>();
    private Piece selected;
    private ChessState state;

    public ChessBoard(ChessState state) {
        this.state = state;

        for (int x = 0; x < Main.CHESS_DIMENSIONS; x++) {
            for (int y =  0; y < Main.CHESS_DIMENSIONS; y++) {
                Label square = new Label();
                square.setAlignment(Pos.CENTER);
                square.setMinSize(50, 50);
                square.setMaxSize(50, 50);

                int xCopy = x;
                int yCopy = y;
                square.setOnMouseClicked(ev -> {
                    if (this.selected != null) {
                        List<Pair<Integer, Integer>> positions = this.selected != null ? this.selected.getPositions(state) : new ArrayList<>();
                        // Remove checked positions...
                        if (positions.contains(new Pair<>(xCopy, yCopy)) && state.getPiece(xCopy, yCopy) == null) {
                            state.movePiece(selected.getX(), selected.getY(), xCopy, yCopy);
                            selected.setX(xCopy);
                            selected.setY(yCopy);
                        }
                        this.selected = null;
                    } else {
                        this.selected = this.state.getPiece(xCopy, yCopy);
                    }
                    this.render();
                });

                add(square, x, y);
                squares[x][y] = square;
            }
        }
    }

    private boolean isWhite(int x, int y) {
        return x % 2 == 1 && y % 2 == 1 || x % 2 == 0 && y % 2 == 0;
    }

    private String getColor(List<Pair<Integer, Integer>> positions, int x, int y) {
        if (this.selected != null && x == this.selected.getX() && y == this.selected.getY()) {
            return "yellow";
        } else if (positions.contains(new Pair<>(x, y))) {
            return "darkred";
        } else {
            return isWhite(x, y) ? "white" : "gray";
        }
    }

    public void render() {
        List<Pair<Integer, Integer>> positions = this.selected != null ? this.selected.getPositions(state) : new ArrayList<>();
        System.out.println(positions);

        for (int x = 0; x < Main.CHESS_DIMENSIONS; x++) {
            for (int y = 0; y < Main.CHESS_DIMENSIONS; y++) {
                squares[x][y].setStyle("-fx-background-color: " + this.getColor(positions, x, y) + ";");
                Piece piece = state.getPiece(x, y);
                if (piece == null) {
                    squares[x][y].setGraphic(null);
                } else {
                    String imageName = piece.getColor().toLowerCase() + "_" + piece.getName().toLowerCase();
                    if (!images.containsKey(imageName)) {
                        images.put(imageName, new Image(imageName + ".png", 50, 50, true, true));
                    }
                    squares[x][y].setGraphic(new ImageView(images.get(imageName)));
                }
            }
        }
    }
}