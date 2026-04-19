package Taller2;

public class pokemon {

	// Declarar Atributos
	private String nombre;
	private double vida;
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
		this.ataque = ataque;
		this.defensa = defensa;
		this.ataqueEspecial = ataqueEspecial;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
		this.tipo = tipo;
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

}
