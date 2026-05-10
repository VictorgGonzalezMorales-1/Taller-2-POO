package Taller2;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Administrador {

	public static Memoria M = new Memoria();

	public static void P(String t) {
		System.out.println(t);
	}

	public void crearJugador(String nombre) {

		try {

			if (nombre == null || nombre.length() == 0) {
				System.out.println("El nombre del jugador no puede estar vacío.");
				return;
			}

			Jugador prota = new Jugador(nombre);
			M.guardarJugador(prota);

		} catch (Exception e) {
			System.out.println("Error al crear jugador: " + e.getMessage());
		}
	}

	public void guardar(String archivo) {

		ArrayList<String> txt = null;

		try {

			if (archivo == null) {
				P("No se reconoce el archivo que se desea guardar.");
				return;
			}

			if (archivo.equals("Registros.txt")) {
				M.entregarTxtJugador();
				txt = M.getTxtJugador();
			}

			if (archivo.equals("Gimnasios.txt")) {
				M.entregarTxtGimnasio();
				txt = M.getTxtGimnasio();
			}

			if (txt == null) {
				P("No se reconoce el archivo que se desea guardar: " + archivo);
				return;
			}

			BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, false));

			for (String s : txt) {

				if (s != null) {
					escritor.write(s);
					escritor.newLine();
				}
			}

			escritor.close();

		} catch (IOException e) {
			P("Error al guardar archivo " + archivo + ": " + e.getMessage());
		} catch (Exception e) {
			P("Error inesperado al guardar archivo: " + e.getMessage());
		}
	}

	public void resetearGimnasios() {

		try {
			M.entregarGimnasios().clear();
			M.setTxtGimnasioDefault();
		} catch (Exception e) {
			P("Error al resetear gimnasios: " + e.getMessage());
		}
	}

	public void rellenarPokemon(String texto, String estado) {

		try {

			if (texto == null || estado == null) {
				return;
			}

			ArrayList<Pokedex> datos = M.getP();

			for (Pokedex po : datos) {

				if (po != null && po.getPokemon() != null && po.getPokemon().getNombre().equals(texto)) {

					Pokemon copia = new Pokemon(po.getPokemon());

					if (estado.equals("Debilitado")) {
						copia.setVida(0);
					}

					almacenarPokemonCapturado(copia);
					return;
				}
			}

		} catch (Exception e) {
			P("Error al rellenar Pokémon del jugador: " + e.getMessage());
		}
	}

	public void crearPokedex(String linea) {

		try {

			if (linea == null || linea.length() == 0) {
				return;
			}

			String[] s = linea.split(";");

			if (s.length == 10) {

				Pokedex p = new Pokedex(s[0], s[1], Double.valueOf(s[2]), Double.valueOf(s[3]),
						Double.valueOf(s[4]), Double.valueOf(s[5]), Double.valueOf(s[6]), Double.valueOf(s[7]),
						Double.valueOf(s[8]), s[9]);

				M.guardarPokedex(p);

			} else {
				P("Línea inválida en Pokedex.txt: " + linea);
			}

		} catch (NumberFormatException e) {
			P("Error numérico en Pokedex.txt: " + linea);
		} catch (Exception e) {
			P("Error al crear Pokémon desde línea: " + linea);
		}
	}

	public void crearHabitat(String nombreHabitat) {

		try {

			if (nombreHabitat == null || nombreHabitat.length() == 0) {
				return;
			}

			Habitat habitatCreado = new Habitat(nombreHabitat);
			M.guardarHabitat(habitatCreado);

		} catch (Exception e) {
			P("Error al crear hábitat: " + e.getMessage());
		}
	}

	public void rellenarHabitat() {

		try {

			for (Habitat h : M.entregarHabitats()) {

				if (h != null) {

					for (Pokedex p : M.getP()) {

						if (p != null && p.getPokemon() != null) {

							if (h.getNombreHabitat().equals(p.getHabitat())) {
								h.rellenarArregloConPokemonNveces(p.getPokemon(),
										(int) (p.getProbAparicion() * 100));
							}
						}
					}
				}
			}

		} catch (Exception e) {
			P("Error al rellenar hábitats: " + e.getMessage());
		}
	}

	public ArrayList<Habitat> solicitarHabitat() {
		return M.entregarHabitats();
	}

	public Pokemon entregarPokemonRandom(Habitat h) {

		try {

			if (h == null || h.tienePokemonsDisponibles() == false) {
				return null;
			}

			Random random = new Random();
			Pokemon[] pokemones = h.conseguirArregloPokemon();

			for (int intentos = 0; intentos < 100; intentos++) {

				int posicion = random.nextInt(100);

				if (pokemones[posicion] != null) {
					return pokemones[posicion];
				}
			}

		} catch (Exception e) {
			P("Error al generar Pokémon aleatorio: " + e.getMessage());
		}

		return null;
	}

	public boolean almacenarPokemonCapturado(Pokemon capturado) {

		try {

			if (capturado == null || M.getJugador() == null) {
				return false;
			}

			ArrayList<Pokemon> equipo = M.getJugador().getListaPokemon();

			for (Pokemon p : equipo) {

				if (p != null && p.getNombre().equals(capturado.getNombre())) {
					return false;
				}
			}

			Pokemon copia = new Pokemon(capturado);
			equipo.add(copia);

			return true;

		} catch (Exception e) {
			P("Error al almacenar Pokémon capturado: " + e.getMessage());
			return false;
		}
	}

	public ArrayList<Pokemon> solicitarEquipo() {

		try {

			if (M.getJugador() == null) {
				return new ArrayList<Pokemon>();
			}

			return M.getJugador().getListaPokemon();

		} catch (Exception e) {
			P("Error al solicitar equipo: " + e.getMessage());
			return new ArrayList<Pokemon>();
		}
	}

	public boolean intercambio(String opcion) {

		try {

			ArrayList<Pokemon> equipo = M.getJugador().getListaPokemon();
			String[] partes = opcion.split(",");

			if (partes.length != 2) {
				return false;
			}

			int pos1 = Integer.valueOf(partes[0]) - 1;
			int pos2 = Integer.valueOf(partes[1]) - 1;

			if (pos1 < 0 || pos1 >= equipo.size() || pos2 < 0 || pos2 >= equipo.size()) {
				return false;
			}

			Pokemon auxiliar = equipo.get(pos1);
			equipo.set(pos1, equipo.get(pos2));
			equipo.set(pos2, auxiliar);

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public void crearGimnasio(String linea) {

		try {

			String[] partes = linea.split(";");

			if (partes.length < 4) {
				P("Línea inválida en Gimnasios.txt: " + linea);
				return;
			}

			int cantidad = Integer.valueOf(partes[3]);

			if (partes.length < 4 + cantidad) {
				P("Cantidad de Pokémon no coincide en Gimnasios.txt: " + linea);
				return;
			}

			Gimnasios nuevoGimnasio = new Gimnasios(partes[0], partes[1], partes[2]);

			for (int a = 0; a < cantidad; a++) {

				Pokemon encontrado = buscarPokemon(partes[4 + a]);

				if (encontrado != null) {
					nuevoGimnasio.guardarPokemon(encontrado);
				}
			}

			M.guardarGimnasio(nuevoGimnasio);

		} catch (Exception e) {
			P("Error al crear gimnasio desde línea: " + linea);
		}
	}

	public Pokemon buscarPokemon(String nombrePokemon) {

		try {

			for (Pokedex p : M.getP()) {

				if (p != null && p.getPokemon() != null && p.getPokemon().getNombre().equals(nombrePokemon)) {
					return new Pokemon(p.getPokemon());
				}
			}

		} catch (Exception e) {
			P("Error al buscar Pokémon: " + e.getMessage());
		}

		return null;
	}

	public Jugador solicitarJugador() {
		return M.getJugador();
	}

	public ArrayList<Gimnasios> solicitarGimnasios() {
		return M.entregarGimnasios();
	}

	public Memoria entregarM() {
		return M;
	}

	public void crearAltomando(String linea) {

		try {

			String[] partes = linea.split(";");

			if (partes.length < 8) {
				P("Línea inválida en Alto Mando.txt: " + linea);
				return;
			}

			AltosMandos altoMando = new AltosMandos(partes[0], partes[1]);

			for (int a = 2; a < partes.length; a++) {

				Pokemon encontrado = buscarPokemon(partes[a]);

				if (encontrado != null) {
					altoMando.rellenarArray(encontrado);
				}
			}

			M.guardarAltoMando(altoMando);

		} catch (Exception e) {
			P("Error al crear Alto Mando desde línea: " + linea);
		}
	}

	public boolean solicitarPermisoAltosMandos() {
		return M.permitirPeleaAltosMandos();
	}

	public ArrayList<AltosMandos> solicitarArrayAltosMandos() {
		return M.entregarAltosMandos();
	}
}