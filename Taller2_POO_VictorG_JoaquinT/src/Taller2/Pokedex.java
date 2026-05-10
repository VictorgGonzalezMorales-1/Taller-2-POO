package Taller2;

public class Pokedex {

	// Declarar atributos
	private String habitat;
	private double probAparicion;
	private Pokemon pokemon;

	// Generar Constructor
	public Pokedex(String nombrePokemon, String habitat, double porcentajeAparicion, double vida, double ataque,
			double defensa, double ataqueEspecial, double defensaEspecial, double velocidad, String tipo) {

		try {

			if (nombrePokemon == null) {
				nombrePokemon = "PokemonDesconocido";
			}

			if (habitat == null) {
				habitat = "none";
			}

			if (tipo == null) {
				tipo = "Normal";
			}

			if (porcentajeAparicion < 0) {
				porcentajeAparicion = 0;
			}

			if (vida < 0) {
				vida = 0;
			}

			if (ataque < 0) {
				ataque = 0;
			}

			if (defensa < 0) {
				defensa = 0;
			}

			if (ataqueEspecial < 0) {
				ataqueEspecial = 0;
			}

			if (defensaEspecial < 0) {
				defensaEspecial = 0;
			}

			if (velocidad < 0) {
				velocidad = 0;
			}

			this.habitat = habitat;
			this.probAparicion = porcentajeAparicion;
			this.pokemon = new Pokemon(nombrePokemon, vida, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad,
					tipo);

		} catch (Exception e) {

			System.out.println("Error al crear registro de Pokédex: " + e.getMessage());

			this.habitat = "none";
			this.probAparicion = 0;
			this.pokemon = new Pokemon("PokemonDesconocido", 0, 0, 0, 0, 0, 0, "Normal");

		}

	}

	// Habitat
	public String getHabitat() {
		return habitat;
	}

	public void setHabitat(String habitat) {

		try {

			if (habitat == null) {
				System.out.println("El hábitat no puede ser nulo.");
				return;
			}

			this.habitat = habitat;

		} catch (Exception e) {
			System.out.println("Error al modificar hábitat de Pokédex: " + e.getMessage());
		}

	}

	// Probabilidad de aparición
	public double getProbAparicion() {
		return probAparicion;
	}

	public void setProbAparicion(double probAparicion) {

		try {

			if (probAparicion < 0) {
				System.out.println("La probabilidad de aparición no puede ser negativa.");
				return;
			}

			this.probAparicion = probAparicion;

		} catch (Exception e) {
			System.out.println("Error al modificar probabilidad de aparición: " + e.getMessage());
		}

	}

	// Pokemon
	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {

		try {

			if (pokemon == null) {
				System.out.println("No se puede asignar un Pokémon vacío a la Pokédex.");
				return;
			}

			this.pokemon = pokemon;

		} catch (Exception e) {
			System.out.println("Error al modificar Pokémon de Pokédex: " + e.getMessage());
		}

	}

	// Método para verificar si el registro de Pokédex pertenece a un hábitat específico
	public boolean perteneceAlHabitat(String habitat) {

		try {

			if (habitat == null) {
				return false;
			}

			return this.habitat.equals(habitat);

		} catch (Exception e) {
			System.out.println("Error al verificar hábitat de Pokédex: " + e.getMessage());
			return false;
		}

	}

	@Override
	public String toString() {
		return "Pokedex [habitat=" + habitat + ", probAparicion=" + probAparicion + ", pokemon=" + pokemon + "]";
	}

}