import java.util.ArrayList;

public class Ship {
	public ArrayList<Cell> ship;
	public int numberOfLifes;
	public ShipStates state;
	public Coordinate fc, lc;

	public static boolean correctCoordinate(Coordinate c, Coordinate lc, SeaField fd) {
		boolean correctCoordinate = true;

		if (fd.inRange(c.x, c.y) == false || fd.inRange(lc.x, lc.y) == false) {
			correctCoordinate = false;

		} else {
			if (correctCoordinate) {
				for (int i = c.x; i <= lc.x; i++) {
					for (int j = c.y; j <= lc.y; j++) {
						if (fd.getCell(i, j).state != CellStates.Water) {

							correctCoordinate = false;
							break;
						}
					}
				}
			}
		}

		return correctCoordinate;
	}

	public boolean cathShip(Coordinate c) {
		for (int a = this.fc.x; a <= this.lc.x; a++) {
			for (int b = this.fc.y; b <= this.lc.y; b++) {
				Coordinate c1 = new Coordinate(a, b);
				if ((c.x == c1.x) && (c.y == c1.y)) {
					return true;

				}

			}
		}
		return false;

	}

	public Ship(Coordinate fc, Coordinate lc, SeaField field) {
		this.fc = fc;
		this.lc = lc;
		for (int a = fc.x; a <= lc.x; a++) {
			for (int b = fc.y; b <= lc.y; b++) {
				this.ship = new ArrayList<>();
				ship.add(field.getCell(a, b));
				field.getCell(a, b).state = CellStates.Ship;
			}
		}

		for (int a = fc.x - 1; a <= lc.x + 1; a++) {
			for (int b = fc.y - 1; b <= lc.y + 1; b++) {
				if (field.inRange(a, b) && field.getCell(a, b).state != CellStates.Ship
						&& field.getCell(a, b).state != CellStates.TemporaryBlocked) {
					field.getCell(a, b).state = CellStates.TemporaryBlocked;

				}
			}
		}
	}

}
