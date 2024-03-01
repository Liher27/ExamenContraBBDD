package ficheros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import bbdd.Utils;

public class GestorDeBorrar {

	public void borraPc(String numSerie) {
		try {
			Class.forName(Utils.DRIVER);

			Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASS);

			Statement statement = connection.createStatement();

			String sql = "DELETE FROM ORDENADOR WHERE NUMSERIE = '" + numSerie + "'";
			statement.executeUpdate(sql);

			
		} catch (ClassNotFoundException e) {
			System.out.println("Ha dado fallo -> " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Malformacion sqlazo -> " + e.getMessage());
		}
	}

}
