package gameprocess;

import java.util.Scanner;

import players.*;
import gameequipment.*;

public class GameController {
	private boolean endGame;
	private static GameController instance;
	private User player1;
	private User player2;
	Scanner sc = new Scanner(System.in);

	private GameController() {
	}
	
	public static GameController getGC(){
		if (instance == null)
			instance = new GameController();
		return instance;
	}

	public void play() {

		Boolean setChoise = true;
		do {
			String choise;
			System.out.println("\n Ctho delaesh' zdes' salaga ?\n");
			System.out.println("1: Piratskij kodex izuchaju, gospodin matros");
			System.out.println("2: Pshel von , bitva u menia");
			System.out.println("3: Da ya voobshe-to v bordel' idu ...");

			choise = sc.nextLine();

			switch (choise) {
			case "1":
				System.out.println("Kak govoril kucenko - pirkod eto nashe vse");
				setChoise = false;
				break;
			case "2":
				setChoise = false;
				do {
					System.out.println("Kak igrat'-to budem kapitan : 1 - robot i robot, 2 - Chelovek i robot");

					choise = sc.nextLine();
					switch (choise) {
					case "1":
						setChoise = true;
						this.player1 = new Robot();
						creataAndSetShips(player1);
						player1.getField().showMap(player1.getUsername());
						break;
					case "2":
						setChoise = true;
						this.player1 = new Human();
						creataAndSetShips(player1);
						player1.getField().showField();
						break;
					default:
						System.out.println("Kapitan, krichite razborchivej ...");
						break;
					}
				} while (!setChoise);

				player2 = new Robot();
				creataAndSetShips(player2);
				player2.getField().showField();

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
					if (canSetShip(coordinate, lc, player.getField())) {
						Ship ship = new Ship(coordinate, player.getField(), lc);
						player.getField().getShips().add(ship);
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
		do {
			coordinate = player1.move();
			if (player2.getField().getCell(coordinate).getState() == CellStates.SUNKSHIP
					|| player2.getField().getCell(coordinate).getState() == CellStates.SHOT) {
				player1.incorrectCoordinate();
				coordinate = player1.move();
			}
			if (checkMove(player2.getField(), coordinate)) {
				player1.missShot();
			} else {
				player1.goodShot();
			}
			player2.getField().showMap(player2.getUsername());
			if (endGame) {
				System.out.println("My proigrali, komandir....");
				break;
			}
			coordinate = player2.move();
			if (player1.getField().getCell(coordinate).getState() == CellStates.SUNKSHIP
					|| player1.getField().getCell(coordinate).getState() == CellStates.SHOT) {
				player2.incorrectCoordinate();
				coordinate = player2.move();
			}
			if (checkMove(player1.getField(), coordinate)) {
				player2.missShot();
			} else {
				player2.goodShot();
			}
			player2.getField().showField();
			if (endGame) {
				System.out.println("My pobedili, komandir!");
				break;
			}

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
					if (field.getCell(temp).getState() != CellStates.WATER) {
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
		Cell cel;
		cel = field.getCell(c);
		boolean missShot = true;
		for (Ship item : field.getShips()) {

			if (item.getShip().contains(cel)) {

				cel.setState(CellStates.SUNKSHIP);
				item.getShip().remove(cel);
				missShot = false;
				if (item.getShip().isEmpty()) {
					item.setState(ShipStates.Sunk);
					field.getShips().remove(item);
					if (field.getShips().isEmpty()) {
						endGame = true;
						break;
					}
				}
				break;
			}
		}
		if (cel.getState() != CellStates.SUNKSHIP) {
			cel.setState(CellStates.SHOT);
		}
		if (field.getShips().isEmpty()) {
			endGame = true;
		}
		return missShot;
	}
}
