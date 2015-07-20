public class Cell {
	public CellStates state;
	public Coordinate c;

	public Cell(Coordinate c) {
		this.c = c;
		state = CellStates.water;
	}

}
