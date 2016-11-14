package alquileres.logica;

import java.util.ArrayList;

import transfers.TAlquiler;

/**
 * Implementacion de los metodos de la interfaz.
 * @author CARPLACE
 *
 */
public class FachadaAlquiler implements IFachadaAlquiler {
	private ISAAlquiler s;
	
	/**
	 * Construye una nueva instancia de FachadaAlquileres.
	 */
	public FachadaAlquiler(){
		this.s = new SAAlquiler();
	}
	
	@Override
	public ArrayList<TAlquiler> consultaAlquileresUsuario(String nick){
		return this.s.consultaAlquileresUsuario(nick);
	}

	@Override
	public TAlquiler mostrarAlquiler(int idAlquiler) {
		return this.s.mostrarAlquiler(idAlquiler);
	}

	@Override
	public boolean altaAlquiler(TAlquiler alquiler) {
		return this.s.altaAlquiler(alquiler);
	}

	@Override
	public boolean bajaAlquiler(int id){
		return this.s.bajaAlquiler(id);
	}

	@Override
	public ArrayList<TAlquiler> listaAlquileres() {
		return this.s.listaAlquileres();
	}
}