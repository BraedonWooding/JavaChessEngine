package main.chess.pieces;

import java.util.List;

import javafx.util.Pair;
import main.chess.ChessState;
import main.chess.Main;

public class PawnMovement implements IMovement {
    private Piece piece;

    public PawnMovement(Piece piece) {
        this.piece = piece;
    }

    public void getPositions(List<Pair<Integer, Integer>> list, ChessState state) {
        int x = piece.getX();
        int y = piece.getY();
    
        if (y == 1 || y == 6) {
            // first row
            Piece piece = state.getPiece(x, y + (y == 1 ? 2 : -2));
            if (piece == null) list.add(new Pair<>(x, y + (y == 1 ? 2 : -2)));
        }
        Piece piece = state.getPiece(x, y + (y == 1 ? 1 : -1));
        if (piece == null) list.add(new Pair<>(x, y + (y == 1 ? 1 : -1)));

        if (x + 1 < Main.CHESS_DIMENSIONS) {
            piece = state.getPiece(x + 1, y + (y == 1 ? 1 : -1));
            if (piece != null) list.add(new Pair<>(x + 1, y + (y == 1 ? 1 : -1)));
        }

        if (x - 1 >= 0) {
            piece = state.getPiece(x - 1, y + (y == 1 ? 1 : -1));
            if (piece != null) list.add(new Pair<>(x - 1, y + (y == 1 ? 1 : -1)));
        }
    }
}