package main.chess.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import main.chess.ChessGame;
import main.chess.ChessState;
import main.chess.Main;
import main.chess.Vec2D;
import main.chess.pieces.Piece;

public class ChessBoard extends StackPane {
    private Label[][] pieces = new Label[Main.CHESS_DIMENSIONS][Main.CHESS_DIMENSIONS];
    private Label[][] highlights = new Label[Main.CHESS_DIMENSIONS][Main.CHESS_DIMENSIONS];
    private Map<String, Image> images = new HashMap<>();

    public ChessBoard(ChessState state, ChessGame engine) {
        GridPane board = new GridPane();
        GridPane groupGrid = new GridPane();

        for (int x = 0; x < Main.CHESS_DIMENSIONS; x++) {
            for (int y =  0; y < Main.CHESS_DIMENSIONS; y++) {
                Label square = new Label();
                square.setAlignment(Pos.CENTER);
                square.setMinSize(50, 50);
                square.setMaxSize(50, 50);

                square.setStyle("-fx-background-color: " + (isWhite(x, y) ? "white" : "gray") + ";");
                board.add(square, x, y);
                
                Label pieceView = new Label();
                pieceView.setMinSize(50, 50);
                pieceView.setMaxSize(50, 50);
                pieces[x][y] = pieceView;

                Label highlightLabel = new Label();
                highlightLabel.setMinSize(50, 50);
                highlightLabel.setMaxSize(50, 50);
                highlights[x][y] = highlightLabel;
                
                Group group = new Group(
                    pieceView,
                    highlightLabel
                );
                Group tmp = new Group(group);
                tmp.setBlendMode(null);
                groupGrid.add(tmp, x, y);

                int xCopy = x;
                int yCopy = y;
                group.setOnMouseClicked(ev -> {
                    List<Vec2D> positions = engine.onSquareClick(new Vec2D(xCopy, yCopy));
                    updateVisuals(positions);
                });

                Piece piece = state.getPiece(x, y);
                if (piece != null) {
                    piece.getPos().observe(state::pieceListener);
                    piece.getPos().observe((oldValue, newValue) -> {
                        if (newValue != null) {
                            pieces[newValue.getX()][newValue.getY()].setGraphic(pieces[oldValue.getX()][oldValue.getY()].getGraphic());
                            pieces[oldValue.getX()][oldValue.getY()].setGraphic(null);
                        }
                    });
                }
            }
        }
        
        getChildren().addAll(board, groupGrid);
    }

    private boolean isWhite(int x, int y) {
        return x % 2 == 1 && y % 2 == 1 || x % 2 == 0 && y % 2 == 0;
    }

    public void updateVisuals(List<Vec2D> positions) {
        for (int x = 0; x < Main.CHESS_DIMENSIONS; x++) {
            for (int y = 0; y < Main.CHESS_DIMENSIONS; y++) {
                if (positions.contains(new Vec2D(x, y))) {
                    highlights[x][y].setStyle("-fx-background-color: " + (pieces[x][y].getGraphic() != null ? "darkRed" : "yellow") + ";");
                    highlights[x][y].setOpacity(0.5);
                } else {
                    highlights[x][y].setStyle("");
                }
            }
        }
    }

    public void initialRender(ChessState state) {
        for (int x = 0; x < Main.CHESS_DIMENSIONS; x++) {
            for (int y = 0; y < Main.CHESS_DIMENSIONS; y++) {
                Piece piece = state.getPiece(x, y);
                if (piece == null) {
                    pieces[x][y].setGraphic(null);
                } else {
                    String imageName = piece.getColor().toLowerCase() + "_" + piece.getName().toLowerCase();
                    if (!images.containsKey(imageName)) {
                        images.put(imageName, new Image(imageName + ".png", 50, 50, true, true));
                    }
                    pieces[x][y].setGraphic(new ImageView(images.get(imageName)));
                }
                highlights[x][y].setStyle("");
            }
        }
    }
}