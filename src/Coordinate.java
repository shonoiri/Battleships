public class Coordinate {
	public int x;
	public int y;

	Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Coordinate getLastCoordinate(int orient, int len, Coordinate c) {
		int f = 0;
		int k = 0;

		int a;
		switch (orient) {
		case 1:
			f = c.x - len + 1;
			a = c.x;
			c.x = f;
			f = a;
			k = c.y;
			break;
		case 4:
			f = c.x;
			k = c.y + len - 1;
			break;
		case 3:
			f = c.x + len - 1;
			k = c.y;

			break;
		case 2:
			f = c.x;
			k = c.y - len + 1;
			a = c.y;
			c.y = k;
			k = a;
			break;
		default:
			System.out.println("Incorrect orientation ");

		}

		return new Coordinate(f, k);
	}

}
