package reservas.logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import transfers.TReserva;

/**
 * Esta clase es la encargada de la comunicacion
 * del subsistema reservas con la base de datos
 * @author CARPLACE
 *
 */
public class DaoReserva implements IDaoReserva { 
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	/**
	 * Crea una lista de las reservas del usuario que le llega por parametro
	 * @param nick identificador de usuario
	 * @return lista con reservas
	 */
	public ArrayList<TReserva> consultarReservas(String nick){
		ArrayList<TReserva> lista = new ArrayList<TReserva>();
		try {
			creaConexionBBDD();
			preparedStatement = connect
					.prepareStatement("SELECT id_reserva, nick_cliente, matricula, fecha_ini, fecha_fin FROM reservas "
							+ " WHERE nick_cliente = ?");
			preparedStatement.setString(1, nick);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				TReserva reserva = new TReserva(null, null, 0, null, null);
				reserva.setId(resultSet.getInt("id_reserva"));
				reserva.setNick(resultSet.getString("nick_cliente"));
				reserva.setMatricula(resultSet.getString("matricula"));
				reserva.setInicio(resultSet.getString("fecha_ini"));
				reserva.setFin(resultSet.getString("fecha_fin"));
				lista.add(reserva);
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
	 * Crea una lista con todas las reservas que hay en la bbdd
	 * @return lista 
	 */
	public ArrayList<TReserva> listaReservas() {
		ArrayList<TReserva> lista = new ArrayList<TReserva>();

		try {
			creaConexionBBDD();
			preparedStatement = connect
					.prepareStatement("SELECT id_reserva, nick_cliente, matricula, fecha_ini, fecha_fin FROM reservas");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				TReserva reserva = new TReserva(null, null, 0, null, null);
				reserva.setId(resultSet.getInt("id_reserva"));
				reserva.setNick(resultSet.getString("nick_cliente"));
				reserva.setMatricula(resultSet.getString("matricula"));
				reserva.setInicio(resultSet.getString("fecha_ini"));
				reserva.setFin(resultSet.getString("fecha_fin"));
				lista.add(reserva);
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
	public TReserva mostrarReserva(int idReserva) {
		TReserva res= new TReserva(null, null, idReserva, null, null);

		try {
			creaConexionBBDD();
			preparedStatement = connect
					.prepareStatement("SELECT id_reserva, nick_cliente, matricula, fecha_ini, fecha_fin FROM reservas WHERE id_reserva = ?");
			preparedStatement.setInt(1,idReserva);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();

			res.setId(resultSet.getInt("id_reserva"));
			res.setNick(resultSet.getString("nick_cliente"));
			res.setMatricula(resultSet.getString("matricula"));
			res.setInicio(resultSet.getString("fecha_ini"));
			res.setFin(resultSet.getString("fecha_fin"));
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		return res;


	}


	/**
	 * modifica la reserva que le viene por parametros
	 * @param reserva reserva a modificar
	 * @return true si ha ido todo bien, false si no se ha podido modificar
	 */
	public boolean modificarReserva(TReserva reserva){
		int cont = 0;

		try {
			creaConexionBBDD();
			preparedStatement = connect
					.prepareStatement("UPDATE reservas SET matricula= ?, fecha_ini= ?, fecha_fin = ? WHERE id_reserva = ?");
			preparedStatement.setString(1, reserva.getMatricula());
			preparedStatement.setString(2, reserva.getInicio());
			preparedStatement.setString(3, reserva.getFin());
			preparedStatement.setInt(4, reserva.getId());
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
	 * Da de alta la reserva que le llega por parametro
	 * @param reserva reserva a registrar en el sistema
	 */
	public void altaReserva(TReserva reserva){
		try{
			creaConexionBBDD();
			preparedStatement = connect
					.prepareStatement("insert into reservas (nick_cliente, matricula, fecha_ini, fecha_fin) values (?,?,?,?)");
			preparedStatement.setString(1, reserva.getNick());
			preparedStatement.setString(2, reserva.getMatricula());
			preparedStatement.setString(3, reserva.getInicio());
			preparedStatement.setString(4, reserva.getFin());

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
	 * da de baja a una reserva de la bbdd
	 * @param id identificador de la reserva a dar de baja
	 * @return true si ha ido bien, false si algo ha salido mal
	 */
	public boolean bajaReserva(int id) {

		int filasAfectadas = 0;
		try {
			creaConexionBBDD();
			preparedStatement = connect
					.prepareStatement("DELETE from reservas WHERE id_reserva = ?");
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