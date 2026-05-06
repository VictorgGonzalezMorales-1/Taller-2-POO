package Taller2;

//Importar librerias necesarias para el funcionamiento
import java.util.ArrayList;
import java.util.Arrays;

public class Habitat {

	// Declarar atributos
	private String nombreHabitat;
	private pokemon[] pokemones;

	// Generar constructor
	public Habitat(String nombreHabitat) {
		this.nombreHabitat = nombreHabitat;
		pokemones = new pokemon[100];
	}

	// Para conseguir el nombre del Habitat
	public String getNombreHabitat() {
		return nombreHabitat;
	}

	// Metodo para rellenar con pokemons
	public void RellenarArregloConPokemonNveces(pokemon p, int veces) {

		int contador = 0;

		for (int a = 0; a < pokemones.length; a++) {

			if (pokemones[a] == null && contador < veces) {

				pokemones[a] = new pokemon(p);
				contador++;

			}

		}

	}

	// Conseguir Arreglo de pokemons
	public pokemon[] ConseguirArregloPokemon() {
		return this.pokemones;
	}

	@Override
	public String toString() {
		return "Habitat [nombreHabitat=" + nombreHabitat + ", pokemones=" + Arrays.toString(pokemones) + "]";
	}

}