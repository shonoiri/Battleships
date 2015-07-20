import java.util.ArrayList;

public class SeaField {
	private static final int VERTICAL_FIELD_SIZE = 10;
	private static final int HORIZONTAL_FIELD_SIZE = 10;
	public Cell[][] field;
	public ArrayList<Ship> ships = new ArrayList<Ship>();

	public Cell getCell(int x, int y) {
		return field[x][y];
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
