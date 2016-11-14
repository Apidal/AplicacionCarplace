package usuarios.control;

import javax.swing.JList;

import transfers.TUsuario;
import usuarios.logica.*;

public class ControlUsuario{
	private IFachadaUsuario fUsuario;
	
	public ControlUsuario() {
		this.fUsuario = new FachadaUsuario();
	}
	
	/**
	 * Da de baja al usuario que coincide con el nick que le viene por parametro
	 * @param nick
	 * @return true si le ha dado de baja
	 */
	public boolean bajaUsuario(String nick) {
		return fUsuario.bajaUsuario(nick);		
	}
	
	/**
	 * Devuelve una lista con todos los usuarios que hay en la bbdd
	 * @return lista de usuarios
	 */
	public JList consultarUsuarios() {
		return fUsuario.consultarUsuarios();		
	}
	
	/**
	 * Devuelve el usuario que tiene por nick el que le viene por parametro
	 * @param nick
	 * @return usuario
	 */
	public TUsuario mostrarUsuario(String nick) {
		return fUsuario.mostrarUsuario(nick);
	}
	
	/**
	 * Recibe un usuario y lo modifica
	 * @param usuario
	 * @return true si ha podido modificar al usuario
	 */
	public boolean modificarUsuario(TUsuario usuario){
		return fUsuario.modificarUsuario(usuario);
	}
	
	/**
	 * Recibe un usuario y le da de alta
	 * @param usuario
	 * @return true si ha dado de alta al usuario
	 */
	public boolean altaUsuario(TUsuario usuario){
		return fUsuario.altaUsuario(usuario);		
	}
	
	/**
	 * Recibe un filtro y devuelve una lista de nicks que coinciden con el filtro
	 * @param comodin
	 * @return lista de usuarios
	 */
	public JList consultarUsuariosFiltro(String comodin) {
		return fUsuario.consultarUsuariosFiltro(comodin);
	}
}
