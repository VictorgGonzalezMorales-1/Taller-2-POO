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
		sobreescribirTexto("Registros.txt", M.getJugador().textoSobreescribir());

	}

	public void sobreescribirTexto(String archivo, ArrayList<String> nuevoTexto) {

		BufferedWriter Escritor;

		try {

			Escritor = new BufferedWriter(new FileWriter(archivo, false));

			for (String s : nuevoTexto) {

				Escritor.write(s);
				Escritor.newLine();

			}

			Escritor.close();

		} catch (IOException e) {
			e.printStackTrace();
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

	// Método que entrega la información de los pokemones del jugador
	public String revisarEquipo() {
		return M.getJugador().revisarEquipo();

	}

	// Método generado para buscar los pokemones de una zona en específico
	public ArrayList<pokedex> buscarPokemon(String habitat) {

		ArrayList<pokedex> buscar = M.getP();
		ArrayList<pokedex> buscados = new ArrayList<>();

		for (pokedex p : buscar) {

			if (p.getHabitat().equals(habitat)) {
				buscados.add(p);

			}

		}

		return buscados;

	}

	// Método generado para buscar un pokemon al azar
	public pokemon generarPokemonAzar(String habitat) {

		ArrayList<pokedex> pokemones = buscarPokemon(habitat);
		pokemon[] buscar = new pokemon[100];

		int cantidad = 0;

		for (pokedex p : pokemones) {

			int probabilidad = (int) (p.getProbAparicion() * 100);

			for (int a = 0; a < probabilidad; a++) {

				buscar[cantidad] = p.getPokemon();
				cantidad++;

			}

		}

		Random random = new Random();

		int posición = random.nextInt(100);

		return buscar[posición];

	}

	// Método generado para guardar los pokémons captirados por el jugador
	public void almacenarPokemonCapturado(pokemon capturado) {
		M.getJugador().getListaPokemon().add(capturado);
	}

}
