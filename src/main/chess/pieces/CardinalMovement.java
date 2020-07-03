package main.chess.pieces;

import java.util.List;

import main.chess.ChessState;
import main.chess.Main;
import main.chess.Vec2D;

public class CardinalMovement implements IMovement {
    private int range;
    private Piece piece;

    public CardinalMovement(int range, Piece piece) {
        this.range = range;
        this.piece = piece;
    }

    public void getPositions(List<Vec2D> list, ChessState state) {
        boolean reachedPiece = false;
        Vec2D pos = piece.getPos().get();
        int x = pos.getX();
        int y = pos.getY();
        for (int i = 1; i <= range && !reachedPiece; i++) {
            if (x + i < Main.CHESS_DIMENSIONS) {
                Piece piece = state.getPiece(x + i, y);
                list.add(new Vec2D(x + i, y));
                if (piece != null) reachedPiece = true;
            }
        }

        reachedPiece = false;
        for (int i = 1; i <= range && !reachedPiece; i++) {
            if (x - i >= 0) {
                Piece piece = state.getPiece(x - i, y);
                list.add(new Vec2D(x - i, y));
                if (piece != null) reachedPiece = true;
            }
        }

        reachedPiece = false;
        for (int i = 1; i <= range && !reachedPiece; i++) {
            if (y + i < Main.CHESS_DIMENSIONS) {
                Piece piece = state.getPiece(x, y + i);
                list.add(new Vec2D(x, y + i));
                if (piece != null) reachedPiece = true;
            }
        }

        reachedPiece = false;
        for (int i = 1; i <= range && !reachedPiece; i++) {
            if (y - i >= 0) {
                Piece piece = state.getPiece(x, y - i);
                list.add(new Vec2D(x, y - i));
                if (piece != null) reachedPiece = true;
            }
        }
    }
}