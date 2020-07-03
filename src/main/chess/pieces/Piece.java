package main.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import main.chess.ChessState;
import main.chess.Vec2D;
import main.chess.Observable;

public class Piece {
    private String color;
    private List<IMovement> behaviours;
    private Observable<Vec2D> vec;

    public Piece(String color, Vec2D vec) {
        this.color = color;
        this.behaviours = new ArrayList<>();
        this.vec = new Observable<>(vec);
    }

    protected void addBehaviour(IMovement behaviour) {
        this.behaviours.add(behaviour);
    }

    public Observable<Vec2D> getPos() {
        return this.vec;
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

    public List<Vec2D> getPositions(ChessState state) {
        List<Vec2D> list = new ArrayList<>();
        this.behaviours.forEach(o -> o.getPositions(list, state));
        list.removeIf(o -> {
            Piece piece = state.getPiece(o.getX(), o.getY());
            if (piece != null && piece.getColor() == this.getColor()) return true;

            // ... remove the king pieces and stuff like that
            //     or you can do this elsewhere...

            return false;
        });
        return list;
    }
}
