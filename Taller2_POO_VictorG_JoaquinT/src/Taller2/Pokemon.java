package Taller2;

public class Pokemon {

	// Declarar atributos
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
	public Pokemon(String nombre, double vida, double ataque, double defensa, double ataqueEspecial,
			double defensaEspecial, double velocidad, String tipo) {

		try {

			if (nombre == null) {
				nombre = "PokemonDesconocido";
			}

			if (tipo == null) {
				tipo = "Normal";
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

			this.nombre = nombre;
			this.vida = vida;
			this.vidaMaxima = vida;
			this.ataque = ataque;
			this.defensa = defensa;
			this.ataqueEspecial = ataqueEspecial;
			this.defensaEspecial = defensaEspecial;
			this.velocidad = velocidad;
			this.tipo = tipo;

		} catch (Exception e) {

			System.out.println("Error al crear Pokémon: " + e.getMessage());

			this.nombre = "PokemonDesconocido";
			this.vida = 0;
			this.vidaMaxima = 0;
			this.ataque = 0;
			this.defensa = 0;
			this.ataqueEspecial = 0;
			this.defensaEspecial = 0;
			this.velocidad = 0;
			this.tipo = "Normal";

		}

	}

	/*
	 * Generar otro constructor para que los Pokémon que estén en el equipo del
	 * jugador puedan ser editados sin modificar ningún otro Pokémon, desligando la
	 * referencia de Pokédex.
	 */
	public Pokemon(Pokemon copiado) {

		try {

			if (copiado == null) {

				this.nombre = "PokemonDesconocido";
				this.vida = 0;
				this.vidaMaxima = 0;
				this.ataque = 0;
				this.defensa = 0;
				this.ataqueEspecial = 0;
				this.defensaEspecial = 0;
				this.velocidad = 0;
				this.tipo = "Normal";

				return;
			}

			this.nombre = copiado.nombre;
			this.ataque = copiado.ataque;
			this.ataqueEspecial = copiado.ataqueEspecial;
			this.defensa = copiado.defensa;
			this.defensaEspecial = copiado.defensaEspecial;
			this.tipo = copiado.tipo;
			this.velocidad = copiado.velocidad;
			this.vida = copiado.vida;
			this.vidaMaxima = copiado.vidaMaxima;

		} catch (Exception e) {

			System.out.println("Error al copiar Pokémon: " + e.getMessage());

			this.nombre = "PokemonDesconocido";
			this.vida = 0;
			this.vidaMaxima = 0;
			this.ataque = 0;
			this.defensa = 0;
			this.ataqueEspecial = 0;
			this.defensaEspecial = 0;
			this.velocidad = 0;
			this.tipo = "Normal";

		}

	}

	// Método para entregar el nombre del Pokémon
	public String getNombre() {
		return nombre;
	}

	// Método para entregar el tipo del Pokémon
	public String getTipo() {
		return tipo;
	}

	// Método para entregar la vida actual del Pokémon
	public double getVida() {
		return vida;
	}

	// Setter para modificar la vida
	public void setVida(double vida) {

		try {

			if (vida < 0) {
				this.vida = 0;
				return;
			}

			if (vida > vidaMaxima) {
				this.vida = vidaMaxima;
				return;
			}

			this.vida = vida;

		} catch (Exception e) {
			System.out.println("Error al modificar la vida del Pokémon: " + e.getMessage());
			this.vida = 0;
		}

	}

	// Método que sumará todas las estadísticas
	public double statsTotales() {

		try {

			return this.ataque + this.ataqueEspecial + this.defensa + this.defensaEspecial + this.velocidad + this.vida;

		} catch (Exception e) {
			System.out.println("Error al calcular estadísticas totales del Pokémon: " + e.getMessage());
			return 0;
		}

	}

	// Método para verificar el estado del Pokémon
	public String estado() {

		try {

			if (vida <= 0) {
				return "Debilitado";
			}

			return "Vivo";

		} catch (Exception e) {
			System.out.println("Error al verificar estado del Pokémon: " + e.getMessage());
			return "Debilitado";
		}

	}

	// Método para verificar si el Pokémon está vivo
	public boolean estaVivo() {

		try {

			return estado().equals("Vivo");

		} catch (Exception e) {
			System.out.println("Error al verificar si el Pokémon está vivo: " + e.getMessage());
			return false;
		}

	}

	// Método para verificar si el Pokémon está debilitado
	public boolean estaDebilitado() {

		try {

			return estado().equals("Debilitado");

		} catch (Exception e) {
			System.out.println("Error al verificar si el Pokémon está debilitado: " + e.getMessage());
			return true;
		}

	}

	// Curar Pokémon
	public void curar() {

		try {

			this.vida = this.vidaMaxima;

		} catch (Exception e) {
			System.out.println("Error al curar Pokémon: " + e.getMessage());
		}

	}

	// Método para debilitar Pokémon
	public void debilitar() {

		try {

			this.vida = 0;

		} catch (Exception e) {
			System.out.println("Error al debilitar Pokémon: " + e.getMessage());
		}

	}

	// Entregar información del Pokémon
	@Override
	public String toString() {
		return getNombre() + "|" + getTipo() + "|Stats totales: " + statsTotales();
	}

}