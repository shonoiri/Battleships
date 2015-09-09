package gameequipment;

public class Cell {
	private CellStates state;
	private Coordinate c;

	public Cell(Coordinate c) {
		this.c = c;
	}

	public CellStates getState() {
		return this.state;
	}

	public void setState(CellStates state) {
		this.state = state;
	}

	public Coordinate getCellCoordinate() {
		return this.c;
	}

	public void setCellCoordinate(Coordinate c) {
		this.c = c;
	}
}