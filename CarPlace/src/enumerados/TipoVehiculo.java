package enumerados;

public enum TipoVehiculo {
	Tipo(null),
	COCHE("Coche"),
	MOTO("Moto"),
	LIMUSINA("Limusina");
	
	private String simbolo;
	
	TipoVehiculo(String s){
		this.simbolo = s;
	}
	
	public String getSimbolo(){
		return this.simbolo;
	}
	
	public TipoVehiculo aEnum(String g){
		TipoVehiculo m = null;
		
		switch (g){
			case "Coche": m = COCHE; break;
			case "Moto": m = MOTO; break;
			case "Limusina": m = LIMUSINA; break;
		}
		
		return m;
	}

}
