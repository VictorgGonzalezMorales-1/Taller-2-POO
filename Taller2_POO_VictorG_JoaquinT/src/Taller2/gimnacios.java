package Taller2;

//Importar librerias necesarias para el funcionamiento
import java.util.ArrayList;

public class gimnacios {
	
	//N°Gimnasio;Lider;Estado;cantPokemons;Pokemons....
	
	//Declarar atributos
	private String numeroGimnasio, lider, estado;
	private ArrayList<pokemon> p;
	
	//Generar Constructor
	public gimnacios(String numeroGimnasio, String lider, String estado) {

		this.numeroGimnasio = numeroGimnasio;
		this.lider = lider;
		this.estado = estado;
		this.p = new ArrayList<pokemon>();
	}
	
	//Método para guardar el pokemon ingresado en el ArrayList
	public void GuardarPokemon(pokemon pokemon) {
		p.add(pokemon);
	}

	@Override
	public String toString() {
		return this.numeroGimnasio+") " + this.lider + " - Estado: " + this.estado;
	}

}
