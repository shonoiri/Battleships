package gameequipment;
import java.util.ArrayList;

public class Ship {
	private ArrayList<Cell> ship;
	private ShipStates state;
	public Coordinate firstCoordinate, lastCoordinate;

	public Ship(Coordinate firstCoordinate, SeaField field, Coordinate lastCoordinate) {
		this.firstCoordinate = firstCoordinate;
		this.lastCoordinate = lastCoordinate;
		Cell cell;
		Coordinate temp;
		this.ship = new ArrayList<Cell>();
		this.state = ShipStates.OK;
		for (int a = Math.min(firstCoordinate.getX(), lastCoordinate.getX()); a <= Math.max(firstCoordinate.getX(), lastCoordinate.getX()); a++) {
			for (int b = Math.min(firstCoordinate.getY(), lastCoordinate.getY()); b <= Math.max(firstCoordinate.getY(), lastCoordinate.getY()); b++) {
				temp = new Coordinate(a, b);
				cell = field.getCell(temp);
				ship.add(cell);
				cell.setState(CellStates.SHIP);
			}
		}

		for (int a = Math.min(firstCoordinate.getX(), lastCoordinate.getX()) - 1; a <= Math.max(firstCoordinate.getX(), lastCoordinate.getX()) + 1; a++) {
			for (int b = Math.min(firstCoordinate.getY(), lastCoordinate.getY()) - 1; b <= Math.max(firstCoordinate.getY(), lastCoordinate.getY()) + 1; b++) {				
				temp = new Coordinate(a, b);
				if (temp.inRange()) {
					cell = field.getCell(temp);
					if (cell.getState() != CellStates.SHIP && cell.getState() != CellStates.NEXTTOSHIP) {
						cell.setState(CellStates.NEXTTOSHIP);
					}
				}
			}
		}
	}

	public ShipStates getState() {
		return this.state;
	}

	public void setState(ShipStates state) {
		this.state = state;
	}
	
	public ArrayList<Cell> getShip() {
		return this.ship;
	}
}