import java.util.*;

public class GUI {

	static Scanner sc = new Scanner(System.in);

	static void showMap(SeaField field) {
		System.out.println("  |0 1 2 3 4 5 6 7 8 9\n--+-------------------");
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " |");
			for (int j = 0; j < 10; j++) {
				if (field.getCell(j, i).state == CellStates.Ship) {
					System.out.print("O ");
				} else if (field.getCell(j, i).state == CellStates.Shooted) {
					System.out.print("* ");
				} else if (field.getCell(j, i).state == CellStates.SunkShip) {
					System.out.print("X ");
				} else {
					System.out.print("~ ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	static void getShips(User u, SeaField field) {

		Coordinate c, lc;
		int orient;

		for (int i = 1; i <= 4; i++) {
			for (int j = 5 - i; j >= 1; j--) {

				do {
					c = u.getCoordinate();
					orient = u.getOrientation();
					lc = c.getLastCoordinate(orient, i, c);
					if (u.isHuman() && Ship.correctCoordinate(c, lc, field) != true) {
						System.out.println("Incorrect coordinate , please input correct one ");
					}
				} while (Ship.correctCoordinate(c, lc, field) == false);

				Ship f = new Ship(c, lc, field);
				f.numberOfLifes = i;
				field.ships.add(f);
			}
		}
	}
}
