public class Robot implements User {
	private SeaField field;
	//public Coordinate last;	
	
	public Robot() {
		this.field = new SeaField();
	}
	
	public SeaField getField(){
		return this.field;
	}
	public void setField(SeaField field){
		this.field = field;		
	}
}
