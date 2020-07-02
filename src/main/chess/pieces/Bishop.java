package main.chess.pieces;

import main.chess.Main;

public class Bishop extends Piece {
    public Bishop(String color, int x, int y) {
        super(color, x, y);
        super.addBehaviour(new DiagonalMovement(Main.CHESS_DIMENSIONS, this));
    }
}
