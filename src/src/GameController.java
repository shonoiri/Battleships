import java.util.Random;
import java.util.Scanner;

public class GameController {
	private boolean endGame;
	
	//public GameController(Human human, Robot robot) {
	public GameController(Robot robot, Robot human){
		//setHumanShips(human);
		setRobotShips(robot);
		setRobotShips(human);
		do{
		robotMove(human.getField());
		robotMove(robot.getField());
		}while(!endGame);

	}

	Scanner sc = new Scanner(System.in);
	Random rand = new Random();

	private Coordinate askHumanCoordinate() {
		Coordinate c;
		do {
			System.out.print("x : ");
			int a = sc.nextInt();
			System.out.print("y : ");
			int b = sc.nextInt();
			c = new Coordinate(a, b);
			if (!c.inRange()) {
				System.out.println("Incorrect coordinate ");
				askHumanCoordinate();
			}
		} while (!c.inRange());

		return c;
	}

	private int askHumanOrientation() {
		int orient;
		do{
		System.out.println("orient");
		orient = sc.nextInt();
		if(orient !=1||orient!=2||orient!=3||orient !=4){
			System.out.println("Incorrect orien ");
		}
		}while(orient !=1||orient!=2||orient!=3||orient !=4);
		return orient;
	}

	private boolean canSetShip(Coordinate c, Coordinate lc, SeaField fd) {

		boolean canSetShip = true;

		for (int i = Math.min(c.getX(), lc.getX()); i <= Math.max(c.getX(), lc.getX()); i++) {
			for (int j = Math.min(c.getY(), lc.getY()); j <= Math.max(c.getY(), lc.getY()); j++) {
				if (fd.getCell(i, j).getState() != CellStates.WATER) {
					canSetShip = false;
					break;
				}
			}
		}
		return canSetShip;
	}

	private void setHumanShips(Human human) {
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
					orient = askHumanOrientation();
					c = askHumanCoordinate();

					switch (orient) {
					case 1:
						x = c.getX() - len + 1;
						y = c.getY();
						lc = new Coordinate(x, y);
						if (lc.inRange() && canSetShip(c, lc, human.getField())) {
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
						if (lc.inRange() && canSetShip(c, lc, human.getField())) {
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
						if (lc.inRange() && canSetShip(c, lc, human.getField())) {
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
						if (lc.inRange() && canSetShip(c, lc, human.getField())) {
							ship = new Ship(c, human.getField(), lc);
							ship.setNumberOfLifes(i);// sizeof
							human.getField().getShips().add(ship);
						} else {
							System.out.println("Can't set ship , please input correct coordinate ");
							setShip = false;
						}
						break;
					default: System.out.println("Incorrect orien ");
						break;
					}
				} while (!setShip);
			}
		}
	}

	private Coordinate generateRobotCoordinate() {
		int a;
		int b;

		Random rand = new Random();
		a = rand.nextInt(10) + 0;
		b = rand.nextInt(10) + 0;
		Coordinate c = new Coordinate(a, b);
		return c;
	}

	private int getRobotOrientation() {
		Random rand = new Random();
		int orient = (rand.nextInt(4) + 1);
		return orient;
	}

	private void setRobotShips(Robot robot) {
		int x, y;
		int orient;
		int len;
		boolean setShip;
		Coordinate lc;
		Coordinate c;
		Ship ship;
		setShip = true;

		for (int i = 1; i <= 4; i++) {
			for (int j = 5 - i; j >= 1; j--) {

				len = i;

				do {
					orient = getRobotOrientation();
					c = generateRobotCoordinate();

					switch (orient) {
					case 1:
						x = c.getX() - len + 1;
						y = c.getY();
						lc = new Coordinate(x, y);
						if (lc.inRange() && canSetShip(c, lc, robot.getField())) {
							ship = new Ship(c, robot.getField(), lc);
							ship.setNumberOfLifes(i);
							robot.getField().getShips().add(ship);
							setShip = true;
						} else {
							setShip = false;
						}
						break;
					case 4:
						x = c.getX();
						y = c.getY() + len - 1;
						lc = new Coordinate(x, y);
						if (lc.inRange() && canSetShip(c, lc, robot.getField())) {
							ship = new Ship(c, robot.getField(), lc);
							ship.setNumberOfLifes(i);
							robot.getField().getShips().add(ship);
							setShip = true;
						} else {
							setShip = false;
						}
						break;
					case 3:
						x = c.getX() + len - 1;
						y = c.getY();
						lc = new Coordinate(x, y);
						if (lc.inRange() && canSetShip(c, lc, robot.getField())) {
							ship = new Ship(c, robot.getField(), lc);
							ship.setNumberOfLifes(i);
							robot.getField().getShips().add(ship);
							setShip = true;
						} else {
							setShip = false;
						}
						break;
					case 2:
						x = c.getX();
						y = c.getY() - len + 1;
						lc = new Coordinate(x, y);
						if (lc.inRange() && canSetShip(c, lc, robot.getField())) {
							ship = new Ship(c, robot.getField(), lc);
							ship.setNumberOfLifes(i);
							robot.getField().getShips().add(ship);
							setShip = true;
						} else {
							setShip = false;
						}
						break;
					}
				} while (!setShip);
			}
		}
		robot.getField().showMap();
	}

	private boolean cathShip(Coordinate c, Ship ship) {

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

	private void humanMove(SeaField field) {
		Coordinate c;
		do {
			c = askHumanCoordinate();
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
				humanMove(field);
				break;
			} else{
				field.getCell(c.getX(), c.getY()).setState(CellStates.SHOT);
			}
		}
		field.showMap();
		if(field.getShips().isEmpty()){endGame = true;}
	}

	private void robotMove(SeaField field) {
		Coordinate c;
		do {
			c = generateRobotCoordinate();
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
				robotMove(field);
				break;
			} else {
				field.getCell(c.getX(), c.getY()).setState(CellStates.SHOT);
			}
		}
		field.showMap();
		if(field.getShips().isEmpty()){endGame = true;}
	}
}
