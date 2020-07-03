package main.chess.pieces;

import java.util.List;

import main.chess.ChessState;
import main.chess.Vec2D;

public interface IMovement {
    public void getPositions(List<Vec2D> list, ChessState state);
}