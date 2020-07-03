package main.chess.pieces;
import main.chess.Vec2D;

public class King extends Piece {
    public King(String color, Vec2D vec) {
        super(color, vec);
        super.addBehaviour(new CardinalMovement(1, this));
        super.addBehaviour(new DiagonalMovement(1, this));
    }

    @Override
    public boolean isTakeable() {
        return false;
    }
}