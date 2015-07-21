import java.util.ArrayList;

public class Ship {
	private ArrayList<Cell> ship;
	private int numberOfLifes;
	private ShipStates state;
	public Coordinate fc, lc;
	
	public ShipStates getState(){
		return this.state;
	}
	public void setState(ShipStates state){
		this.state = state;
	}
	public int getNumberOfLifes(){
		return this.numberOfLifes;
	}
	public void setNumberOfLifes(int number){
		this.numberOfLifes = number;
	}
	public  ArrayList<Cell> getShip(){
		return this.ship;
	}

	public static boolean correctCoordinate(Coordinate c, Coordinate lc, SeaField fd) {
		boolean correctCoordinate = true;

		if (fd.inRange(c.x, c.y) == false || fd.inRange(lc.x, lc.y) == false) {
			correctCoordinate = false;

		} else {
			if (correctCoordinate) {
				for (int i = c.x; i <= lc.x; i++) {
					for (int j = c.y; j <= lc.y; j++) {
						if (fd.getCell(i, j).getState() != CellStates.Water) {

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
	public static Coordinate getLastCoordinate(int orient, int len, Coordinate c) {
		int f = 0;
		int k = 0;

		int a;
		switch (orient) {
		case 1:
			f = c.x - len + 1;
			a = c.x;
			c.x = f;
			f = a;
			k = c.y;
			break;
		case 4:
			f = c.x;
			k = c.y + len - 1;
			break;
		case 3:
			f = c.x + len - 1;
			k = c.y;

			break;
		case 2:
			f = c.x;
			k = c.y - len + 1;
			a = c.y;
			c.y = k;
			k = a;
			break;
		default:
			System.out.println("Incorrect orientation ");

		}

		return new Coordinate(f, k);
	}

	public Ship(Coordinate fc, Coordinate lc, SeaField field) {
		this.fc = fc;
		this.lc = lc;
		for (int a = fc.x; a <= lc.x; a++) {
			for (int b = fc.y; b <= lc.y; b++) {
				this.ship = new ArrayList<>();
				ship.add(field.getCell(a, b));
				field.getCell(a, b).setState(CellStates.Ship);
			}
		}

		for (int a = fc.x - 1; a <= lc.x + 1; a++) {
			for (int b = fc.y - 1; b <= lc.y + 1; b++) {
				if (field.inRange(a, b) && field.getCell(a, b).getState() != CellStates.Ship
						&& field.getCell(a, b).getState() != CellStates.TemporaryBlocked) {
					field.getCell(a, b).setState(CellStates.TemporaryBlocked);

				}
			}
		}
	}

}