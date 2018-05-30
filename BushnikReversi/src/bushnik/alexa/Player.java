package bushnik.alexa;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Player {
	
	Scanner kb = new Scanner(System.in);
	private String name; // name of the player
	private int colour; // colour of the chips
	private Board board; // board

	public Player(String name, int colour, Board board) {
		this.name = name;
		this.colour = colour;
		this.board = board;
	}

	public void setNames() throws IOException {

		System.out.println("What is your name: ");
		String line = kb.nextLine();
		this.name = line;
	}

	public String getName() {
		return this.name;
	}

	public int getcolour() {
		return this.colour;
	}
	/**
	 * Places the piece for the chosen player at the chosen location. Replaces the piece in between 
	 * the old piece and the new piece to the players colour.
	 * @param row row of the piece
	 * @param col column of the piece
	 */
	public void placeChip(int row, int col) {
		this.board.placeChip(this.colour, row, col);

		Move move = new Move(row, col);
		this.board.replaceChip(move, this.colour);
		
	}

	public void findCanSelect() {

		ArrayList<Move> moves = board.validMove(this.colour);

		for (Move move : moves)
			board.setCanSelect(move);
	}
}