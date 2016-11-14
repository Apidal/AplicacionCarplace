package sesion.logica;


public class SASesion implements ISASesion {
	
	private IDaoSesion dao;
	
	public SASesion(){
		
		this.dao = new DaoSesion();
		
	}

	/**
	 * Comprueba que los datos introducidos son validos y
	 * accede al DAO para comprobar que los datos son correctos
	 * @param nick identificador del usuario
	 * @param pass password del usuario
	 * @return Devuelve un booleano que indica si se puede iniciar o no sesión
	 */
	public boolean login(String nick, String pass) {
		
		if(!comprobarDatos(nick, pass)) return false;
		return dao.loginCorrecto(nick, pass);
		
	}
	
	/**
	 * Compreba que los datos introducidos son validos
	 * @param nick identificador del usuario
	 * @param pass password del usuario
	 * @return devuelve un booleano que indica si los datos son correctos
	 */
	private boolean comprobarDatos(String nick, String pass){
		if(nick.equals("")) return false;
		if(pass.equals("") || pass.length() < 4 || pass.length() > 16) return false;
		return true;
	}

	/**
	 * Accede a la BBDD y pone la variable de conectado a false
	 * @param nick identificador del usuario
	 */
	public void logOut(String nick) {
		this.dao.logOut(nick);
		
	}

}
