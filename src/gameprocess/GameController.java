package gameprocess;

import java.util.ArrayList;
import java.util.Scanner;

import gameequipment.Cell;
import gameequipment.CellStates;
import gameequipment.Coordinate;
import gameequipment.SeaField;
import gameequipment.Ship;
import gameequipment.ShipStates;
import players.Human;
import players.Robot;
import players.User;

public class GameController {
	private boolean endGame;
	private static GameController instance;
	private User player1;
	private User player;
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
			System.out.println("\n What you want to do ?\n");
			System.out.println("1: Read manual ");
			System.out.println("2: Play ");
			System.out.println("3: Exit ");

			choise = sc.nextLine();
			switch (choise) {
			case "1":
				System.out.println("See https://en.wikipedia.org/wiki/Battleship_(game) ");
				setChoise = false;
				break;
			case "2":
				setChoise = false;
				do {
					System.out.println("How do you want to play : 1 computer-computer, 2 human-computer ?");
					choise = sc.nextLine();

					switch (choise) {
					case "1":
						setChoise = true;
						this.player1 = new Robot();
						break;
					case "2":
						setChoise = true;
						this.player1 = new Human();
						break;
					default:
						System.out.println("Wrong number. Please, try again ");
						setChoise = false;
						break;
					}
				} while (!setChoise);

				player1.setUsername();
				creataAndSetShips(player1);
				player1.showField();

				this.player = new Robot();
				player.setUsername();
				creataAndSetShips(player);
				player.showField();

				attack(player1, player);

			case "3":
				System.out.println("Goodbye!");
				return;
			default:
				System.out.println("Wrong number. Please, try again \n");
				setChoise = false;
				break;
			}
		} while (!setChoise);
		//return;
	}

	private void creataAndSetShips(User player) {
		Coordinate coordinate;
		Coordinate lastCoordinate;
		int orientation;
		boolean setShip;
		Ship ship;
		SeaField playerField = player.getField();
		ArrayList<Ship> playersShips = playerField.getShips();

		for (int i = 1; i <= 4; i++) {
			for (int j = 5 - i; j >= 1; j--) {
				do {
					coordinate = player.askCoordinateOfShip();
					if (i == 1) {
						orientation = 3;
					} else {
						orientation = player.askOrientation();
					}
					lastCoordinate = createLastCoordinate(coordinate, orientation, i);
					if (canSetShip(coordinate, lastCoordinate, playerField)) {
						ship = new Ship();
						ship.setFirstCoordinate(coordinate);
						ship.setLastCoordinate(lastCoordinate);
						ship.setShip(playerField);
						playersShips.add(ship);
						setShip = true;
					} else {
						player.incorrectCoordinate();
						setShip = false;
					}
				} while (!setShip);
			}
		}
	}

	private void playersMove(User pl1, User pl2) {
		Coordinate coordinate;
		boolean setMove = true;
		String playerName = pl1.getUsername();
		SeaField player2Field = pl2.getField();
		do {
			coordinate = pl1.move();
			if (missShot(player2Field, coordinate)) {
				setMissShot(player2Field, coordinate);
				pl1.setShotCoordinates(coordinate);
				pl1.missShot();
				setMove = true;
			} else {
				pl1.goodShot();
				pl1.setShotCoordinates(coordinate);
				Ship shoutedShip = setGoodShot(player2Field, coordinate);
				if (shoutedShip.getState() == ShipStates.SUNK) {
					pl1.setNextToSunkShipCoordinates(shoutedShip.getNextToShipCells());
					pl1.sunkShip();
				}
				pl2.showField();
				if (endGame) {
					System.out.println(playerName + " win.");
					return;
				}
				setMove = false;
			}
			pl1.setShotCoordinates(coordinate);
		} while (!setMove);
		pl2.showField();
	}

	private void attack(User player1, User player2) {
		do {
			playersMove(player2, player1);
			if (endGame)
				break;
			playersMove(player1, player2);
		} while (!endGame);
		play();
	}

	private boolean canSetShip(Coordinate Coordinate, Coordinate lastCoordinate, SeaField field) {

		boolean canSetShip = true;
		Coordinate temp;
		CellStates cellState;
		if (lastCoordinate.inRange()) {
			for (int i = Math.min(Coordinate.getX(), lastCoordinate.getX()); i <= Math.max(Coordinate.getX(),
					lastCoordinate.getX()); i++) {
				for (int j = Math.min(Coordinate.getY(), lastCoordinate.getY()); j <= Math.max(Coordinate.getY(),
						lastCoordinate.getY()); j++) {
					temp = new Coordinate(i, j);
					cellState = field.getCellState(temp);
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

	private Coordinate createLastCoordinate(Coordinate coordinate, int orient, int lengthOfShip) {
		int x, y;
		Coordinate lastCoordinate = null;
		switch (orient) {
		case 1:
			x = coordinate.getX() - lengthOfShip + 1;
			y = coordinate.getY();
			lastCoordinate = new Coordinate(x, y);
			break;
		case 4:
			x = coordinate.getX();
			y = coordinate.getY() + lengthOfShip - 1;
			lastCoordinate = new Coordinate(x, y);
			break;
		case 3:
			x = coordinate.getX() + lengthOfShip - 1;
			y = coordinate.getY();
			lastCoordinate = new Coordinate(x, y);
			break;
		case 2:
			x = coordinate.getX();
			y = coordinate.getY() - lengthOfShip + 1;
			lastCoordinate = new Coordinate(x, y);
			break;
		}
		return lastCoordinate;
	}

	private Ship setGoodShot(SeaField field, Coordinate coordinate) {
		Cell cell = field.getCell(coordinate);
		ArrayList<Ship> shipsOnField = field.getShips();

		for (Ship ship : field.getShips()) {

			ArrayList<Cell> cellsOfShip = ship.getCells();

			if (cellsOfShip.contains(cell)) {
				cellsOfShip.remove(cell);
				if (cellsOfShip.isEmpty()) {
					ship.setState(ShipStates.SUNK);
					ship.setSunkShipArea();
					shipsOnField.remove(ship);
					cell.setState(CellStates.SUNKSHIP);
				} else {
					cell.setState(CellStates.SHOTSHIP);
				}
				if (shipsOnField.isEmpty()) {
					endGame = true;
				}
				return ship;
			}
		}
		return null;
	}

	private void setMissShot(SeaField field, Coordinate coordinate) {
		Cell cell = field.getCell(coordinate);
		cell.setState(CellStates.SHOT);
	}

	private boolean missShot(SeaField field, Coordinate coordinate) {
		CellStates cellState = field.getCellState(coordinate);
		if (cellState == CellStates.SHIP)
			return false;
		return true;
	}
}
