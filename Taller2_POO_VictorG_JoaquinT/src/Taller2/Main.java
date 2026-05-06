package Taller2;

//Importar librerias necesarias para el funcionamiento del Main
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Main {

	// Método generado para llamar a administrador y que el realice las tareas
	public static administrador A = new administrador();
	public static Batalla B = new Batalla();
	public static tablaTipos t = new tablaTipos();

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

						String[] partes = linea.split(";");
						A.crearJugador(partes[0]);

						if (partes.length > 1 && !partes[1].equals("none")) {
							A.solicitarJugador().setDerrotados(partes[1]);
						}

						contador++;

					} else {

						A.rellenarPokemon(linea.split(";")[0], linea.split(";")[1]);

					}

				}

				if (string.equals("Habitats.txt")) {

					A.CrearHabitat(linea);

				}

				if (string.equals("Gimnasios.txt")) {
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

		int largo = 0;

		if (equipo.size() >= 6) {
			largo = 6;
		} else {
			largo = equipo.size();
		}

		for (int a = 0; a < largo; a++) {
			texto += contador + ") " + equipo.get(a) + "\n";
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

		P(entregarPokemones());
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
					P(entregarPokemones());
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

	public static String entregarPokemones() {

		ArrayList<pokemon> equipo = A.SolicitarEquipo();

		String texto = "";

		for (int a = 0; a < equipo.size(); a++) {
			texto += (a + 1) + ") " + equipo.get(a) + "\n";
		}

		return texto;

	}

	// -----------------------------------------------------------------------------//

	// Método generado para completar la opción 4

	private static void RetarGimnasio(Scanner scanner) {

		ArrayList<gimnacios> oponentes = A.SolicitarGimnasios();
		P(EntregarGimnasios());
		String respuesta = scanner.nextLine();

		if (RevisarPermisoBatalla(respuesta) == true) {

			gimnacios oponente = oponentes.get(Integer.valueOf(respuesta) - 1);
			jugador j = A.solicitarJugador();

			P("Desafiando a " + oponente.EntregarLiderGimnasio() + "!!\n");

			// Guardar los jugadores en batalla
			B.otorgarJugador(j);
			B.otorgarOponente(oponente);

			// Otorgar distribución incicial de pokemon a los participantes de la batalla
			B.distribucionInicial();

			P(oponente.EntregarLiderGimnasio() + " saca a " + B.getPokemonOponente().getNombre());
			P(j.getJugador() + " saca a " + B.getPokemonJugador().getNombre() + "!\n");

			eleccionesDeBatalla(scanner, oponente, j);

		}

	}

	private static void eleccionesDeBatalla(Scanner scanner, gimnacios oponente, jugador j) {

		String respuesta = "";
		
		while(B.combateFinalizado == false && !respuesta.equals("3")) {
			
			P(oponente.EntregarLiderGimnasio() + " saca a " + B.getPokemonOponente().getNombre());
			P(j.getJugador() + " saca a " + B.getPokemonJugador().getNombre() + "!\n");
			
			P("Que deseas hacer?\r\n" + "1) Atacar\r\n" + "2) Cambiar de pokemon\r\n" + "3) Rendirse\r\n"
				+ "Ingrese Opcion:");
			
			respuesta = scanner.nextLine();
			
			switch(respuesta) {
			
			case"1":
				
				luchar();
				
				break;
				
			case"2":
				
				elegirPokemonParaPelear(scanner);
				
				break;
				
			case"3":
				
				P("Volviendo al menu...\n");
				B.combateFinalizado = true;
				
				break;

			default:
				P("Ingrese un valor permitido ..\n");
			
			}
			
			B.revisarVivos();
			
		}
		
		if(B.jugadorVivo == false) {
			P("Te has quedado sin pokemons en tu equipo!");
			respuesta = "3";
		}
		
		if(B.oponeteVivo == false) {
			P("Felicidades " + B.getJugador().getJugador() + " venciste a " + B.getOponente().EntregarLiderGimnasio() + "\n");
			B.getOponente().setEstado("Derrotado");
			//Falta agregar la medalla
		}
		
		//Lo de si gana al loco 

	}

	
	private static void luchar() {
		
		P(B.getPokemonJugador().getNombre() + " -> " + B.getPokemonJugador().StatsTotales() + "puntos");
		P(B.getPokemonOponente().getNombre() + " -> " + B.getPokemonOponente().StatsTotales() + "puntos\n");
		
		//Revisar Efectividad
		
		int efectividadJugador = t.encontrarIndice(B.getPokemonJugador().getTipo());
		int efectividadOponente = t.encontrarIndice(B.getPokemonOponente().getTipo());
		
		double efectividad = t.entregarEfectividad(efectividadJugador, efectividadOponente);
		
		//Imprimir efectividad pokemon
		
		double multiplicadorJugador = 1.0;

		if (efectividad == 0.0) {
			P(B.getPokemonJugador().getNombre() + " no afecta a " + B.getPokemonOponente().getNombre() + "!");
			multiplicadorJugador = 0.0;
		}
		
		else if(efectividad == 0.5) {
			P(B.getPokemonJugador().getNombre() + " es poco eficaz contra " + B.getPokemonOponente().getNombre() + "!");
			multiplicadorJugador = 0.5;
			
		}
		else if(efectividad == 1.0) {
			P(B.getPokemonJugador().getNombre() + " es un ataque normal contra " + B.getPokemonOponente().getNombre() + "!");
			multiplicadorJugador = 1.0;
		}
		
		else {
			
			P(B.getPokemonJugador().getNombre() + " es súper eficaz contra " + B.getPokemonOponente().getNombre() + "!");
			multiplicadorJugador = 2.0;
		}
		

		P("Nuevo puntaje:");
		P(B.getPokemonJugador().getNombre() + " -> " + B.getPokemonJugador().StatsTotales()*multiplicadorJugador + "puntos");
		P(B.getPokemonOponente().getNombre() + " -> " + B.getPokemonOponente().StatsTotales() + "puntos\n");
		
		if(B.getPokemonJugador().StatsTotales()*multiplicadorJugador > B.getPokemonOponente().StatsTotales()) {
			P("Ha ganado " + B.getPokemonJugador().getNombre() + "! "+ B.getPokemonOponente().getNombre() +" ha sido derrotado...\n");
			B.getPokemonOponente().setVida(0);
			B.actualizarPokemonOponente();
		}
		else {
			P("Ha ganado " + B.getPokemonOponente().getNombre() + "! "+ B.getPokemonJugador().getNombre() +" ha sido derrotado...\n");
			B.getPokemonJugador().setVida(0);
			B.actualizarPokemonJugador();
		}
		
	}

	private static void elegirPokemonParaPelear(Scanner scanner) {
		
		ArrayList<pokemon> equipo = A.SolicitarEquipo();

		//Imprimir los pokemon
		
		int largo = 0;
		String texto = "Ingrese el numero del pokemon que quiera usar\n\n";

		if (equipo.size() >= 6) {
			largo = 6;
		} else {
			largo = equipo.size();
		}

		for (int a = 0; a < largo; a++) {

			texto += (a+1) + ") " + equipo.get(a).getNombre() + " - Estado: " + equipo.get(a).estado() + "\n";

		}
		
		P(texto);
		
		//Seleccionar el pokemon y cambiarlo
		
		pokemon p = equipo.get(Integer.valueOf(scanner.nextLine())-1);
		
		while(p.estado().equals("Debilitado")) {
			
			P("Solo puedes seleccionar pokemon no debilitados\n");
			P("Ingrese el numero del pokemon que quiera usar\n");
			p = equipo.get(Integer.valueOf(scanner.nextLine())-1);
			
		}
		
		B.cambiarPokemonJugador(p);
		
	}

	public static String EntregarGimnasios() {

		ArrayList<gimnacios> oponentes = A.SolicitarGimnasios();

		String texto = "A cual Lider deseas retar??\n\n";

		for (gimnacios g : oponentes) {

			texto += g + "\n";
		}

		texto += String.valueOf(oponentes.size() + 1) + ") Volver al menu.\n";

		return texto;
	}

	public static boolean RevisarPermisoBatalla(String respuesta) {

		ArrayList<gimnacios> oponentes = A.SolicitarGimnasios();

		for (int a = 0; a < Integer.valueOf(respuesta) - 1; a++) {

			if (oponentes.get(a).EntregarEstadoGimnasio().equals("Sin derrotar")) {
				return false;
			}

		}

		if (oponentes.get(Integer.valueOf(respuesta) - 1).EntregarEstadoGimnasio().equals("Sin derrotar")) {
			return true;
		}

		return false;

	}

	// -----------------------------------------------------------------------------//

	// Método generado para completar la opción 5

	// -----------------------------------------------------------------------------//

	// Método generado para completar la opción 6
	private static void CurarPokemon() {

		ArrayList<pokemon> equipo = A.SolicitarEquipo();

		int largo = 0;

		if (equipo.size() >= 6) {
			largo = 6;
		} else {
			largo = equipo.size();
		}

		for (int a = 0; a < largo; a++) {

			if (equipo.get(a).estado().equals("Debilitado")) {
				equipo.get(a).curar();
			}

		}

	}

	// -----------------------------------------------------------------------------//

	// Método generado para completar la opción 7

	// -----------------------------------------------------------------------------//

	// Método generado para imprimir más rápido
	public static void P(String t) {
		System.out.println(t);
	}

}
