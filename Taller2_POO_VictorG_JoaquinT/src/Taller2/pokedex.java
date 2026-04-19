package Taller2;

public class pokedex {

	// Declarar Atributos
	private String habitat;
	private double probAparicion;
	private pokemon pokemon;

	// Generar Constructor
	public pokedex(String pokemon, String habitat, double porcentajeAparicion, double vida, double ataque,
			double defensa, double ataqueEspecial, double defensaEspecial, double velocidad, String Tipo) {

		this.habitat = habitat;
		this.probAparicion = porcentajeAparicion;
		this.pokemon = new pokemon(pokemon, vida, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad, Tipo);

	}

	// Getters y Setters

	// Habitat
	public String getHabitat() {
		return habitat;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}

	// Probabilidad de aparición
	public double getProbAparicion() {
		return probAparicion;
	}

	public void setProbAparicion(double probAparicion) {
		this.probAparicion = probAparicion;
	}

	// Pokemon
	public pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(pokemon pokemon) {
		this.pokemon = pokemon;
	}

}
