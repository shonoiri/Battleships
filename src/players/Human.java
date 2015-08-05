package players;
import java.util.Scanner;

import gameequipment.Coordinate;
import gameequipment.SeaField;

public class Human extends User {
	private String username;
	private Scanner sc = new Scanner(System.in);

	public Human() {
		System.out.println("Please, enter your name: ");
		String username = sc.nextLine();
		this.username = username;
		this.field = new SeaField();
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public Coordinate askCoordinateOfShip() {
		boolean corCor = false;
		Coordinate coordinate = null;
		int x = 0, y = 0;
		char[] temp1, temp2;
		SeaField humanField = this.field;
		
		humanField.showField(this.username);
		System.out.println("Nu chto, " + this.username + ", kuda loshan' stavit' budem ?");

		do {
			System.out.print("x : ");
			temp1 = sc.next().toCharArray();
			System.out.print("y : ");
			temp2 = sc.next().toCharArray();

			if (temp1.length != 1 || temp2.length != 1) {
				System.out.println("chet kak-to ne idet ..... \n");
				corCor = false;
			} else {
				x = Character.getNumericValue(temp1[0]);
				y = Character.getNumericValue(temp2[0]);
				coordinate = new Coordinate(x, y);
				corCor = true;
				if (!coordinate.inRange()) {
					System.out.println("chet kak-to ne idet ..... \n");
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
			System.out.println("\nA vdol' ili poperek -to ? \n 3: vpravo\n 1: vlevo \n 2: vverch \n 4: vniz \n");
			temp1 = sc.next();
			temp = temp1.toCharArray();
			orientation = Character.getNumericValue(temp[0]);
			corOr = true;
			if (temp.length != 1 || orientation != 1 && orientation != 2 && orientation != 3
					&& orientation != 4) {
				System.out.println("chet kak-to ne idet ..... \n");
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
		System.out.println(" kuda streliat' to budem kapitan ?");
		do {
			System.out.print("x : ");
			temp1 = sc.next().toCharArray();
			System.out.print("y : ");
			temp2 = sc.next().toCharArray();

			if (temp1.length != 1 || temp2.length != 1) {
				System.out.println("chet kak-to ne idet ..... \n");
				corCor = false;
			} else {
				x = Character.getNumericValue(temp1[0]);
				y = Character.getNumericValue(temp2[0]);
				coordinate = new Coordinate(x, y);
				corCor = true;
				if (!coordinate.inRange()) {
					System.out.println("chet kak-to ne idet ..... \n");
					corCor = false;
				}
			}
		} while (!corCor);
		return coordinate;
	}

	@Override
	public void missShot() {
		System.out.println("eto veter vinovat , tochno govorju .... veter ...");
	}

	@Override
	public void goodShot() {
		System.out.println("masterskij vystrel kapitan ");
	}
	
	@Override
	public void sunkShip(){
		System.out.println("Da kapitan , vy sdelali eto !!!! Ego korabl' potoplen");
	}

	public void incorrectCoordinate() {
		System.out.println("bocman, eta p'yanaya svin'ya opyat' chto-to naputal s coordinatami , kapitan .... ");
	}

}
