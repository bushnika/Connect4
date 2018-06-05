package bushnik.alexa;

import javafx.application.Application;

import java.io.IOException;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

public class GUIDriver extends Application {

	Scanner kb = new Scanner(System.in);
	private Board board = new Board(); // creates the board
	int player;
	int[] count = new int[2];
	final static int BLACK = 1;
	final static int WHITE = 0;
	int i = 0;
	private static final double BUTTON_WIDTH = 50;
	private static final double BUTTON_HEIGHT = 50;
	private static NewButton[][] slots = new NewButton[Board.num][Board.num];

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	/**
	 * Starts the game, sets up GUI elements
	 */
	public void start(Stage window) throws Exception {
		window.setTitle("Othello Game");
		GridPane gridPane = new GridPane();
		Scene mySceneGraph = new Scene(gridPane, 500, 500);
		gridPane.setAlignment(Pos.TOP_CENTER);

		for (int row = 0; row < board.getNumRows(); row++) {
			for (int col = 0; col < board.getNumCols(); col++) {
				slots[row][col] = new NewButton(row, col);

				slots[row][col].setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
				if (board.getCell(row, col).getPlayer() == 0) {
					slots[row][col].setStyle("-fx-base: #FFFFFF;");
				} else if (board.getCell(row, col).getPlayer() == 1) {
					slots[row][col].setStyle("-fx-base: #000000;");
				} else {
					slots[row][col].setStyle("-fx-base: #C0C0C0;");
				}
				gridPane.add(slots[row][col], col, row);
				slots[row][col].setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						int row = ((NewButton) event.getSource()).getRow();
						int col = ((NewButton) event.getSource()).getCol();

						if (event.getSource() == slots[row][col]) {
							// white
							if (board.findLegalMove(new Move(row, col), i) == true && i == 0) {
								board.placeChip(i, row, col);
								board.replaceChip(new Move(row, col), i);

								i = 1;

							}
							// black
							if (board.findLegalMove(new Move(row, col), i) == true & i == 1) {
								board.placeChip(i, row, col);
								board.replaceChip(new Move(row, col), i);

								i = 0;
							}

							for (int j = 0; j < 8; j++) {
								for (int k = 0; k < 8; k++) {
									if (board.getCell(j, k).getPlayer() == 0) {
										slots[j][k].setStyle("-fx-base: #FFFFFF;");
									} else if (board.getCell(j, k).getPlayer() == 1) {
										slots[j][k].setStyle("-fx-base: #000000;");
									} else {
										slots[j][k].setStyle("-fx-base: #C0C0C0;");
									}
								}
							}
						}
						board.scoreDisplay();
						System.out.print("Move made at: (" + ((NewButton) event.getSource()).getRow());
						System.out.print("," + ((NewButton) event.getSource()).getCol() + ")\n");
						if (board.gameOver()) {
							System.out.println("Game Over!");
							System.out.println("Final Score:");
							board.winner();
							board.scoreDisplay();
						}

					}
				});

			}
		}

		window.setScene(mySceneGraph);
		window.show();

	}

}
