package players;

import java.util.Random;
import java.util.Scanner;

import gameequipment.Coordinate;
import gameequipment.SeaField;

public class Robot extends User {

	private Scanner sc = new Scanner(System.in);

	public Robot() {
		System.out.print("Kak chotite vraga obozvat' kapitan ? ");
		String username = sc.nextLine();
		this.username = username;
		this.field = new SeaField();
	}

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
	public void missShot() {
		System.out.println("ha, konanir " + this.username + " kosit kak drysch ot armii....");

	}

	@Override
	public void goodShot() {
		System.out.println(this.username + " popal svoloch'");
	}

	@Override
	public void sunkShip() {
		System.out.println("O net, kapitan, nash korabl' potoplen ....");
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
		Random rand = new Random();
		x = rand.nextInt(10) + 0;
		y = rand.nextInt(10) + 0;
		coordinate = new Coordinate(x, y);
		System.out.println(this.username + " lupit po " + x + ":" + y);
		return coordinate;
	}
}
