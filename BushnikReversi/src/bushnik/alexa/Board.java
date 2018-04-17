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

	}
	public boolean isEmpty (int row, int col){
		return board[row][col] == CellState.EMPTY;
	}
	public boolean isWhite (int row, int col){
		
		
		
		return board[row][col] == CellState.WHITE;
	}
	public boolean isBlack (int row, int col){
		
		
		
		return board[row][col] == CellState.BLACK;
	}
	public boolean isValid(int row, int col){
		return (row >= 0 && row < 7) && (col >= 0 && col < 7);
	}

}