package controlador;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ficheros.Ficheros;
import ficheros.GestorDeAnnadir;
import ficheros.GestorDeBorrar;
import ficheros.GestorDeOrdenador;
import bbdd.ordenador;

public class Menu {

	private static Scanner teclado = new Scanner(System.in);
	private List<ordenador> ordenador = new ArrayList<ordenador>();
	private GestorDeBorrar gestorDeBorrar = new GestorDeBorrar();
	public void mostrarMenu() {

		int opcionMenu = -1;
		while (opcionMenu != 0) {
			try {
				System.out.println("------------LISTADO DE ORDENADORES------------");
				System.out.println("0. Salir");
				System.out.println("1. Mostrar todos los ordenadores");
				System.out.println("2. Buscar ordenador");
				System.out.println("3. Añadir ordenador");
				System.out.println("4. Eliminar ordenador");
				System.out.println("5. Modificar ordenador");
				System.out.println("6. Crear Back-up");
				System.out.println("-----------------------------------------------");
				System.out.println("Escoge una opción:");
				opcionMenu = teclado.nextInt();
				teclado.nextLine();
			} catch (Exception e) {
				teclado.nextLine();
				opcionMenu = -1;
			}
			if (opcionMenu < 0 || (opcionMenu > 6)) {
				System.out.println("Error, introduzca un número entre 0 y 6");
			} else {
				elegirOpcion(opcionMenu);
			}
		}
	}

	private void elegirOpcion(int opcionMenu) {

		switch (opcionMenu) {
		case 1:
			mostrarTodosOrdenadores();

			break;
		case 2:
			buscarOrdenador();
			break;
		case 3:
			try {
				anadirOrdenador();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		case 4:
			borrarOrdenador();
			break;
		case 5:
			try {
			System.out.println("Introduzca el Numero De Serie para buscar:");
			String numSerie = teclado.next();	
			gestorDeBorrar.borraPc(numSerie);	
			modificarOrdenador(numSerie);
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		case 6:
			crearBackUp();  System.out.println("Despues de mostrar todos los ordenadores.");
			break;
		case 0:
			System.out.println("Fin del programa!!");
			break;
		}
	}

	private void crearBackUp() {
		Ficheros fichero = new Ficheros();
		fichero.crearFichero(ordenador);
		
	}

	public void mostrarTodosOrdenadores() {
		GestorDeOrdenador gestorDeOrdenador = new GestorDeOrdenador();
		ordenador = gestorDeOrdenador.obtenerPc();
		for (int i = 0; i < ordenador.size(); i++) {
			String datos = "Numero de serie de este ordenador:" + ordenador.get(i).getNumSerie() + "\n"
					+ "La marca de este ordenador:" + ordenador.get(i).getMarcaString() + "\n"
					+ "El modelo este ordenador:" + ordenador.get(i).getModelo() + "\n"
					+ "La fecha de compra de este ordenador:" + ordenador.get(i).getFechaCompra() + "\n"
					+ "La memoria este ordenador:" + ordenador.get(i).getMemoria() + "\n"
					+ "El tamaño de disco de este ordenador:" + ordenador.get(i).getDisco() + "\n"
					+ "Este ordenador funciona?:" + ordenador.get(i).getFunciona() + "\n"
					+ "-------------------------------------------------------------" + "\n";
			System.out.println(datos);
		}
		crearBackUp();

	}

	public void buscarOrdenador() {
		System.out.println("Introduzca el Numero De Serie para buscar:");
		String numSerie = teclado.next();
		GestorDeOrdenador gestorDeOrdenador = new GestorDeOrdenador();
		ordenador = gestorDeOrdenador.buscarPc(numSerie);

		for (int i = 0; i < ordenador.size(); i++) {
			String datos = "Numero de serire de este ordenador:" + ordenador.get(i).getNumSerie() + "\n"
					+ "La marca de este ordenador:" + ordenador.get(i).getMarcaString() + "\n"
					+ "El modelo este ordenador:" + ordenador.get(i).getModelo() + "\n"
					+ "La fecha de compra de este ordenador:" + ordenador.get(i).getFechaCompra() + "\n"
					+ "La memoria este ordenador:" + ordenador.get(i).getMemoria() + "\n"
					+ "El tamaño de disco de este ordenador:" + ordenador.get(i).getDisco() + "\n"
					+ "Este ordenador funciona?:" + ordenador.get(i).getFunciona() + "\n"
					+ "-------------------------------------------------------------" + "\n";
			System.out.println(datos);

		}
	}

	public void anadirOrdenador() throws ParseException {

		System.out.println("Introduzca el Numero De Serie:");
		String numSerie = teclado.next();
		System.out.println("Introduzca La marca del ordenador:");
		String marca = teclado.next();
		System.out.println("Introduzca la fecha de compra:");
		String numFecha = teclado.next();
		System.out.println("Introduzca el tamaño de disco:");
		String disco = teclado.next();
		System.out.println("Introduzca la memoria que tiene:");
		String memoria = teclado.next();
		System.out.println("Introduzca el modelo del ordenador:");
		String modelo = teclado.next();
		System.out.println("Funciona correctamente?:");
		String fun = teclado.next();

		GestorDeAnnadir gestorDeAnnadir = new GestorDeAnnadir();
		gestorDeAnnadir.anadirOrdenador(numSerie, marca, numFecha, disco, memoria, modelo, fun);
	}

	private void borrarOrdenador() {
		System.out.println("Introduzca el Numero De Serie para modificar:");
		String numSerie = teclado.next();
		gestorDeBorrar.borraPc(numSerie);
		System.out.println("El ordenador ha sido borrado correctamente");
		
		crearBackUp();
		
	}

	private void modificarOrdenador(String numSerie) throws ParseException {
		System.out.println("Introduzca La marca del ordenador modificada:");
		String marca = teclado.next();
		System.out.println("Introduzca la fecha de compra modificada:");
		String numFecha = teclado.next();
		System.out.println("Introduzca el tamaño de disco modificada:");
		String disco = teclado.next();
		System.out.println("Introduzca la memoria que tiene modificada:");
		String memoria = teclado.next();
		System.out.println("Introduzca el modelo del ordenador modificada:");
		String modelo = teclado.next();
		System.out.println("Funciona correctamente?:");
		String fun = teclado.next();
		
		GestorDeOrdenador gestorDeOrdenador = new GestorDeOrdenador();
		gestorDeOrdenador.modificarPc(numSerie, marca, numFecha, disco, memoria, modelo, fun);
	
		crearBackUp();
	}

}
