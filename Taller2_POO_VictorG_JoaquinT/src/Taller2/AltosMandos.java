package Taller2;

import java.util.ArrayList;

public class AltosMandos {

	// N°AltoMando;Nombre;Pokemons....

	// Declarar atributos
	private String nAltomando, nombre;
	private ArrayList<Pokemon> pokemons;

	// Generar Constructor
	public AltosMandos(String nAltomando, String nombre) {
		this.nAltomando = nAltomando;
		this.nombre = nombre;
		pokemons = new ArrayList<Pokemon>();
	}

	// Método para rellenar la ArrayList del Alto Mando
	public void rellenarArray(Pokemon p) {

		try {

			if (p == null) {
				System.out.println("No se puede agregar un Pokémon vacío al Alto Mando.");
				return;
			}

			pokemons.add(p);

		} catch (Exception e) {
			System.out.println("Error al agregar Pokémon al Alto Mando: " + e.getMessage());
		}
	}

	public String getNAltomando() {
		return nAltomando;
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Pokemon> getPokemons() {
		return pokemons;
	}

	public void curarPokemons() {

		try {

			if (pokemons == null || pokemons.size() == 0) {
				System.out.println("El Alto Mando no tiene Pokémon para curar.");
				return;
			}

			for (Pokemon p : pokemons) {

				if (p != null) {
					p.curar();
				}
			}

		} catch (Exception e) {
			System.out.println("Error al curar los Pokémon del Alto Mando: " + e.getMessage());
		}
	}

	public boolean tienePokemonVivo() {

		try {

			if (pokemons == null || pokemons.size() == 0) {
				return false;
			}

			for (Pokemon p : pokemons) {

				if (p != null && p.estado().equals("Vivo")) {
					return true;
				}
			}

			return false;

		} catch (Exception e) {
			System.out.println("Error al verificar Pokémon vivos del Alto Mando: " + e.getMessage());
			return false;
		}
	}

	@Override
	public String toString() {
		return "AltosMandos [nAltomando=" + nAltomando + ", nombre=" + nombre + ", pokemons=" + pokemons + "]";
	}

}