package gameequipment;
import java.util.ArrayList;

public class Ship {
<<<<<<< HEAD
	private ArrayList<Cell> shipCells;
=======
	private ArrayList<Cell> cells;
>>>>>>> 686fa24add6b9100cd7d3c458b20f852e79978a5
	private ArrayList<Cell> nextToShipCells;
	private ShipStates state;
	public Coordinate firstCoordinate, lastCoordinate;

	public Ship() {
	}

	public void setShip(SeaField field) {
		Cell cell;
		Coordinate temp;
<<<<<<< HEAD
		this.shipCells = new ArrayList<Cell>();
=======
		this.cells = new ArrayList<Cell>();
>>>>>>> 686fa24add6b9100cd7d3c458b20f852e79978a5
		this.nextToShipCells = new ArrayList<Cell>();

		for (int a = Math.min(firstCoordinate.getX(), lastCoordinate.getX()); a <= Math.max(firstCoordinate.getX(),
				lastCoordinate.getX()); a++) {
			for (int b = Math.min(firstCoordinate.getY(), lastCoordinate.getY()); b <= Math.max(firstCoordinate.getY(),
					lastCoordinate.getY()); b++) {
				temp = new Coordinate(a, b);
				cell = field.getCell(temp);
<<<<<<< HEAD
				shipCells.add(cell);
=======
				cells.add(cell);
>>>>>>> 686fa24add6b9100cd7d3c458b20f852e79978a5
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
<<<<<<< HEAD
		return shipCells;
	}

	public void setCells(ArrayList<Cell> cells) {
		this.shipCells = cells;
=======
		return cells;
	}

	public void setCells(ArrayList<Cell> cells) {
		this.cells = cells;
>>>>>>> 686fa24add6b9100cd7d3c458b20f852e79978a5
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