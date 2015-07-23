import java.util.Scanner;

public class Human implements User {
	private String username;
	private SeaField field;
	//public SeaField map;
	
	Human() {
		System.out.print("Please, enter your name: ");
		String username = sc.nextLine();
		System.out.print("\nWelcome, " + username + "! We're glad you're with us here\n\n");
		this.username = username;
		this.field = new SeaField();
	}
	
	Scanner sc = new Scanner(System.in);
	
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
}
