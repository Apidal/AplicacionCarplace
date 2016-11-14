package transfers;



public class TSesion {
	
	private String nick;
	private String pass;
	
	public TSesion(String nick, String pass){
		
		this.nick = nick;
		this.pass = pass;
	}
	
	public String getNick(){
		
		return this.nick;
	}
	
	public String getPass(){
		
		return this.pass;
	}
	
	

}
