package usuarios.logica;

import javax.swing.JList;

import transfers.TUsuario;

public interface IDaoUsuario {

	/**
	 * Comprueba si el nick ya existe en la BBDD
	 * @param nick
	 * @return True si existe, False si no existe
	 */
	public boolean existeNick(String nick);
	
	/**
	 * Da de baja a un usuario
	 * @param nick
	 * @return true si ha dado de baja correctamente al usuario
	 */
	public boolean bajaUsuario(String nick);
	
	/**
	 * Da de alta a un usuario
	 * @param usuario
	 * @return true si todo ha ido bien
	 */
	public boolean altaUsuario(TUsuario usuario);
	
	/**
	 * Devuelve un usuario
	 * @param nick
     * @return usuario
	 */
	public TUsuario mostrarUsuario(String nick);
	
	/**
	 * Crea una lista con todos los usuarios ordenados por el nick
	 * @return lista
	 */
	public JList consultarUsuarios();
	
	/**
	 * modifica un usuario que le viene por parametros
	 * @param usuario
	 * @return true si ha ido todo bien, false si no se ha podido modificar
	 */
	public boolean modificarUsuario(TUsuario usuario);
	
	/**
	 * Crea una lista con los nicks de los usuarios que coinciden con el comodin
	 * @param comodin
	 * @return lista con los nicks
	 */
	public JList consultarUsuariosFiltro(String comodin);

}
