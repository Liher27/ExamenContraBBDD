package bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {
	public static final String URL = "jdbc:mysql://localhost:3306/ordenador";

	// El Driver que vamos a usar
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";

	// Nombre y Pass de acceso a la Base de Datos
	public static final String USER = "root";
	public static final String PASS = "";

	public void release(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
	}
}
