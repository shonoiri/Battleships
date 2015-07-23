
public class Battle {

	public static void main(String[] args) {

		Robot r = new Robot();
		Robot test = new Robot();

		Human h = new Human();
		
		GameController gc = new GameController(test, r);
	}
}