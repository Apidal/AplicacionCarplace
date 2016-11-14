package usuarios.logica;

import javax.swing.JList;

import transfers.TUsuario;

public class FachadaUsuario implements IFachadaUsuario {
	private ISAUsuario saUsuario;
	
	public FachadaUsuario() {
		this.saUsuario = new SAUsuario();
	}


	@Override
	public boolean bajaUsuario(String nick) {
		return saUsuario.bajaUsuario(nick);
		
	}

	@Override
	public JList consultarUsuarios() {
		return saUsuario.consultarUsuarios();
		
	}

	@Override
	public TUsuario mostrarUsuario(String nick) {
		// TODO Auto-generated method stub
		return saUsuario.mostrarUsuario(nick);
	}

	public boolean modificarUsuario(TUsuario usuario){
		return saUsuario.modificarUsuario(usuario);
	}

	public boolean altaUsuario(TUsuario usuario){
		return saUsuario.altaUsuario(usuario);
		
	}
	
	public JList consultarUsuariosFiltro(String comodin) {
		return saUsuario.consultarUsuariosFiltro(comodin);
	}

}
