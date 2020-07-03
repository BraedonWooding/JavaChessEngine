package main.chess;

import java.util.ArrayList;
import java.util.List;

import main.chess.pieces.Bishop;
import main.chess.pieces.King;
import main.chess.pieces.Knight;
import main.chess.pieces.Pawn;
import main.chess.pieces.Piece;
import main.chess.pieces.Queen;
import main.chess.pieces.Rook;

public class ChessState {
    private Piece[][] board = new Piece[Main.CHESS_DIMENSIONS][Main.CHESS_DIMENSIONS];
    private King whiteKing;
    private King blackKing;

    public ChessState() {
        board[0][0] = new Rook("black", new Vec2D(0, 0));
        board[1][0] = new Knight("black", new Vec2D(1, 0));
        board[2][0] = new Bishop("black", new Vec2D(2, 0));
        board[3][0] = new Queen("black", new Vec2D(3, 0));
        board[4][0] = blackKing = new King("black", new Vec2D(4, 0));
        board[5][0] = new Bishop("black", new Vec2D(5, 0));
        board[6][0] = new Knight("black", new Vec2D(6, 0));
        board[7][0] = new Rook("black", new Vec2D(7, 0));

        for (int i = 0; i < 8; i++) {
            board[i][1] = new Pawn("black", new Vec2D(i, 1));
            board[i][6] = new Pawn("white", new Vec2D(i, 6));
        }

        board[0][7] = new Rook("white", new Vec2D(0, 7));
        board[1][7] = new Knight("white", new Vec2D(1, 7));
        board[2][7] = new Bishop("white", new Vec2D(2, 7));
        board[3][7] = new Queen("white", new Vec2D(3, 7));
        board[4][7] = whiteKing = new King("white", new Vec2D(4, 7));
        board[5][7] = new Bishop("white", new Vec2D(5, 7));
        board[6][7] = new Knight("white", new Vec2D(6, 7));
        board[7][7] = new Rook("white", new Vec2D(7, 7));
    }

    public List<Vec2D> enemyPositions(boolean isWhite) {
        List<Vec2D> positions = new ArrayList<Vec2D>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = getPiece(x, y);
                if (piece != null && piece.getColor() == (isWhite ? "black" : "white")) {
                    positions.addAll(piece.getPositions(this));
                }
            }
        }
        return positions;
    }

    public King getKing(boolean isWhite) {
        return isWhite ? whiteKing : blackKing;
    }

    public void pieceListener(Vec2D oldPos, Vec2D newPos) {
        if (newPos != null) {
            board[newPos.getX()][newPos.getY()] = board[oldPos.getX()][oldPos.getY()];
        }
        board[oldPos.getX()][oldPos.getY()] = null;
    }

    public Piece getPiece(int x, int y) {
        if (x < 0 || y < 0 || x >= Main.CHESS_DIMENSIONS || y >= Main.CHESS_DIMENSIONS) return null;
        return board[x][y];
    }
}
