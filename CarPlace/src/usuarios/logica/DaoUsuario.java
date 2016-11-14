package usuarios.logica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import transfers.TUsuario;


public class DaoUsuario implements IDaoUsuario{
	  private Connection connect = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;
	  
	  public DaoUsuario(){
		  
	  }
	
	  
	public boolean existeNick(String nick){
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
	
	
	public boolean altaUsuario(TUsuario usuario) {
		int filasAfectadas = 0;
		try{
			creaConexionBBDD();
			preparedStatement = connect
			          .prepareStatement("insert into usuarios (nick, nombre, apellidos, password, dni, fNac) values (?,?,?,?,?,?)");
		      preparedStatement.setString(1,usuario.getNick());
		     preparedStatement.setString(2, usuario.getNombre());
		     preparedStatement.setString(3, usuario.getApellidos());
		     preparedStatement.setString(4, usuario.getPassword());
		     preparedStatement.setString(5, usuario.getDni());
		     preparedStatement.setString(6, usuario.getFechaNac());
		     filasAfectadas = preparedStatement.executeUpdate();
		 	close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(filasAfectadas == 0)
			return false;
		else
			return true;
		
	}
	
	
	/**
	 * Abre la conexion con la bbdd
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
	   * Cierra la conexion con la bbdd
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
	  
	  
	  public TUsuario mostrarUsuario(String nick){
		  TUsuario usuario = new TUsuario(null, null, null, null, null, null, null, false, false);
		  
		  try {
			creaConexionBBDD();
			preparedStatement = connect
			          .prepareStatement("SELECT nick, password, nombre, apellidos, dni, fNac,administrador FROM usuarios WHERE nick = ?");
			preparedStatement.setString(1,nick);
		resultSet = preparedStatement.executeQuery();
		resultSet.next();
		
		usuario.setNick(resultSet.getString("nick"));
		usuario.setPassword(resultSet.getString("password"));
		usuario.setNombre(resultSet.getString("nombre"));
		usuario.setApellidos(resultSet.getString("apellidos"));
		usuario.setDni(resultSet.getString("dni"));
		usuario.setfNac(resultSet.getString("fNac"));
		usuario.setAdmin(resultSet.getBoolean("administrador"));
		close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  
		  return usuario;
	  }
	  
	  
	  public boolean modificarUsuario(TUsuario nick){
		  int cont = 0;
		  
		  try {
				creaConexionBBDD();
				preparedStatement = connect
				          .prepareStatement("UPDATE usuarios SET nombre = ?, apellidos =?, password = ?, dni = ?, fNac = ?, administrador = ? WHERE nick = ?");
				preparedStatement.setString(1, nick.getNombre());
				preparedStatement.setString(2, nick.getApellidos());
				preparedStatement.setString(3, nick.getPassword());
				preparedStatement.setString(4, nick.getDni());
				preparedStatement.setString(5, nick.getFechaNac());
				preparedStatement.setBoolean(6, nick.isAdmin());
				preparedStatement.setString(7, nick.getNick());
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
	
	
	public boolean bajaUsuario(String nick) {
		
		int filasAfectadas = 0;
		try {
			creaConexionBBDD();
			preparedStatement = connect
			          .prepareStatement("DELETE from usuarios WHERE nick = ?");
			preparedStatement.setString(1,nick);
			filasAfectadas = preparedStatement.executeUpdate();
			
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(filasAfectadas == 0)
			return false;
		else
			return true;
	}
	
	
	public JList consultarUsuarios(){
		
		DefaultListModel modListaPanel = new DefaultListModel();
		JList listaNick = new JList(modListaPanel);
		try {
			creaConexionBBDD();
			preparedStatement = connect
			          .prepareStatement("SELECT nick FROM usuarios ORDER by nick");
		resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
			modListaPanel.addElement(resultSet.getString("nick"));
		
		close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaNick;
	}
	
	
	public JList consultarUsuariosFiltro(String comodin){
		
		DefaultListModel modListaPanel = new DefaultListModel();
		JList listaNick = new JList(modListaPanel);
		try {
			creaConexionBBDD();
			preparedStatement = connect
			          .prepareStatement("SELECT nick FROM usuarios where nick like ? ORDER by nick");
			preparedStatement.setString(1,"%" + comodin + "%");
		resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
			modListaPanel.addElement(resultSet.getString("nick"));
		
		close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaNick;
	}
}

