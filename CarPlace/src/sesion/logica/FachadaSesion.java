package sesion.logica;


public class FachadaSesion implements IFachadaSesion {
	
	private ISASesion sa;
	
	public FachadaSesion(){
		
		this.sa = new SASesion();
	}

	/**
	 * Inicia sesión en la aplicación si es posible
	 * @param nick identificador del usuario
	 * @param pass password del usuario
	 * @return Devuelve un booleano que indica si se puede iniciar o no sesión
	 */
	public boolean login(String nick, String pass) {
		
		return sa.login(nick, pass);
		
	}

	/**
	 * Accede a la BBDD y pone la variable de conectado a false
	 * @param nick identificador del usuario
	 */
	public void logOut(String nick) {
		this.sa.logOut(nick);
		
	}

}
