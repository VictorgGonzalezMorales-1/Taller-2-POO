package Taller2;

// Importar librerías necesarias para el funcionamiento
import java.util.Arrays;

public class Habitat {

	// Declarar atributos
	private String nombreHabitat;
	private Pokemon[] pokemones;

	// Generar constructor
	public Habitat(String nombreHabitat) {
		this.nombreHabitat = nombreHabitat;
		pokemones = new Pokemon[100];
	}

	// Para conseguir el nombre del Habitat
	public String getNombreHabitat() {
		return nombreHabitat;
	}

	// Método para rellenar con Pokémon
	public void rellenarArregloConPokemonNveces(Pokemon p, int veces) {

		try {

			if (p == null) {
				System.out.println("No se puede agregar un Pokémon vacío al hábitat.");
				return;
			}

			if (veces <= 0) {
				return;
			}

			int contador = 0;

			for (int a = 0; a < pokemones.length; a++) {

				if (pokemones[a] == null && contador < veces) {

					pokemones[a] = new Pokemon(p);
					contador++;

				}

			}

			if (contador < veces) {
				System.out.println("No hay espacio suficiente en el hábitat para agregar todas las apariciones de "
						+ p.getNombre() + ".");
			}

		} catch (Exception e) {
			System.out.println("Error al rellenar el hábitat con Pokémon: " + e.getMessage());
		}

	}

	// Conseguir Arreglo de Pokémon
	public Pokemon[] conseguirArregloPokemon() {

		try {

			return this.pokemones;

		} catch (Exception e) {
			System.out.println("Error al conseguir el arreglo de Pokémon del hábitat: " + e.getMessage());
			return new Pokemon[100];
		}

	}

	public boolean tienePokemonsDisponibles() {

		try {

			for (int a = 0; a < pokemones.length; a++) {

				if (pokemones[a] != null) {
					return true;
				}

			}

			return false;

		} catch (Exception e) {
			System.out.println("Error al verificar Pokémon disponibles en el hábitat: " + e.getMessage());
			return false;
		}

	}

	@Override
	public String toString() {
		return "Habitat [nombreHabitat=" + nombreHabitat + ", pokemones=" + Arrays.toString(pokemones) + "]";
	}

}