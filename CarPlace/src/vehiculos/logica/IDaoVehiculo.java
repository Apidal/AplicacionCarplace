package vehiculos.logica;

import java.util.ArrayList;

import transfers.TVehiculo;

public interface IDaoVehiculo {
	
	/**
	 * Devuelve la lista de vehiculos que cumple con los filtros introducidos y donde importa la fecha de ocupacion de los vehiculos.
	 * @param fechaIni fecha inicial de ocupacion de vehiculo
	 * @param fechaFin fecha final de ocupacion de vehiculo
	 * @param marca marca a la que pertenece el vehiculo
	 * @param carburante carburante con el que funciona el vehiculo
	 * @param categoria categoria a la que pertenece el vehiculo
	 * @param numPlazas numero de asientos que posee el vehiculo
	 * @return lista de vehiculos del sistema que cumple con los filtros
	 */
	public ArrayList<TVehiculo> consultarVehiculosFechas(String fechaIni, String fechaFin, String marca, String carburante, String categoria, int numPlazas);
	
	/**
	 * Devuelve la lista de vehiculos que cumple con los filtros introducidos.
	 * @param marca marca a la que pertenece el vehiculo
	 * @param carburante carburante con el que funciona el vehiculo
	 * @param categoria categoria a la que pertenece el vehiculo
	 * @param numPlazas numero de asientos que posee el vehiculo
	 * @return lista de vehiculos del sistema que cumple con los filtros
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
	public void altaVehiculo(TVehiculo vehiculo);
	
	/**
	 * Da de baja la reserva con el identificador pasado
	 * por parametro.
	 * @param matricula identificador del vehiculo
	 */
	public boolean bajaVehiculo(String matricula);
	
	/**
	 * Comprueba si un vehiculo determinado existe en la BBDD
	 * @param matricula identificador del vehiculo
	 * @return true si exite, false si no existe
	 */
	public boolean existeVehiculo(String matricula);
}
