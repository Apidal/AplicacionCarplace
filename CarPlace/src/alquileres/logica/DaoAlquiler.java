package alquileres.logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import transfers.TAlquiler;

/**
 * Esta clase es la encargada de la comunicacion
 * del subsistema reservas con la base de datos
 * @author CARPLACE
 *
 */
public class DaoAlquiler implements IDaoAlquiler { 
	 private Connection connect = null;
	 private Statement statement = null;
	 private PreparedStatement preparedStatement = null;
	 private ResultSet resultSet = null;
	
	/**
	 * Crea una lista de las alquileres del usuario que le llega por parametro
	 * @param nick identificador de usuario
	 * @return lista con alquileres
	 */
	public ArrayList<TAlquiler> consultaAlquileresUsuario(String nick){
		ArrayList<TAlquiler> lista = new ArrayList<TAlquiler>();
		try {
			creaConexionBBDD();
			preparedStatement = connect
					  .prepareStatement("SELECT id_alquiler, nick_usuario, matricula, fecha_ini, fecha_fin, metodo_pago, precio FROM alquileres "
					  		+ " WHERE nick_usuario = ?");
			preparedStatement.setString(1, nick);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				TAlquiler alquiler = new TAlquiler(null, null, 0, null, null, null,0);
				alquiler.setId(resultSet.getInt("id_alquiler"));
				alquiler.setNick(resultSet.getString("nick_usuario"));
				alquiler.setMatricula(resultSet.getString("matricula"));
				alquiler.setInicio(resultSet.getString("fecha_ini"));
				alquiler.setFin(resultSet.getString("fecha_fin"));
				alquiler.setTipoPago(resultSet.getString("metodo_pago"));
				alquiler.setPrecio(resultSet.getFloat("precio"));
				lista.add(alquiler);
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
	 * Crea una lista con todas los alquileres que hay en la bbdd
	 * @return lista 
	 */
	public ArrayList<TAlquiler> listaAlquileres() {
		ArrayList<TAlquiler> lista = new ArrayList<TAlquiler>();

		try {
			creaConexionBBDD();
			preparedStatement = connect
					.prepareStatement("SELECT id_alquiler, nick_usuario, matricula, fecha_ini, fecha_fin, metodo_pago, precio FROM alquileres");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				TAlquiler alquiler = new TAlquiler(null, null, 0, null, null, null,0);
				alquiler.setId(resultSet.getInt("id_alquiler"));
				alquiler.setNick(resultSet.getString("nick_usuario"));
				alquiler.setMatricula(resultSet.getString("matricula"));
				alquiler.setInicio(resultSet.getString("fecha_ini"));
				alquiler.setFin(resultSet.getString("fecha_fin"));
				alquiler.setTipoPago(resultSet.getString("metodo_pago"));
				alquiler.setPrecio(resultSet.getFloat("precio"));
				lista.add(alquiler);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		return lista;
	}
	
	@Override
	public TAlquiler mostrarAlquiler(int idAlquiler) {
		TAlquiler alq = new TAlquiler(null, null, idAlquiler, null, null, null, 0);

		try {
			creaConexionBBDD();
			preparedStatement = connect
					.prepareStatement("SELECT id_alquiler, nick_usuario, matricula, fecha_ini, fecha_fin, "
							+ "metodo_pago, precio FROM alquileres WHERE id_alquiler = ?");
			preparedStatement.setInt(1,idAlquiler);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();

			alq.setId(resultSet.getInt("id_alquiler"));
			alq.setNick(resultSet.getString("nick_usuario"));
			alq.setMatricula(resultSet.getString("matricula"));
			alq.setInicio(resultSet.getString("fecha_ini"));
			alq.setFin(resultSet.getString("fecha_fin"));
			alq.setTipoPago(resultSet.getString("metodo_pago"));
			alq.setPrecio(resultSet.getFloat("precio"));
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		return alq;
	}
	/**
	 * Da de alta el alquiler que le llega por parametro
	 * @param alquiler alquiler a registrar en el sistema
	 */
	public void altaAlquiler(TAlquiler alquiler){
		try{
			creaConexionBBDD();
			preparedStatement = connect
			          .prepareStatement("insert into alquileres (nick_usuario, matricula, fecha_ini, fecha_fin, metodo_pago, precio) values (?,?,?,?,?,?)");
		      preparedStatement.setString(1, alquiler.getNick());
		     preparedStatement.setString(2, alquiler.getMatricula());
		     preparedStatement.setString(3, alquiler.getInicio());
		     preparedStatement.setString(4, alquiler.getFin());
		     preparedStatement.setString(5, alquiler.getTipoPago());
		     preparedStatement.setFloat(6, alquiler.getPrecio());
		     
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
	
	/**
	 * da de baja a un alquiler de la bbdd
	 * @param id identificador del alquiler a dar de baja del sistema
	 * @return true si ha ido bien, false si algo ha salido mal
	 */
	public boolean bajaAlquiler(int id) {
		
		int filasAfectadas = 0;
		try {
			creaConexionBBDD();
			preparedStatement = connect
			          .prepareStatement("DELETE from alquileres WHERE id_alquiler = ?");
			preparedStatement.setInt(1,id);
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
