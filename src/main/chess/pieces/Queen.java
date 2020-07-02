package main.chess.pieces;

import main.chess.Main;

public class Queen extends Piece {
    public Queen(String color, int x, int y) {
        super(color, x, y);
        super.addBehaviour(new CardinalMovement(Main.CHESS_DIMENSIONS, this));
        super.addBehaviour(new DiagonalMovement(Main.CHESS_DIMENSIONS, this));
    }
}