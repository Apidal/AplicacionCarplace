package sesion.logica;

public interface ISASesion {

	public boolean login(String nick, String pass);
	
	public void logOut(String nick);

}
