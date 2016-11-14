package enumerados;

public enum Marcas {
	Marca(null),
	SEAT("Seat"),
	OPEL("Opel"),
	PEUGEOT("Peugeot"),
	BMW("BMW"),
	RENAULT("RENAULT");
	
	private String simbolo;
	
	Marcas(String s){
		this.simbolo = s;
	}
	
	public String getSimbolo(){
		return this.simbolo;
	}
	
	public Marcas aEnum(String s){
		Marcas x = null;
		
		switch (s){
			case "Seat" : x = SEAT; break;
			case "Opel" : x = OPEL; break;
			case "Peugeot" : x = PEUGEOT; break;
			case "BMW" : x = BMW; break;
			case "RENAULT" : x = RENAULT; break;
		}
		
		return x;
	}
	
}
