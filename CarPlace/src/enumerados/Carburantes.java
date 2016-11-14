package enumerados;

public enum Carburantes {
	Carburante(null),
	GASOLINA("Gasolina"),
	DIESEL("Diesel");
	
	private String simbolo;
	
	Carburantes(String s){
		this.simbolo = s;
	}
	
	public String getSimbolo(){
		return this.simbolo;
	}
	
	public Carburantes aEnum(String s){
		Carburantes x = null;
		
		switch (s){
			case "Gasolina" : x = GASOLINA; break;
			case "Diesel" : x = DIESEL; break;
		}
		
		return x;
	}
}
