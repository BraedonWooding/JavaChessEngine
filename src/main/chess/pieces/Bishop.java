package main.chess.pieces;

import main.chess.Main;
import main.chess.Vec2D;

public class Bishop extends Piece {
    public Bishop(String color, Vec2D vec) {
        super(color, vec);
        super.addBehaviour(new DiagonalMovement(Main.CHESS_DIMENSIONS, this));
    }
}
