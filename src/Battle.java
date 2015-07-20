
public class Battle {

	public static void main(String[] args) {

		Robot r = new Robot();

	//	Human h = new Human();

		Robot r1 = new Robot();

		GUI.getShips(r, r.field);
		GUI.showMap(r.field);

		GUI.getShips(r1, r1.field);
		GUI.showMap(r1.field);
		
		//GUI.showMap(h.map);

		while (!r.isLooser() && !r1.isLooser()) {
			r1.shoot(r.field);
			r.shoot(r1.field);
			}
			GUI.showMap(r.field);
			GUI.showMap(r1.field);

		//}
		if (r.isLooser()){System.out.println(", u win, my sun, u win  ..... " + r.field.ships.size() + r1.field.ships.size());
		}else{
			System.out.println(", ur looser, my friend ..... " + r.field.ships.size() + r1.field.ships.size());
		}
	}
}
