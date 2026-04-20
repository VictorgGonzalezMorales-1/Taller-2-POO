package Taller2;

public class pokemon {

	// Declarar Atributos
	private String nombre;
	private double vida;
	private double vidaMaxima;
	private double ataque;
	private double defensa;
	private double ataqueEspecial;
	private double defensaEspecial;
	private double velocidad;
	private String tipo;

	// Generar Constructor
	public pokemon(String nombre, double vida, double ataque, double defensa, double ataqueEspecial,
			double defensaEspecial, double velocidad, String tipo) {

		this.nombre = nombre;
		this.vida = vida;
		this.vidaMaxima = vida;
		this.ataque = ataque;
		this.defensa = defensa;
		this.ataqueEspecial = ataqueEspecial;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
		this.tipo = tipo;

	}

	/*
	 * Generar otro construtor para que los pokemon que estén en el equipo del
	 * jugador puedan ser editados sin modificar ningún otro pokemon, desligadno la
	 * referecia de pokedex
	 */
	public pokemon(pokemon copiado) {

		this.nombre = copiado.nombre;
		this.ataque = copiado.ataque;
		this.ataqueEspecial = copiado.ataqueEspecial;
		this.defensa = copiado.defensa;
		this.defensaEspecial = copiado.defensaEspecial;
		this.tipo = copiado.tipo;
		this.velocidad = copiado.velocidad;
		this.vida = copiado.vida;
		this.vidaMaxima = copiado.vidaMaxima;

	}

	// Método para verificar el estado del pokémon
	public String estado() {

		if (vida <= 0) {
			return "Debilitado";
		}

		return "Vivo";
	}

	// Nombre
	public String getNombre() {
		return nombre;
	}

	public String toString() {
		return nombre + "; " + estado();
	}

	// Método que sumará todas las estadisticas
	public double sumaEstads() {

		return this.ataque + this.ataqueEspecial + this.defensa + this.defensaEspecial + this.velocidad + this.vida;

	}

	// Curar pokemon
	public void curar() {

		this.vida = this.vidaMaxima;

	}

	//Setter para modificar la vida
	public void setVida(double vida) {
		this.vida = vida;
	}

}
