package main.chess.ui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import main.chess.ChessGame;
import main.chess.ChessState;
import main.chess.Main;

public class ChessView {
    private BorderPane parent = new BorderPane();

    public ChessView(ChessState state, ChessGame game) {
        GridPane table = new GridPane();
        for (int i = 0; i < Main.CHESS_DIMENSIONS; i++) {
            table.add(createRowLabel(i), 0, i + 1, 1, 1);
            table.add(createRowLabel(i), 9, i + 1, 1, 1);
            table.add(createColLabel(i), i + 1, 0, 1, 1);
            table.add(createColLabel(i), i + 1, 9, 1, 1);
        }

        ChessBoard board = new ChessBoard(state, game);
        table.add(board, 1, 1, 8, 8);
        table.setAlignment(Pos.CENTER);
        board.initialRender(state);
        parent.setCenter(table);
    }

	public Parent getAsParent() {
		return parent;
    }

    private Label createRowLabel(int i) {
        Label label = new Label(Integer.toString(8 - i));
        label.setFont(Font.font("Arial", 30));
        label.setMinSize(20, 50);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    private Label createColLabel(int i) {
        Label label = new Label(Character.toString(i + 'A'));
        label.setFont(Font.font("Arial", 30));
        label.setMinSize(50, 20);
        label.setAlignment(Pos.CENTER);
        return label;
    }
}
