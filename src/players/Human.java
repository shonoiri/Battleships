package players;
import gameequipment.Coordinate;
import gameequipment.SeaField;

import java.util.Scanner;

public class Human extends User {
	private String username;

	public Human() {
		System.out.println("Please, enter your name: ");
		String username = sc.nextLine();
		this.username = username;
		this.field = new SeaField();
	}

	private Scanner sc = new Scanner(System.in);

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public Coordinate askCoordinateOfShip() {
		boolean corCor = false;
		Coordinate c = null;
		int a = 0, b = 0;
		char[] temp1, temp2;
		
		getField().showField(this.username);
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
				a = Character.getNumericValue(temp1[0]);
				b = Character.getNumericValue(temp2[0]);
				c = new Coordinate(a, b);
				corCor = true;
				if (!c.inRange()) {
					System.out.println("chet kak-to ne idet ..... \n");
					corCor = false;
				}
			}
		} while (!corCor);
		return c;
	}

	@Override
	public int askOrientation() {
		int orient = 0;
		String temp1 = null;
		char[] temp;
		boolean corOr = false;
		do {
			System.out.println("\nA vdol' ili poperek -to ? \n 3: vpravo\n 1: vlevo \n 2: vverch \n 4: vniz \n");
			temp1 = sc.next();
			temp = temp1.toCharArray();
			orient = Character.getNumericValue(temp[0]);
			corOr = true;
			if (temp.length != 1 || orient != 1 && orient != 2 && orient != 3
					&& orient != 4) {
				System.out.println("chet kak-to ne idet ..... \n");
				corOr = false;
			}
		} while (!corOr);
		return orient;
	}

	@Override
	public Coordinate move() {	
		boolean corCor = false;
		System.out.println(" kuda streliat' to budem kapitan ?");
		Coordinate c = null;
		int a = 0, b = 0;
		char[] temp1, temp2;

		do {
			System.out.print("x : ");
			temp1 = sc.next().toCharArray();
			System.out.print("y : ");
			temp2 = sc.next().toCharArray();

			if (temp1.length != 1 || temp2.length != 1) {
				System.out.println("chet kak-to ne idet ..... \n");
				corCor = false;
			} else {
				a = Character.getNumericValue(temp1[0]);
				b = Character.getNumericValue(temp2[0]);
				c = new Coordinate(a, b);
				corCor = true;
				if (!c.inRange()) {
					System.out.println("chet kak-to ne idet ..... \n");
					corCor = false;
				}
			}
		} while (!corCor);
		return c;
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
		System.out
				.println("bocman, eta p'yanaya svin'ya opyat' chto-to naputal s coordinatami , kapitan .... ");
	}

}
