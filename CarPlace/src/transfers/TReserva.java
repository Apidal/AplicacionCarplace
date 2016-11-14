package transfers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import vehiculos.control.ControlVehiculo;

public class TReserva {
	private String inicio;
	private String fin;
	private int id;
	private String matricula;
	private String nick;
	
	public TReserva(String inicio,String fin,int id,String matricula,String nick){
		this.inicio=inicio;
		this.fin=fin;
		this.id=id;
		this.matricula=matricula;
		this.nick=nick;
	}
	
	public String getNick() {
		return this.nick ;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public String getInicio() {
		return inicio;
	}

	public String getFin() {
		return fin;
	}
	
	public void setNick(String usuario){
		this.nick=usuario;
	}
	
	public void setMatricula(String vehiculo){
		this.matricula=vehiculo;
	}
	public void setId(int id){
		this.id=id;
	}

	public void setInicio(String iniciof) {
		this.inicio=iniciof;
		
	}

	public void setFin(String finf) {
		this.fin=finf;
	}

	public int getId() {
		return id;
	}
	
	public String toString(){
		DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ControlVehiculo cVeh = new ControlVehiculo();
		String s = "";
		try {
			s = "ini: " + dateFormat2.format(dateFormat.parse(inicio)) + " /" + "fin: " + dateFormat2.format(dateFormat.parse(fin)) + " /" + cVeh.mostrarVehiculo(matricula).toString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return s;
	}

}
