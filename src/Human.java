
public class Human implements User {
	private String username;
	private SeaField field;
	//public SeaField map;
	
	public String getUsername(){
		return this.username;
	}
	
	public void setUsername(String username){
		this.username = username;
		
	}
	
	public SeaField getField(){
		return this.field;
	}
	
	public void setField(SeaField field){
		this.field = field;
	}
	
	@Override
	
	public void getShips() {

		Coordinate c, lc;
		int orient;

		for (int i = 1; i <= 4; i++) {
			for (int j = 5 - i; j >= 1; j--) {

				do {
					c = getCoordinate();
					orient = getOrientation();
					lc = Ship.getLastCoordinate(orient, i, c);
					if (Ship.correctCoordinate(c, lc, this.field) != true) {
						System.out.println("Incorrect coordinate , please input correct one ");
					}
				} while (Ship.correctCoordinate(c, lc, field) == false);

				Ship f = new Ship(c, lc, field);
				f.setNumberOfLifes(i);
				this.field.getShips().add(f);
			}
		}
	}

	
	@Override
	public boolean isHuman() {
		return true;
	}

	Human() {
		System.out.print("Please, enter your name: ");
		String username = sc.nextLine();
		System.out.print("\nWelcome, " + username + "! We're glad you're with us here\n\n");
		this.username = username;
		this.field = new SeaField();
		//this.map = new SeaField();
	}

	@Override
	public Coordinate getCoordinate() {

		System.out.print("x : ");
		int a = sc.nextInt();
		System.out.print("y : ");
		int b = sc.nextInt();

		Coordinate c = new Coordinate(a, b);
		return c;
	}

	@Override
	public int getOrientation() {
		System.out.println("orient");
		int orient = sc.nextInt();
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

}
