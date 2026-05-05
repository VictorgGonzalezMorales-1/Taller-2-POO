package Taller2;

//Importar librerias necesarias para el funcionamiento del Main
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Main {

	// Método generado para llamar a administrador y que el realice las tareas
	public static administrador A = new administrador();

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		CargarTxt("Pokedex.txt");
		CargarTxt("Habitats.txt");
		CargarTxt("Gimnasios.txt");
		
		menu(scanner);

		scanner.close();

	}

	// Metodo generado para cargar los txt que se les entregue
	private static void CargarTxt(String string) {

		int contador = 0;

		try {

			File file = new File(string);
			Scanner lector = new Scanner(file);

			while (lector.hasNextLine()) {

				String linea = lector.nextLine();

				if (string.equals("Pokedex.txt")) {

					A.crearPokedex(linea);

				}

				if (string.equals("Registros.txt")) {

					if (contador == 0) {

						A.crearJugador(linea.split(";")[0]);
						contador++;

					} else {

						A.rellenarPokemon(linea.split(";")[0], linea.split(";")[1]);

					}

				}

				if (string.equals("Habitats.txt")) {

					A.CrearHabitat(linea);

				}
				
				if(string.equals("Gimnasios.txt")) {
					A.CrearGimnasio(linea);
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

				CargarTxt("Registros.txt");
				respuesta = Opciones(scanner);

				break;

			case "2":

				P("Ingrese su apodo de jugador:");
				respuesta = scanner.nextLine();
				A.crearJugador(respuesta);
				respuesta = Opciones(scanner);

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
	private static String Opciones(Scanner scanner) {

		// Llamado al método para rellenar los habitats con sus pokemons
		// correspondientes
		// solo una vez en toda la partida
		A.RellenarHabitat();

		String respuesta = "";

		while (!respuesta.equals("8")) {

			P("1) Revisar equipo.\r\n" + "2) Salir a capturar.\r\n"
					+ "3) Acceso al PC (cambiar Pokémon del equipo).\r\n" + "4) Retar un gimnasio.\r\n"
					+ "5) Desafío al Alto Mando.\r\n" + "6) Curar Pokémon.\r\n" + "7) Guardar.\r\n"
					+ "8) Guardar y Salir.");
			respuesta = scanner.nextLine();

			switch (respuesta) {

			// Completado al 90% (falta prueba de errores)
			case "1":
				P("\n" + RevisarEquipo());
				break;

			// Completado al 90% (falta prueba de errores)
			case "2":
				SalirACapturar(scanner);
				break;

			// Completado al 90% (falta prueba de errores)
			case "3":
				AccesoPc(scanner);
				break;

			case "4":
				RetarGimnasio(scanner);
				break;

			case "5":
				break;

			// Completado al 90% (falta prueba de errores)
			case "6":
				CurarPokemon();
				break;

			// Método temporal
			case "7":
				A.guardar("Registros.txt");
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

	// -----------------------------------------------------------------------------//

	// Método generado para completar la opción 1
	private static String RevisarEquipo() {

		ArrayList<pokemon> equipo = A.SolicitarEquipo();
		int contador = 1;
		String texto = "Equipo Actual:\n";

		for (pokemon p : equipo) {
			texto += contador + ") " + p + "\n";
			contador++;
		}

		return texto;
	}

	// -----------------------------------------------------------------------------//

	// Método generado para completar la opción 2
	public static void SalirACapturar(Scanner scanner) {

		ArrayList<Habitat> habitats = A.SolicitarHabitat();

		String texto = "Donde deseas ir a explorar?\r\n" + "\r\n" + "Zonas disponibles: \n";

		for (int a = 0; a < habitats.size(); a++) {
			texto += "\n" + (a + 1) + ") " + habitats.get(a).getNombreHabitat();
		}
		texto += "\n7) Volver al menu.";

		P(texto);

		int posicion = Integer.valueOf(scanner.nextLine());

		if (posicion != 7) {

			pokemon pokemonGenerado = A.EntregarPokemonRandom(habitats.get(posicion - 1));

			P("Oh!! Ha aparecido un increible " + pokemonGenerado.getNombre() + "!!\r\n" + "\r\n"
					+ "Que deseas hacer?\r\n" + "\r\n" + "1) Capturar\r\n" + "2) Huir");

			String respuesta = scanner.nextLine();

			while (!respuesta.equals("1") && !respuesta.equals("2")) {

				P("Ingrese un valor valido ");
				respuesta = scanner.nextLine();

			}

			switch (respuesta) {

			case "1":

				A.AlmacenarPokemonCapturado(pokemonGenerado);
				break;

			case "2":
				P("Huyendo.....");
				break;

			}

		}

	}

	// -----------------------------------------------------------------------------//

	// Método generado para completar la opción 3
	private static void AccesoPc(Scanner scanner) {

		P(EntregarEquipo());
		String opción = "";

		while (!opción.equals("2")) {

			P("1) Cambiar Pokémon\n" + "2) Salir\n");

			opción = scanner.nextLine();

			switch (opción) {

			case "1":
				P("Ingrese el numero de posición de los pokemon que quiera intercambiar (N°pokemon1, N°pokemon2)");
				opción = scanner.nextLine();
				boolean funciona = A.intercambio(opción);
				if (funciona == false)
					P("Ingrese numeros separados por comas !!!!\n");
				else {
					P(EntregarEquipo());
				}
				break;

			case "2":
				P("Volviendo ......\n");
				break;

			default:
				P("Ingrese una opción valida\n");
				break;

			}

		}

	}

	public static String EntregarEquipo() {

		ArrayList<pokemon> equipo = A.SolicitarEquipo();

		String texto = "";

		int largo = 0;

		if (equipo.size() >= 6) {
			largo = 6;
		} else {
			largo = equipo.size();
		}

		for (int a = 0; a < largo; a++) {
			texto += (a + 1) + ") " + equipo.get(a) + "\n";
		}

		return texto;

	}

	// -----------------------------------------------------------------------------//

	// Método generado para completar la opción 4
	private static void RetarGimnasio(Scanner scanner) {
		
		P(EntregarGimnasios());
		
	}
	
	public static String EntregarGimnasios() {
		
		ArrayList<gimnacios> oponentes= A.SolicitarGimnasios();
		
		String texto = "A cual Lider deseas retar??\n\n";
		
		for(gimnacios g: oponentes) {
			
			texto+= g + "\n";
		}
		
		texto += String.valueOf(oponentes.size() + 1) + ") Volver al menu.\n";
		
		return texto;
	}

	// -----------------------------------------------------------------------------//

	// Método generado para completar la opción 5

	// -----------------------------------------------------------------------------//

	// Método generado para completar la opción 6
	private static void CurarPokemon() {

		ArrayList<pokemon> equipo = A.SolicitarEquipo();

		int contador = 1;

		for (pokemon p : equipo) {

			if (contador < 7 && p.estado().equals("Debilitado")) {
				p.curar();
				contador++;
			} else {
				break;
			}

		}

		P("\nTu equipo se ha recuperado!\n");

	}

	// -----------------------------------------------------------------------------//

	// Método generado para completar la opción 7

	// -----------------------------------------------------------------------------//

	// Método generado para imprimir más rápido
	public static void P(String t) {
		System.out.println(t);
	}

}
