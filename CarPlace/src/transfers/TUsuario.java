package transfers;




public class TUsuario {

	private String dni;
	private String nombre;
	private String apellidos;
	private String nick;
	private String password;
	private String repassword;
	private String fechaDeNacimiento;
	private boolean esAdmin;
	private boolean conectado;
	
	
	public TUsuario(String nick, String nombre ,String apellidos, String password , String repassword ,String dni, String fecha, boolean admin, boolean conectado){
		
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nick = nick;
		this.password = password;
		this.repassword = repassword;
		this.fechaDeNacimiento = fecha;	
		this.esAdmin = admin;
		this.conectado = conectado;
	}
	
	public void setNick(String nick){
		this.nick = nick;
	}
	
	public void setPassword(String pass){
		this.password = pass;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public void setApellidos(String apellidos){
		this.apellidos = apellidos;
	}
	
	public void setfNac(String fecha){
		this.fechaDeNacimiento = fecha;
	}
	
	public void setDni(String dni){
		this.dni = dni;
	}
	
	public void setConectado(boolean log){
		this.conectado = log;
	}
		
	public String getPassword(){
		return this.password;
	}
	
	public String getRepassword(){
		return this.repassword;
	}
			
	public String getNick() {
		return this.nick;
	}

	public String getDni() {
		return this.dni;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getApellidos(){
		return this.apellidos;
	}
	
	public String getFechaNac(){
		return this.fechaDeNacimiento;
	}
		
	public boolean isAdmin(){
		return this.esAdmin;
	}
	
	public void setAdmin(boolean admin){
		this.esAdmin = admin;
	}
	
	
	
	
}
