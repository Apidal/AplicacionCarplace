package vehiculos.logica;

import java.util.ArrayList;

import enumerados.Carburantes;
import enumerados.Marcas;
import enumerados.NumPlazas;
import enumerados.TipoVehiculo;
import transfers.TVehiculo;

public class SAVehiculo implements ISAVehiculo{
	
	/**
	 *
	 */private IDaoVehiculo d;
	
	 /**
		 * Construye una nueva instancia de SAVehiculo (Servicio de apliacion).
		 */
	public SAVehiculo() {
		this.d = new DaoVehiculo();
	}
	
	/**
	 * Devuelve la lista de vehiculos que cumple con los filtros introducidos y donde importa la fecha de ocupacion  de los vehiculos.
	 * @param fechaIni fecha inicial de ocupacion  de vehiculo
	 * @param fechaFin fecha final de ocupacion  de vehiculo
	 * @param marca marca a la que pertenece el vehiculo
	 * @param carburante carburante con el que funciona el vehiculo
	 * @param categoria categoria a la que pertenece el vehiculo
	 * @param numPlazas numero de asientos que posee el vehiculo
	 * @return lista de vehiculos del sistema que cumple con los filtros
	 */
	@Override
	public ArrayList<TVehiculo> consultarVehiculosFechas(String fechaIni, String fechaFin, String marca, String carburante, String categoria, int numPlazas) {
		return this.d.consultarVehiculosFechas(fechaIni, fechaFin, marca, carburante, categoria, numPlazas);
	}
	
	/**
	 * Devuelve la lista de vehiculos que cumple con los filtros introducidos.
	 * @param marca marca a la que pertenece el vehiculo
	 * @param carburante carburante con el que funciona el vehiculo
	 * @param categoria categoria a la que pertenece el vehiculo
	 * @param numPlazas numero de asientos que posee el vehiculo
	 * @return lista de vehiculos del sistema que cumple con los filtros
	 */
	@Override
	public ArrayList<TVehiculo> consultarVehiculosFiltros(String marca, String carburante, String categoria, int numPlazas) {
		return this.d.consultarVehiculosFiltros(marca, carburante, categoria, numPlazas);
	}

	/**
	 * Devuelve un TVehiculo dado su identificador (matricula).
	 * @param matricula identificador del vehiculo.
	 * @return reserva del id(idReserva)
	 */
	@Override
	public TVehiculo mostrarVehiculo(String matricula) {
		return this.d.mostrarVehiculo(matricula);
		
	}

	/**
	 * Modifica el vehiculo pasado por parametro.
	 * @param vehiculo vehiculo a modificar
	 */
	@Override
	public boolean modificarVehiculo(TVehiculo vehiculo) {
		
		if(!comprobarDatos(vehiculo))
			return this.d.modificarVehiculo(vehiculo);
		else
			return false;
		
	}

	/**
	 * Da de alta en el sistema un vehiculo pasado por
	 * parametro.
	 * @param vehiculo vehiculo a registrar
	 */
	@Override
	public boolean altaVehiculo(TVehiculo vehiculo) {
		
		if(!comprobarDatos(vehiculo))
			if(existeVehiculo(vehiculo.getMatricula()))
				return false;
			else{
				this.d.altaVehiculo(vehiculo);
				return true;
			}
		else
			return false;
		
	}

	/**
	 * Da de baja la reserva con el identificador pasado
	 * por parametro.
	 * @param matricula identificador del vehiculo
	 */
	@Override
	public boolean bajaVehiculo(String matricula) {
		return this.d.bajaVehiculo(matricula);
		
	}
	
	/**
	 * Comprueba si un vehiculo determinado existe en la BBDD
	 * @param matricula identificador del vehiculo
	 * @return true si exite, false si no existe
	 */
	private boolean existeVehiculo(String matricula){
		return this.d.existeVehiculo(matricula);
	}
	
	private boolean comprobarDatos(TVehiculo vehiculo){
		boolean faltaDatos = false;
		
		if(vehiculo.getMarca() == null)
			faltaDatos = true;
		
		if(vehiculo.getModelo().equals(""))
			faltaDatos = true;
		
		if(vehiculo.getMatricula().equals("") || vehiculo.getMatricula().length() != 7)
			faltaDatos = true;
		
		if(vehiculo.getNumplazas() == 0)
			faltaDatos = true;
		
		if(vehiculo.getCarburante() == null)
			faltaDatos = true;
		
		if(vehiculo.getCategoria() == null)
			faltaDatos = true;
		
		return faltaDatos;
		
	}
}
