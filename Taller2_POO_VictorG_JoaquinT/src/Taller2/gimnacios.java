package Taller2;

//Importar librerias necesarias para el funcionamiento
import java.util.ArrayList;

public class gimnacios {

	// N°Gimnasio;Lider;Estado;cantPokemons;Pokemons....

	// Declarar atributos
	private String numeroGimnasio, lider, estado;
	private ArrayList<pokemon> p;

	// Generar Constructor
	public gimnacios(String numeroGimnasio, String lider, String estado) {

		this.numeroGimnasio = numeroGimnasio;
		this.lider = lider;
		this.estado = estado;
		this.p = new ArrayList<pokemon>();
	}

	// Método para guardar el pokemon ingresado en el ArrayList
	public void GuardarPokemon(pokemon pokemon) {
		p.add(pokemon);
	}

	// Método para entregar el numero del gimnasio
	public String EntregarNumeroGimnasio() {
		return this.numeroGimnasio;
	}

	// Método para entregar el estado del gimnasio
	public String EntregarEstadoGimnasio() {
		return this.estado;
	}

	// Método para entregar el nombre del gimnasio
	public String EntregarLiderGimnasio() {
		return this.lider;
	}

	@Override
	public String toString() {
		return this.numeroGimnasio + ") " + this.lider + " - Estado: " + this.estado;
	}

}
