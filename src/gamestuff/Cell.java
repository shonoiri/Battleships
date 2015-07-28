package gamestuff;

public class Cell {
	private CellStates state;
	private Coordinate c;

	public Cell(Coordinate c) {
		this.c = c;
		state = CellStates.WATER;
	}

	public CellStates getState() {
		return state;
	}

	public void setState(CellStates state) {
		this.state = state;
	}

	public Coordinate getCellCoordinate() {
		return c;
	}

	public void setCellCoordinate(Coordinate c) {
		this.c = c;
	}
}