package players;
import gameequipment.Coordinate;
import gameequipment.SeaField;

import java.util.Random;
import java.util.Scanner;

public class Robot extends User {

	public Robot() {
		System.out.print("Kak chotite vraga obozvat' kapitan ? ");
		String username = sc.nextLine();
		this.username = username;
		this.field = new SeaField();
	}
	Scanner sc = new Scanner(System.in);
	@Override
	public Coordinate askCoordinateOfShip() {
		int a;
		int b;

		Random rand = new Random();
		a = rand.nextInt(10) + 0;
		b = rand.nextInt(10) + 0;
		Coordinate c = new Coordinate(a, b);
		return c;
	}

	@Override
	public void missShot() {
		System.out.println("ha, konanir " + this.username + " kosit kak drysch ot armii....");

	}

	@Override
	public void goodShot() {
		System.out.println("on nas podbil , on nas podbil , trevogaaaaaaaa");
	}

	@Override
	public int askOrientation() {
		Random rand = new Random();
		int orient = (rand.nextInt(4) + 1);
		return orient;
	}

	@Override
	public Coordinate move() {
		int a;
		int b;

		Random rand = new Random();
		a = rand.nextInt(10) + 0;
		b = rand.nextInt(10) + 0;
		Coordinate c = new Coordinate(a, b);
		System.out.println("etot idiot " + this.username + " lupit po " + a + ":" + b);
		return c;
	}
}
