package bushnik.alexa;

public class Cell {

	public static final char BLACK = 'B'; // displays 'B' for the black player.
	public static final char WHITE = 'W'; // displays 'W' for the white player.
	public static final char CANSELECT = 'X'; // displays 'X' for the possible
												// moves available.

	public boolean empty; // boolean if cell empty or not
	public boolean canselect; // boolean if cell can be selected or not

	public int value; // 0 = white, 1 = black, -1 = empty


	public Cell() {
		this.empty = true;
		this.value = -1;
	}


	public boolean isEmpty() {
		return this.empty;
	}


	public int getPlayer() {
		return this.value;
	}

	public void placeChip(int player) {
		this.empty = false;
		this.value = player;
	}

	public void changeChip() {
		placeChip((value + 1) % 2);
	}


	public void setSelect() {
		this.canselect = true;
	}


	public boolean canSelect() {
		return this.canselect;
	}

	public void unselect() {
		this.canselect = false;
	}


	public void display() {
		if (this.isEmpty()) // if cell empty
		{
			if (this.canselect) // if can be selected
				System.out.print("[ " + CANSELECT + " ]"); // print 'X'
			else
				System.out.print("[ " + " " + " ]"); // print empty space
		} else {
			char content = BLACK;
			if (this.value == 0)
				content = WHITE;
			System.out.print("[ " + content + " ]"); // For black 'B' & for white 'W'
		}
	}
}