import java.util.ArrayList;

public class Ship {
	private ArrayList<Cell> ship;
	private int numberOfLifes;
	private ShipStates state;
	public Coordinate fc, lc;

	public Ship(Coordinate fc, SeaField field, Coordinate lc) {
		this.fc = fc;
		this.lc = lc;

		for (int a = fc.getX(); a <= lc.getX(); a++) {
			for (int b = fc.getY(); b <= lc.getY(); b++) {
				this.ship = new ArrayList<>();
				ship.add(field.getCell(a, b));
				field.getCell(a, b).setState(CellStates.SHIP);
			}
		}

		for (int a = fc.getX() - 1; a <= lc.getX() + 1; a++) {
			for (int b = fc.getY() - 1; b <= lc.getY() + 1; b++) {
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