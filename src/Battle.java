
public class Battle {

	public static void main(String[] args) {

		Robot r = new Robot();

		// Human h = new Human();

		Robot r1 = new Robot();

		r.getShips();
		r.getField().showMap();
		
		r1.getShips();
		r1.getField().showMap();

		// GUI.showMap(h.map);

		for (;;) {
			r.getField().shoot(r1);
			r.getField().showMap();
			if (r.isLooser()) {
				break;
			}

			r1.getField().shoot(r);
			r1.getField().showMap();
			if (r1.isLooser()) {
				break;
			}

		}
		if (r.isLooser()) {
			System.out.println(", u win, my sun, u win  ..... " + r.getField().getShips().size() + r1.getField().getShips().size());
		} else {
			System.out.println(", ur looser, my friend ..... " + r.getField().getShips().size() + r1.getField().getShips().size());
		}
	}
}
