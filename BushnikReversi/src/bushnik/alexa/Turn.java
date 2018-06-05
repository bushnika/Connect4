package bushnik.alexa;

public class Turn {
	private int value; // the player number
	/**
	 * Creates a turn starting with a random player
	 */
	public Turn() {
		this.value = (int) (Math.random() * 2.0d);
	}
	/**
	 * Creates a turn starting with selected first player
	 * @param v - value
	 */
	public Turn(int v) {
		this.value = v;
	}
	/**
	 * Returns the player whose turn it is
	 * @return value of the player whose turn it is
	 */
	public int getTurn() {
		return this.value;
	}
	/**
	 * Change the turn to the other player
	 */
	public void change() {
		this.value = (this.value + 1) % 2;
	}
}
