package reservas.logica;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import transfers.TReserva;

/**
 * Implementación de los metodos de la interfaz.
 * Esta clase es la que tiene la logica del subsistema
 * reservas.
 * @author CARPLACE
 *
 */
public class SAReserva implements ISAReserva {
	private IDaoReserva d;

	/**
	 * Construye una nueva instancia de SAReservas
	 */
	public SAReserva (){
		this.d = new DaoReserva();
	}
	
	@Override
	public ArrayList<TReserva> consultarReservas(String nick){
		return this.d.consultarReservas(nick);
	}
	
	@Override
	public ArrayList<TReserva> listaReservas() {
		return this.d.listaReservas();
	}

	@Override
	public TReserva mostrarReserva(int idReserva) {
		return this.d.mostrarReserva(idReserva);		
	}

	@Override
	public boolean modificarReserva(TReserva reserva) {
		boolean correcto = true;
		if(comprobarDatos(reserva))
			this.d.modificarReserva(reserva);
		else
			correcto = false;
		
		return correcto;
	}

	@Override
	public boolean altaReserva(TReserva reserva) {
		boolean correcto = true;
		
		if(comprobarDatos(reserva))
			this.d.altaReserva(reserva);
		else
			correcto = false;
		
		return correcto;
	}

	@Override
	public boolean bajaReserva(int id) {
		return this.d.bajaReserva(id);
	}
	
	private boolean comprobarDatos(TReserva reserva){
		boolean correcto = true;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String fechaact = dateFormat.format(cal.getTime());
		
		if(reserva.getInicio() == null || reserva.getFin() == null || reserva.getMatricula() == null ||
				reserva.getNick() == null || reserva.getInicio().compareTo(fechaact) < 0 || reserva.getFin().compareTo(reserva.getInicio()) < 0){
			correcto = false;
		}
		
		return correcto;
	}
}