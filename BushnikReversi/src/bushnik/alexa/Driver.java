package bushnik.alexa;

import java.io.IOException;
import java.util.Scanner;

public class Driver {
	
	Scanner kb = new Scanner(System.in);
	private Board board = new Board(); // creates the board
	private Player[] players = new Player[2]; // array of players 
	private Turn turn; // creates a turn
	int[] count = new int[2];
	final static int BLACK = 1;
	final static int WHITE = 0;

	public static void main(String[] args) throws IOException {
		new Driver().startGame();
	}
	/**
	 * Starts the game by finding first players possible moves
	 * @throws IOException
	 */
	public void startGame() throws IOException {

		int who = this.initPlayers(); // initializes the first player
		this.turn = new Turn((who + 1) % 2); // initializes the turn

		for (int i = 0; i < 2; i++) { // asks players for their names
			System.out.print("Player " + (i + 1) + " ");
			players[i].setNames();
		}

		System.out.println(players[0].getName() + " moves"); // indicates whose turn it is
		this.players[turn.getTurn()].findCanSelect(); 
		board.display(); // displays board
		
		while (!board.gameOver()) { // find possible moves when game not over

			int count = 0; // count of possible moves
			for (int j = 0; j < Board.num; j++)// search the entire board
				for (int i = 0; i < Board.num; i++)
					if (board.findLegalMoveNew(new Move(i, j), turn.getTurn()) == true) {
						count++; // add a possible move to the count
					}

			if (count == 0) { // when no possible moves
				turn.change(); // change the turn to the other player
				board.display(); // display the updated board
				board.scoreDisplay(); //display updated score
				count = 0; // reset count to 0
			}

			else {
				int row = this.readRow(); 
				int col = this.readCol(); 

				Move move = new Move(row, col); // creates a new move
				if (board.canSelect(move)) { // if move valid
					this.players[turn.getTurn()].placeChip(row, col); // place the chip
					turn.change(); // change the turn to the other player
				}

				this.players[turn.getTurn()].findCanSelect(); // find the possible moves
				board.display(); // display updated board with possible moves
				System.out.println(players[turn.getTurn()].getName() + " moves"); // indicates whose turn it is
			}
		}
		System.out.println("Game over!");
	}
	/**
	 * Creates two players
	 * @return 1 if black starts, 0 for white
	 */
	private int initPlayers() {
		Turn a = new Turn(); // temporary turn

		this.players[0] = new Player("name 1", a.getTurn(), this.board); // player 1
		a.change(); // changes to player 2
		this.players[1] = new Player("name 2", a.getTurn(), this.board); // player 2
		return 1;
	}
	/**
	 * Gets the row for the move
	 * @return the value of row wanted
	 */
	private int readRow() {
		System.out.print("Select a row: "); 
		Integer value = -1;
		value = kb.nextInt();
		return value; // returns the value selected
	}
	/**
	 * Gets the col for the move
	 * @return the value of col wanted
	 */
	private int readCol() {
		System.out.print("Select a column: ");
		Integer value = -1;
		value = kb.nextInt();
		return value; // returns value selected
	}

}
