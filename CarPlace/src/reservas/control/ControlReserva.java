package reservas.control;

import java.util.ArrayList;

import reservas.logica.FachadaReserva;
import reservas.logica.IFachadaReserva;
import transfers.TReserva;

/**
 * Esta clase es la encargada de llevar el control
 * del subsistema de reservas.
 * @author CARPLACE
 *
 */
public class ControlReserva {
	
	private IFachadaReserva f;
	
	/**
	 * Construye una nueva instancia de ControladorReservas.
	 */
	public ControlReserva(){
		this.f = new FachadaReserva();
	}
	
	/**
	 * Devuelve la lista de reservas que tiene el usuario.
	 * @param nick identificador del usuario
	 * @return lista de reservas del usuario
	 */
	public ArrayList<TReserva> consultarReservas(String nick){
		return this.f.consultarReservas(nick);
	}
	
	/**
	 * Devuelve toda la lista de reservas de la aplicacion
	 * @return lista de reservas de la aplicacion.
	 */
	public ArrayList<TReserva> listaReservas(){
		return this.f.listaReservas();
	}
	
	/**
	 * Devuelve un TReserva dado su identificador de reserva.
	 * @param idReserva identificador de la reserva.
	 * @return reserva del id(idReserva)
	 */
	public TReserva mostrarReserva(int idReserva) {
		return this.f.mostrarReserva(idReserva);
	}
	
	/**
	 * Modifica la reserva pasado por parametro
	 * y devuelve si todo ha ido bien o no.
	 * @param reserva a modificar
	 * @return true si todo ha ido bien, false en otro caso
	 */
	public boolean modificarReserva(TReserva reserva) {
		return this.f.modificarReserva(reserva);
	}
	
	/**
	 * Da de alta en el sistema la reserva pasado por
	 * parametro y devuelve si todo ha ido bien o no.
	 * @param reserva reserva a registrar
	 * @return true si todo ha ido bien, false en otro caso
	 */
	public boolean altaReserva(TReserva reserva) {
		return this.f.altaReserva(reserva);
	}
	
	/**
	 * Da de baja la reserva con el identificador pasado
	 * por parametro y devuelve si todo ha ido bien o no.
	 * @param id identificador de la reserva
	 * @return true si todo ha ido bien, false en otro caso
	 */
	public boolean bajaReserva(int id){
		return this.f.bajaReserva(id);
	}
}