package bushnik.alexa;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUIDriver extends Application {

	private Board board = new Board(); // creates the board
	private Player[] players = new Player[2]; // array of players
	private Turn turn; // creates a turn
	int[] count = new int[2];
	final static int BLACK = 1;
	final static int WHITE = 0;

	private static final double BUTTON_WIDTH = 50;
	private static final double BUTTON_HEIGHT = 50;
	private static NewButton[][] slots = new NewButton[Board.num][Board.num];

	public static void main(String[] args) {
		launch(args);
	}

	@Override

	public void start(Stage window) throws Exception {
		GridPane gridPane = new GridPane();
		Scene mySceneGraph = new Scene(gridPane, 500, 500);
		gridPane.setAlignment(Pos.TOP_CENTER);

		for (int row = 0; row < board.getNumRows(); row++) {
			for (int col = 0; col < board.getNumCols(); col++) {
				slots[row][col] = new NewButton(row, col);
				
				slots[row][col].setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
				if (board.getCell(row, col).getPlayer() == 1) {
					slots[row][col].setStyle("-fx-base: #FF0000;");
				} else if (board.getCell(row, col).getPlayer() == 0) {
					slots[row][col].setStyle("-fx-base: #00FF00;");
				} else {
					slots[row][col].setStyle("-fx-base: #C0C0C0;");
				}
				slots[row][col].setOnAction(new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent event) {

		            	int row = ((NewButton)event.getSource()).getRow();
		            	int col = ((NewButton)event.getSource()).getCol();
		            	
		            	Move move = new Move(row, col); // creates a new move
		            	if (board.canSelect(move)) { // if move valid
							players[turn.getTurn()].placeChip(row, col); // place the chip
							turn.change(); // change the turn to the other player
						}
		            	System.out.print("Move made at: (" + ((NewButton)event.getSource()).getRow());
		            	System.out.print("," + ((NewButton)event.getSource()).getCol() + ")\n");

		            }
		        });
				gridPane.add(slots[row][col], col, row);

				window.setScene(mySceneGraph);
				window.show();
			}
		}
	}
}