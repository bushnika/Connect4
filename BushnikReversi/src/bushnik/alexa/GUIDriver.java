package bushnik.alexa;

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
	private static NewButton[][] slots = new NewButton[7][7];
	private static final double BUTTON_WIDTH = 50;
	private static final double BUTTON_HEIGHT = 50;

	public static void main(String[] args) {
		launch(args);
	}

	@Override

	public void start(Stage window) throws Exception {
		GridPane gridPane = new GridPane();
		Scene mySceneGraph = new Scene(gridPane, 500, 500);
		gridPane.setAlignment(Pos.TOP_CENTER);
		initilizeScreen(gridPane);

		refreshScreen(gridPane, board);

		window.setScene(mySceneGraph);
		window.show();

	}

	private void initilizeScreen(GridPane gridPane) {
		// setup slots
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				slots[i][j] = new NewButton(i, j);
				slots[i][j].setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);

				slots[i][j].setStyle("-fx-base: #C0C0C0;");
				slots[i][j].setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println(((NewButton) event.getSource()).getRow());
					}
				});
			}
		}
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				gridPane.add(slots[i][j], j, i + 1);
			}
		}

	}

	private void refreshScreen(GridPane gp, Board board) {

		Cell[][] b = board.getBoard();
		for (int row = 0; row < b.length; row++) {
			for (int col = 0; col < b[0].length; col++) {
				slots[row][col].setStyle("-fx-base: #FF0000;");
				gp.add(slots[row][col], col, row);
					
			}
		}

	}

}
