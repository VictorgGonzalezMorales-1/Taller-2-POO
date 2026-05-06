package Taller2;

import java.util.ArrayList;

public class Batalla {

	// Declarar atributos

	boolean combateFinalizado = false;
	boolean jugadorVivo = true;
	boolean oponeteVivo = true;

	private jugador jugador;
	private gimnacios oponente;

	private pokemon pokemonJugador;
	private pokemon pokemonOponente;

	// Método para guardar el jugador en el atributo
	public void otorgarJugador(jugador j) {
		this.jugador = j;
	}

	public jugador getJugador() {
		return jugador;
	}

	// Método para guardar al oponente en el atributo
	public void otorgarOponente(gimnacios g) {
		this.oponente = g;
	}

	public gimnacios getOponente() {
		return oponente;
	}

	// Método generado para rellenar los pokemones de los participantes con el
	// primero vivo
	public void distribucionInicial() {

		// Jugador
		for (pokemon p : jugador.getListaPokemon()) {

			if (p.estado().equals("Vivo")) {
				pokemonJugador = p;
				break;
			}

		}

		// Oponente
		for (pokemon p : oponente.entregarPokemon()) {

			if (p.estado().equals("Vivo")) {
				pokemonOponente = p;
				break;
			}

		}

	}

	// Método para actualizar el pokemon del oponente en caso de que pierda
	public void actualizarPokemonOponente() {

		for (pokemon p : oponente.entregarPokemon()) {

			if (p.estado().equals("Vivo")) {
				pokemonOponente = p;
				break;
			}

		}

	}

	// Método para actualizar el pokemon del jugador en caso de que pierda
	public void actualizarPokemonJugador() {

		for (pokemon p : jugador.getListaPokemon()) {

			if (p.estado().equals("Vivo")) {
				pokemonJugador = p;
				break;
			}

		}

	}

	public pokemon getPokemonJugador() {
		return pokemonJugador;
	}

	public pokemon getPokemonOponente() {
		return pokemonOponente;
	}

	public void setCombateFinalizado(boolean combateFinalizado) {
		this.combateFinalizado = combateFinalizado;
	}

	// Método para cambiar el pokemon que se está usando
	public void cambiarPokemonJugador(pokemon p) {
		pokemonJugador = p;
	}

	// Método para ver si el jugador o el oponente pierden
	public void revisarVivos() {

		this.jugadorVivo = false;
		this.oponeteVivo = false;

		// Jugador
		for (pokemon p : jugador.getListaPokemon()) {

			if (p.estado().equals("Vivo")) {
				this.jugadorVivo = true;
				break;
			}

		}

		// Oponente
		for (pokemon p : oponente.entregarPokemon()) {

			if (p.estado().equals("Vivo")) {
				this.oponeteVivo = true;
				break;
			}

		}

		if (this.jugadorVivo == false || this.oponeteVivo == false) {
			combateFinalizado = true;
		}

	}

}
