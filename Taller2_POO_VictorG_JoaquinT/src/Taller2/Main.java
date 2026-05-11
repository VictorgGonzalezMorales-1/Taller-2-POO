// Victor Manuel Gonzalez Morales - RUT: 22.061.552-9 - Carrera: Ingeniería Civil Industrial
// Joaquin Esteban Torres Flores - RUT: 21.547.370-8 - Carrera: Ingeniería Civil Industrial

package Taller2;

// Importar librerías necesarias para el funcionamiento del Main
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Main {

	// Método generado para llamar a Administrador y que él realice las tareas
	public static Administrador A = new Administrador();
	public static Batalla B = new Batalla();
	public static TablaTipos t = new TablaTipos();

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		cargarTxt("Pokedex.txt");
		cargarTxt("Habitats.txt");
		cargarTxt("Gimnasios.txt");
		cargarTxt("Alto Mando.txt");

		A.rellenarHabitat();

		menu(scanner);

		scanner.close();

	}

	// Método generado para cargar los txt que se les entregue
	private static void cargarTxt(String archivo) {

		int contador = 0;

		try {

			File file = new File(archivo);
			Scanner lector = new Scanner(file);

			while (lector.hasNextLine()) {

				String linea = lector.nextLine();

				if (linea.length() == 0) {
					continue;
				}

				try {

					if (archivo.equals("Pokedex.txt")) {

						A.crearPokedex(linea);

					}

					if (archivo.equals("Registros.txt")) {

						String[] partes = linea.split(";");

						if (contador == 0) {

							if (partes.length >= 1) {

								A.crearJugador(partes[0]);

								if (partes.length > 1 && !partes[1].equals("null")) {
									A.solicitarJugador().setDerrotados(partes[1]);
								}

							}

							contador++;

						} else {

							if (partes.length >= 2) {
								A.rellenarPokemon(partes[0], partes[1]);
							} else {
								P("Línea inválida en Registros.txt: " + linea);
							}

						}

					}

					if (archivo.equals("Habitats.txt")) {

						A.crearHabitat(linea);

					}

					if (archivo.equals("Gimnasios.txt")) {

						A.crearGimnasio(linea);

					}

					if (archivo.equals("Alto Mando.txt")) {

						A.crearAltomando(linea);

					}

				} catch (Exception e) {
					P("Error al procesar línea en " + archivo + ": " + linea);
				}

			}

			lector.close();

		} catch (FileNotFoundException e) {

			P("No se encontró el archivo: " + archivo);

		} catch (Exception e) {

			P("Error al cargar el archivo " + archivo + ": " + e.getMessage());

		}

	}

	/*
	 * Método generado para mostrar y direccionar a las opciones que desee
	 * el jugador.
	 */
	private static void menu(Scanner scanner) {

		String respuesta = "";

		while (!respuesta.equals("3")) {

			P("1) Continuar.\r\n" + "2) Nueva Partida.\r\n" + "3) Salir.");
			respuesta = scanner.nextLine();

			switch (respuesta) {

			case "1":

				cargarTxt("Registros.txt");
				respuesta = opciones(scanner);

				break;

			case "2":

				P("Ingrese su apodo de jugador:");
				respuesta = scanner.nextLine();

				if (respuesta.length() == 0) {
					P("El apodo no puede estar vacío.\n");
					break;
				}

				A.crearJugador(respuesta);
				A.resetearGimnasios();

				for (String linea : A.entregarM().getTxtGimnasio()) {
					A.crearGimnasio(linea);
				}

				respuesta = opciones(scanner);

				break;

			case "3":

				P("Saliendo del juego...");
				break;

			default:

				P("Ingrese una opción válida ");
				break;

			}

		}

	}

	/*
	 * Método generado para mostrar las opciones de juego disponibles para el
	 * jugador y comunicarse con Administrador para completarlas.
	 */
	private static String opciones(Scanner scanner) {

		String respuesta = "";

		while (!respuesta.equals("8")) {

			P("1) Revisar equipo.\r\n" + "2) Salir a capturar.\r\n"
					+ "3) Acceso al PC (cambiar Pokémon del equipo).\r\n" + "4) Retar un gimnasio.\r\n"
					+ "5) Desafío al Alto Mando.\r\n" + "6) Curar Pokémon.\r\n" + "7) Guardar.\r\n"
					+ "8) Guardar y Salir.");

			respuesta = scanner.nextLine();

			switch (respuesta) {

			case "1":

				P("\n" + revisarEquipo());
				break;

			case "2":

				salirACapturar(scanner);
				break;

			case "3":

				accesoPc(scanner);
				break;

			case "4":

				retarGimnasio(scanner);
				break;

			case "5":

				peleaAltoMando(scanner);
				break;

			case "6":

				curarPokemon();
				break;

			case "7":

				A.guardar("Registros.txt");
				break;

			case "8":

				A.guardar("Registros.txt");
				A.guardar("Gimnasios.txt");
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
	private static String revisarEquipo() {

		String texto = "Equipo Actual:\n";

		try {

			ArrayList<Pokemon> equipo = A.solicitarEquipo();

			if (equipo == null || equipo.size() == 0) {
				return texto + "No tienes Pokémon en tu equipo.\n";
			}

			int contador = 1;
			int largo = 0;

			if (equipo.size() >= 6) {
				largo = 6;
			} else {
				largo = equipo.size();
			}

			for (int a = 0; a < largo; a++) {

				if (equipo.get(a) != null) {
					texto += contador + ") " + equipo.get(a) + "\n";
					contador++;
				}

			}

		} catch (Exception e) {
			texto += "Error al revisar equipo: " + e.getMessage() + "\n";
		}

		return texto;
	}

	// -----------------------------------------------------------------------------//

	// Método generado para completar la opción 2
	public static void salirACapturar(Scanner scanner) {

		try {

			ArrayList<Habitat> habitats = A.solicitarHabitat();

			if (habitats == null || habitats.size() == 0) {
				P("No existen hábitats disponibles para explorar.\n");
				return;
			}

			String texto = "Donde deseas ir a explorar?\r\n" + "\r\n" + "Zonas disponibles: \n";

			for (int a = 0; a < habitats.size(); a++) {
				texto += "\n" + (a + 1) + ") " + habitats.get(a).getNombreHabitat();
			}

			texto += "\n" + (habitats.size() + 1) + ") Volver al menu.";

			P(texto);

			int posicion = leerEnteroEnRango(scanner, 1, habitats.size() + 1);

			if (posicion == -1) {
				P("Ingrese una zona válida.\n");
				return;
			}

			if (posicion != habitats.size() + 1) {

				Pokemon pokemonGenerado = A.entregarPokemonRandom(habitats.get(posicion - 1));

				if (pokemonGenerado == null) {
					P("No se pudo generar un Pokémon en esta zona.\n");
					return;
				}

				P("Oh!! Ha aparecido un increible " + pokemonGenerado.getNombre() + "!!\r\n" + "\r\n"
						+ "Que deseas hacer?\r\n" + "\r\n" + "1) Capturar\r\n" + "2) Huir");

				String respuesta = scanner.nextLine();

				while (!respuesta.equals("1") && !respuesta.equals("2")) {

					P("Ingrese un valor válido ");
					respuesta = scanner.nextLine();

				}

				switch (respuesta) {

				case "1":

					boolean agregado = A.almacenarPokemonCapturado(pokemonGenerado);

					if (agregado == true) {
						P("Pokémon capturado con éxito\n");
					} else {
						P("Ya tienes este Pokémon, no puedes capturarlo nuevamente\n");
					}

					break;

				case "2":

					P("Huyendo.....");
					break;

				}

			}

		} catch (Exception e) {
			P("Error al salir a capturar: " + e.getMessage());
		}

	}

	// -----------------------------------------------------------------------------//

	// Método generado para completar la opción 3
	private static void accesoPc(Scanner scanner) {

		try {

			P(entregarPokemones());
			String opcion = "";

			while (!opcion.equals("2")) {

				P("1) Cambiar Pokémon\n" + "2) Salir\n");

				opcion = scanner.nextLine();

				switch (opcion) {

				case "1":

					P("Ingrese el número de posición de los Pokémon que quiera intercambiar (N°pokemon1, N°pokemon2)");
					opcion = scanner.nextLine();

					boolean funciona = A.intercambio(opcion);

					if (funciona == false) {
						P("Entrada inválida. Debe ingresar dos números separados por coma, por ejemplo: 1,3\n");
					} else {
						P(entregarPokemones());
					}

					break;

				case "2":

					P("Volviendo ......\n");
					break;

				default:

					P("Ingrese una opción válida\n");
					break;

				}

			}

		} catch (Exception e) {
			P("Error al acceder al PC: " + e.getMessage());
		}

	}

	public static String entregarPokemones() {

		String texto = "";

		try {

			ArrayList<Pokemon> equipo = A.solicitarEquipo();

			if (equipo == null || equipo.size() == 0) {
				return "No tienes Pokémon capturados.\n";
			}

			for (int a = 0; a < equipo.size(); a++) {

				if (equipo.get(a) != null) {
					texto += (a + 1) + ") " + equipo.get(a) + "\n";
				}

			}

		} catch (Exception e) {
			texto += "Error al entregar Pokémon: " + e.getMessage() + "\n";
		}

		return texto;

	}

	// -----------------------------------------------------------------------------//

	// Método generado para completar la opción 4
	private static void retarGimnasio(Scanner scanner) {

		try {

			ArrayList<Gimnasios> oponentes = A.solicitarGimnasios();

			if (oponentes == null || oponentes.size() == 0) {
				P("No existen gimnasios disponibles.\n");
				return;
			}

			P(entregarGimnasios());

			int opcionGimnasio = leerEnteroEnRango(scanner, 1, oponentes.size() + 1);

			if (opcionGimnasio == -1) {
				P("El gimnasio seleccionado no existe.\n");
				return;
			}

			if (opcionGimnasio == oponentes.size() + 1) {
				P("Volviendo al menú ....\n");
				return;
			}

			B.combateFinalizado = false;

			boolean batallaPermitida = revisarPermisoBatalla(String.valueOf(opcionGimnasio));

			if (batallaPermitida == true) {

				Gimnasios oponente = oponentes.get(opcionGimnasio - 1);
				Jugador j = A.solicitarJugador();

				if (j == null) {
					P("No existe un jugador cargado para combatir.\n");
					return;
				}

				if (hayPokemonVivoEquipoPrincipal() == false) {
					P("No tienes Pokémon vivos en tu equipo principal para combatir.\n");
					return;
				}

				P("Desafiando a " + oponente.entregarLiderGimnasio() + "!!\n");

				// Guardar los jugadores en batalla
				B.otorgarJugador(j);
				B.otorgarOponente(oponente);

				// Otorgar distribución inicial de Pokémon a los participantes de la batalla
				B.distribucionInicial();

				if (B.combateFinalizado == true || B.getPokemonJugador() == null || B.getPokemonOponente() == null) {
					P("No se pudo iniciar la batalla.\n");
					return;
				}

				eleccionesDeBatalla(scanner, oponente, j);

				A.solicitarJugador().imprimirJugadorMedallas();

			} else {

				if (oponentes.get(opcionGimnasio - 1).entregarEstadoGimnasio().equals("Sin derrotar")) {
					P("Calmado Entrenador!!! No puedes retar a "
							+ oponentes.get(opcionGimnasio - 1).entregarLiderGimnasio()
							+ " sin haber derrotado a los líderes anteriores!!\n");
				} else if (oponentes.get(opcionGimnasio - 1).entregarEstadoGimnasio().equals("Derrotado")) {
					P("Ya derrotaste este gimnasio\n");
				}

			}

		} catch (Exception e) {
			P("Error al retar gimnasio: " + e.getMessage());
		}

	}

	private static void eleccionesDeBatalla(Scanner scanner, Gimnasios oponente, Jugador j) {

		try {

			String respuesta = "";

			while (B.combateFinalizado == false && !respuesta.equals("3")) {

				if (B.getPokemonJugador() == null || B.getPokemonOponente() == null) {
					P("No hay Pokémon disponibles para continuar el combate.\n");
					B.combateFinalizado = true;
					return;
				}

				P(oponente.entregarLiderGimnasio() + " saca a " + B.getPokemonOponente().getNombre());
				P(j.getJugador() + " saca a " + B.getPokemonJugador().getNombre() + "!\n");

				P("Que deseas hacer?\r\n" + "1) Atacar\r\n" + "2) Cambiar de pokemon\r\n" + "3) Rendirse\r\n"
						+ "Ingrese Opcion:");

				respuesta = scanner.nextLine();

				switch (respuesta) {

				case "1":

					luchar();
					break;

				case "2":

					elegirPokemonParaPelear(scanner);
					break;

				case "3":

					P("Volviendo al menu...\n");
					B.combateFinalizado = true;
					break;

				default:

					P("Ingrese un valor permitido ..\n");
					break;

				}

				B.revisarVivos();

			}

			if (B.jugadorVivo == false && B.oponenteVivo == false) {
				P("Empate técnico... ambos se han quedado sin Pokémon!\n");
			}

			else if (B.jugadorVivo == false) {
				P("Te has quedado sin Pokémon en tu equipo!");
				respuesta = "3";
			}

			else if (B.oponenteVivo == false) {
				P("Felicidades " + B.getJugador().getJugador() + " venciste a "
						+ B.getOponente().entregarLiderGimnasio() + "\n");
				B.getOponente().setEstado("Derrotado");
				j.actualizarDerrotados(B.getOponente().entregarLiderGimnasio());
			}

		} catch (Exception e) {
			P("Error durante la batalla contra gimnasio: " + e.getMessage());
			B.combateFinalizado = true;
		}

	}

	private static void luchar() {

		try {

			if (B.getPokemonJugador() == null || B.getPokemonOponente() == null) {
				P("No hay Pokémon disponibles para continuar el combate.\n");
				B.combateFinalizado = true;
				return;
			}

			P(B.getPokemonJugador().getNombre() + " -> " + B.getPokemonJugador().statsTotales() + "puntos");
			P(B.getPokemonOponente().getNombre() + " -> " + B.getPokemonOponente().statsTotales() + "puntos\n");

			double efectividad = t.entregarEfectividadPorTipo(B.getPokemonJugador().getTipo(),
					B.getPokemonOponente().getTipo());

			double multiplicadorJugador = 1.0;

			if (efectividad == 0.0) {
				P(B.getPokemonJugador().getNombre() + " no afecta a " + B.getPokemonOponente().getNombre() + "!");
				multiplicadorJugador = 0.0;
			}

			else if (efectividad == 0.5) {
				P(B.getPokemonJugador().getNombre() + " es poco eficaz contra " + B.getPokemonOponente().getNombre()
						+ "!");
				multiplicadorJugador = 0.5;
			}

			else if (efectividad == 1.0) {
				P(B.getPokemonJugador().getNombre() + " es un ataque normal contra "
						+ B.getPokemonOponente().getNombre() + "!");
				multiplicadorJugador = 1.0;
			}

			else {
				P(B.getPokemonJugador().getNombre() + " es súper eficaz contra "
						+ B.getPokemonOponente().getNombre() + "!");
				multiplicadorJugador = 2.0;
			}

			P("Nuevo puntaje:");
			P(B.getPokemonJugador().getNombre() + " -> " + B.getPokemonJugador().statsTotales() * multiplicadorJugador
					+ "puntos");
			P(B.getPokemonOponente().getNombre() + " -> " + B.getPokemonOponente().statsTotales() + "puntos\n");

			if (B.getPokemonJugador().statsTotales() * multiplicadorJugador > B.getPokemonOponente().statsTotales()) {
				P("Ha ganado " + B.getPokemonJugador().getNombre() + "! " + B.getPokemonOponente().getNombre()
						+ " ha sido derrotado...\n");
				B.getPokemonOponente().setVida(0);
				B.actualizarPokemonOponente();
			}

			else if (B.getPokemonJugador().statsTotales() * multiplicadorJugador < B.getPokemonOponente()
					.statsTotales()) {
				P("Ha ganado " + B.getPokemonOponente().getNombre() + "! " + B.getPokemonJugador().getNombre()
						+ " ha sido derrotado...\n");
				B.getPokemonJugador().setVida(0);
				B.actualizarPokemonJugador();
			}

			else {

				P("Empate\n");

				B.getPokemonOponente().setVida(0);
				B.actualizarPokemonOponente();
				B.getPokemonJugador().setVida(0);
				B.actualizarPokemonJugador();

			}

		} catch (Exception e) {
			P("Error al luchar: " + e.getMessage());
			B.combateFinalizado = true;
		}

	}

	private static void elegirPokemonParaPelear(Scanner scanner) {

		try {

			ArrayList<Pokemon> equipo = A.solicitarEquipo();

			if (equipo == null || equipo.size() == 0) {
				P("No tienes Pokémon disponibles.\n");
				return;
			}

			int largo = 0;
			String texto = "Ingrese el número del Pokémon que quiera usar\n\n";

			if (equipo.size() >= 6) {
				largo = 6;
			} else {
				largo = equipo.size();
			}

			for (int a = 0; a < largo; a++) {

				if (equipo.get(a) != null) {
					texto += (a + 1) + ") " + equipo.get(a).getNombre() + " - Estado: "
							+ equipo.get(a).estado() + "\n";
				}

			}

			P(texto);

			int opcion = leerEnteroEnRango(scanner, 1, largo);

			if (opcion == -1) {
				P("Debe seleccionar un Pokémon válido.\n");
				return;
			}

			Pokemon p = equipo.get(opcion - 1);

			while (p.estado().equals("Debilitado")) {

				P("Solo puedes seleccionar Pokémon no debilitados\n");
				P("Ingrese el número del Pokémon que quiera usar\n");

				opcion = leerEnteroEnRango(scanner, 1, largo);

				if (opcion == -1) {
					P("Debe seleccionar un Pokémon válido.\n");
					return;
				}

				p = equipo.get(opcion - 1);

			}

			B.cambiarPokemonJugador(p);

		} catch (Exception e) {
			P("Error al elegir Pokémon para pelear: " + e.getMessage());
		}

	}

	public static String entregarGimnasios() {

		String texto = "A cual Líder deseas retar??\n\n";

		try {

			ArrayList<Gimnasios> oponentes = A.solicitarGimnasios();

			if (oponentes == null || oponentes.size() == 0) {
				return "No existen gimnasios disponibles.\n";
			}

			for (Gimnasios g : oponentes) {

				texto += g + "\n";

			}

			texto += String.valueOf(oponentes.size() + 1) + ") Volver al menu.\n";

		} catch (Exception e) {
			texto += "Error al entregar gimnasios: " + e.getMessage() + "\n";
		}

		return texto;
	}

	public static boolean revisarPermisoBatalla(String respuesta) {

		try {

			ArrayList<Gimnasios> oponentes = A.solicitarGimnasios();

			int opcion;

			try {
				opcion = Integer.valueOf(respuesta);
			} catch (NumberFormatException e) {
				return false;
			}

			if (opcion < 1 || opcion > oponentes.size()) {
				return false;
			}

			for (int a = 0; a < opcion - 1; a++) {

				if (oponentes.get(a).entregarEstadoGimnasio().equals("Sin derrotar")) {
					return false;
				}

			}

			if (oponentes.get(opcion - 1).entregarEstadoGimnasio().equals("Sin derrotar")) {
				return true;
			}

			return false;

		} catch (Exception e) {
			P("Error al revisar permiso de batalla: " + e.getMessage());
			return false;
		}

	}

	// -----------------------------------------------------------------------------//

	// Método generado para completar la opción 5
	private static void peleaAltoMando(Scanner scanner) {

		try {

			boolean permitirPelea = A.solicitarPermisoAltosMandos();

			if (permitirPelea == true) {

				if (hayPokemonVivoEquipoPrincipal() == false) {
					P("No tienes Pokémon vivos en tu equipo principal para desafiar al Alto Mando.\n");
					return;
				}

				ArrayList<AltosMandos> altosMandos = A.solicitarArrayAltosMandos();

				if (altosMandos == null || altosMandos.size() == 0) {
					P("No existen miembros del Alto Mando cargados.\n");
					return;
				}

				for (AltosMandos a : altosMandos) {
					a.curarPokemons();
				}

				for (AltosMandos a : altosMandos) {

					B.combateFinalizadoAltoMando = false;
					AltosMandos altoMando = a;
					Jugador j = A.solicitarJugador();

					if (j == null) {
						P("No existe un jugador cargado para desafiar al Alto Mando.\n");
						return;
					}

					if (hayPokemonVivoEquipoPrincipal() == false) {
						P("No tienes Pokémon vivos en tu equipo principal para continuar el Alto Mando.\n");
						return;
					}

					if (altoMando == null || altoMando.tienePokemonVivo() == false) {
						P("El miembro del Alto Mando no tiene Pokémon disponibles.\n");
						return;
					}

					P("Desafiando a " + altoMando.getNombre() + "!!\n");

					// Guardar los jugadores en batalla
					B.otorgarJugador(j);
					B.otorgarOponente(altoMando);

					// Otorgar distribución inicial de Pokémon a los participantes de la batalla
					B.distribucionInicialAltoMando();

					if (B.combateFinalizadoAltoMando == true || B.getPokemonJugador() == null
							|| B.getPokemonAltoMando() == null) {
						P("No se pudo iniciar la batalla contra el Alto Mando.\n");
						return;
					}

					boolean ganar = eleccionesDeBatallaAltoMando(scanner, altoMando, j);

					if (ganar == false) {
						P("Has sido expulsado del Alto Mando...\n");
						return;
					}

				}

				P("FELICIDADES!!!");
				P("Has derrotado al Alto Mando completo!");
				P("Ahora eres el nuevo Campeón Pokémon!");

			}

			else {

				P("Para retar a los altos mandos, primero necesitas derrotar a todos los gimnasios");

			}

		} catch (Exception e) {
			P("Error en el desafío al Alto Mando: " + e.getMessage());
		}

	}

	private static boolean eleccionesDeBatallaAltoMando(Scanner scanner, AltosMandos altoMando, Jugador j) {

		try {

			String respuesta = "";

			while (B.combateFinalizadoAltoMando == false && !respuesta.equals("3")) {

				if (B.getPokemonJugador() == null || B.getPokemonAltoMando() == null) {
					P("No hay Pokémon disponibles para continuar el combate.\n");
					B.combateFinalizadoAltoMando = true;
					return false;
				}

				P(altoMando.getNombre() + " saca a " + B.getPokemonAltoMando().getNombre());
				P(j.getJugador() + " saca a " + B.getPokemonJugador().getNombre() + "!\n");

				P("Que deseas hacer?\r\n" + "1) Atacar\r\n" + "2) Cambiar de pokemon\r\n" + "3) Rendirse\r\n"
						+ "Ingrese Opcion:");

				respuesta = scanner.nextLine();

				switch (respuesta) {

				case "1":

					lucharAltoMando();
					break;

				case "2":

					elegirPokemonParaPelear(scanner);
					break;

				case "3":

					P("Volviendo al menu...\n");
					return false;

				default:

					P("Ingrese un valor permitido ..\n");
					break;

				}

				B.revisarVivosAltoMando();

			}

			if (B.jugadorVivo == false && B.altoMandoVivo == false) {
				P("Empate técnico... ambos se han quedado sin Pokémon!\n");
				return false;
			}

			else if (B.jugadorVivo == false) {
				P("Te has quedado sin Pokémon en tu equipo!");
				respuesta = "3";
				return false;
			}

			else if (B.altoMandoVivo == false) {
				P("Felicidades " + B.getJugador().getJugador() + " venciste a "
						+ B.getAltoMando().getNombre() + "\n");

				return true;
			}

			return false;

		} catch (Exception e) {
			P("Error durante la batalla contra Alto Mando: " + e.getMessage());
			B.combateFinalizadoAltoMando = true;
			return false;
		}

	}

	private static void lucharAltoMando() {

		try {

			if (B.getPokemonJugador() == null || B.getPokemonAltoMando() == null) {
				P("No hay Pokémon disponibles para continuar el combate.\n");
				B.combateFinalizadoAltoMando = true;
				return;
			}

			P(B.getPokemonJugador().getNombre() + " -> " + B.getPokemonJugador().statsTotales() + "puntos");
			P(B.getPokemonAltoMando().getNombre() + " -> " + B.getPokemonAltoMando().statsTotales() + "puntos\n");

			double efectividad = t.entregarEfectividadPorTipo(B.getPokemonJugador().getTipo(),
					B.getPokemonAltoMando().getTipo());

			double multiplicadorJugador = 1.0;

			if (efectividad == 0.0) {
				P(B.getPokemonJugador().getNombre() + " no afecta a " + B.getPokemonAltoMando().getNombre() + "!");
				multiplicadorJugador = 0.0;
			}

			else if (efectividad == 0.5) {
				P(B.getPokemonJugador().getNombre() + " es poco eficaz contra "
						+ B.getPokemonAltoMando().getNombre() + "!");
				multiplicadorJugador = 0.5;
			}

			else if (efectividad == 1.0) {
				P(B.getPokemonJugador().getNombre() + " es un ataque normal contra "
						+ B.getPokemonAltoMando().getNombre() + "!");
				multiplicadorJugador = 1.0;
			}

			else {
				P(B.getPokemonJugador().getNombre() + " es súper eficaz contra "
						+ B.getPokemonAltoMando().getNombre() + "!");
				multiplicadorJugador = 2.0;
			}

			P("Nuevo puntaje:");
			P(B.getPokemonJugador().getNombre() + " -> " + B.getPokemonJugador().statsTotales() * multiplicadorJugador
					+ "puntos");
			P(B.getPokemonAltoMando().getNombre() + " -> " + B.getPokemonAltoMando().statsTotales() + "puntos\n");

			if (B.getPokemonJugador().statsTotales() * multiplicadorJugador > B.getPokemonAltoMando()
					.statsTotales()) {
				P("Ha ganado " + B.getPokemonJugador().getNombre() + "! " + B.getPokemonAltoMando().getNombre()
						+ " ha sido derrotado...\n");
				B.getPokemonAltoMando().setVida(0);
				B.actualizarPokemonAltoMando();
			}

			else if (B.getPokemonJugador().statsTotales() * multiplicadorJugador < B.getPokemonAltoMando()
					.statsTotales()) {
				P("Ha ganado " + B.getPokemonAltoMando().getNombre() + "! " + B.getPokemonJugador().getNombre()
						+ " ha sido derrotado...\n");
				B.getPokemonJugador().setVida(0);
				B.actualizarPokemonJugador();
			}

			else {

				P("Empate\n");

				B.getPokemonAltoMando().setVida(0);
				B.actualizarPokemonAltoMando();
				B.getPokemonJugador().setVida(0);
				B.actualizarPokemonJugador();

			}

		} catch (Exception e) {
			P("Error al luchar contra Alto Mando: " + e.getMessage());
			B.combateFinalizadoAltoMando = true;
		}

	}

	// -----------------------------------------------------------------------------//

	// Método generado para completar la opción 6
	private static void curarPokemon() {

		try {

			ArrayList<Pokemon> equipo = A.solicitarEquipo();

			if (equipo == null || equipo.size() == 0) {
				P("No tienes Pokémon para curar.\n");
				return;
			}

			for (int a = 0; a < equipo.size(); a++) {

				if (equipo.get(a) != null && equipo.get(a).estado().equals("Debilitado")) {
					equipo.get(a).curar();
				}

			}

			P("Tu equipo se ha recuperado!\n");

		} catch (Exception e) {
			P("Error al curar Pokémon: " + e.getMessage());
		}

	}

	// -----------------------------------------------------------------------------//

	// Método para verificar si existe al menos un Pokémon vivo en el equipo principal
	private static boolean hayPokemonVivoEquipoPrincipal() {

		try {

			ArrayList<Pokemon> equipo = A.solicitarEquipo();

			if (equipo == null || equipo.size() == 0) {
				return false;
			}

			int largo = 6;

			if (equipo.size() < 6) {
				largo = equipo.size();
			}

			for (int a = 0; a < largo; a++) {

				if (equipo.get(a) != null && equipo.get(a).estado().equals("Vivo")) {
					return true;
				}

			}

			return false;

		} catch (Exception e) {
			P("Error al revisar Pokémon vivos del equipo principal: " + e.getMessage());
			return false;
		}

	}

	// -----------------------------------------------------------------------------//

	// Método generado para imprimir más rápido
	public static void P(String t) {
		System.out.println(t);
	}

	// Método para leer números sin que el programa se caiga
	private static int leerEnteroSeguro(Scanner scanner) {

		try {

			return Integer.valueOf(scanner.nextLine());

		} catch (NumberFormatException e) {

			return -1;

		} catch (Exception e) {

			return -1;

		}

	}

	// Método para validar números dentro de un rango permitido
	private static int leerEnteroEnRango(Scanner scanner, int minimo, int maximo) {

		int numero = leerEnteroSeguro(scanner);

		if (numero < minimo || numero > maximo) {
			return -1;
		}

		return numero;
	}

}