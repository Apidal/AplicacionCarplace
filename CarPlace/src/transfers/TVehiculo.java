package transfers;


public class TVehiculo {
	private String marca;
	private String modelo;
	private String matricula;
	private Float precio;
	private int numplazas;
	private String carburante;
	private String categoria;
	
	public TVehiculo(String mar, String mo, String mat, Float p,int n, String c, String tv){
		this.marca = mar;
		this.modelo = mo;
		this.matricula = mat;
		this.precio = p;
		this.numplazas = n;
		this.carburante = c;
		this.categoria = tv;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public Float getPrecio() {
		return precio;
	}
	
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	
	public int getNumplazas() {
		return numplazas;
	}
	
	public void setNumplazas(int numplazas) {
		this.numplazas = numplazas;
	}
	
	public String getCarburante() {
		return carburante;
	}
	
	public void setCarburante(String carburante) {
		this.carburante = carburante;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String toString(){
		return this.marca + " " + this.modelo;
	}
}
