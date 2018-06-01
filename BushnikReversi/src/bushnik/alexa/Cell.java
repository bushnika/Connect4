package bushnik.alexa;

public class Cell {

	public static final char BLACK = 'B'; // displays 'B' for the black player.
	public static final char WHITE = 'W'; // displays 'W' for the white player.
	public static final char CANSELECT = '*'; // displays '*' for the possible moves available.

	public boolean empty; // boolean if cell empty or not
	public boolean canselect; // boolean if cell can be selected or not

	public int value; // 0 = white, 1 = black, -1 = empty

	/**
	 * Class constructor. Cell is empty by default.
	 */
	public Cell() {
		this.empty = true;
		this.value = -1;
	}
	/**
	 * Checks if cell is empty.
	 * @return true when empty. False when not empty.
	 */
	public boolean isEmpty() {
		return this.empty;
	}
	/**
	 * Returns player's colour
	 * @return the player's colour
	 */
	public int getPlayer() {
		return this.value;
	}
	/**
	 * Places a chip in the cell
	 * @param player - the player chosen
	 */
	public void placeChip(int player) {
		this.empty = false;
		this.value = player;
	}
	/**
	 * Changes the colour value of chip (0,1)
	 */
	public void changeChip() {
		placeChip((value + 1) % 2);
	}
	/**
	 * Makes cell possible to select/choose
	 */
	public void setSelect() {
		this.canselect = true;
	}
	/**
	 * Makes the cell selectable
	 * @returns the cell that can be selected
	 */
	public boolean canSelect() {
		return this.canselect;
	}
	/**
	 * Makes the cell unselectable
	 */
	public void unselect() {
		this.canselect = false;
	}
	/**
	 * Displays the cell so that valid moves are *, black is B, and white is W
	 */
	public void display() {
		if (this.isEmpty()) // if cell empty
		{
			if (this.canselect) // if can be selected
				System.out.print("[ " + CANSELECT + " ]"); // print '*'
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
