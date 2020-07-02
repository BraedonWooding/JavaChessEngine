package main.chess.pieces;

import main.chess.Main;

public class Rook extends Piece {
    public Rook(String color, int x, int y) {
        super(color, x, y);
        super.addBehaviour(new CardinalMovement(Main.CHESS_DIMENSIONS, this));
    }
}