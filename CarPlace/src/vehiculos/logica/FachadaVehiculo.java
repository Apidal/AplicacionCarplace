package vehiculos.logica;

import java.util.ArrayList;

import transfers.TVehiculo;

public class FachadaVehiculo implements IFachadaVehiculo{
	/**
	 *
	 */
	private ISAVehiculo s;
	
	/**
	 * Construye una nueva instancia de FachadarVehiculo.
	 */
	public FachadaVehiculo(){
		this.s = new SAVehiculo();
	}
	
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
	@Override
	public ArrayList<TVehiculo> consultarVehiculosFechas(String fechaIni, String fechaFin, String marca, String carburante, String categoria, int numPlazas) {
		return this.s.consultarVehiculosFechas(fechaIni, fechaFin, marca, carburante, categoria, numPlazas);
	}
	
	/**
	 * Devuelve la lista de vehiculos que cumple con los filtros introducidos.
	 * @param marca marca a la que pertenece el vehiculo
	 * @param carburante carburante con el que funciona el vehiculo
	 * @param categoria categoria a la que pertenece el vehiculo
	 * @param numPlazas numero de asientos que posee el vehiculo
	 * @return lista de vehiculos del sistema que cumple con los filtros
	 */
	public ArrayList<TVehiculo> consultarVehiculosFiltros(String marca, String carburante, String categoria, int numPlazas) {
		return this.s.consultarVehiculosFiltros(marca, carburante, categoria, numPlazas);
	}

	/**
	 * Devuelve un TVehiculo dado su identificador (matricula).
	 * @param matricula identificador del vehiculo.
	 * @return reserva del id(idReserva)
	 */
	@Override
	public TVehiculo mostrarVehiculo(String matricula) {
		return this.s.mostrarVehiculo(matricula);
	}

	/**
	 * Modifica el vehiculo pasado por parametro.
	 * @param vehiculo vehiculo a modificar
	 */
	@Override
	public boolean modificarVehiculo(TVehiculo vehiculo) {
		return this.s.modificarVehiculo(vehiculo);
	}

	/**
	 * Da de alta en el sistema un vehiuclo pasado por
	 * parametro.
	 * @param vehiculo vehiculo a registrar
	 */
	@Override
	public boolean altaVehiculo(TVehiculo vehiculo) {
		return this.s.altaVehiculo(vehiculo);
	}

	/**
	 * Da de baja la reserva con el identificador pasado
	 * por parametro.
	 * @param matricula identificador del vehiculo
	 */
	@Override
	public boolean bajaVehiculo(String matricula) {
		return this.s.bajaVehiculo(matricula);
	}
}
