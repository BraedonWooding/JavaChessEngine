package main.chess.pieces;

import main.chess.Main;
import main.chess.Vec2D;

public class Rook extends Piece {
    public Rook(String color, Vec2D vec) {
        super(color, vec);
        super.addBehaviour(new CardinalMovement(Main.CHESS_DIMENSIONS, this));
    }
}