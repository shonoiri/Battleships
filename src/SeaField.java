import java.util.*;

public class SeaField {
	private static final int VERTICAL_FIELD_SIZE = 10;
	private static final int HORIZONTAL_FIELD_SIZE = 10;
	private Cell[][] field;
	private ArrayList<Ship> ships = new ArrayList<Ship>();
	
	public ArrayList<Ship> getShips(){
		return this.ships;
	}
	
	public void setShips(ArrayList<Ship> ships){
		this.ships = ships;		
	}
	
	public Cell getCell(int x, int y) {
		return field[x][y];
	}

	public void showMap() {
		System.out.println("  |0 1 2 3 4 5 6 7 8 9\n--+-------------------");
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " |");
			for (int j = 0; j < 10; j++) {
				if (this.field[j][ i].getState() == CellStates.SHIP) {
					System.out.print("O ");
				} else if (this.field[j][ i].getState() == CellStates.SHOT) {
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
}
