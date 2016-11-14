package reservas.logica;

import java.util.ArrayList;

import transfers.TReserva;

/**
 * Interfaz del DaoReservas.
 * @author CARPLACE
 *
 */
public interface IDaoReserva {
	
	/**
	 * Devuelve la lista de reservas que tiene el usuario.
	 * @param nick identificador del usuario
	 * @return lista de reservas del usuario
	 */
	public ArrayList<TReserva> consultarReservas(String nick);
	
	/**
	 * Devuelve toda la lista de reservas de la aplicacion
	 * @return lista de reservas de la aplicacion.
	 */
	public ArrayList<TReserva> listaReservas();
	
	/**
	 * Devuelve un TReserva dado su identificador de reserva.
	 * @param idReserva identificador de la reserva.
	 * @return reserva del id(idReserva)
	 */
	public TReserva mostrarReserva(int idReserva);
	
	/**
	 * Modifica la reserva pasado por parametro
	 * y devuelve si todo ha ido bien o no.
	 * @param reserva a modificar
	 * @return true si todo ha ido bien, false en otro caso
	 */
	public boolean modificarReserva(TReserva reserva);
	
	/**
	 * Da de alta en el sistema la reserva pasado por
	 * parametro.
	 * @param reserva reserva a registrar
	 */
	public void altaReserva(TReserva reserva);
	
	/**
	 * Da de baja la reserva con el identificador pasado
	 * por parametro y devuelve si todo ha ido bien o no.
	 * @param id identificador de la reserva
	 * @return true si todo ha ido bien, false en otro caso
	 */
	public boolean bajaReserva(int id); 
}