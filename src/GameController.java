import java.util.Random;
import java.util.Scanner;

public class GameController {

	public GameController(Human human, Robot robot) {

		setHumanShips(human);
		setRobotShips(robot);

	}

	Scanner sc = new Scanner(System.in);
	Random rand = new Random();

	public Coordinate getHumanCoordinate() {
		Coordinate c;
		do {
			System.out.print("x : ");
			int a = sc.nextInt();
			System.out.print("y : ");
			int b = sc.nextInt();
			c = new Coordinate(a, b);
			if (!c.inRange()) {
				System.out.println("Incorrect coordinate ");
				getHumanCoordinate();
			}
		} while (!c.inRange());

		return c;
	}

	public int getHumanOrientation() {
		System.out.println("orient");
		int orient = sc.nextInt();
		return orient;
	}

	public boolean canSetShip(Coordinate c, Coordinate lc, SeaField fd) {

		boolean canSetShip = true;

		for (int i = c.getX(); i <= lc.getX(); i++) {
			for (int j = c.getY(); j <= lc.getY(); j++) {
				if (fd.getCell(i, j).getState() != CellStates.WATER) {
					canSetShip = false;
					break;
				}
			}
		}
		return canSetShip;
	}

	public void setHumanShips(Human human) {
		int x, y;
		int orient;
		int len;
		boolean setShip;
		Coordinate lc;
		Coordinate c;
		Ship ship;

		for (int i = 1; i <= 4; i++) {
			for (int j = 5 - i; j >= 1; j--) {
				setShip = true;
				len = i;

				do {
					orient = getHumanOrientation();
					c = getHumanCoordinate();

					switch (orient) {
					case 1:
						x = c.getX() - len + 1;
						y = c.getY();
						lc = new Coordinate(x, y);
						if (canSetShip(c, lc, human.getField())) {
							ship = new Ship(c, human.getField(), lc);
							ship.setNumberOfLifes(i);
							human.getField().getShips().add(ship);
						} else {
							System.out.println("Can't set ship , please input correct coordinate ");
							setShip = false;
						}
						break;
					case 4:
						x = c.getX();
						y = c.getY() + len - 1;
						lc = new Coordinate(x, y);
						if (canSetShip(c, lc, human.getField())) {
							ship = new Ship(c, human.getField(), lc);
							ship.setNumberOfLifes(i);
							human.getField().getShips().add(ship);
						} else {
							System.out.println("Can't set ship , please input correct coordinate ");
							setShip = false;
						}
						break;
					case 3:
						x = c.getX() + len - 1;
						y = c.getY();
						lc = new Coordinate(x, y);
						if (canSetShip(c, lc, human.getField())) {
							ship = new Ship(c, human.getField(), lc);
							ship.setNumberOfLifes(i);
							human.getField().getShips().add(ship);
						} else {
							System.out.println("Can't set ship , please input correct coordinate ");
							setShip = false;
						}
						break;
					case 2:
						x = c.getX();
						y = c.getY() - len + 1;
						lc = new Coordinate(x, y);
						if (canSetShip(c, lc, human.getField())) {
							ship = new Ship(c, human.getField(), lc);
							ship.setNumberOfLifes(i);
							human.getField().getShips().add(ship);
						} else {
							System.out.println("Can't set ship , please input correct coordinate ");
							setShip = false;
						}
						break;
					}
				} while (!setShip);
			}
		}
	}

	public Coordinate getRobotCoordinate() {
		int a;
		int b;

		Random rand = new Random();
		a = rand.nextInt(10) + 0;
		b = rand.nextInt(10) + 0;
		Coordinate c = new Coordinate(a, b);
		return c;
	}

	public int getRobotOrientation() {
		Random rand = new Random();
		int orient = (rand.nextInt(4) + 1);
		return orient;
	}

	public void setRobotShips(Robot robot) {
		int x, y;
		int orient;
		int len;
		boolean setShip;
		Coordinate lc;
		Coordinate c;
		Ship ship;

		for (int i = 1; i <= 4; i++) {
			for (int j = 5 - i; j >= 1; j--) {
				setShip = true;
				len = i;

				do {
					orient = getHumanOrientation();
					c = getHumanCoordinate();

					switch (orient) {
					case 1:
						x = c.getX() - len + 1;
						y = c.getY();
						lc = new Coordinate(x, y);
						if (canSetShip(c, lc, robot.getField())) {
							ship = new Ship(c, robot.getField(), lc);
							ship.setNumberOfLifes(i);
							robot.getField().getShips().add(ship);
						} else {
							setShip = false;
						}
						break;
					case 4:
						x = c.getX();
						y = c.getY() + len - 1;
						lc = new Coordinate(x, y);
						if (canSetShip(c, lc, robot.getField())) {
							ship = new Ship(c, robot.getField(), lc);
							ship.setNumberOfLifes(i);
							robot.getField().getShips().add(ship);
						} else {
							setShip = false;
						}
						break;
					case 3:
						x = c.getX() + len - 1;
						y = c.getY();
						lc = new Coordinate(x, y);
						if (canSetShip(c, lc, robot.getField())) {
							ship = new Ship(c, robot.getField(), lc);
							ship.setNumberOfLifes(i);
							robot.getField().getShips().add(ship);
						} else {
							setShip = false;
						}
						break;
					case 2:
						x = c.getX();
						y = c.getY() - len + 1;
						lc = new Coordinate(x, y);
						if (canSetShip(c, lc, robot.getField())) {
							ship = new Ship(c, robot.getField(), lc);
							ship.setNumberOfLifes(i);
							robot.getField().getShips().add(ship);
						} else {
							setShip = false;
						}
						break;
					}
				} while (!setShip);
			}
		}
	}

	public boolean cathShip(Coordinate c, Ship ship) {

		for (int a = ship.fc.getX(); a <= ship.lc.getX(); a++) {
			for (int b = ship.fc.getY(); b <= ship.lc.getY(); b++) {
				Coordinate c1 = new Coordinate(a, b);
				if ((c.getX() == c1.getX()) && (c.getY() == c1.getY())) {
					return true;
				}
			}
		}
		return false;
	}

	public void humanMove(SeaField field) {
		Coordinate c;
		do {
			c = getHumanCoordinate();
			if (field.getCell(c.getX(), c.getY()).getState() == CellStates.SHOT
					|| field.getCell(c.getX(), c.getY()).getState() == CellStates.SUNKSHIP) {
				System.out.println("ty byl zdes' , ustalyj putnik, idi v drugoe mesto ");
			}
		} while (field.getCell(c.getX(), c.getY()).getState() == CellStates.SHOT
				|| field.getCell(c.getX(), c.getY()).getState() == CellStates.SUNKSHIP);

		for (Ship item : field.getShips()) {

			if (cathShip(c, item)) {

				field.getCell(c.getX(), c.getY()).setState(CellStates.SUNKSHIP);
				item.setNumberOfLifes(item.getNumberOfLifes() - 1);

				if (item.getNumberOfLifes() == 0) {
					item.setState(ShipStates.Sunk);
					field.getShips().remove(item);
					if (field.getShips().isEmpty()) {
						break;
					}
				} else {
					item.setState(ShipStates.Deaed);
				}
				// GUI.showMap(field);
				humanMove(field);
				break;

			} else if (field.getCell(c.getX(), c.getY()).getState() != CellStates.SHOT) {
				field.getCell(c.getX(), c.getY()).setState(CellStates.SHOT);
			}
		}
		field.showMap();
	}

	public void robotMove(SeaField field) {
		Coordinate c;
		do {
			c = getHumanCoordinate();
		} while (field.getCell(c.getX(), c.getY()).getState() == CellStates.SHOT
				|| field.getCell(c.getX(), c.getY()).getState() == CellStates.SUNKSHIP);

		for (Ship item : field.getShips()) {

			if (cathShip(c, item)) {

				field.getCell(c.getX(), c.getY()).setState(CellStates.SUNKSHIP);
				item.setNumberOfLifes(item.getNumberOfLifes() - 1);

				if (item.getNumberOfLifes() == 0) {
					item.setState(ShipStates.Sunk);
					field.getShips().remove(item);
					if (field.getShips().isEmpty()) {
						break;
					}
				} else {
					item.setState(ShipStates.Deaed);
				}
				humanMove(field);
				break;

			} else if (field.getCell(c.getX(), c.getY()).getState() != CellStates.SHOT) {
				field.getCell(c.getX(), c.getY()).setState(CellStates.SHOT);
			}
		}
	}
}
