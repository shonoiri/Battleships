import java.util.ArrayList;

public class Ship {
	private ArrayList<Cell> ship;
	private int numberOfLifes;
	private ShipStates state;
	public Coordinate fc, lc;

	public Ship(Coordinate fc, SeaField field, Coordinate lc) {
		this.fc = fc;
		this.lc = lc;

		for (int a = Math.min(fc.getX(), lc.getX()); a <= Math.max(fc.getX(), lc.getX()); a++) {
			for (int b = Math.min(fc.getY(), lc.getY()); b <= Math.max(fc.getY(), lc.getY()); b++) {
				this.ship = new ArrayList<Cell>();
				ship.add(field.getCell(a, b));
				field.getCell(a, b).setState(CellStates.SHIP);
			}
		}

		for (int a = Math.min(fc.getX(), lc.getX()) - 1; a <= Math.max(fc.getX(), lc.getX()) + 1; a++) {
			for (int b = Math.min(fc.getY(), lc.getY()) - 1; b <= Math.max(fc.getY(), lc.getY()) + 1; b++) {
				Coordinate temp = new Coordinate(a, b);
				if (temp.inRange() && field.getCell(a, b).getState() != CellStates.SHIP
						&& field.getCell(a, b).getState() != CellStates.NEXTTOSHIP) {
					field.getCell(a, b).setState(CellStates.NEXTTOSHIP);
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

	public int getNumberOfLifes() {
		return this.numberOfLifes;
	}

	public void setNumberOfLifes(int number) {
		this.numberOfLifes = number;
	}

	public ArrayList<Cell> getShip() {
		return this.ship;
	}
}