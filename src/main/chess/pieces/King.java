package main.chess.pieces;

public class King extends Piece {
    public King(String color, int x, int y) {
        super(color, x, y);
        super.addBehaviour(new CardinalMovement(1, this));
        super.addBehaviour(new DiagonalMovement(1, this));
    }

    @Override
    public boolean isTakeable() {
        return false;
    }
}