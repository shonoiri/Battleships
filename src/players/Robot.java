package players;
import gameequipment.Coordinate;
import gameequipment.SeaField;

import java.util.Random;

public class Robot extends User {

	public Robot() {
		this.field = new SeaField();
	}

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
		System.out.println("ha, ego konanir kosit kak drysch ot armii....");

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
		System.out.println("etot idiot lupit po " + a + ":" + b);
		return c;
	}
}
