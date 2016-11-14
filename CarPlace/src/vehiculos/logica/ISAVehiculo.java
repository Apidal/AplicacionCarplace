package vehiculos.logica;

import java.util.ArrayList;

import transfers.TVehiculo;

public interface ISAVehiculo {
	
	/**
	 * Devuelve la lista de vehiculos que cumple con los filtros introducidos y donde importa la fecha de ocupación de los vehículos.
	 * @param fechaIni fecha inicial de ocupación de vehículo
	 * @param fechaFin fecha final de ocupación de vehículo
	 * @param marca marca a la que pertenece el vehículo
	 * @param carburante carburante con el que funciona el vehículo
	 * @param categoria categoria a la que pertenece el vehículo
	 * @param numPlazas numero de asientos que posee el vehículo
	 * @return lista de vehículos del sistema que cumple con los filtros
	 */
	public ArrayList<TVehiculo> consultarVehiculosFechas(String fechaIni, String fechaFin, String marca, String carburante, String categoria, int numPlazas);
	
	/**
	 * Devuelve la lista de vehiculos que cumple con los filtros introducidos.
	 * @param marca marca a la que pertenece el vehículo
	 * @param carburante carburante con el que funciona el vehículo
	 * @param categoria categoria a la que pertenece el vehículo
	 * @param numPlazas numero de asientos que posee el vehículo
	 * @return lista de vehículos del sistema que cumple con los filtros
	 */
	public ArrayList<TVehiculo> consultarVehiculosFiltros(String marca, String carburante, String categoria, int numPlazas);
	
	/**
	 * Devuelve un TVehiculo dado su identificador (matricula).
	 * @param matricula identificador del vehiculo.
	 * @return reserva del id(idReserva)
	 */
	public TVehiculo mostrarVehiculo(String matricula);	
	
	/**
	 * Modifica el vehiculo pasado por parametro.
	 * @param vehiculo vehiculo a modificar
	 */
	public boolean modificarVehiculo(TVehiculo vehiculo);
	
	/**
	 * Da de alta en el sistema un vehiuclo pasado por
	 * parametro.
	 * @param vehiculo vehiculo a registrar
	 */
	public boolean altaVehiculo(TVehiculo vehiculo);
	
	/**
	 * Da de baja la reserva con el identificador pasado
	 * por parametro.
	 * @param matricula identificador del vehiculo
	 */
	public boolean bajaVehiculo(String matricula);
}
