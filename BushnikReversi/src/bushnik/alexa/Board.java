package bushnik.alexa;

import java.util.ArrayList;

public class Board {

	public static final int num = 8; // 8 rows by 8 columns board
	private Cell[][] cell = new Cell[num][num]; // matrix
	int[] counter = new int[2]; // counter for both players moves
	boolean gameOver; // game is over
	final static int BLACK = 1;
	final static int WHITE = 0;

	public final Move up = new Move(0, -1); // up
	public final Move down = new Move(0, 1); // down
	public final Move right = new Move(1, 0); // right
	public final Move left = new Move(-1, 0); // left
	public final Move upRight = new Move(1, -1); // up and right
	public final Move upLeft = new Move(-1, -1); // up and left
	public final Move downRight = new Move(1, 1); // down and right
	public final Move downLeft = new Move(-1, 1); // down and left

	final Move directions[] = { up, down, right, left, upRight, upLeft, downRight, downLeft }; // array of possible directions

	public Board() {
		for (int row = 0; row < num; row++){ // Goes through whole board
			for (int col = 0; col < num; col++){
				this.cell[row][col] = new Cell(); // sets all the cells to empty
			}
		}
		// 1 for black 
		this.cell[3][4].placeChip(1);
		this.cell[4][3].placeChip(1);

		// 0 for white 
		this.cell[3][3].placeChip(0);
		this.cell[4][4].placeChip(0);

		// start counter at 2 for each player
		counter[0] = 2; // white player 
		counter[1] = 2; // black player 

		// allows moves by default at start of game
		gameOver = false;
	}
	/**
	 * Gets the cell clicked
	 * @param row - row selected
	 * @param col - col selected
	 * @return row and col of selected cell
	 */
	public Cell getCell(int row, int col){
		return cell[row][col];
	}
	/**
	 * Gets the row of selected cell
	 * @returns row of cell
	 */
	public int getNumRows(){
		return cell.length;
	}
	/**
	 * Gets the col of selected cell
	 * @returns col of cell
	 */
	public int getNumCols(){
		return cell[0].length;
	}
	/**
	 * Displays the board with rows and col numbers
	 */
	public void display() {

		for (int i = 0; i < num; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < num; j++) {
				this.cell[i][j].display();
			}

			System.out.println(" ");
		}

		System.out.print("   ");
		for (int j = 0; j < num; j++) {
			System.out.print("  " + j + "  ");
		}

		System.out.println();
	}
	/**
	 * Displays the winner of the game
	 */
	public void winner(){
		if (getChipsCount(0)<getChipsCount(1)){
			System.out.println("Black Won!");
		}else if(getChipsCount(1)<getChipsCount(0)){
			System.out.println("White Won!");
		}
	}
	/**
	 * Displays the scores of both players
	 */
	public void scoreDisplay() {
		System.out.println("White score: " + getChipsCount(0));
		System.out.println("Black score: " + getChipsCount(1));
		System.out.println();
	}
	/**
	 * Finds all the valid and available moves of current player
	 * @param move - the move chosen
	 * @param player - the current player
	 * @return boolean true if valid move otherwise false
	 */
	public boolean findLegalMove(Move move, int player) {

		boolean result = false; // no legal moves found by default
		int opponent = (player + 1) % 2; // finds the opponent
		int cPlayer = player; // player playing

		int i = move.getI(); // get position i axis
		int j = move.getJ(); // get position j axis

		if (cell[i][j].isEmpty() == false) { // checks if cell is empty
			return false; // if cell not empty, no moves available
		} else {
			for (int k = 0; k < directions.length; k++) { // get all directions
				Move direction = directions[k]; 
				int iDir = direction.getI(); //gets i and j axis
				int jDir = direction.getJ(); 
				int jump = 2; // jump one chip
				try {
					if (cell[i + iDir][j + jDir].getPlayer() == opponent) {
						while ((j + (jump * jDir)) > -1&& (j + (jump * jDir)) < 8 && (i + (jump * iDir)) < 8 && (i + (jump * iDir)) > -1) { 
							if (!cell[i + jump * iDir][j + jump * jDir].isEmpty()) { // cell cannot be empty
								if (cell[i + jump * iDir][j + jump * jDir].getPlayer() == cPlayer)
									return true; 
								else if (cell[i + jump * iDir][j + jump * jDir].isEmpty())
									break;
							} else {
								break;
							}
							jump++; // jump another extra chip
						}
					}
				} catch (Exception e) {
				}

			}
		}
		return result; // if true move found, if false no moves found
	}
	
	/**
	 * Places the chip on the board
	 * @param colour colour of the chip (player)
	 * @param row the row chosen
	 * @param col the column chosen
	 */
	public void placeChip(int colour, int row, int col) {
		this.cell[row][col].placeChip(colour);
	}
	/**
	 * An array list of all valid moves available
	 * @param colour - colour of player
	 * @return array list of all valid moves
	 */
	public ArrayList<Move> validMoves(int colour) {
		ArrayList<Move> allValidMoves = new ArrayList<Move>();

		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell.length; j++) {
				cell[i][j].unselect();
				Move testMove = new Move(i, j);
				boolean valid = findLegalMove(testMove, colour);
				if (valid) {
					allValidMoves.add(testMove);
				}
			}
		}
		return allValidMoves;
	}
	/**
	 * Allows the cell to be selected
	 * @param move - the move chosen
	 */
	public void setCanSelect(Move move) {
		this.cell[move.getI()][move.getJ()].setSelect();
	}
	/**
	 * Allows the cell to be selected
	 * @param move - the move chosen
	 * @return true when cell can be selected otherwise false
	 */
	public boolean canSelect(Move move) {
		return this.cell[move.getI()][move.getJ()].canSelect();
	}
	/**
	 * Replaces the chip(s) in between the players old and current chip.
	 * @param move - the move chosen
	 * @param player - the current player
	 */
	public void replaceChip(Move move, int player) {
		int opponent = (player + 1) % 2;
		int playing = player;

		int i = move.getI();
		int j = move.getJ();

		for (int movement = 0; movement < directions.length; movement++) {
			Move direction = directions[movement];
			int iDir = direction.getI();
			int jDir = direction.getJ();
			boolean possible = false;

			if ((j + jDir) > -1 && (j + jDir) < num && (i + iDir) < num
					&& (i + iDir) > -1) { // checks for an opponent in all directions
				if (cell[i + iDir][j + jDir].getPlayer() == opponent) {
					possible = true;
				}
			}

			if (possible == true) {
				int jump = 2;
				while ((j + (jump * jDir)) > -1 && (j + (jump * jDir)) < 8
						&& (i + (jump * iDir)) < 8 && (i + (jump * iDir)) > -1) { 
					if (!cell[i + jump * iDir][j + jump * jDir].isEmpty()) //cell cannot be empty
						if (cell[i + jump * iDir][j + jump * jDir].getPlayer() == playing) {
							for (int k = 1; k < jump; k++) {
								cell[i + k * iDir][j + k * jDir].changeChip();
							}
							break;
						}
					jump++;
				}
			}
		}
	}
	/**
	 * Updates the number/counter of chips on the board per player
	 * @param colour - the colour of the player
	 * @return counter for each player
	 */
	public int getChipsCount(int colour) {

		int score=0;
		
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (cell[i][j].getPlayer() == colour) {
					score++;
				}
			}
		}
		return score;
	}
	/**
	 * Checks if all the 64 chips have been placed on board meaning the game is over.
	 * @return boolean true if game over otherwise false
	 */
	public boolean gameOver() {
		int count = 0;

		if (counter[0] + counter[1] == 64) { // if cells are full, game over
			return true;
		}

		else {
			for (int j = 0; j < num; j++) {
				for (int i = 0; i < num; i++) {
					if (findLegalMove(new Move(i, j), 0) == true) {
						count++;
					}
					if (findLegalMove(new Move(i, j), 1) == true) {
						count++;
					}
				}
			}
			if (count == 0) { // if no legal moves left then game over
				return true;
			}
		}
		return false;
	}	
}
