package main.chess.pieces;

import main.chess.Main;
import main.chess.Vec2D;

public class Queen extends Piece {
    public Queen(String color, Vec2D vec) {
        super(color, vec);
        super.addBehaviour(new CardinalMovement(Main.CHESS_DIMENSIONS, this));
        super.addBehaviour(new DiagonalMovement(Main.CHESS_DIMENSIONS, this));
    }
}