import java.util.Random;

public class Robot implements User {
	public SeaField field;

	public void shoot(SeaField field) {
		Coordinate c = getCoordinate();
		for (Ship item : field.ships) {
			if (item.cathShip(c)) {

				field.getCell(c.x, c.y).state = CellStates.SunkShip;
				item.numberOfLifes--;

				if (item.numberOfLifes == 0) {
					item.state = ShipStates.Sunk;
					field.ships.remove(item);
					if (field.ships.isEmpty()) {
						break;
					}
				} else {
					item.state = ShipStates.Deaed;
				}
				// GUI.showMap(field);
				shoot(field);
				break;

			} else {
				field.getCell(c.x, c.y).state = CellStates.Shooted;
			}
		}
	}

	@Override
	public boolean isHuman() {
		return false;
	}

	@Override
	public Coordinate getCoordinate() {
		int a;
		int b;

		Random rand = new Random();
		a = rand.nextInt(9) + 0;
		b = rand.nextInt(9) + 0;
		Coordinate c = new Coordinate(a, b);
		return c;
	}

	@Override
	public int getOrientation() {
		Random rand = new Random();
		int orient = (rand.nextInt(4) + 1);
		return orient;
	}

	@Override
	public boolean isLooser() {
		if (this.field.ships.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public Robot() {
		this.field = new SeaField();
	}

}
