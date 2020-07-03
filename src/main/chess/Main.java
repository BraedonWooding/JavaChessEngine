package main.chess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.chess.ui.ChessView;

public class Main extends Application {
	public static final int CHESS_DIMENSIONS = 8;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Chess");
		ChessState state = new ChessState();
		ChessGame controller = new ChessGame(state);
		ChessView view = new ChessView(state, controller);

		Scene scene = new Scene(view.getAsParent(), 800, 800);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}