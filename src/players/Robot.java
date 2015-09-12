package players;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import gameequipment.Cell;
import gameequipment.CellStates;
import gameequipment.Coordinate;
import gameequipment.SeaField;

public class Robot extends User {
	public Robot() {
		this.field = new SeaField();
	}

	@Override
	public void setUsername() {
		System.out.print("Please enter name of robot ");
		String username = sc.nextLine();
		this.username = username;
	}

	@Override
	public String getUsername() {
		return this.username;
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
	private Set<Cell> allCellsOnField;

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
		this.shotCoordinates.add(coordinate);
	}

	@Override
	public int askOrientation() {
		Random rand = new Random();
		int orientation = (rand.nextInt(4) + 1);
		return orientation;
	}
	
	private boolean contains(Coordinate coordinate){
		for (Coordinate coordinate1 : nextToSunkShipCoordinates) {
			if((coordinate1.getX() == coordinate.getX()) && (coordinate1.getY() == coordinate.getY()))
				return true;
		}
		for (Coordinate coordinate2 : shotCoordinates) {
			if((coordinate2.getX() == coordinate.getX()) && (coordinate2.getY() == coordinate.getY()))
				return true;
		}
		return false;
	}

	@Override
	public Coordinate move() {

		Cell cell;
		Iterator<Cell> iterator = allCellsOnField.iterator();
		cell = iterator.next();
		allCellsOnField.remove(cell);
		System.out.println(this.username + " is shoting at " + cell.getCellCoordinate().getX() + ":" + cell.getCellCoordinate().getY());
		return cell.getCellCoordinate();

	}
}
