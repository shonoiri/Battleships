package gameequipment;
import java.util.ArrayList;

public class Ship {
	private ArrayList<Cell> ship;
	private ShipStates state;
	public Coordinate fc, lc;

	public Ship(Coordinate fc, SeaField field, Coordinate lc) {
		this.fc = fc;
		this.lc = lc;
		Cell cell;
		Coordinate temp;
		this.ship = new ArrayList<Cell>();
		for (int a = Math.min(fc.getX(), lc.getX()); a <= Math.max(fc.getX(), lc.getX()); a++) {
			for (int b = Math.min(fc.getY(), lc.getY()); b <= Math.max(fc.getY(), lc.getY()); b++) {

				temp = new Coordinate(a, b);
				cell = field.getCell(temp);
				ship.add(cell);
				cell.setState(CellStates.SHIP);
			}
		}

		for (int a = Math.min(fc.getX(), lc.getX()) - 1; a <= Math.max(fc.getX(), lc.getX()) + 1; a++) {
			for (int b = Math.min(fc.getY(), lc.getY()) - 1; b <= Math.max(fc.getY(), lc.getY()) + 1; b++) {
				
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