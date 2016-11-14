package sesion.control;

import sesion.logica.FachadaSesion;
import sesion.logica.IFachadaSesion;


public class ControlSesion {
	
	private IFachadaSesion fachada;
	
	/**
	 * Constructor de la clase
	 */
	public ControlSesion(){
		
		this.fachada = new FachadaSesion();
	}
	
	/**
	 * Inicia sesión en la aplicación si es posible
	 * @param nick identificador del usuario
	 * @param pass password del usuario
	 * @return Devuelve un booleano que indica si se puede iniciar o no sesión
	 */
	public boolean login(String nick, String pass) {
		
		return fachada.login(nick, pass);
	}
	
	/**
	 * Accede a la BBDD y pone la variable de conectado a false
	 * @param nick identificador del usuario
	 */
	public void logOut(String nick){
		this.fachada.logOut(nick);
	}

}
