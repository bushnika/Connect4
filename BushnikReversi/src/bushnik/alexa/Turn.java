package bushnik.alexa;

public class Turn {
	private int value; // the player.

	public Turn() {
		this.value = (int) (Math.random() * 2.0d);
	}

	public Turn(int v) {
		this.value = v;
	}

	public int getTurn() {
		return this.value;
	}

	public void change() {
		this.value = (this.value + 1) % 2;
	}
}