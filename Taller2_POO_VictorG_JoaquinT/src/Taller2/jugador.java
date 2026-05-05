package Taller2;

//Importar librerias necesarias para el funcionamiento
import java.util.ArrayList;

public class jugador {

	// Declarar Atributos
	private String jugador;
	private String derrotados;
	private ArrayList<pokemon> ArrayPokemon;
	private ArrayList<String> texto;

	// Generar Constructor
	public jugador(String jugador) {
		this.jugador = jugador;
		this.derrotados = null;
		ArrayPokemon = new ArrayList<pokemon>();
		texto = new ArrayList<String>();
	}

	// Generar getters y Setters necesarios
	
	//Entregar ArrayList del equipo
	public ArrayList<pokemon> getListaPokemon() {
		return ArrayPokemon;
	}

	// Nombre jugador
	public String getJugador() {
		return jugador;
	}

	
	

	// Derrotados
	public String getDerrotados() {
		return derrotados;
	}

	public void setDerrotados(String derrotados) {
		this.derrotados = derrotados;
	}

	// Generar Texto para sobreescribirlo
	public ArrayList<String> textoSobreescribir() {

		texto.clear();

		texto.add(this.jugador + ";" + this.getDerrotados());

		for (pokemon p : ArrayPokemon) {
			texto.add(p.toString());
		}

		return texto;

	}

}
