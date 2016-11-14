package sesion.logica;

public interface IDaoSesion {

	boolean loginCorrecto(String nick, String pass);
	
	public boolean existeUsuario(String nick);
	
	public boolean compruebaPasswordCorrecta(String nick, String pass);
	
	public void logOut(String nick);
}
