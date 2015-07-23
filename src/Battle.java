public class Battle {

	public static void main(String[] args) {

		Robot r = new Robot();

		Human h = new Human();
		
		GameController gc = new GameController(h, r);
	}
}