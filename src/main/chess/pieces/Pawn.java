package main.chess.pieces;
import main.chess.Vec2D;

public class Pawn extends Piece {
    public Pawn(String color, Vec2D vec) {
        super(color, vec);
        super.addBehaviour(new PawnMovement(this));
    }
}