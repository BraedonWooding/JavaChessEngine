package main.chess.pieces;

import java.util.List;

import javafx.util.Pair;
import main.chess.ChessState;
import main.chess.Main;

public class CardinalMovement implements IMovement {
    private int range;
    private Piece piece;

    public CardinalMovement(int range, Piece piece) {
        this.range = range;
        this.piece = piece;
    }

    public void getPositions(List<Pair<Integer, Integer>> list, ChessState state) {
        boolean reachedPiece = false;
        int x = piece.getX();
        int y = piece.getY();
        for (int i = 1; i <= range && !reachedPiece; i++) {
            if (x + i < Main.CHESS_DIMENSIONS) {
                Piece piece = state.getPiece(x + i, y);
                list.add(new Pair<>(x + i, y));
                if (piece != null) reachedPiece = true;
            }
        }

        reachedPiece = false;
        for (int i = 1; i <= range && !reachedPiece; i++) {
            if (x - i >= 0) {
                Piece piece = state.getPiece(x - i, y);
                list.add(new Pair<>(x - i, y));
                if (piece != null) reachedPiece = true;
            }
        }

        reachedPiece = false;
        for (int i = 1; i <= range && !reachedPiece; i++) {
            if (y + i < Main.CHESS_DIMENSIONS) {
                Piece piece = state.getPiece(x, y + i);
                list.add(new Pair<>(x, y + i));
                if (piece != null) reachedPiece = true;
            }
        }

        reachedPiece = false;
        for (int i = 1; i <= range && !reachedPiece; i++) {
            if (y - i >= 0) {
                Piece piece = state.getPiece(x, y - i);
                list.add(new Pair<>(x, y - i));
                if (piece != null) reachedPiece = true;
            }
        }
    }
}