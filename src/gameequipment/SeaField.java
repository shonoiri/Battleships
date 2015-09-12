	package gameequipment;

import java.util.ArrayList;
import java.util.List;

public class SeaField {
	private static final int VERTICAL_FIELD_SIZE = 10;
	private static final int HORIZONTAL_FIELD_SIZE = 10;
	private Cell[][] field;
	private List<Ship> ships = new ArrayList<Ship>();

	public SeaField() {
		field = new Cell[VERTICAL_FIELD_SIZE][HORIZONTAL_FIELD_SIZE];
		Cell cell;
		Coordinate coordinate;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				coordinate = new Coordinate(i, j);
				cell = new Cell(coordinate);
				cell.setState(CellStates.WATER);
				field[i][j] = cell;
			}
		}
	}

	public CellStates getCellState(Coordinate coordinate) {
		Cell cell = getCell(coordinate);
		return cell.getState();
	}

	public List<Ship> getShips() {
		return this.ships;
	}

	public void setShips(ArrayList<Ship> ships) {
		this.ships = ships;
	}

	public Cell getCell(Coordinate c) {
		int x = c.getX();
		int y = c.getY();
		Cell cell = field[x][y];
		return cell;
	}
}
