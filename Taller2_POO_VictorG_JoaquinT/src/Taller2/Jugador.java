package Taller2;

// Importar librerías necesarias para el funcionamiento
import java.util.ArrayList;

public class Jugador {

	// Declarar atributos
	private String jugador;
	private String derrotados;
	private ArrayList<Pokemon> arrayPokemon;

	// Generar Constructor
	public Jugador(String jugador) {

		try {

			if (jugador == null) {
				this.jugador = "Jugador";
			} else {
				this.jugador = jugador;
			}

			this.derrotados = "none";
			arrayPokemon = new ArrayList<Pokemon>();

		} catch (Exception e) {
			System.out.println("Error al crear jugador: " + e.getMessage());
			this.jugador = "Jugador";
			this.derrotados = "none";
			arrayPokemon = new ArrayList<Pokemon>();
		}
	}

	// Entregar ArrayList del equipo
	public ArrayList<Pokemon> getListaPokemon() {
		return arrayPokemon;
	}

	// Nombre jugador
	public String getJugador() {
		return jugador;
	}

	// Derrotados
	public String getDerrotados() {
		return derrotados;
	}

	public void setDerrotados(String derrotados) {

		try {

			if (derrotados == null) {
				this.derrotados = "none";
				return;
			}

			if (derrotados.length() == 0) {
				this.derrotados = "none";
				return;
			}

			this.derrotados = derrotados;

		} catch (Exception e) {
			System.out.println("Error al modificar gimnasios derrotados: " + e.getMessage());
			this.derrotados = "none";
		}
	}

	// Método para guardar el gimnasio derrotado
	public void actualizarDerrotados(String nombreLider) {

		try {

			if (nombreLider != null) {

				if (derrotados.equals("none") || derrotados.length() == 0) {
					derrotados = nombreLider;
				} else {
					derrotados += "," + nombreLider;
				}

			}

		} catch (Exception e) {
			System.out.println("Error al actualizar gimnasios derrotados: " + e.getMessage());
		}
	}

	public void imprimirJugadorMedallas() {

		try {

			System.out.println(jugador + " - Medallas: " + derrotados);

		} catch (Exception e) {
			System.out.println("Error al imprimir medallas del jugador: " + e.getMessage());
		}
	}

	// Método para agregar un Pokémon al jugador
	public void agregarPokemon(Pokemon p) {

		try {

			if (p == null) {
				System.out.println("No se puede agregar un Pokémon vacío al jugador.");
				return;
			}

			arrayPokemon.add(p);

		} catch (Exception e) {
			System.out.println("Error al agregar Pokémon al jugador: " + e.getMessage());
		}
	}

	// Método para verificar si el jugador ya tiene un Pokémon capturado
	public boolean tienePokemon(String nombrePokemon) {

		try {

			if (nombrePokemon == null) {
				return false;
			}

			for (Pokemon p : arrayPokemon) {

				if (p != null && p.getNombre().equals(nombrePokemon)) {
					return true;
				}

			}

			return false;

		} catch (Exception e) {
			System.out.println("Error al verificar Pokémon del jugador: " + e.getMessage());
			return false;
		}
	}

	// Método para verificar si el jugador tiene al menos un Pokémon vivo en el equipo principal
	public boolean tienePokemonVivoEquipoPrincipal() {

		try {

			if (arrayPokemon == null || arrayPokemon.size() == 0) {
				return false;
			}

			int largo = 6;

			if (arrayPokemon.size() < 6) {
				largo = arrayPokemon.size();
			}

			for (int a = 0; a < largo; a++) {

				if (arrayPokemon.get(a) != null && arrayPokemon.get(a).estado().equals("Vivo")) {
					return true;
				}

			}

			return false;

		} catch (Exception e) {
			System.out.println("Error al verificar Pokémon vivos del equipo principal: " + e.getMessage());
			return false;
		}
	}

	// Método para curar todos los Pokémon del jugador
	public void curarPokemons() {

		try {

			if (arrayPokemon == null || arrayPokemon.size() == 0) {
				System.out.println("No tienes Pokémon para curar.");
				return;
			}

			for (Pokemon p : arrayPokemon) {

				if (p != null) {
					p.curar();
				}

			}

		} catch (Exception e) {
			System.out.println("Error al curar Pokémon del jugador: " + e.getMessage());
		}
	}

	@Override
	public String toString() {
		return "Jugador [jugador=" + jugador + ", derrotados=" + derrotados + ", pokemons=" + arrayPokemon + "]";
	}

}