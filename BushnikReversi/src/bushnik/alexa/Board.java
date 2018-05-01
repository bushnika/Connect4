package bushnik.alexa;

import java.util.ArrayList;

public class Board {

	public static final int num = 8; // 8 rows by 8 columns board
	private Cell[][] cells = new Cell[num][num]; // matrix
	int[] counter = new int[2]; // counter for both players moves
	boolean gameOver; // game is over
	final static int BLACK = 1;
	final static int WHITE = 0;

	public final Move up = new Move(0, -1); // up
	public final Move down = new Move(0, 1); // down
	public final Move left = new Move(-1, 0); // left
	public final Move right = new Move(1, 0); // right
	public final Move upLeft = new Move(-1, -1); // up and left
	public final Move upRight = new Move(1, -1); // up and right
	public final Move downLeft = new Move(-1, 1); // down and left
	public final Move downRight = new Move(1, 1); // down and right

	final Move directions[] = { up, down, left, right, upLeft, upRight, downLeft, downRight }; // array of possible directions

	public Board() {
		for (int i = 0; i < num; i++) // for i and j coordinates
			for (int j = 0; j < num; j++)
				this.cells[i][j] = new Cell(); // sets all the cells to empty

		// 1 for black starting chips
		this.cells[3][4].placeChip(1);
		this.cells[4][3].placeChip(1);

		// 0 for white starting chips
		this.cells[3][3].placeChip(0);
		this.cells[4][4].placeChip(0);

		// start counter at 2 for each player
		counter[0] = 2; // white player counter
		counter[1] = 2; // black player counter

		// allows moves by default at start of game
		gameOver = false;
	}

	public void display() {

		for (int i = 0; i < num; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < num; j++) {
				this.cells[i][j].display();
			}

			System.out.println(" ");
		}

		System.out.print("   ");
		for (int j = 0; j < num; j++) {
			System.out.print("  " + j + "  ");
		}

		System.out.println();

	}

	public void placeChip(int color, int row, int col) {
		this.cells[row][col].placeChip(color);
	}

	public boolean findLegalMoveNew(Move move, int player) {

		boolean result = false; // no legal moves found by default
		int opponent = (player + 1) % 2; // finds the opponent
		int playing = player; // player playing

		int i = move.getI(); // get position i axis
		int j = move.getJ(); // get position j axis

		if (cells[i][j].isEmpty() == false) { // checks if cell is empty
			return false; // if cell not empty, no moves available
		} else {
			for (int k = 0; k < directions.length; k++) { // get all directions
				Move direction = directions[k]; 
				int iDir = direction.getI(); //gets i and j axis
				int jDir = direction.getJ(); 
				int jump = 2; // jump one chip
				try {
					if (cells[i + iDir][j + jDir].getPlayer() == opponent) {
						while ((j + (jump * jDir)) > -1&& (j + (jump * jDir)) < 8 && (i + (jump * iDir)) < 8 && (i + (jump * iDir)) > -1) { 
							if (!cells[i + jump * iDir][j + jump * jDir].isEmpty()) { // cell cannot be empty
								if (cells[i + jump * iDir][j + jump * jDir].getPlayer() == playing)
									return true; 
								else if (cells[i + jump * iDir][j + jump * jDir].isEmpty())
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
		return result; // true move found, false no moves found
	}

	public ArrayList<Move> validMove(int color) {
		ArrayList<Move> allValidMoves = new ArrayList<Move>();

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells.length; j++) {
				cells[i][j].unselect();
				Move testMove = new Move(i, j);
				boolean valid = findLegalMoveNew(testMove, color);
				if (valid) {
					allValidMoves.add(testMove);
				}
			}
		}
		return allValidMoves;
	}

	public void setCanSelect(Move move) {
		this.cells[move.getI()][move.getJ()].setSelect();
	}

	public boolean canSelect(Move move) {
		return this.cells[move.getI()][move.getJ()].canSelect();
	}

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
					&& (i + iDir) > -1) { // checks for an opponent in all the
											// directions
				if (cells[i + iDir][j + jDir].getPlayer() == opponent) {
					possible = true;
				}
			}

			if (possible == true) {
				int jump = 2;
				while ((j + (jump * jDir)) > -1 && (j + (jump * jDir)) < 8
						&& (i + (jump * iDir)) < 8 && (i + (jump * iDir)) > -1) { 
					if (!cells[i + jump * iDir][j + jump * jDir].isEmpty()) //cell cannot be empty
						if (cells[i + jump * iDir][j + jump * jDir].getPlayer() == playing) {
							for (int k = 1; k < jump; k++) {
								cells[i + k * iDir][j + k * jDir].changeChip();// change the chips colour to player colour
							}
							break;
						}
					jump++;
				}
			}
		}
	}

	public int getChipsCount(int color) {

		int scoreWhite = 2;
		int scoreBlack = 2;

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[j].length; j++) {
				if (color == 0) {
					scoreWhite++;
				}
				if (color == 1) {
					scoreBlack++;
				}
			}
		}
		counter[0] = scoreWhite;
		counter[1] = scoreBlack;

		return counter[color];
	}

	public int totalTurns() {
		return counter[0] + counter[1];
	}

	public boolean gameOver() {
		int count = 0;

		if (counter[0] + counter[1] == 64) { // if cells are full, game over
			return true;
		}

		else {
			for (int j = 0; j < num; j++) {
				for (int i = 0; i < num; i++) {
					if (findLegalMoveNew(new Move(i, j), 0) == true) {
						count++;
					}
					if (findLegalMoveNew(new Move(i, j), 1) == true) {
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
	
	public String toString() {
		return "White score: " + getChipsCount(0) + "\n" + "Black score: " + getChipsCount(1);
	}
	
}