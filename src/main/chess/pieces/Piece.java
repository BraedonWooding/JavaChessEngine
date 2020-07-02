package main.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import main.chess.ChessState;

public class Piece {
    private String color;
    private List<IMovement> behaviours;
    private int x;
    private int y;

    public Piece(String color, int x, int y) {
        this.color = color;
        this.behaviours = new ArrayList<>();
        this.setX(x);
        this.setY(y);
    }

    protected void addBehaviour(IMovement behaviour) {
        this.behaviours.add(behaviour);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public boolean isTakeable() {
        return true;
    }

    public String getColor() {
        return this.color;
    }

    public List<Pair<Integer, Integer>> getPositions(ChessState state) {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        this.behaviours.forEach(o -> o.getPositions(list, state));
        list.removeIf(o -> {
            Piece piece = state.getPiece(o.getKey(), o.getValue());
            if (piece != null && piece.getColor() == this.getColor()) return true;

            // ... remove the king pieces and stuff like that
            //     or you can do this elsewhere...

            return false;
        });
        return list;
    }
}
