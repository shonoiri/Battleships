import java.util.*;

interface User {

	public boolean isHuman();

	public Coordinate getCoordinate();

	public int getOrientation();

	public void shoot(SeaField field);

	public boolean isLooser();

	static Scanner sc = new Scanner(System.in);

}

