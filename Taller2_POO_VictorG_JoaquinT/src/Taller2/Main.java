package Taller2;

//Importar librerias necesarias para el funcionamiento del Main
import java.util.Scanner;
import java.io.*;

public class Main {

	// Método generado para llamar a administrador y que el realice las tareas
	public static administrador A = new administrador();

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		menu(scanner);

		scanner.close();

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
			
			P("1) Revisar equipo.\r\n" + "2) Salir a capturar.\r\n" + "3) Acceso al PC (cambiar Pokémon del equipo).\r\n"
					+ "4) Retar un gimnasio.\r\n" + "5) Desafío al Alto Mando.\r\n" + "6) Curar Pokémon.\r\n"
					+ "7) Guardar.\r\n" + "8) Guardar y Salir.");
			respuesta = scanner.nextLine();

			switch (respuesta) {

			case "1":
				break;

			case "2":
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

			}

		}
		
		P("Nos vemos entrenador...");
		
		return "3";

	}

	// Método generado para imprimir más rápido
	public static void P(String t) {
		System.out.println(t);
	}

}
