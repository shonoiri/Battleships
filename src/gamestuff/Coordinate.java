package gamestuff;
public class Coordinate {
	private int x;
	private int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return this.x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return this.y;
	}

	public boolean inRange() {
		if (this.x < 0 || this.y < 0 || this.x >= 10 || this.y >= 10)
			return false;
		else
			return true;
	}
}
