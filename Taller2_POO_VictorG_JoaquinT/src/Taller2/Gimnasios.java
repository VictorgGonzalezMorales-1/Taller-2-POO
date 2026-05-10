package Taller2;

// Importar librerías necesarias para el funcionamiento
import java.util.ArrayList;

public class Gimnasios {

	// N°Gimnasio;Lider;Estado;cantPokemons;Pokemons....

	// Declarar atributos
	private String numeroGimnasio, lider, estado;
	private ArrayList<Pokemon> p;

	// Generar Constructor
	public Gimnasios(String numeroGimnasio, String lider, String estado) {

		this.numeroGimnasio = numeroGimnasio;
		this.lider = lider;
		this.estado = estado;
		this.p = new ArrayList<Pokemon>();
	}

	// Método para guardar el pokemon ingresado en el ArrayList
	public void guardarPokemon(Pokemon pokemon) {

		try {

			if (pokemon == null) {
				System.out.println("No se puede guardar un Pokémon vacío en el gimnasio.");
				return;
			}

			p.add(pokemon);

		} catch (Exception e) {
			System.out.println("Error al guardar el Pokémon en el gimnasio: " + e.getMessage());
		}
	}

	// Método para entregar el número del gimnasio
	public String entregarNumeroGimnasio() {
		return this.numeroGimnasio;
	}

	// Método para entregar el estado del gimnasio
	public String entregarEstadoGimnasio() {
		return this.estado;
	}

	// Método para entregar el nombre del líder del gimnasio
	public String entregarLiderGimnasio() {
		return this.lider;
	}

	// Método para entregar el ArrayList de pokemon
	public ArrayList<Pokemon> entregarPokemon() {
		return this.p;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {

		try {

			if (estado == null) {
				System.out.println("El estado del gimnasio no puede ser nulo.");
				return;
			}

			if (!estado.equals("Sin derrotar") && !estado.equals("Derrotado")) {
				System.out.println("Estado inválido para el gimnasio.");
				return;
			}

			this.estado = estado;

		} catch (Exception e) {
			System.out.println("Error al modificar el estado del gimnasio: " + e.getMessage());
		}
	}

	@Override
	public String toString() {
		return this.numeroGimnasio + ") " + this.lider + " - Estado: " + this.estado;
	}

}