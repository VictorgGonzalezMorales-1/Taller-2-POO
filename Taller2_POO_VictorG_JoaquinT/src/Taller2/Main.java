package Taller2;

//Importar librerias necesarias para el funcionamiento del Main
import java.util.Scanner;
import java.io.*;

public class Main {

	// Método generado para llamar a administrador y que el realice las tareas
	public static administrador A = new administrador();

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		cargartxt("Pokedex.txt");

		menu(scanner);

		scanner.close();

	}

	// Metodo generado para cargar los txt que se les entregue
	private static void cargartxt(String string) {

		try {

			File file = new File(string);
			Scanner lector = new Scanner(file);

			while (lector.hasNextLine()) {

				String linea = lector.nextLine();

				if (string.equals("Pokedex.txt")) {

					A.crearPokedex(linea);

				}

			}

			lector.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}

	/*
	 * Método generado para mostrar y direccionar a las opciones del menú que desee
	 * el jugador
	 */
	private static void menu(Scanner scanner) {

		String respuesta = "";

		while (!respuesta.equals("3")) {

			P("1) Continuar.\r\n" + "2) Nueva Partida.\r\n" + "3) Salir.");
			respuesta = scanner.nextLine();

			switch (respuesta) {

			case "1":

				respuesta = continuar(scanner);

				break;

			case "2":

				P("Ingrese su apodo de jugador:");
				respuesta = scanner.nextLine();
				A.crearJugador(respuesta);
				respuesta = continuar(scanner);

				break;

			default:
				P("Ingrese una opción válida ");
				break;

			}

		}

	}

	/*
	 * Método generado para mostrar las opciones de juego disponibles para le
	 * jugador y comunicarse con administrador para completarlas
	 */
	private static String continuar(Scanner scanner) {

		String respuesta = "";

		while (!respuesta.equals("8")) {

			P("1) Revisar equipo.\r\n" + "2) Salir a capturar.\r\n"
					+ "3) Acceso al PC (cambiar Pokémon del equipo).\r\n" + "4) Retar un gimnasio.\r\n"
					+ "5) Desafío al Alto Mando.\r\n" + "6) Curar Pokémon.\r\n" + "7) Guardar.\r\n"
					+ "8) Guardar y Salir.");
			respuesta = scanner.nextLine();

			switch (respuesta) {

			case "1":
				P(A.revisarEquipo());
				break;

			case "2":
				salirCapturar(scanner);
				break;

			case "3":
				break;

			case "4":
				break;

			case "5":
				break;

			case "6":
				break;

			case "7":
				break;

			case "8":
				P("Nos vemos entrenador...");
				break;

			default:
				P("Ingrese una opción válida ");
				break;

			}

		}

		return "3";

	}

	//Método generado para coordinar la tarea de capturar un pokemon decidiendo en que habitat se desea hacer esto
	private static void salirCapturar(Scanner scanner) {

		String opcion = "0";

		while (!opcion.equals("7")) {

			P("Donde deseas ir a explorar?\r\n" + "\r\n" + "Zonas disponibles:\r\n" + "\r\n" + "1) Lago\r\n"
					+ "2) Cueva\r\n" + "3) Montaña\r\n" + "4) Bosque\r\n" + "5) Prado\r\n" + "6) Mar\r\n"
					+ "7) Volver al menu.");

			opcion = scanner.nextLine();

			switch (opcion) {

			case "1":
				capturar(scanner, "Lago");
				break;

			case "2":
				capturar(scanner, "Cueva");
				break;

			case "3":
				capturar(scanner, "Montaña");
				break;

			case "4":
				capturar(scanner, "Bosque");
				break;

			case "5":
				capturar(scanner, "Prado");
				break;

			case "6":
				capturar(scanner, "Mar");
				break;

			case "7":
				P("Volviendo al menú ....");
				break;

			default:
				P("Ingrese un valor valido ");
				break;

			}

		}

	}

	// Método generado para generar el pokemon al azar mediante llamado al administrador y decidir su capturaro o no
	public static void capturar(Scanner scanner, String opcion) {

		pokemon pokemonGenerado = A.generarPokemonAzar(opcion);

		P("Oh!! Ha aparecido un increible " + pokemonGenerado.getNombre() + "!!\r\n" + "\r\n" + "Que deseas hacer?\r\n"
				+ "\r\n" + "1) Capturar\r\n" + "2) Huir");

		opcion = scanner.nextLine();

		while (!opcion.equals("1") && !opcion.equals("2")) {

			P("Ingrese un valor valido ");
			opcion = scanner.nextLine();

		}

		switch (opcion) {

		case "1":
			A.almacenarPokemonCapturado(pokemonGenerado);
			break;

		case "2":
			P("Huyendo.....");
			break;

		}

	}

	// Método generado para imprimir más rápido
	public static void P(String t) {
		System.out.println(t);
	}

}
