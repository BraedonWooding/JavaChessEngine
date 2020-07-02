package main.chess;

import main.chess.pieces.Bishop;
import main.chess.pieces.King;
import main.chess.pieces.Knight;
import main.chess.pieces.Pawn;
import main.chess.pieces.Piece;
import main.chess.pieces.Queen;
import main.chess.pieces.Rook;

public class ChessState {
    private Piece[][] board = new Piece[Main.CHESS_DIMENSIONS][Main.CHESS_DIMENSIONS];

    public ChessState() {
        board[0][0]= new Rook("black", 0, 0);
        board[1][0]= new Knight("black", 1, 0);
        board[2][0]= new Bishop("black", 2, 0);
        board[3][0]= new Queen("black", 3, 0);
        board[4][0]= new King("black", 4, 0);
        board[5][0]= new Bishop("black", 5, 0);
        board[6][0]= new Knight("black", 6, 0);
        board[7][0]= new Rook("black", 7, 0);

        for (int i = 0; i < 8; i++) {
            board[i][1] = new Pawn("black", i, 1);
            board[i][6] = new Pawn("white", i, 6);
        }

        board[0][7]= new Rook("white", 0, 7);
        board[1][7]= new Knight("white", 1, 7);
        board[2][7]= new Bishop("white", 2, 7);
        board[3][7]= new Queen("white", 3, 7);
        board[4][7]= new King("white", 4, 7);
        board[5][7]= new Bishop("white", 5, 7);
        board[6][7]= new Knight("white", 6, 7);
        board[7][7]= new Rook("white", 7, 7);
    }

    public void movePiece(int x, int y, int newX, int newY) {
        board[newX][newY] = board[x][y];
        board[x][y] = null;
    }

    public Piece getPiece(int x, int y) {
        if (x < 0 || y < 0 || x >= Main.CHESS_DIMENSIONS || y >= Main.CHESS_DIMENSIONS) return null;
        return board[x][y];
    }
}
