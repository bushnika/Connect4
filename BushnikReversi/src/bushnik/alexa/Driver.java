package bushnik.alexa;

import java.util.Scanner;
public class Driver {

	public static void main(String[] args) {
		String board [][] = new String [8][8];
		displayBoard(board);
		Scanner kb = new Scanner(System.in);
		System.out.println("Which column do you want to place your piece?");
		int col = kb.nextInt();
		System.out.println("Which column do you want to place your piece?");
		int row = kb.nextInt();
		displayBoard(board);
	}
	public static void displayBoard (String[][] board){
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.printf("%3d", board[i][j]);
			}
		System.out.println();
		}
	}

}
