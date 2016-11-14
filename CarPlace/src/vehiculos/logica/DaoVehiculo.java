package vehiculos.logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import transfers.TVehiculo;

public class DaoVehiculo implements IDaoVehiculo {
	/**
	 *
	 */
	 private Connection connect = null;
	 private Statement statement = null;
	 private PreparedStatement preparedStatement = null;
	 private ResultSet resultSet = null;
	 

	/**
	* crea una lista con los vehiculos que hay en la bbdd que coinciden con los parametros
	* pasados
	* @param fechaIni
	* @param fechaFin
	* @param marca
	* @param carburante
	* @param categoria
	* @param numPlazas
	* @return lista de vehiculos
	*/
	@Override
	public ArrayList<TVehiculo> consultarVehiculosFechas(String fechaIni, String fechaFin, String marca, String carburante, String categoria, int numPlazas) {
		ArrayList<TVehiculo> lista = new ArrayList<TVehiculo>();
		String consulta = preparaConsultaSqlFechas(fechaIni, fechaFin, marca, carburante, categoria, numPlazas);
		try {
			creaConexionBBDD();
			preparedStatement = connect
					  .prepareStatement(consulta);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				TVehiculo vehiculo = new TVehiculo(null, null, null, null, 0, null, null);
				vehiculo.setCategoria(resultSet.getString("categoria"));
				vehiculo.setMarca(resultSet.getString("marca"));
				vehiculo.setModelo(resultSet.getString("modelo"));
				vehiculo.setNumplazas(resultSet.getInt("numero_plazas"));
				vehiculo.setCarburante(resultSet.getString("carburante"));
				vehiculo.setPrecio(resultSet.getFloat("precio_dia"));
				vehiculo.setMatricula(resultSet.getString("matricula"));
				lista.add(vehiculo);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
	
	/**
	 * crea una consulta en la que comprobara que los vehiculos no esten ni reservados ni alquilados
	 * y seleccionando solo los que coincidan con los filtros
	 * @param fechaIni
	 * @param fechaFin
	 * @param marca
	 * @param carburante
	 * @param categoria
	 * @param numPlazas
	 * @return string con la sentencia sql
	 */
	private String preparaConsultaSqlFechas(String fechaIni, String fechaFin, String marca, String carburante, String categoria, int numPlazas){
		String sentencia1 = "", sentencia2 = "", sentencia3 = "";
		
		sentencia1 = "select matricula,categoria, marca, modelo, numero_plazas, carburante, precio_dia from vehiculos where ";
		sentencia3 = "MATRICULA NOT IN (SELECT matricula FROM ALQUILERES WHERE FECHA_INI <= '" + fechaFin +
				               "' AND FECHA_FIN >= '" + fechaFin + "') AND MATRICULA NOT IN( SELECT MATRICULA FROM ALQUILERES WHERE FECHA_INI <= '"+
				               fechaIni + "' AND FECHA_FIN >= '" + fechaIni + "') AND MATRICULA NOT IN(" +
				 	  "SELECT MATRICULA FROM ALQUILERES WHERE FECHA_INi >= '" + fechaIni +
				 "' AND FECHA_FIN <= '" + fechaFin +"') AND MATRICULA NOT IN (SELECT matricula FROM RESERVAS WHERE FECHA_INI <= '" + fechaFin +
				               "' AND FECHA_FIN >= '" + fechaFin + "') AND MATRICULA NOT IN( SELECT MATRICULA FROM RESERVAS WHERE FECHA_INI <= '"+
				               fechaIni + "' AND FECHA_FIN >= '" + fechaIni + "') AND MATRICULA NOT IN(" +
				 	  "SELECT MATRICULA FROM RESERVAS WHERE FECHA_INi >= '" + fechaIni +
				 "' AND FECHA_FIN <= '" + fechaFin +"')   group by categoria, marca, modelo, numero_plazas, carburante, precio_dia " +
				 "ORDER BY categoria, marca, modelo, numero_plazas, carburante";
		
		if(categoria != null)
			sentencia2 = "CATEGORIA = '" + categoria + "' AND ";
		if(marca != null)
			sentencia2 = sentencia2 + "MARCA = '" + marca + "' AND ";
		if(numPlazas != 0){
			sentencia2 = sentencia2 + "NUMERO_PLAZAS  ";
			if(numPlazas < 6)
				sentencia2 = sentencia2 + " = " + numPlazas + " AND ";
			else
				sentencia2 = sentencia2 + " >= " + numPlazas + " AND ";
		}
		if(carburante != null)
			sentencia2 = sentencia2 + "CARBURANTE = '" + carburante + "' AND ";
		
		

		return sentencia1 + sentencia2 + sentencia3;
	}
	
	/**
	 * Devuelve la lista de vehiculos que cumple con los filtros introducidos.
	 * @param marca marca a la que pertenece el vehiculo
	 * @param carburante carburante con el que funciona el vehiculo
	 * @param categoria categoria a la que pertenece el vehiculo
	 * @param numPlazas nimero de asientos que posee el vehiculo
	 * @return lista de vehiculos del sistema que cumple con los filtros
	 */
	public ArrayList<TVehiculo> consultarVehiculosFiltros(String marca, String carburante, String categoria, int numPlazas) {
		ArrayList<TVehiculo> lista = new ArrayList<TVehiculo>();
		String consulta = preparaConsultaSql(marca, carburante, categoria, numPlazas);
		try {
			creaConexionBBDD();
			preparedStatement = connect
					  .prepareStatement(consulta);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				TVehiculo vehiculo = new TVehiculo(null, null, null, null, 0, null, null);
				vehiculo.setCategoria(resultSet.getString("categoria"));
				vehiculo.setMarca(resultSet.getString("marca"));
				vehiculo.setModelo(resultSet.getString("modelo"));
				vehiculo.setNumplazas(resultSet.getInt("numero_plazas"));
				vehiculo.setCarburante(resultSet.getString("carburante"));
				vehiculo.setPrecio(resultSet.getFloat("precio_dia"));
				vehiculo.setMatricula(resultSet.getString("matricula"));
				lista.add(vehiculo);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
	
	/**
	 * crea una consulta en la que comprobara que los vehiculos coincidan con los filtros
	 * @param marca
	 * @param carburante
	 * @param categoria
	 * @param numPlazas
	 * @return string con la sentencia sql
	 */
	private String preparaConsultaSql(String marca, String carburante, String categoria, int numPlazas){
		String sentencia1 = "", sentencia2 = "", sentencia3 = "";
		
		sentencia1 = "select * from vehiculos where ";
		sentencia3 = "MATRICULA != '0'";
		
		if(categoria != null)
			sentencia2 = "CATEGORIA = '" + categoria + "' AND ";
		if(marca != null)
			sentencia2 = sentencia2 + "MARCA = '" + marca + "' AND ";
		if(numPlazas != 0){
			sentencia2 = sentencia2 + "NUMERO_PLAZAS  ";
			if(numPlazas < 6)
				sentencia2 = sentencia2 + " = " + numPlazas + " AND ";
			else
				sentencia2 = sentencia2 + " >= " + numPlazas + " AND ";
		}
		if(carburante != null)
			sentencia2 = sentencia2 + "CARBURANTE = '" + carburante + "' AND ";
		
		return sentencia1 + sentencia2 + sentencia3;
	}
	
	

	@Override
	/**
	 * Dada una matricula devuelve un vehiculo con sus datos
	 * @param matricula
	 * @return vehiculo
	 */
	public TVehiculo mostrarVehiculo(String matricula){
		TVehiculo vehiculo = new TVehiculo(null, null, null, null, 0, null, null);
		  
		  try {
			creaConexionBBDD();
			preparedStatement = connect
			          .prepareStatement("SELECT marca, modelo, carburante, numero_plazas, categoria, precio_dia FROM vehiculos WHERE matricula = ?");
			preparedStatement.setString(1,matricula);
		resultSet = preparedStatement.executeQuery();
		resultSet.next();
		
		vehiculo.setMarca(resultSet.getString("marca"));
		vehiculo.setModelo(resultSet.getString("modelo"));
		vehiculo.setMatricula(matricula);
		vehiculo.setPrecio(resultSet.getFloat("precio_dia"));
		vehiculo.setNumplazas(resultSet.getInt("numero_plazas"));
		vehiculo.setCarburante(resultSet.getString("carburante"));
		vehiculo.setCategoria(resultSet.getString("categoria"));
		close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  
		  return vehiculo;
	  }

	@Override
	/**
	   * modifica un vehiculo que le viene por parametros
	   * @param vehiculo
	   * @return true si ha ido todo bien, false si no se ha podido modificar
	   */
	  public boolean modificarVehiculo(TVehiculo vehiculo){
		  int cont = 0;
		  
		  try {
				creaConexionBBDD();
				preparedStatement = connect
				          .prepareStatement("UPDATE vehiculos SET marca = ?, modelo= ?, carburante = ?, numero_plazas = ?, categoria= ?, precio_dia = ? WHERE matricula = ?");
				preparedStatement.setString(1, vehiculo.getMarca());
				preparedStatement.setString(2, vehiculo.getModelo());
				preparedStatement.setString(3, vehiculo.getCarburante());
				preparedStatement.setInt(4, vehiculo.getNumplazas());
				preparedStatement.setString(5, vehiculo.getCategoria());
				preparedStatement.setFloat(6, vehiculo.getPrecio());
				preparedStatement.setString(7, vehiculo.getMatricula());
				cont = preparedStatement.executeUpdate();
				
				close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		  if(cont == 0)
				return false;
			else
				return true;
		  
	  }

	/**
	 * Da de alta un vehiculo que le llega por parametro
	 * @param vehiculo
	 */
	public void altaVehiculo(TVehiculo vehiculo){
		try{
			creaConexionBBDD();
			preparedStatement = connect
			          .prepareStatement("INSERT INTO `vehiculos`(`matricula`, `marca`, `modelo`, `carburante`, `numero_plazas`, `categoria`, `precio_dia`) values (?,?,?,?,?,?,?)");
		      preparedStatement.setString(1, vehiculo.getMatricula());
		     preparedStatement.setString(2, vehiculo.getMarca());
		     preparedStatement.setString(3, vehiculo.getModelo());
		     preparedStatement.setString(4, vehiculo.getCarburante());
		     preparedStatement.setInt(5, vehiculo.getNumplazas());
		     preparedStatement.setString(6, vehiculo.getCategoria());
		     preparedStatement.setFloat(7, vehiculo.getPrecio());
		     
		     preparedStatement.executeUpdate();
		 	close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * da de baja a un vehiculo de la bbdd
	 * @param matricula
	 * @return true si ha ido bien, false si algo ha salido mal
	 */
	public boolean bajaVehiculo(String matricula) {
		
		int filasAfectadas = 0;
		try {
			creaConexionBBDD();
			preparedStatement = connect
			          .prepareStatement("DELETE from vehiculos WHERE matricula = ?");
			preparedStatement.setString(1,matricula);
			filasAfectadas = preparedStatement.executeUpdate();
			
		

		close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//comprobar si ha ido bien
		if(filasAfectadas == 0)
			return false;
		else
			return true;
	}

	@Override
	/**
	 * Comprueba si el vehiculo ya existe en la BBDD
	 * @return True si existe, False si no existe
	 */	
	public boolean existeVehiculo(String matricula){
		int cont = 0;
		try{
			creaConexionBBDD();
	
			preparedStatement = connect
			          .prepareStatement("SELECT count(*) FROM vehiculos WHERE matricula = ?");
			preparedStatement.setString(1,matricula);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			cont = resultSet.getInt(1);
			close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(cont == 0)
			return false;
		else
			return true;
	}
	
	private void creaConexionBBDD() throws SQLException, ClassNotFoundException{
		 
  	  // This will load the MySQL driver, each DB has its own driver
    Class.forName("com.mysql.jdbc.Driver");
    // Setup the connection with the DB
		connect = DriverManager
		      .getConnection("jdbc:mysql://localhost/carplace?"
		          + "user=root&password=root");
		// Statements allow to issue SQL queries to the database
    statement = connect.createStatement();
    // Result set get the result of the SQL query

	}
	
	 private void close() throws SQLException {
		   
	      if (resultSet != null) {
	        resultSet.close();
	      }

	      if (statement != null) {
	        statement.close();
	      }

	      if (connect != null) {
	        connect.close();
	      }
	  }
}
