package reservas.logica;

import java.util.ArrayList;

import transfers.TReserva;

/**
 * Implementacion de los metodos de la interfaz.
 * @author CARPLACE
 *
 */
public class FachadaReserva implements IFachadaReserva {
	private ISAReserva s;
	
	/**
	 * Construye una nueva instancia de FachadaReservas.
	 */
	public FachadaReserva(){
		this.s = new SAReserva();
	}
	
	@Override
	public ArrayList<TReserva> consultarReservas(String nick){
		return this.s.consultarReservas(nick);
	}
	
	@Override
	public ArrayList<TReserva> listaReservas() {
		return this.s.listaReservas();
	}

	@Override
	public TReserva mostrarReserva(int idReserva) {
		return this.s.mostrarReserva(idReserva);
	}

	@Override
	public boolean modificarReserva(TReserva reserva) {
		return this.s.modificarReserva(reserva);
	}

	@Override
	public boolean altaReserva(TReserva reserva) {
		return this.s.altaReserva(reserva);
	}

	@Override
	public boolean bajaReserva(int id){
		return this.s.bajaReserva(id);
	}
}