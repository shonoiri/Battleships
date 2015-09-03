package players;

import java.util.Scanner;

import gameequipment.Cell;
import gameequipment.CellStates;
import gameequipment.Coordinate;
import gameequipment.SeaField;

public class Human extends User {

	private String username;
	private Scanner sc = new Scanner(System.in);

	public Human() {
		this.field = new SeaField();
	}

	@Override
	public void setShotCoordinates(Coordinate coordinate) {
		this.shotCoordinates.add(coordinate);
	}

	@Override
	public void showField() {
		Coordinate coordinate;
		Cell cell;
		CellStates cellState;
		System.out.println("Field of " + this.username + " : ");
		System.out.println("  |0 1 2 3 4 5 6 7 8 9\n--+-------------------");
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " |");
			for (int j = 0; j < 10; j++) {
				coordinate = new Coordinate(j, i);
				cell = this.field.getCell(coordinate);
				cellState = cell.getState();
				if (cellState == CellStates.SHIP) {
					System.out.print("o ");
				} else if (cellState == CellStates.SHOT) {
					System.out.print("* ");
				} else if (cellState == CellStates.SUNKSHIP || cellState == CellStates.SHOTSHIP) {
					System.out.print("X ");
				} else {
					System.out.print("~ ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public void setUsername() {
		System.out.println("Please, enter your name: ");
		String username = sc.nextLine();
		this.username = username;
	}

	@Override
	public Coordinate askCoordinateOfShip() {
		boolean corCor = false;
		Coordinate coordinate = null;
		int x = 0, y = 0;
		char[] temp1, temp2;

		showField();
		System.out.println(" Please enter coordinate of ship ");

		do {
			System.out.print("x : ");
			temp1 = sc.next().toCharArray();
			System.out.print("y : ");
			temp2 = sc.next().toCharArray();
			
			if (temp1.length != 1 || temp2.length != 1) {
				System.out.println("Wrong coordinate. Please try again \n");
				corCor = false;
			} else {
				x = Character.getNumericValue(temp1[0]);
				y = Character.getNumericValue(temp2[0]);
				coordinate = new Coordinate(x, y);
				corCor = true;
				if (!coordinate.inRange()) {
					System.out.println("Wrong coordinate. Please try again \n");
					corCor = false;
				}
			}
		} while (!corCor);
		return coordinate;
	}

	@Override
	public int askOrientation() {
		int orientation = 0;
		String temp1 = null;
		char[] temp;
		boolean corOr = false;
		do {
			System.out.println("\nPlease enter orientation of ship : 1 - to the left, 2 - up, 3 - to the right, 4 - down  \n");
			temp1 = sc.next();
			temp = temp1.toCharArray();
			orientation = Character.getNumericValue(temp[0]);
			corOr = true;
			if (temp.length != 1 || orientation != 1 && orientation != 2 && orientation != 3
					&& orientation != 4) {
				System.out.println("Wrong orientation. Please try again \n");
				corOr = false;
			}
		} while (!corOr);
		return orientation;
	}

	@Override
	public Coordinate move() {	
		boolean corCor = false;
		Coordinate coordinate = null;
		int x = 0, y = 0;
		char[] temp1, temp2;
		System.out.println(" Please enter coordinate of shoot ");
		do {
			System.out.print("x : ");
			temp1 = sc.next().toCharArray();
			System.out.print("y : ");
			temp2 = sc.next().toCharArray();

			if (temp1.length != 1 || temp2.length != 1) {
				System.out.println("Wrong coordinate. Please try again \n");
				corCor = false;
			} else {
				x = Character.getNumericValue(temp1[0]);
				y = Character.getNumericValue(temp2[0]);
				coordinate = new Coordinate(x, y);
				corCor = true;
				if (!coordinate.inRange()) {
					System.out.println("Wrong coordinate. Please try again \n");
					corCor = false;
				}
			}
			if (shotCoordinates.contains(coordinate)) {
				incorrectCoordinate();
				corCor = false;
			}
		} while (!corCor);
		return coordinate;
	}

	public void incorrectCoordinate() {
		System.out.println("Wrong coordinate. Please try again \n");
	}

}
