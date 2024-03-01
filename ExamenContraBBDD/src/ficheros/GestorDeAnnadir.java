package ficheros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import bbdd.Utils;

public class GestorDeAnnadir {

	public boolean anadirOrdenador(String numSeries, String marca, String numFecha, String disco, String memoria,
			String modelo, String funciona) throws ParseException {

		String getInputNumSeries = numSeries;
		String getInputMarca = marca;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = numFecha;
		java.util.Date fechaEnSQLConvertida = dateFormat.parse(fecha);
		java.sql.Date sqlDate = new java.sql.Date(fechaEnSQLConvertida.getTime());
		String getInputDisco = disco;
		String getInputMemoria = memoria;
		String getInputModelo = modelo;
		String getInputFunciona = funciona;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASS);
			String sql = "INSERT INTO ordenador (`NumSerie`, `marca`, `fechaCompra`, `disco`, `memoria`, `modelo`, `funcionaElOrdenador`)"
					+ " VALUES (?,?,?,?,?,?,?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, getInputNumSeries);
			pstmt.setString(2, getInputMarca);
			pstmt.setDate(3, sqlDate);
			pstmt.setString(4, getInputDisco);
			pstmt.setString(5, getInputMemoria);
			pstmt.setString(6, getInputModelo);
			pstmt.setString(7, getInputFunciona);
			int i = pstmt.executeUpdate();
			if (i > 0) {
				return true;
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			Utils utils = new Utils();
			utils.release(conn, pstmt, rs);
		}
		return false;

	}

}
