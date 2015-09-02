package players;

import java.util.Random;
import java.util.Scanner;

import gameequipment.Cell;
import gameequipment.CellStates;
import gameequipment.Coordinate;
import gameequipment.SeaField;

public class Robot extends User {
	public Robot() {
		System.out.print("Please enter name of robot ");
		String username = sc.nextLine();
		this.username = username;
		this.field = new SeaField();
	}

	@Override
	public void showField() {
		Coordinate coordinate;
		Cell cell;
		CellStates cellState;
		System.out.println("Field of " + this.username + " : " );
		System.out.println("  |0 1 2 3 4 5 6 7 8 9\n--+-------------------");
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " |");
			for (int j = 0; j < 10; j++) {
				coordinate = new Coordinate(j, i);
				cell = this.field.getCell(coordinate);
				cellState = cell.getState();
				if (cellState == CellStates.SHOT) {
					System.out.print("* ");
				} else if (cellState == CellStates.SUNKSHIP || cellState == CellStates.SHOTSHIP) {
					System.out.print("X ");
				} else if (cellState == CellStates.NEXTTOSUNKSHIP) {
					System.out.print("- ");
				} else {
					System.out.print("~ ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	private Scanner sc = new Scanner(System.in);

	@Override
	public Coordinate askCoordinateOfShip() {
		int x;
		int y;
		Coordinate coordinate;
		Random rand = new Random();
		x = rand.nextInt(9) + 0;
		y = rand.nextInt(9) + 0;
		coordinate = new Coordinate(x, y);
		return coordinate;
	}

	@Override
	public void setShotCoordinates(Coordinate coordinate) {
		Cell cell = this.field.getCell(coordinate);
		this.shotCoordinates.add(cell);
	}

	@Override
	public int askOrientation() {
		Random rand = new Random();
		int orientation = (rand.nextInt(4) + 1);
		return orientation;
	}

	@Override
	public Coordinate move() {
		int x;
		int y;
		Coordinate coordinate;
		Cell cell;
		do {
			Random rand = new Random();
			x = rand.nextInt(10) + 0;
			y = rand.nextInt(10) + 0;
			coordinate = new Coordinate(x, y);
			cell = this.field.getCell(coordinate);
		} while (this.shotCoordinates.contains(cell));
		System.out.println(this.username + " is shoting at " + x + ":" + y);
		return coordinate;
	}
}
