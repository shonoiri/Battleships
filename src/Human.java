
public class Human implements User {
	public String username;
	public SeaField field;
	public SeaField map;

	public void shoot(SeaField field) {
		Coordinate c;
		do {
			System.out.println("Please add coordinate of shoot '");
			c = getCoordinate();
		} while (!field.inRange(c.x, c.y));

		for (Ship item : field.ships) {
			if (item.cathShip(c)) {

				this.map.getCell(c.x, c.y).state = CellStates.SunkShip;
				item.numberOfLifes--;

				if (item.numberOfLifes == 0) {
					item.state = ShipStates.Sunk;
					field.ships.remove(item);
					System.out.println("Hi is dead! ");
					if (field.ships.isEmpty()) {
						break;
					}
				} else {
					item.state = ShipStates.Deaed;
					System.out.println("You got him! ");
				}
				GUI.showMap(map);
				shoot(field);
				break;

			} else {
				this.map.getCell(c.x, c.y).state = CellStates.Shooted;
			}
		}
		System.out.println("Next time cap, next time ....");
	}

	@Override
	public boolean isHuman() {
		return true;
	}

	Human() {
		System.out.print("Please, enter your name: ");
		String username = sc.nextLine();
		System.out.print("\nWelcome, " + username + "! We're glad you're with us here\n\n");
		this.username = username;
		this.field = new SeaField();
		this.map = new SeaField();
	}

	@Override
	public Coordinate getCoordinate() {

		System.out.print("x : ");
		int a = sc.nextInt();
		System.out.print("y : ");
		int b = sc.nextInt();

		Coordinate c = new Coordinate(a, b);
		return c;
	}

	@Override
	public int getOrientation() {
		System.out.println("orient");
		int orient = sc.nextInt();
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

}
