package main.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javafx.application.Application;
import javafx.stage.Stage;
import main.chess.pieces.King;
import main.chess.pieces.Piece;

public class ChessGame {
	private ChessState state;
	private Piece selected;
	private boolean isWhiteMove = true;
	private int turnCount = 1;

	public ChessGame(ChessState state) {
		this.state = state;
	}

	public boolean isOutOfCheck(Vec2D move) {
		King king = state.getKing(isWhiteMove);
		Vec2D old = this.selected.getPos().get();
		this.selected.getPos().set(move);
		List<Vec2D> enemyPos = state.enemyPositions(isWhiteMove);
		this.selected.getPos().set(old);
		if (enemyPos.contains(king.getPos().get())) {
			return false;
		}
		return true;
	}

	public List<Vec2D> onSquareClick(Vec2D vec) {
		if (this.selected != null) {
			List<Vec2D> positions = this.selected != null ? this.selected.getPositions(state) : new ArrayList<>();

			if (positions.contains(vec) && isOutOfCheck(vec)) {
				// NOTE: Make sure to check for moving into a position that would check you
				Piece piece = state.getPiece(vec.getX(), vec.getY());
				if (piece != null) {
					piece.getPos().set(null);
				}
				selected.getPos().set(vec);
				isWhiteMove = !isWhiteMove;
				turnCount++;
			}
			this.selected = null;
		} else {
			this.selected = state.getPiece(vec.getX(), vec.getY());
			if (this.selected != null && this.selected.getColor() != (isWhiteMove ? "white" : "black")) {
				this.selected = null;
			}
		}

		List<Vec2D> positions = new ArrayList<>();
		if (this.selected != null) {
			positions = this.selected.getPositions(this.state);
		}

		return positions;
	}
}