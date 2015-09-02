package players;
import java.util.ArrayList;

import gameequipment.Cell;
import gameequipment.Coordinate;
import gameequipment.SeaField;

public abstract class User {
	protected ArrayList<Cell> shotCoordinates = new ArrayList<Cell>();
	
	protected ArrayList<Cell> nextToSunkShipCoordinates = new ArrayList<Cell>();

	public ArrayList<Cell> getNextToSunkShipCoordinates() {
		return nextToSunkShipCoordinates;
	}

	public void setNextToSunkShipCoordinates(ArrayList<Cell> nextToSunkShipCoordinates) {
		this.nextToSunkShipCoordinates = nextToSunkShipCoordinates;
	}

	protected SeaField field;
	
	protected String username;
	
	public ArrayList<Cell> getShotCoordinates() {
		return shotCoordinates;
	}

	public void setShotCoordinates(ArrayList<Cell> shotCoordinates) {
		this.shotCoordinates = shotCoordinates;
	}
	
	public void setShotCoordinates(Coordinate coordinate) {
	}

	public SeaField getField() {
		return this.field;
	}

	public void showField() {
	}

	public void setField(SeaField field) {
		this.field = field;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
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
