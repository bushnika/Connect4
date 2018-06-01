package bushnik.alexa;

class Move {

	int i;
	int j; // position on i and j axis
	/**
	 * Creates the directions on the i and j axis
	 * @param i - direction on i axis
	 * @param j - direction on j axis
	 */
	public Move(int i, int j) {
		this.i = i;
		this.j = j;
	}
	/**
	 * Gets the location of the position on the i axis 
	 * @return location of chip on i axis
	 */
	public int getI() {
		return i;
	}
	/**
	 * Gets the location of the position on j axis
	 * @return location of chip on j axis
	 */
	public int getJ() {
		return j;
	}
}
