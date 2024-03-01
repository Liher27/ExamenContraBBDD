package ficheros;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bbdd.Utils;
import bbdd.ordenador;

public class GestorDeOrdenador {

	ordenador ordenador = new ordenador();

	public List<ordenador> obtenerPc() {

		List<ordenador> ordenador = new ArrayList<ordenador>();

		try {
			Class.forName(Utils.DRIVER);

			Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASS);

			Statement statement = connection.createStatement();

			String sql = "SELECT * FROM ORDENADOR";
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				ordenador ret = new ordenador();

				ret.setNumSerie(result.getString("NUMSERIE"));
				ret.setMarcaString(result.getString("MARCA"));
				ret.setFechaCompra(result.getDate("FECHACOMPRA"));
				ret.setDisco(result.getString("DISCO"));
				ret.setMemoria(result.getString("MEMORIA"));
				ret.setModelo(result.getString("MODELO"));
				ret.setFunciona(result.getString("FUNCIONAELORDENADOR"));

				ordenador.add(ret);

			}
		} catch (ClassNotFoundException e) {
			System.out.println("Ha dado fallo -> " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Malformacion sqlazo -> " + e.getMessage());
		}

		return ordenador;

	}

	public List<ordenador> buscarPc(String numSerie) {

		List<ordenador> ordenador = new ArrayList<ordenador>();

		try {
			Class.forName(Utils.DRIVER);

			Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASS);

			Statement statement = connection.createStatement();

			String sql = "SELECT * FROM ORDENADOR WHERE NUMSERIE = " + numSerie;
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				ordenador ret = new ordenador();

				ret.setNumSerie(result.getString("NUMSERIE"));
				ret.setMarcaString(result.getString("MARCA"));
				ret.setFechaCompra(result.getDate("FECHACOMPRA"));
				ret.setDisco(result.getString("DISCO"));
				ret.setMemoria(result.getString("MEMORIA"));
				ret.setModelo(result.getString("MODELO"));
				ret.setFunciona(result.getString("FUNCIONAELORDENADOR"));

				ordenador.add(ret);

			}
		} catch (ClassNotFoundException e) {
			System.out.println("Ha dado fallo -> " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Malformacion sqlazo -> " + e.getMessage());
		}

		return ordenador;
	}

	public boolean modificarPc(String numSerie, String marca, String numFecha, String disco, String memoria, String modelo, String funciona) throws ParseException {
		
		String getInputNumSeries = numSerie;
		String getInputMarca = marca;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = numFecha;
		java.util.Date fechaEnSQLConvertida = dateFormat.parse(fecha);
		java.sql.Date sqlDate = new java.sql.Date(fechaEnSQLConvertida.getTime());
		String getInputDisco = disco;
		String getInputMemoria = memoria;
		String getInputModelo = modelo;
		String getInputFunciona = funciona;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(Utils.DRIVER);

			connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASS);

			String sql = "INSERT INTO ordenador (`NumSerie`, `marca`, `fechaCompra`, `disco`, `memoria`, `modelo`, `funcionaElOrdenador`)"
					+ " VALUES (?,?,?,?,?,?,?);";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, getInputNumSeries);
			preparedStatement.setString(2, getInputMarca);
			preparedStatement.setDate(3, sqlDate);
			preparedStatement.setString(4, getInputDisco);
			preparedStatement.setString(5, getInputMemoria);
			preparedStatement.setString(6, getInputModelo);
			preparedStatement.setString(7, getInputFunciona);

			int i = preparedStatement.executeUpdate();

			if (i > 0) {
				return true;
			}

		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();

			Utils reto3Utils = new Utils();
			reto3Utils.release(connection, preparedStatement, null);
		}

		return false;
	}

	public void mostrarLosOrdenadores(ArrayList<ordenador> ordenadores, String numSerie) {

		try {
			Class.forName(Utils.DRIVER);

			Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASS);

			Statement statement = connection.createStatement();

			String sql = "UPDATE ordenador.ordenador SET (`NumSerie`, `marca`, `fechaCompra`, `disco`, `memoria`, `modelo`, `funcionaElOrdenador`)"
					+ "	VALUES (?,?,?,?,?,?,?)" + "	WHERE numSerie = " + numSerie;
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				ordenador ret = new ordenador();

				ret.setNumSerie(result.getString("NUMSERIE"));
				ret.setMarcaString(result.getString("MARCA"));
				ret.setFechaCompra(result.getDate("FECHACOMPRA"));
				ret.setDisco(result.getString("DISCO"));
				ret.setMemoria(result.getString("MEMORIA"));
				ret.setModelo(result.getString("MODELO"));
				ret.setFunciona(result.getString("FUNCIONAELORDENADOR"));

				ordenadores.add(ret);

			}
		} catch (ClassNotFoundException e) {
			System.out.println("Ha dado fallo -> " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Malformacion sqlazo -> " + e.getMessage());
		}

	}

	private Date convertDateToSqDate(java.util.Date fechaCompra) {
		Date fechaSql = new Date(fechaCompra.getTime());
		return fechaSql;
	}

}
