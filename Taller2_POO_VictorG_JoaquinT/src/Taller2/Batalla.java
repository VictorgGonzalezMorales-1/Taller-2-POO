package Taller2;

import java.util.ArrayList;

public class Batalla {

	boolean combateFinalizado = false;
	boolean combateFinalizadoAltoMando = false;
	boolean jugadorVivo = true;
	boolean oponenteVivo = true;
	boolean altoMandoVivo = true;

	private Jugador jugador;
	private Gimnasios oponente;
	private AltosMandos altoMando;

	private Pokemon pokemonJugador;
	private Pokemon pokemonOponente;
	private Pokemon pokemonAltoMando;

	public void otorgarJugador(Jugador j) {

		try {

			if (j == null) {
				combateFinalizado = true;
				combateFinalizadoAltoMando = true;
				return;
			}

			this.jugador = j;

		} catch (Exception e) {
			System.out.println("Error al asignar jugador: " + e.getMessage());
		}
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void otorgarOponente(Gimnasios g) {

		try {

			if (g == null) {
				combateFinalizado = true;
				return;
			}

			this.oponente = g;

		} catch (Exception e) {
			System.out.println("Error al asignar gimnasio rival: " + e.getMessage());
		}
	}

	public void otorgarOponente(AltosMandos a) {

		try {

			if (a == null) {
				combateFinalizadoAltoMando = true;
				return;
			}

			this.altoMando = a;

		} catch (Exception e) {
			System.out.println("Error al asignar Alto Mando: " + e.getMessage());
		}
	}

	public Gimnasios getOponente() {
		return oponente;
	}

	public AltosMandos getAltoMando() {
		return altoMando;
	}

	public void distribucionInicial() {

		try {

			pokemonJugador = null;
			pokemonOponente = null;

			ArrayList<Pokemon> equipo = jugador.getListaPokemon();

			int largo = 6;

			if (equipo.size() < 6) {
				largo = equipo.size();
			}

			for (int a = 0; a < largo; a++) {

				if (equipo.get(a) != null && equipo.get(a).estado().equals("Vivo")) {
					pokemonJugador = equipo.get(a);
					break;
				}
			}

			for (Pokemon p : oponente.entregarPokemon()) {

				if (p != null && p.estado().equals("Vivo")) {
					pokemonOponente = p;
					break;
				}
			}

			if (pokemonJugador == null || pokemonOponente == null) {
				combateFinalizado = true;
			}

		} catch (Exception e) {
			System.out.println("Error al distribuir batalla inicial: " + e.getMessage());
			combateFinalizado = true;
		}
	}

	public void distribucionInicialAltoMando() {

		try {

			pokemonJugador = null;
			pokemonAltoMando = null;

			ArrayList<Pokemon> equipo = jugador.getListaPokemon();

			int largo = 6;

			if (equipo.size() < 6) {
				largo = equipo.size();
			}

			for (int a = 0; a < largo; a++) {

				if (equipo.get(a) != null && equipo.get(a).estado().equals("Vivo")) {
					pokemonJugador = equipo.get(a);
					break;
				}
			}

			for (Pokemon p : altoMando.getPokemons()) {

				if (p != null && p.estado().equals("Vivo")) {
					pokemonAltoMando = p;
					break;
				}
			}

			if (pokemonJugador == null || pokemonAltoMando == null) {
				combateFinalizadoAltoMando = true;
			}

		} catch (Exception e) {
			System.out.println("Error al distribuir batalla Alto Mando: " + e.getMessage());
			combateFinalizadoAltoMando = true;
		}
	}

	public void actualizarPokemonJugador() {

		try {

			pokemonJugador = null;

			ArrayList<Pokemon> equipo = jugador.getListaPokemon();

			int largo = 6;

			if (equipo.size() < 6) {
				largo = equipo.size();
			}

			for (int a = 0; a < largo; a++) {

				if (equipo.get(a) != null && equipo.get(a).estado().equals("Vivo")) {
					pokemonJugador = equipo.get(a);
					break;
				}
			}

			if (pokemonJugador == null) {
				jugadorVivo = false;
				combateFinalizado = true;
				combateFinalizadoAltoMando = true;
			}

		} catch (Exception e) {
			jugadorVivo = false;
			combateFinalizado = true;
			combateFinalizadoAltoMando = true;
		}
	}

	public void actualizarPokemonOponente() {

		try {

			pokemonOponente = null;

			for (Pokemon p : oponente.entregarPokemon()) {

				if (p != null && p.estado().equals("Vivo")) {
					pokemonOponente = p;
					break;
				}
			}

			if (pokemonOponente == null) {
				oponenteVivo = false;
				combateFinalizado = true;
			}

		} catch (Exception e) {
			oponenteVivo = false;
			combateFinalizado = true;
		}
	}

	public void actualizarPokemonAltoMando() {

		try {

			pokemonAltoMando = null;

			for (Pokemon p : altoMando.getPokemons()) {

				if (p != null && p.estado().equals("Vivo")) {
					pokemonAltoMando = p;
					break;
				}
			}

			if (pokemonAltoMando == null) {
				altoMandoVivo = false;
				combateFinalizadoAltoMando = true;
			}

		} catch (Exception e) {
			altoMandoVivo = false;
			combateFinalizadoAltoMando = true;
		}
	}

	public Pokemon getPokemonJugador() {
		return pokemonJugador;
	}

	public Pokemon getPokemonOponente() {
		return pokemonOponente;
	}

	public Pokemon getPokemonAltoMando() {
		return pokemonAltoMando;
	}

	public void cambiarPokemonJugador(Pokemon p) {

		try {

			if (p == null || p.estado().equals("Debilitado")) {
				return;
			}

			pokemonJugador = p;

		} catch (Exception e) {
			System.out.println("Error al cambiar Pokémon del jugador: " + e.getMessage());
		}
	}

	public void revisarVivos() {

		try {

			jugadorVivo = false;
			oponenteVivo = false;

			ArrayList<Pokemon> equipo = jugador.getListaPokemon();

			int largo = 6;

			if (equipo.size() < 6) {
				largo = equipo.size();
			}

			for (int a = 0; a < largo; a++) {

				if (equipo.get(a) != null && equipo.get(a).estado().equals("Vivo")) {
					jugadorVivo = true;
					break;
				}
			}

			for (Pokemon p : oponente.entregarPokemon()) {

				if (p != null && p.estado().equals("Vivo")) {
					oponenteVivo = true;
					break;
				}
			}

			if (jugadorVivo == false || oponenteVivo == false) {
				combateFinalizado = true;
			}

		} catch (Exception e) {
			combateFinalizado = true;
		}
	}

	public void revisarVivosAltoMando() {

		try {

			jugadorVivo = false;
			altoMandoVivo = false;

			ArrayList<Pokemon> equipo = jugador.getListaPokemon();

			int largo = 6;

			if (equipo.size() < 6) {
				largo = equipo.size();
			}

			for (int a = 0; a < largo; a++) {

				if (equipo.get(a) != null && equipo.get(a).estado().equals("Vivo")) {
					jugadorVivo = true;
					break;
				}
			}

			for (Pokemon p : altoMando.getPokemons()) {

				if (p != null && p.estado().equals("Vivo")) {
					altoMandoVivo = true;
					break;
				}
			}

			if (jugadorVivo == false || altoMandoVivo == false) {
				combateFinalizadoAltoMando = true;
			}

		} catch (Exception e) {
			combateFinalizadoAltoMando = true;
		}
	}
}