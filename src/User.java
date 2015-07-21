import java.util.*;

interface User {

	public boolean isHuman();

	public Coordinate getCoordinate();

	public int getOrientation();

	public boolean isLooser();

	static Scanner sc = new Scanner(System.in);
	
	public void getShips(); 

}

