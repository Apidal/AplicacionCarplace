package enumerados;

public enum NumPlazas {
	NumPlazas(0),
	UNO(1),
	DOS(2),
	TRES(3),
	CUATRO(4),
	CINCO(5),
	MASCINCO(6);
	
	private int simbolo;
	
	NumPlazas(int s){
		this.simbolo = s;
	}
	
	public int getNumPlazas(){
		return this.simbolo;
	}
	
	public int stringAInt(String s){
		int n = 0;
		
		switch (s){
			case "UNO": n = 1; break;
			case "DOS": n = 2; break;
			case "TRES": n = 3; break;
			case "CUATRO": n = 4; break;
			case "CINCO": n = 5; break;
			case "MASCINCO": n = 6; break;
		}
		
		return n;
	}
	
	public Enum stringAEnum(String e){
		NumPlazas c = null;
		
		switch (e){
			 
			case "1": c = NumPlazas.UNO; break;
			case "2": c = NumPlazas.DOS; break;
			case "3": c = NumPlazas.TRES; break;
			case "4": c = NumPlazas.CUATRO; break;
			case "5": c = NumPlazas.CINCO; break;
			case "6": c = NumPlazas.MASCINCO; break;
				
		}
		
		return c;
	}
}
