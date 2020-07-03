package main.chess.pieces;

import java.util.List;

import main.chess.ChessState;
import main.chess.Main;
import main.chess.Vec2D;

public class PawnMovement implements IMovement {
    private Piece piece;

    public PawnMovement(Piece piece) {
        this.piece = piece;
    }

    public void getPositions(List<Vec2D> list, ChessState state) {
        Vec2D pos = piece.getPos().get();
        int x = pos.getX();
        int y = pos.getY();
    
        if (y == 1 || y == 6) {
            // first row
            Piece piece = state.getPiece(x, y + (this.piece.getColor() == "black" ? 2 : -2));
            if (piece == null) list.add(new Vec2D(x, y + (this.piece.getColor() == "black" ? 2 : -2)));
        }
        Piece piece = state.getPiece(x, y + (this.piece.getColor() == "black" ? 1 : -1));
        if (piece == null) list.add(new Vec2D(x, y + (this.piece.getColor() == "black" ? 1 : -1)));

        if (x + 1 < Main.CHESS_DIMENSIONS) {
            piece = state.getPiece(x + 1, y + (this.piece.getColor() == "black" ? 1 : -1));
            if (piece != null) list.add(new Vec2D(x + 1, y + (this.piece.getColor() == "black" ? 1 : -1)));
        }

        if (x - 1 >= 0) {
            piece = state.getPiece(x - 1, y + (this.piece.getColor() == "black" ? 1 : -1));
            if (piece != null) list.add(new Vec2D(x - 1, y + (this.piece.getColor() == "black" ? 1 : -1)));
        }
    }
}