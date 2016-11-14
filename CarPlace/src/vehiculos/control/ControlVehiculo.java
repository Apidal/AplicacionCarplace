package vehiculos.control;

import java.util.ArrayList;

import transfers.TVehiculo;
import vehiculos.logica.FachadaVehiculo;
import vehiculos.logica.IFachadaVehiculo;

/**
 * Esta clase es la encargada de llevar el control
 * del subsistema de vehiculos.
 * @author CARPLACE
 *
 */
public class ControlVehiculo {
	/**
	 *
	 */
	private IFachadaVehiculo f;
	
	/**
	 * Construye una nueva instancia de ControladorVehiculos.
	 */
	public ControlVehiculo(){
		this.f = new FachadaVehiculo();
	}
	

	/**
	 * Devuelve la lista de vehiculos que cumple con los filtros introducidos y donde importa la fecha de ocupación de los vehiculos.
	 * @param fechaIni fecha inicial de ocupacion de vehiculo
	 * @param fechaFin fecha final de ocupación de vehiculo
	 * @param marca marca a la que pertenece el vehiculo
	 * @param carburante carburante con el que funciona el vehiculo
	 * @param categoria categoria a la que pertenece el vehiculo
	 * @param numPlazas numero de asientos que posee el vehiculo
	 * @return lista de vehiculos del sistema que cumple con los filtros
	 */
	public ArrayList<TVehiculo> consultarVehiculosFechas(String fechaIni, String fechaFin, String marca, String carburante, String categoria, int numPlazas) {
		return this.f.consultarVehiculosFechas(fechaIni, fechaFin, marca, carburante, categoria, numPlazas);
	}
	

	/**
	 * Devuelve la lista de vehiculos que cumple con los filtros introducidos.
	 * @param marca marca a la que pertenece el vehículo
	 * @param carburante carburante con el que funciona el vehículo
	 * @param categoria categoria a la que pertenece el vehículo
	 * @param numPlazas numero de asientos que posee el vehículo
	 * @return lista de vehículos del sistema que cumple con los filtros
	 */
	public ArrayList<TVehiculo> consultarVehiculosFiltros(String marca, String carburante, String categoria, int numPlazas) {
		return this.f.consultarVehiculosFiltros(marca, carburante, categoria, numPlazas);
	}
	
	/**
	 * Devuelve un TVehiculo dado su identificador (matricula).
	 * @param matricula identificador del vehiculo.
	 * @return reserva del id(idReserva)
	 */
	public TVehiculo mostrarVehiculo(String matricula){
		return this.f.mostrarVehiculo(matricula);
	}
	
	/**
	 * Modifica el vehiculo pasado por parametro.
	 * @param vehiculo vehiculo a modificar
	 */
	public boolean modificarVehiculo(TVehiculo vehiculo){
		return this.f.modificarVehiculo(vehiculo);
	}
	
	/**
	 * Da de alta en el sistema un vehiculo pasado por
	 * parametro.
	 * @param vehiculo vehiculo a registrar
	 */
	public boolean altaVehiculo(TVehiculo vehiculo){
		return this.f.altaVehiculo(vehiculo);
	}
	
	/**
	 * Da de baja la reserva con el identificador pasado
	 * por parametro.
	 * @param matricula identificador del vehiculo
	 */
	public boolean bajaVehiculo(String matricula){
		return this.f.bajaVehiculo(matricula);
	}
}
