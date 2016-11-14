package CarPlace;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import reservas.control.ControlReserva;
import sesion.control.ControlSesion;
import transfers.TAlquiler;
import transfers.TReserva;
import usuarios.control.ControlUsuario;
import vehiculos.control.ControlVehiculo;
import alquileres.control.ControlAlquiler;


public class Main {
	public static void main(String[] args) {
		ControlReserva cRes = new ControlReserva();
		ControlVehiculo cVeh = new ControlVehiculo();
		ControlSesion cSes = new ControlSesion();
		ControlUsuario cUsu = new ControlUsuario();
		ControlAlquiler cAlq = new ControlAlquiler();
		
		prepararAplicacion(cRes,cAlq);
		VistaGui vistaPrincpal = new VistaGui(cRes, cAlq, cVeh, cUsu, cSes);
		vistaPrincpal.setResizable(false);
	}
	
	/**
	 * Eliminamos reservas y alquileres que ya hayan finalizado
	 * @param cRes
	 * @param cAlq
	 */
	private static void prepararAplicacion(ControlReserva cRes,ControlAlquiler cAlq){
		ArrayList<TReserva> listRes = cRes.listaReservas();
		ArrayList<TAlquiler> listAlq = cAlq.listaAlquileres();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String fechaact = dateFormat.format(cal.getTime());
		
		for (TReserva tReserva : listRes) {
			if(tReserva.getInicio().equalsIgnoreCase(fechaact))
				cRes.bajaReserva(tReserva.getId());
		}
		
		for (TAlquiler tAlquiler : listAlq) {
			if(tAlquiler.getFin().equalsIgnoreCase(fechaact))
				cAlq.bajaAlquiler(tAlquiler.getId());
		}
	}

}
