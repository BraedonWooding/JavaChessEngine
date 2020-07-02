package main.chess.pieces;

public class Pawn extends Piece {
    public Pawn(String color, int x, int y) {
        super(color, x, y);
        super.addBehaviour(new PawnMovement(this));
    }
}