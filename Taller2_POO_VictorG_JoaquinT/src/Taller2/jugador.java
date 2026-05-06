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
		this.derrotados = "";
		ArrayPokemon = new ArrayList<pokemon>();
		texto = new ArrayList<String>();
	}

	// Generar getters y Setters necesarios

	// Entregar ArrayList del equipo
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

	// Método para guardar el gimnasio derrotado
	public void actualizarDerrotados(String nombreLider) {
		if (nombreLider != null) {
			derrotados += nombreLider + ",";
		}

	}

	public void imprimirJugadorMedallas() {
		System.out.println(jugador + " - Medallas: " + derrotados);
	}

}
