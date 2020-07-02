package main.chess.pieces;

import java.util.List;

import javafx.util.Pair;
import main.chess.ChessState;

public interface IMovement {
    public void getPositions(List<Pair<Integer, Integer>> list, ChessState state);
}