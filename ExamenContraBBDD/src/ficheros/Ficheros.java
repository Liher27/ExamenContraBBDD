package ficheros;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import bbdd.ordenador;

public class Ficheros {

	FileWriter fileWriter = null;
	PrintWriter printWriter = null;

	private static final String NOMBRE_FICHERO = "ordenador.txt";
	private static final String RUTA_FICHERO = "c://Users//in1dm3-v//desktop//";

	public List<ordenador> crearFichero(List<ordenador> ordenadores) {
		
		List<ordenador> ordenador = new ArrayList<ordenador>();
		
		try {
			fileWriter = new FileWriter(RUTA_FICHERO + NOMBRE_FICHERO);
			printWriter = new PrintWriter(fileWriter);
			for (int i = 0; i < ordenadores.size(); i++) {
				String datos = "Numero de serire de este ordenador:" + ordenadores.get(i).getNumSerie() + "\n"
						+ "La marca de este ordenador:" + ordenadores.get(i).getMarcaString() + "\n"
						+ "El modelo este ordenador:" + ordenadores.get(i).getModelo() + "\n"
						+ "La fecha de compra de este ordenador:" + ordenadores.get(i).getFechaCompra() + "\n"
						+ "La memoria este ordenador:" + ordenadores.get(i).getMemoria() + "\n"
						+ "El tamaño de disco de este ordenador:" + ordenadores.get(i).getDisco() + "\n"
						+ "Este ordenador funciona?:" + ordenadores.get(i).getFunciona() + "\n"
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
		return ordenador;
	}
}
