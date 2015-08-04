package gameprocess;

import java.util.ArrayList;
import java.util.Scanner;

import players.*;
import gameequipment.*;

public class GameController {
	private boolean endGame;
	private static GameController instance;
	private User player1;
	private User player2;
	private Scanner sc = new Scanner(System.in);

	private GameController() {
	}

	public static GameController getGC() {
		if (instance == null)
			instance = new GameController();
		return instance;
	}

	public void play() {
		boolean setChoise = true;
		String choise;

		do {
			System.out.println("\n Ctho delaesh' zdes' salaga ?\n");
			System.out.println("1: Piratskij kodex izuchaju, gospodin matros");
			System.out.println("2: Pshel von , bitva u menia");
			System.out.println("3: Da ya voobshe-to v bordel' idu ...");

			choise = sc.nextLine();

			switch (choise) {
			case "1":
				System.out.println("Kak govoril kucenko - pirkod eto nashe vse"); // DD
				setChoise = false;
				break;
			case "2":
				setChoise = false;
				SeaField player1Field;
				String player1Name;
				SeaField player2Field;
				String player2Name;
				do {
					System.out.println("Kak igrat'-to budem kapitan : 1 - robot i robot, 2 - Chelovek i robot");
					choise = sc.nextLine();

					switch (choise) {
					case "1":
						setChoise = true;
						this.player1 = new Robot();
						creataAndSetShips(player1);
						player1Field = player1.getField();
						player1Name = player1.getUsername();
						player1Field.showMap(player1Name);
						break;
					case "2":
						setChoise = true;
						this.player1 = new Human();
						creataAndSetShips(player1);
						player1Field = player1.getField();
						player1Name = player1.getUsername();
						player1Field.showField(player1Name);
						break;
					default:
						System.out.println("Kapitan, krichite razborchivej ...");
						setChoise = false;
						break;
					}
				} while (!setChoise);

				this.player2 = new Robot();
				player2Field = player2.getField();
				player2Name = player2.getUsername();
				creataAndSetShips(player2);
				player2Field.showMap(player2Name);

				attack(player1, player2);

			case "3":
				System.out.println("Pogod', mil chelovek, - ya s toboi !");
				break;
			default:
				System.out.println("Ty p'yan , salaga, otospis' i prichodi ...\n");
				setChoise = false;
				break;
			}
		} while (!setChoise);
	}

	private void creataAndSetShips(User player) {

		Coordinate coordinate;
		Coordinate lc;
		int orientace;
		boolean setShip;

		SeaField playerField = player.getField();
		
		for (int i = 1; i <= 4; i++) {
			for (int j = 5 - i; j >= 1; j--) {
				do {
					coordinate = player.askCoordinateOfShip();
					if (i == 1) {
						orientace = 3;
					} else {
						orientace = player.askOrientation();
					}
					lc = createLastCoordinate(coordinate, orientace, i);
					
					if (canSetShip(coordinate, lc, playerField)) {
						Ship ship;
						ship = new Ship(coordinate, playerField, lc);
						ArrayList<Ship> ships = playerField.getShips();
						ships.add(ship);
						setShip = true;
					} else {
						player.incorrectCoordinate();
						setShip = false;
					}
				} while (!setShip);
			}
		}

	}

	private void attack(User player1, User player2) {
		Coordinate coordinate;
		SeaField player2Field = player2.getField();
		SeaField player1Field = player1.getField();
		String player1Name = player1.getUsername();
		String player2Name = player2.getUsername();
		CellStates stateOFShotCell;
		do {
			boolean setMove = true;
			do {
				coordinate = player1.move();
				stateOFShotCell = player2Field.getCellState(coordinate);

				if (stateOFShotCell == CellStates.SUNKSHIP || stateOFShotCell == CellStates.SHOT) {
					player1.incorrectCoordinate();
					setMove = false;
				}
				if (checkMove(player2Field, coordinate)) {
					player1.missShot();
					setMove = true;
				} else {
					player1.goodShot();
					stateOFShotCell = player2Field.getCellState(coordinate);
					if (stateOFShotCell == CellStates.SUNKSHIP) {
						player1.sunkShip();
					}
					setMove = false;
				}
				player2Field.showField(player2Name);
			} while (!setMove);
			if (endGame) {
				System.out.println("My proigrali, komandir....");
				break;
			}
			setMove = true;

			do {
				coordinate = player2.move();
				stateOFShotCell = player1Field.getCellState(coordinate);

				if (stateOFShotCell == CellStates.SUNKSHIP || stateOFShotCell == CellStates.SHOT) {
					player2.incorrectCoordinate();
					coordinate = player2.move();
					setMove = false;
				}
				if (checkMove(player1Field, coordinate)) {
					player2.missShot();
					setMove = true;
				} else {
					player2.goodShot();
					stateOFShotCell = player2Field.getCellState(coordinate);
					if (stateOFShotCell == CellStates.SUNKSHIP) {
						player2.sunkShip();
					}
					setMove = false;
				}
				player1Field.showField(player1Name);
				if (endGame) {
					System.out.println("My pobedili, komandir!");
					break;
				}
			} while (!setMove);

		} while (!endGame);
		play();
	}

	
	private boolean canSetShip(Coordinate c, Coordinate lc, SeaField field) {

		boolean canSetShip = true;

		if (lc.inRange()) {

			for (int i = Math.min(c.getX(), lc.getX()); i <= Math.max(c.getX(),
					lc.getX()); i++) {
				for (int j = Math.min(c.getY(), lc.getY()); j <= Math.max(
						c.getY(), lc.getY()); j++) {
					Coordinate temp = new Coordinate(i, j);
					CellStates cellState = field.getCellState(temp);
					if (cellState != CellStates.WATER) {
						canSetShip = false;
						break;
					}
				}
			}
		} else {
			canSetShip = false;
		}
		return canSetShip;
	}

	private Coordinate createLastCoordinate(Coordinate c, int orient, int len) {
		int x, y;
		Coordinate lc = null  ;
		switch (orient) {
		case 1:
			x = c.getX() - len + 1;
			y = c.getY();
			lc = new Coordinate(x, y);
			break;
		case 4:
			x = c.getX();
			y = c.getY() + len - 1;
			lc = new Coordinate(x, y);
			break;
		case 3:
			x = c.getX() + len - 1;
			y = c.getY();
			lc = new Coordinate(x, y);
			break;
		case 2:
			x = c.getX();
			y = c.getY() - len + 1;
			lc = new Coordinate(x, y);
			break;
		}
		return lc;
	}

	
	private boolean checkMove(SeaField field, Coordinate c) {
		Cell cell = field.getCell(c);
		CellStates cellState = field.getCellState(c);
		boolean missShot = true;
		if (cellState != CellStates.SHIP) {

			for (Ship item : field.getShips()) {

				if (item.getShip().contains(cell)) {

					item.getShip().remove(cell);
					missShot = false;
					if (item.getShip().isEmpty()) {
						item.setState(ShipStates.Sunk);
						field.getShips().remove(item);
						cell.setState(CellStates.SUNKSHIP);
					} else {
						cell.setState(CellStates.SHOTSHIP);
					}
					break;
				}
			}
		} else {
			cell.setState(CellStates.SHOT);
		}
		if (field.getShips().isEmpty()) {
			endGame = true;
		}
		return missShot;
	}
}
