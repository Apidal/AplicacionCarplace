package alquileres.control;

import java.util.ArrayList;

import transfers.TAlquiler;
import alquileres.logica.FachadaAlquiler;
import alquileres.logica.IFachadaAlquiler;

/**
 * Esta clase es la encargada de llevar el control
 * del subsistema de alquileres.
 * @author CARPLACE
 *
 */
public class ControlAlquiler {
	
	private IFachadaAlquiler f;
	
	/**
	 * Construye una nueva instancia de ControladorAlquileres.
	 */
	public ControlAlquiler(){
		this.f = new FachadaAlquiler();
	}
	
	/**
	 * Devuelve la lista de alquileres que tiene el usuario.
	 * @param nick identificador del usuario
	 * @return lista de alquileres del usuario
	 */
	public ArrayList<TAlquiler> consultaAlquileresUsuario(String nick){
		return this.f.consultaAlquileresUsuario(nick);
	}
	
	/**
	 * Devuelve toda la lista de alquileres de la aplicacion
	 * @return lista de alquileres de la aplicacion.
	 */
	public ArrayList<TAlquiler> listaAlquileres(){
		return this.f.listaAlquileres();
	}

	/**
	 * Devuelve un TAlquiler dado su identificador de alquiler.
	 * @param idAlquiler identificador del alquiler.
	 * @return alquiler del id(idAlquiler)
	 */
	public TAlquiler mostrarAlquiler(int idAlquiler) {
		return this.f.mostrarAlquiler(idAlquiler);
	}
	
	/**
	 * Da de alta en el sistema el alquiler pasado por
	 * parametro y devuelve si todo ha ido bien o no.
	 * @param alquiler alquiler a registras
	 * @return true si todo ha ido bien, false en otro caso
	 */
	public boolean altaAlquiler(TAlquiler alquiler) {
		return this.f.altaAlquiler(alquiler);
	}
	
	/**
	 * Da de baja el alquiler con el identificador pasado
	 * por parametro y devuelve si todo ha ido bien o no. 
	 * @param id identificador del alquiler
	 * @return true si todo ha ido bien, false en otro caso
	 */
	public boolean bajaAlquiler(int id){
		return this.f.bajaAlquiler(id);
	}
}