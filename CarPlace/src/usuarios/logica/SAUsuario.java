package usuarios.logica;

import javax.swing.JList;

import transfers.TUsuario;

import com.aeat.valida.Validador;

public class SAUsuario implements ISAUsuario {
	private IDaoUsuario dUsuario;
	
	public SAUsuario() {
		this.dUsuario = new DaoUsuario();
	}

	
	public boolean bajaUsuario(String nick) {
		return dUsuario.bajaUsuario(nick);
		
	}

	
	public JList consultarUsuarios() {
		return dUsuario.consultarUsuarios();
	}

	
	public TUsuario mostrarUsuario(String nick) {
		return dUsuario.mostrarUsuario(nick);
	}

	
	public boolean modificarUsuario(TUsuario usuario){
		
		if(comprobarTamano(usuario.getPassword()))
			if(comprobarCampoNoVacio(usuario.getNombre()))
				if(comprobarCampoNoVacio(usuario.getApellidos()))
					if(comprobarDni(usuario.getDni()))
						if(dUsuario.modificarUsuario(usuario))
							return true;
				
		return false;
	}
		
	
	public boolean altaUsuario(TUsuario usuario){
		
		if(comprobarTamano(usuario.getNick()))
			if(!comprobarExistenciaNick(usuario.getNick()))
				if(comprobarTamano(usuario.getPassword()))
					if(comprobarSimilitudPasswords(usuario.getPassword(), usuario.getRepassword()))
						if(comprobarCampoNoVacio(usuario.getNombre()))
							if(comprobarCampoNoVacio(usuario.getApellidos()))
								if(comprobarDni(usuario.getDni()))
									if(dUsuario.altaUsuario(usuario))
										return true;
		
		return false;
	}
	
	/**
	 * Comprueba que la palabra que le venga no esta vacia
	 * @param palabra
	 * @return true si no esta vacia
	 */
	private boolean comprobarCampoNoVacio(String palabra){
		
		return (palabra.length() > 0);
	}
	
	/**
	 * Recibe una palabra y comprueba que su longitud sea mayor de 4 y menor de 17
	 * @param palabra
	 * @return true si es de un tamano valido
	 */
	private boolean comprobarTamano(String palabra){
		
		return ((palabra.length() > 4) && (palabra.length() < 17));
	}
		
	/**
	 * Comprueba que la password y la repassword son las mismas
	 * @param password
	 * @param repassword
	 * @return true si son iguales
	 */
	private boolean comprobarSimilitudPasswords(String password, String repassword){
			
		return password.equals(repassword);
	}
	
	/**
	 * Comprueba si el nick ya existe en la bbdd
	 * @param nick
	 * @return true si el nick ya existe en la bbdd
	 */
	private boolean comprobarExistenciaNick(String nick){
		
		return dUsuario.existeNick(nick);
	}
	
	/**
	 * Comprueba si el DNI introducido es valido
	 * @param dni
	 * @return true si es valido
	 */
	private boolean comprobarDni(String dni){
		Validador validador = new Validador();
        int e = validador.checkNif(dni);
 
        if (e > 0)
            return true;
        else
            return false;
	}

	
	public JList consultarUsuariosFiltro(String comodin) {
		return dUsuario.consultarUsuariosFiltro(comodin);
	}
}
