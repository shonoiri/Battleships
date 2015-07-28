package players;
import gameequipment.Coordinate;
import gameequipment.SeaField;

import java.util.Scanner;

public class Human extends User {
	private String username;

	Human() {
		System.out.print("Please, enter your name: ");
		String username = sc.nextLine();
		System.out.print("\nWelcome, " + username
				+ "! We're glad you're with us here\n\n");
		this.username = username;
		this.field = new SeaField();
	}

	Scanner sc = new Scanner(System.in);

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public Coordinate askCoordinateOfShip() {
		System.out.println("kuda loshan' stavit' budem kapitan ?");
		Coordinate c;
		do {
			System.out.print("x : ");
			int a = sc.nextInt();
			System.out.print("y : ");
			int b = sc.nextInt();
			c = new Coordinate(a, b);
			if (!c.inRange()) {
				System.out.println("chet kak=to ne idet ..... ");
			}
		} while (!c.inRange());
		return c;
	}

	@Override
	public int askOrientation() {
		int orient;
		do {
			System.out.println("a vdol' ili poperek -to ?");
			orient = sc.nextInt();
			if (orient != 1 && orient != 2 && orient != 3 && orient != 4) {
				System.out.println("chet kak=to ne idet  jakor' ei v ..... ");
			}
		} while (orient != 1 && orient != 2 && orient != 3 && orient != 4);
		return orient;
	}

	@Override
	public Coordinate move() {
		System.out.println("kuda streliat' to budem kapitan ?");
		Coordinate c;
		do {
			System.out.print("x : ");
			int a = sc.nextInt();
			System.out.print("y : ");
			int b = sc.nextInt();
			c = new Coordinate(a, b);
			if (!c.inRange()) {
				System.out.println("chet kak=to ne idet ..... ");
			}
		} while (!c.inRange());
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

	public void incorrectCoordinate() {
		System.out
				.println("bocman, eta p'yanaya svin'ya opyat' chto-to naputal s coordinatami , kapitan .... ");
	}
}
