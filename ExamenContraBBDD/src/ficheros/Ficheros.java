package ficheros;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import bbdd.ordenador;

public class Ficheros {

	FileWriter fileWriter = null;
	PrintWriter printWriter = null;

	private static final String NOMBRE_FICHERO = "ordenador.txt";
	private static final String RUTA_FICHERO = "C:\\Users\\Usuario\\eclipse-workspace\\ExamenContraBBDD";

	public void crearFichero(List<ordenador> ordenador) {
		try {
			fileWriter = new FileWriter(RUTA_FICHERO + NOMBRE_FICHERO);
			printWriter = new PrintWriter(fileWriter);
			for (int i = 0; i < ordenador.size(); i++) {
				String datos = "Numero de serire de este ordenador:" + ordenador.get(i).getNumSerie() + "\n"
						+ "La marca de este ordenador:" + ordenador.get(i).getMarcaString() + "\n"
						+ "El modelo este ordenador:" + ordenador.get(i).getModelo() + "\n"
						+ "La fecha de compra de este ordenador:" + ordenador.get(i).getFechaCompra() + "\n"
						+ "La memoria este ordenador:" + ordenador.get(i).getMemoria() + "\n"
						+ "El tamaño de disco de este ordenador:" + ordenador.get(i).getDisco() + "\n"
						+ "Este ordenador funciona?:" + ordenador.get(i).getFunciona() + "\n"
						+ "-------------------------------------------------------------" + "\n";
				printWriter.println(datos);
			}

		} catch (IOException e) {
			System.out.println("IOException - Error de escritura en el fichero " + RUTA_FICHERO + NOMBRE_FICHERO);
		} finally {
			printWriter.close();
			try {
				fileWriter.close();
			} catch (IOException e) {
			}
		}
	}
}
