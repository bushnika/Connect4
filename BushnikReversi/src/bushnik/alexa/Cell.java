package bushnik.alexa;

public class Cell {
	private CellState cell;
	
	public Cell (CellState cs) {
		cell = cs;
	}
	public void setState (CellState cs){
		cell = cs;
	}
	public CellState getColor() {
		return cell;
	}
	public String toString() {
		switch (cell) {
		case EMPTY:
			return "-";
		case WHITE:
			return "W";
		case BLACK:
			return "B";
		default:
			return "-";
		}
	}
}
