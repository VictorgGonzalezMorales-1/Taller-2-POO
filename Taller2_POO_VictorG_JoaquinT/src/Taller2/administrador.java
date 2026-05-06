package Taller2;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class administrador {

	public static memoria M = new memoria();
	public static ArrayList<String> zonas = new ArrayList<String>();

	// Método generado para imprimir más rápido
	public static void P(String t) {
		System.out.println(t);
	}

	/*
	 * Método el cual se comunica con la clase jugador, crea el objeto y le dice a
	 * la clase memoria que lo almacene
	 */
	public void crearJugador(String nombre) {

		jugador prota = new jugador(nombre);
		M.guardarJugador(prota);

	}

	public void guardar(String archivo) {

		ArrayList<String> txt = null;

		if (archivo.equals("Registros.txt")) {
			M.entregarTxtJugador();
			txt = M.getTxtJugador();
		} if (archivo.equals("Gimnasios.txt")) {
		    M.entregarTxtGimnasio();
		    txt = M.getTxtGimnasio();
		}

		BufferedWriter Escritor;

		try {

			Escritor = new BufferedWriter(new FileWriter(archivo, false));

			for (String s : txt) {

				Escritor.write(s);
				Escritor.newLine();

			}

			Escritor.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Método para que cuando se genere una partida nueva, no venga con partidas ya
	// ganadas
	public void resetearGimnasios() {

		M.EntregarGimnasios().clear();
		M.setTxtGimnasioDefault();

	}

	// Metodo para buscar pokemon en la pokedex y guardarlo en el equipo
	public void rellenarPokemon(String texto, String estado) {

		ArrayList<pokedex> datos = M.getP();

		for (pokedex po : datos) {

			if (po.getPokemon().getNombre().equals(texto)) {

				pokemon copia = new pokemon(po.getPokemon());
				if (estado.equals("Debilitado")) {
					copia.setVida(0);
				}
				AlmacenarPokemonCapturado(copia);

			}

		}

	}

	// método generado para generar los objetos de la pokedex
	public void crearPokedex(String linea) {

		String[] s = linea.split(";");

		if (s.length == 10) {

			pokedex p = new pokedex(s[0], s[1], Double.valueOf(s[2]), Double.valueOf(s[3]), Double.valueOf(s[4]),
					Double.valueOf(s[5]), Double.valueOf(s[6]), Double.valueOf(s[7]), Double.valueOf(s[8]), s[9]);
			M.guardarPokedex(p);

		}
	}

	// ----------------------------------------------------------

	// Método encargado de Crear habitats y almacenarlos
	public void CrearHabitat(String Habitat) {
		Habitat habitatCreado = new Habitat(Habitat);
		M.GuardarHabitat(habitatCreado);
	}

	// Rellenar habitats con sus pokemones tantas veces como la probabilidad lo diga
	public void RellenarHabitat() {

		for (Habitat h : M.EntregarHabitats()) {

			for (pokedex p : M.getP()) {

				if (h.getNombreHabitat().equals(p.getHabitat())) {

					h.RellenarArregloConPokemonNveces(p.getPokemon(), (int) (p.getProbAparicion() * 100));

				}

			}

		}

	}

	// Método para solicitar el ArrayList de habitats
	public ArrayList<Habitat> SolicitarHabitat() {
		return M.EntregarHabitats();
	}

	// Método para calcular el pokemon Random
	public pokemon EntregarPokemonRandom(Habitat h) {
		Random random = new Random();
		int posicion = random.nextInt(100);
		return h.ConseguirArregloPokemon()[posicion];
	}

	// Método generado para guardar los pokémons capturados por el jugador
	public void AlmacenarPokemonCapturado(pokemon capturado) {
		pokemon copia = new pokemon(capturado);
		M.getJugador().getListaPokemon().add(copia);
	}

	// Método que entrega la información de los pokemones del jugador
	public ArrayList<pokemon> SolicitarEquipo() {
		return M.getJugador().getListaPokemon();

	}

	// Método para intercambiar la posición de los pokemon soleccionado en el equipo
	public boolean intercambio(String opción) {

		boolean funciona = false;

		try {

			ArrayList<pokemon> equipo = M.getJugador().getListaPokemon();
			String[] partes = opción.split(",");

			pokemon primero = equipo.get(Integer.valueOf(partes[0]) - 1);
			pokemon segundo = equipo.get(Integer.valueOf(partes[1]) - 1);
			pokemon auxiliar = primero;

			equipo.set(Integer.valueOf(partes[0]) - 1, segundo);
			equipo.set(Integer.valueOf(partes[1]) - 1, auxiliar);

			funciona = true;

		} catch (Exception e) {
		}

		return funciona;
	}

	// ---------------------------------------------------------------------------------

	// Método empleado para crear y guardar un Gymnasio al leer su línea
	// correspondiente
	public void CrearGimnasio(String linea) {

		String[] partes = linea.split(";");

		gimnacios nuevoGimnasio = new gimnacios(partes[0], partes[1], partes[2]);

		for (int a = 0; a < Integer.valueOf(partes[3]); a++) {

			nuevoGimnasio.GuardarPokemon(BuscarPokemon(partes[4 + a]));

		}

		M.GuardarGimnasio(nuevoGimnasio);

	}

	// Método para buscar el pokemon que se pide y devolver una copia
	public pokemon BuscarPokemon(String nombrePokemon) {

		for (pokedex p : M.getP()) {

			if (p.getPokemon().getNombre().equals(nombrePokemon)) {
				return new pokemon(p.getPokemon());
			}

		}

		return null;

	}

	// Método para solicitar jugador
	public jugador solicitarJugador() {
		return M.getJugador();
	}

	// Método generado para solcitar la lista completa de los gimnasios
	public ArrayList<gimnacios> SolicitarGimnasios() {
		return M.EntregarGimnasios();
	}

	// ---------------------para probar los txt--------------------- depue se borra
	public memoria entregarM() {
		return M;
	}

}