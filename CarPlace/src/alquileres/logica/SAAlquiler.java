package alquileres.logica;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import transfers.TAlquiler;

/**
 * Implementación de los metodos de la interfaz.
 * Esta clase es la que tiene la logica del subsistema
 * alquileres.
 * @author CARPLACE
 *
 */
public class SAAlquiler implements ISAAlquiler {
	private IDaoAlquiler d;

	/**
	 * Construye una nueva instancia de SAAlquileres
	 */
	public SAAlquiler (){
		this.d = new DaoAlquiler();
	}
	
	@Override
	public ArrayList<TAlquiler> consultaAlquileresUsuario(String nick){
		return this.d.consultaAlquileresUsuario(nick);
	}

	@Override
	public ArrayList<TAlquiler> listaAlquileres(){
		return this.d.listaAlquileres();
	}
	
	@Override
	public TAlquiler mostrarAlquiler(int idAlquiler) {
		return this.d.mostrarAlquiler(idAlquiler);		
	}
	
	@Override
	public boolean altaAlquiler(TAlquiler alquiler){
		boolean correcto = true;
		if(comprobarDatos(alquiler))
			this.d.altaAlquiler(alquiler);
		else{
			correcto = false;
		}
		
		return correcto;	
	}
	
	@Override
	 public boolean bajaAlquiler(int id) {
		return this.d.bajaAlquiler(id);
	}

	private boolean comprobarDatos(TAlquiler alquiler){
		boolean correcto = true;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String fechaact = dateFormat.format(cal.getTime());
		
		if(alquiler.getInicio() == null || alquiler.getFin() == null || alquiler.getMatricula() == null ||
				alquiler.getNick() == null || alquiler.getInicio().compareTo(fechaact) < 0 || alquiler.getFin().compareTo(alquiler.getInicio()) < 0
				|| alquiler.getTipoPago() == null || alquiler.getPrecio() < 0){
			correcto = false;
		}
		
		return correcto;
	}
}