package players;
import java.util.ArrayList;
import java.util.List;

import gameequipment.Cell;
import gameequipment.Coordinate;
import gameequipment.SeaField;

public abstract class User {
	protected ArrayList<Coordinate> shotCoordinates = new ArrayList<Coordinate>();
	
	protected ArrayList<Coordinate> nextToSunkShipCoordinates = new ArrayList<Coordinate>();

	protected SeaField field;
	
	protected String username;
	
	public ArrayList<Coordinate> getNextToSunkShipCoordinates() {
		return nextToSunkShipCoordinates;
	}

	public void setNextToSunkShipCoordinates(List<Cell> list) {
		for (Cell cell : list) {
			Coordinate coordinate = cell.getCellCoordinate();
			this.nextToSunkShipCoordinates.add(coordinate);
		}
	}

	
	public ArrayList<Coordinate> getShotCoordinates() {
		return shotCoordinates;
	}

	public void setShotCoordinates(Coordinate coordinate) {
		this.shotCoordinates.add(coordinate);
	}

	public SeaField getField() {
		return this.field;
	}

	public void showField() {
	}

	public void setField(SeaField field) {
		this.field = field;
	}

	public void setUsername() {
	}

	public String getUsername() {
		return this.username;
	}

	public Coordinate askCoordinateOfShip() {
		return null;
	}

	public void missShot() {
		System.out.println(this.username + " missed shot ");
	}

	public void goodShot() {
		System.out.println(this.username + " made shot");
	}
	
	public void sunkShip(){
		System.out.println("Ship had sunk");
	}

	public int askOrientation() {
		return 0;
	}

	public void incorrectCoordinate() {

	}

	public Coordinate move() {
		return null;
	}
}
