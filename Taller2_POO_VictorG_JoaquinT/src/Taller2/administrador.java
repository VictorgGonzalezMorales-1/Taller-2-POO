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

		BufferedWriter Escritor;

		try {

			Escritor = new BufferedWriter(new FileWriter(archivo, false));

			for (String s : M.getJugador().textoSobreescribir()) {

				Escritor.write(s);
				Escritor.newLine();

			}

			Escritor.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

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

	// Método que entrega la información de los pokemones del jugador
	public ArrayList<pokemon> SolicitarEquipo() {
		return M.getJugador().getListaPokemon();

	}

	// método el cual entregará la lista de los pokemon del prota
	public String equipo() {

		ArrayList<pokemon> equipo = M.getJugador().getListaPokemon();
		int contador = 1;
		String texto = "";

		for (pokemon p : equipo) {

			texto += (contador++) + ") " + p.toString() + "\n";

		}

		return texto;
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

	// Método para curar todo el equipo del jugador
	public void curarPokemon() {

		ArrayList<pokemon> equipo = M.getJugador().getListaPokemon();

		int contador = 1;

		for (pokemon p : equipo) {

			if (contador < 7) {
				p.curar();
				contador++;
			} else {
				break;
			}

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

}