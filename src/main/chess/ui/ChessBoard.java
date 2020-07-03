package main.chess.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import main.chess.ChessGame;
import main.chess.ChessState;
import main.chess.Main;
import main.chess.Vec2D;
import main.chess.pieces.Piece;

public class ChessBoard extends GridPane {
    private Label[][] squares = new Label[Main.CHESS_DIMENSIONS][Main.CHESS_DIMENSIONS];
    private Map<String, Image> images = new HashMap<>();

    public ChessBoard(ChessState state, ChessGame engine) {
        for (int x = 0; x < Main.CHESS_DIMENSIONS; x++) {
            for (int y =  0; y < Main.CHESS_DIMENSIONS; y++) {
                Label square = new Label();
                square.setAlignment(Pos.CENTER);
                square.setMinSize(50, 50);
                square.setMaxSize(50, 50);

                int xCopy = x;
                int yCopy = y;
                square.setOnMouseClicked(ev -> {
                    List<Vec2D> positions = engine.onSquareClick(new Vec2D(xCopy, yCopy));
                    updateVisuals(positions);
                });

                Piece piece = state.getPiece(x, y);
                if (piece != null) {
                    piece.getPos().observe(state::pieceListener);
                    piece.getPos().observe((oldValue, newValue) -> {
                        if (newValue != null) {
                            squares[newValue.getX()][newValue.getY()].setGraphic(squares[oldValue.getX()][oldValue.getY()].getGraphic());
                            squares[oldValue.getX()][oldValue.getY()].setGraphic(null);
                        }
                    });
                }

                add(square, x, y);
                squares[x][y] = square;
            }
        }
    }

    private boolean isWhite(int x, int y) {
        return x % 2 == 1 && y % 2 == 1 || x % 2 == 0 && y % 2 == 0;
    }

    private String getColor(List<Vec2D> positions, Vec2D vec) {
        if (positions.contains(vec)) {
            return squares[vec.getX()][vec.getY()].getGraphic() != null ? "darkRed" : "yellow";
        } else {
            return isWhite(vec.getX(), vec.getY()) ? "white" : "gray";
        }
    }

    public void updateVisuals(List<Vec2D> positions) {
        for (int x = 0; x < Main.CHESS_DIMENSIONS; x++) {
            for (int y = 0; y < Main.CHESS_DIMENSIONS; y++) {
                squares[x][y].setStyle("-fx-background-color: " + getColor(positions, new Vec2D(x, y)) + ";");
            }
        }
    }

    public void initialRender(ChessState state) {
        for (int x = 0; x < Main.CHESS_DIMENSIONS; x++) {
            for (int y = 0; y < Main.CHESS_DIMENSIONS; y++) {
                squares[x][y].setStyle("-fx-background-color: " + (isWhite(x, y) ? "white" : "gray") + ";");
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