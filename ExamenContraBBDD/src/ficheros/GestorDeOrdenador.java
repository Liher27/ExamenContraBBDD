package ficheros;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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

			if (result.next()) {
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

			if (result.next()) {
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

	public boolean modificarPc(String numSerie) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(Utils.DRIVER);

			connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASS);

			String sql = "UPDATE ORDENADOR SET MARCA=?, FECHACOMPRA=?,MODELO=?, DISCO=?,"
					+ " MEMORIA=?, FUNCIONAELORDENADOR=? WHERE NumSerie=?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, ordenador.getNumSerie());
			preparedStatement.setString(2, ordenador.getMarcaString());
			preparedStatement.setDate(3, convertDateToSqDate(ordenador.getFechaCompra()));
			preparedStatement.setString(4, ordenador.getDisco());
			preparedStatement.setString(5, ordenador.getMemoria());
			preparedStatement.setString(6, ordenador.getModelo());
			preparedStatement.setString(7, ordenador.getFunciona());

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

			if (result.next()) {
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
