package players;
import gameequipment.Coordinate;
import gameequipment.SeaField;

public class User {
	protected SeaField field;
	
	protected String username;

	public SeaField getField() {
		return this.field;
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

	}

	public void goodShot() {

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
