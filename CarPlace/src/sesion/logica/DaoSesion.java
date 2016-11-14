package sesion.logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoSesion implements IDaoSesion {
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	/**
	 * Accede a la BBDD para comprobar que los datos de login son correctos
	 * @param nick identificador del usuario
	 * @param pass password del usuario
	 * @return Devuelve un booleano que indica si se puede iniciar o no sesión
	 */
	public boolean loginCorrecto(String nick, String pass) {
		// TODO Auto-generated method stub
		
		if(!existeUsuario(nick)) return false;
		if(!compruebaPasswordCorrecta(nick, pass)) return false;
		 try {
				creaConexionBBDD();
				preparedStatement = connect
				          .prepareStatement("UPDATE usuarios SET logueado = ? WHERE nick = ?");
				preparedStatement.setBoolean(1, true);
				preparedStatement.setString(2, nick);
				preparedStatement.executeUpdate();
				
				close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return true;
	}
	
	/**
	 * Accede a la BBDD y pone la variable de conectado a false
	 * @param nick identificador del usuario
	 */
	public void logOut(String nick) {
		// TODO Auto-generated method stub
		
		 try {
				creaConexionBBDD();
				preparedStatement = connect
				          .prepareStatement("UPDATE usuarios SET logueado = ? WHERE nick = ?");
				preparedStatement.setBoolean(1, false);
				preparedStatement.setString(2, nick);
				preparedStatement.executeUpdate();
				
				close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	/**
	   * comprueba en la BBDD que la password sea la correcta
	   * @param nick identificador del usuario
	   * @param pass password del usuario
	   * @return devuelve un booleano indicando si la password es correcta
	   */
	  public boolean compruebaPasswordCorrecta(String nick, String pass){
		  int cont = 0;
		  try{
				creaConexionBBDD();
				preparedStatement = connect
				          .prepareStatement("SELECT count(*) FROM usuarios WHERE nick = ? AND password = ?");
				preparedStatement.setString(1,nick);
				preparedStatement.setString(2,pass);
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				cont = resultSet.getInt(1);
				resultSet.next();
				
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

	/**
	 * Comprueba si el nick ya existe en la BBDD
	 * @param nick identificador del usuario
	 * @return True si existe, False si no existe
	 */
	public boolean existeUsuario(String nick){
		int cont = 0;
		try{
			creaConexionBBDD();
	
			preparedStatement = connect
			          .prepareStatement("SELECT count(*) FROM usuarios WHERE nick = ?");
			preparedStatement.setString(1,nick);
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
	
	/**
	 * Crea una conexión con la BBDD
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
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
	
	 /**
	  * Cierra las conexiones con la BBDD
	  * @throws SQLException
	  */
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
