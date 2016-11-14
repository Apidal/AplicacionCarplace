package alquileres.logica;

import java.util.ArrayList;

import transfers.TAlquiler;

/**
 * Interfaz de la fachada del susbistema alquileres
 * @author CARPLACE
 *
 */
public interface IFachadaAlquiler {
	
	/**
	 * Devuelve la lista de alquileres que tiene el usuario.
	 * @param nick identificador del usuario
	 * @return lista de alquileres del usuario
	 */
	public ArrayList<TAlquiler> consultaAlquileresUsuario(String nick);
	
	/**
	 * Devuelve toda la lista de alquileres de la aplicacion
	 * @return lista de alquileres de la aplicacion.
	 */
	public ArrayList<TAlquiler> listaAlquileres();
	
	/**
	 * Devuelve un TAlquiler dado su identificador de alquiler.
	 * @param idAlquiler identificador del alquiler.
	 * @return alquiler del id(idAlquiler)
	 */
	public TAlquiler mostrarAlquiler(int idAlquiler);
	
	/**
	 * Da de alta en el sistema el alquiler pasado por
	 * parametro y devuelve si todo ha ido bien o no.
	 * @param alquiler alquiler a registras
	 * @return true si todo ha ido bien, false en otro caso
	 */
	public boolean altaAlquiler(TAlquiler alquiler);
	
	/**
	 * Da de baja el alquiler con el identificador pasado
	 * por parametro y devuelve si todo ha ido bien o no. 
	 * @param id identificador del alquiler
	 * @return true si todo ha ido bien, false en otro caso
	 */
	public boolean bajaAlquiler(int id);
}