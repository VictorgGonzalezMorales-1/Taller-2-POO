package Taller2;

import java.util.ArrayList;

public class Memoria {

	private Jugador prota;
	private ArrayList<Pokedex> p = new ArrayList<Pokedex>();
	private ArrayList<Habitat> h = new ArrayList<Habitat>();
	private ArrayList<Gimnasios> g = new ArrayList<Gimnasios>();
	private ArrayList<AltosMandos> a = new ArrayList<AltosMandos>();

	private ArrayList<String> txtJugador = new ArrayList<String>();
	private ArrayList<String> txtGimnasio = new ArrayList<String>();

	public Jugador getJugador() {
		return prota;
	}

	public void guardarJugador(Jugador j) {

		try {

			if (j == null) {
				System.out.println("No se puede guardar un jugador vacío.");
				return;
			}

			prota = j;

		} catch (Exception e) {
			System.out.println("Error al guardar jugador: " + e.getMessage());
		}
	}

	public ArrayList<Pokedex> getP() {
		return p;
	}

	public void guardarPokedex(Pokedex po) {

		try {

			if (po == null) {
				System.out.println("No se puede guardar un registro vacío en la Pokédex.");
				return;
			}

			p.add(po);

		} catch (Exception e) {
			System.out.println("Error al guardar Pokédex: " + e.getMessage());
		}
	}

	public void guardarHabitat(Habitat habitat) {

		try {

			if (habitat == null) {
				System.out.println("No se puede guardar un hábitat vacío.");
				return;
			}

			h.add(habitat);

		} catch (Exception e) {
			System.out.println("Error al guardar hábitat: " + e.getMessage());
		}
	}

	public ArrayList<Habitat> entregarHabitats() {
		return h;
	}

	public ArrayList<Gimnasios> entregarGimnasios() {
		return g;
	}

	public void guardarGimnasio(Gimnasios gimnasio) {

		try {

			if (gimnasio == null) {
				System.out.println("No se puede guardar un gimnasio vacío.");
				return;
			}

			g.add(gimnasio);

		} catch (Exception e) {
			System.out.println("Error al guardar gimnasio: " + e.getMessage());
		}
	}

	public void entregarTxtGimnasio() {

		try {

			txtGimnasio.clear();

			for (Gimnasios gimnasio : g) {

				if (gimnasio != null) {

					String txt = gimnasio.entregarNumeroGimnasio() + ";" + gimnasio.entregarLiderGimnasio() + ";"
							+ gimnasio.getEstado() + ";" + gimnasio.entregarPokemon().size();

					for (Pokemon p : gimnasio.entregarPokemon()) {

						if (p != null) {
							txt += ";" + p.getNombre();
						}
					}

					txtGimnasio.add(txt);
				}
			}

		} catch (Exception e) {
			System.out.println("Error al generar texto de gimnasios: " + e.getMessage());
		}
	}

	public void entregarTxtJugador() {

		try {

			txtJugador.clear();

			if (prota == null) {
				System.out.println("No existe jugador cargado para guardar.");
				return;
			}

			String txt = prota.getJugador() + ";" + prota.getDerrotados();
			txtJugador.add(txt);

			for (Pokemon p : prota.getListaPokemon()) {

				if (p != null) {
					txt = p.getNombre() + ";" + p.estado();
					txtJugador.add(txt);
				}
			}

		} catch (Exception e) {
			System.out.println("Error al generar texto del jugador: " + e.getMessage());
		}
	}

	public ArrayList<String> getTxtJugador() {
		return txtJugador;
	}

	public ArrayList<String> getTxtGimnasio() {
		return txtGimnasio;
	}

	public void setTxtGimnasioDefault() {

		try {

			txtGimnasio.clear();

			txtGimnasio.add("1;EmmaLaArdillaRabiosa;Sin derrotar;3;Minun;Plusle;Emolga");
			txtGimnasio.add("2;MartinNegro;Sin derrotar;3;MegaBlaziken;Centiskorch;Slugma");
			txtGimnasio.add("3;Ferran;Sin derrotar;4;Gengar;Reuniclus;Mewtwo;Reshiram");
			txtGimnasio.add("4;Branco;Sin derrotar;4;Minior;Dracovish;Porygon-Z;Hydrapple");
			txtGimnasio.add("5;Remi;Sin derrotar;5;Eevee;Scizor;Trevenant;Umbreon;Togekiss");
			txtGimnasio.add("6;Pruno;Sin derrotar;5;Infernape;Aggron;Tyranitar;Lucario;Aerodactyl");
			txtGimnasio.add("7;Dani;Sin derrotar;6;Mew;Swanna;Milotic;Dragapult;Lapras;Gigalith");
			txtGimnasio.add("8;Maxi;Sin derrotar;6;Chandelure;Decidueye;Froslass;Dragapult;Spiritomb;Sableye");

		} catch (Exception e) {
			System.out.println("Error al reiniciar gimnasios: " + e.getMessage());
		}
	}

	public void guardarAltoMando(AltosMandos al) {

		try {

			if (al == null) {
				System.out.println("No se puede guardar un miembro vacío del Alto Mando.");
				return;
			}

			a.add(al);

		} catch (Exception e) {
			System.out.println("Error al guardar Alto Mando: " + e.getMessage());
		}
	}

	public boolean permitirPeleaAltosMandos() {

		try {

			if (g == null || g.size() == 0) {
				return false;
			}

			for (Gimnasios gimnasio : g) {

				if (gimnasio == null) {
					return false;
				}

				if (gimnasio.entregarEstadoGimnasio().equals("Sin derrotar")) {
					return false;
				}
			}

			return true;

		} catch (Exception e) {
			System.out.println("Error al verificar permiso para Alto Mando: " + e.getMessage());
			return false;
		}
	}

	public ArrayList<AltosMandos> entregarAltosMandos() {
		return a;
	}
}