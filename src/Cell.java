public class Cell {
	private CellStates state;
	private Coordinate c;

	public Cell(Coordinate c) {
		this.c = c;
		state = CellStates.Water;
	}
	public CellStates getState(){
		return state;
	}
	public void setState(CellStates state){
		this.state = state;
		
	}


}