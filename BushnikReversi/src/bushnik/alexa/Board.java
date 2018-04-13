package bushnik.alexa;

public class Board {
	private String [][] board;
	private int rows;
	private int cols;
	//board hello
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
	/*
	String board [][] = new String [8][8];{
	board[3][3] = "W";
	board[4][3] = "B";
	board[3][4] = "B";
	board[4][4] = "W";
	
	for (int row = 0; row < 8; row ++){
		for (int col = 0; col < 8; col++){
			System.out.print("A");
		}
	}
	*/
}
