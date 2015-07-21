import java.util.*;


public class SeaField {
	private static final int VERTICAL_FIELD_SIZE = 10;
	private static final int HORIZONTAL_FIELD_SIZE = 10;
	private Cell[][] field;
	private ArrayList<Ship> ships = new ArrayList<Ship>();
	static Scanner sc = new Scanner(System.in);
	
	public Cell getCell(int x, int y) {
		return field[x][y];
	}
	public ArrayList<Ship> getShips(){
		return this.ships;
	}
	public void setShips(ArrayList<Ship> ships){
		this.ships = ships;		
	}
	
	public void shoot(User u) {
		Coordinate c;
		do {
			if(u.isHuman()){System.out.println("Please add coordinate of shoot '");}
			c = u.getCoordinate();
		} while ( ((getCell(c.x,c.y).getState() == CellStates.Shooted )|| (getCell(c.x,c.y).getState() == CellStates.SunkShip )));

		for (Ship item : this.ships) {
			if (item.cathShip(c)) {

				getCell(c.x, c.y).setState(CellStates.SunkShip);
				item.setNumberOfLifes(item.getNumberOfLifes() - 1);

				if (item.getNumberOfLifes() == 0) {
					item.setState(ShipStates.Sunk);
					this.ships.remove(item);
					if(u.isHuman()){System.out.println("Hi is dead! ");}
					if (this.ships.isEmpty()) {
						break;
					}
				} else {
					item.setState(ShipStates.Deaed);
					if(u.isHuman()){System.out.println("You got him! ");}
				}

				shoot(u);
				break;

			} else if(getCell(c.x, c.y).getState() != CellStates.Shooted){
				getCell(c.x, c.y).setState(CellStates.Shooted);
			}
		}
		if(u.isHuman()){System.out.println("Next time cap, next time ....");}
	}

	public void showMap() {
		System.out.println("  |0 1 2 3 4 5 6 7 8 9\n--+-------------------");
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " |");
			for (int j = 0; j < 10; j++) {
				if (this.field[j][ i].getState() == CellStates.Ship) {
					System.out.print("O ");
				} else if (this.field[j][ i].getState() == CellStates.Shooted) {
					System.out.print("* ");
				} else if (this.field[j][ i].getState() == CellStates.SunkShip) {
					System.out.print("X ");
				} else {
					System.out.print("~ ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	

	

	public SeaField() { // создаем пустое поле
		field = new Cell[VERTICAL_FIELD_SIZE][HORIZONTAL_FIELD_SIZE];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Coordinate c = new Coordinate(i, j);
				field[i][j] = new Cell(c);
			}
		}
	}

	public boolean inRange(int x, int y) {
		if (x < 0 || y < 0 || x >= HORIZONTAL_FIELD_SIZE || y >= VERTICAL_FIELD_SIZE)
			return false;
		else
			return true;
	}

}
