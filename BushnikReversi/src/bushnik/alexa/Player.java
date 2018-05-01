package bushnik.alexa;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Player {
	
	Scanner kb = new Scanner(System.in);
	private String name; // name of the player
	private int color; // color of the chips
	private Board board; // board

	public Player(String name, int color, Board board) {
		this.name = name;
		this.color = color;
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

	public int getColor() {
		return this.color;
	}

	public void placeChip(int row, int col) {
		this.board.placeChip(this.color, row, col);

		Move move = new Move(row, col);
		this.board.replaceChip(move, this.color);
	}

	public void findCanSelect() {

		ArrayList<Move> moves = board.validMove(this.color);

		for (Move move : moves)
			board.setCanSelect(move);
	}
}