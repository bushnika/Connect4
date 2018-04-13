package bushnik.alexa;

public class Board {
	private CellState [][] board = new CellState [8][8];{
	
	for(int row = 0; row < 8; row++){
		for (int col = 0; col < 8; col++){
			board[row][col] = CellState.EMPTY;
		}
	}
	
	board[3][3] = CellState.WHITE;
	board[4][3] = CellState.BLACK;
	board[3][4] = CellState.BLACK;
	board[4][4] = CellState.WHITE;
	
	
	//board hello
	/*
	public Board(int aRows, int aCols) {
		board = new String[aRows][aCols];
		rows = aRows;
		cols = aCols;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print("A");
			}
		}
	}

	String board [][] = new String [8][8];{
	
	
	for (int row = 0; row < 8; row ++){
		for (int col = 0; col < 8; col++){
			System.out.print("A");
		}
	}
	*/
}
}
