package Taller2;

import java.util.ArrayList;

public class AltosMandos {

	// N°AltoMando;Nombre;Pokemons....
	private String nAltomando, nombre;
	private ArrayList<pokemon> pokemons;

	public AltosMandos(String nAltomando, String nombre) {
		this.nAltomando = nAltomando;
		this.nombre = nombre;
		pokemons = new ArrayList<pokemon>();
	}

	// Método para rellenar la ArrayList del alto mando
	public void rellenarArray(pokemon p) {
		pokemons.add(p);
	}

	@Override
	public String toString() {
		return "AltosMandos [nAltomando=" + nAltomando + ", nombre=" + nombre + ", pokemons=" + pokemons + "]";
	}

}
