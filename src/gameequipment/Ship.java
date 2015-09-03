package gameequipment;
import java.util.ArrayList;

public class Ship {
	private ArrayList<Cell> cells;
	private ArrayList<Cell> nextToShipCells;
	private ShipStates state;
	public Coordinate firstCoordinate, lastCoordinate;

	public Ship() {
	}

	public void setShip(SeaField field) {
		Cell cell;
		Coordinate temp;
		this.cells = new ArrayList<Cell>();
		this.nextToShipCells = new ArrayList<Cell>();

		for (int a = Math.min(firstCoordinate.getX(), lastCoordinate.getX()); a <= Math.max(firstCoordinate.getX(),
				lastCoordinate.getX()); a++) {
			for (int b = Math.min(firstCoordinate.getY(), lastCoordinate.getY()); b <= Math.max(firstCoordinate.getY(),
					lastCoordinate.getY()); b++) {
				temp = new Coordinate(a, b);
				cell = field.getCell(temp);
				cells.add(cell);
				cell.setState(CellStates.SHIP);
			}
		}

		for (int a = Math.min(firstCoordinate.getX(), lastCoordinate.getX()) - 1; a <= Math.max(firstCoordinate.getX(),
				lastCoordinate.getX()) + 1; a++) {
			for (int b = Math.min(firstCoordinate.getY(), lastCoordinate.getY()) - 1; b <= Math
					.max(firstCoordinate.getY(), lastCoordinate.getY()) + 1; b++) {
				temp = new Coordinate(a, b);
				if (temp.inRange()) {
					cell = field.getCell(temp);
					if (cell.getState() != CellStates.SHIP) {
						cell.setState(CellStates.NEXTTOSHIP);
						nextToShipCells.add(cell);
					}
				}
			}
		}
	}

	public void setSunkShipArea() {
		for (Cell cell : nextToShipCells)
			cell.setState(CellStates.NEXTTOSUNKSHIP);
	}

	public ArrayList<Cell> getCells() {
		return cells;
	}

	public void setCells(ArrayList<Cell> cells) {
		this.cells = cells;
	}

	public ArrayList<Cell> getNextToShipCells() {
		return nextToShipCells;
	}

	public void setNextToShipCells(ArrayList<Cell> nextToShipCells) {
		this.nextToShipCells = nextToShipCells;
	}

	public Coordinate getFirstCoordinate() {
		return firstCoordinate;
	}

	public void setFirstCoordinate(Coordinate firstCoordinate) {
		this.firstCoordinate = firstCoordinate;
	}

	public Coordinate getLastCoordinate() {
		return lastCoordinate;
	}

	public void setLastCoordinate(Coordinate lastCoordinate) {
		this.lastCoordinate = lastCoordinate;
	}

	public ShipStates getState() {
		return this.state;
	}

	public void setState(ShipStates state) {
		this.state = state;
	}


}