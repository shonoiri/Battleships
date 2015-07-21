import java.util.Random;

public class Robot implements User {
	private SeaField field;
	//public Coordinate last;
	
	public SeaField getField(){
		return this.field;
	}
	public void setField(SeaField field){
		this.field = field;		
	}
	
	public void getShips() {

		Coordinate c, lc;
		int orient;

		for (int i = 1; i <= 4; i++) {
			for (int j = 5 - i; j >= 1; j--) {

				do {
					c = getCoordinate();
					orient = getOrientation();
					lc = Ship.getLastCoordinate(orient, i, c);
				} while (Ship.correctCoordinate(c, lc, this.field) == false);

				Ship f = new Ship(c, lc, this.field);
				f.setNumberOfLifes(i);
				this.field.getShips().add(f);
			}
		}
	}

	/*public void shoot(SeaField field) {
		Coordinate c ;
		do{
		c = getCoordinate();
		}while(field.getCell(c.x,c.y).getState() == CellStates.Shooted || field.getCell(c.x,c.y).getState() == CellStates.SunkShip );
		
		for (Ship item : field.ships) {
			
			if (item.cathShip(c)) {

				field.getCell(c.x, c.y).setState(CellStates.SunkShip);
				item.setNumberOfLifes(item.getNumberOfLifes() - 1);

				if (item.getNumberOfLifes() == 0) {
					item.setState(ShipStates.Sunk);
					field.ships.remove(item);
					if (field.ships.isEmpty()) {
						break;
					}
				} else {
					item.setState(ShipStates.Deaed);
				}
				// GUI.showMap(field);
				shoot(field);
				break;

			} else if(field.getCell(c.x, c.y).getState() != CellStates.Shooted){
				field.getCell(c.x, c.y).setState(CellStates.Shooted);
			}
		}
	}
*/
	@Override
	public boolean isHuman() {
		return false;
	}

	@Override
	public Coordinate getCoordinate() {
		int a;
		int b;

		Random rand = new Random();
		a = rand.nextInt(10) + 0;
		b = rand.nextInt(10) + 0;
		Coordinate c = new Coordinate(a, b);
		return c;
	}

	@Override
	public int getOrientation() {
		Random rand = new Random();
		int orient = (rand.nextInt(4) + 1);
		return orient;
	}

	@Override
	public boolean isLooser() {
		if (this.field.getShips().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public Robot() {
		this.field = new SeaField();
	}

}
