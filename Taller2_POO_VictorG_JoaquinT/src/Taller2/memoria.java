package Taller2;

//Importar librerias necesarias para el funcionamiento del Main
import java.util.ArrayList;

public class memoria {

	private jugador prota;
	private ArrayList<pokedex> p = new ArrayList<pokedex>();
	private ArrayList<Habitat> h = new ArrayList<Habitat>();
	private ArrayList<gimnacios> g = new ArrayList<gimnacios>();
	private ArrayList<AltosMandos> a = new ArrayList<AltosMandos>();

	private ArrayList<String> txtJugador = new ArrayList<String>();
	private ArrayList<String> txtGimnasio = new ArrayList<String>();

	// Getters y Setters

	// jugador
	public jugador getJugador() {
		return prota;
	}

	public void guardarJugador(jugador j) {
		prota = j;
	}

	// pokemon
	public ArrayList<pokedex> getP() {
		return p;
	}

	public void setP(ArrayList<pokedex> p) {
		this.p = p;
	}

	public void guardarPokedex(pokedex po) {
		p.add(po);
	}

	// Habitats
	public void GuardarHabitat(Habitat habitat) {
		h.add(habitat);
	}

	public ArrayList<Habitat> EntregarHabitats() {
		return h;
	}

	// Gimnasios
	public ArrayList<gimnacios> EntregarGimnasios() {
		return g;
	}

	public void GuardarGimnasio(gimnacios gimnasios) {
		g.add(gimnasios);
	}

	// Método para entregar de gimnasios String y poder guardarlo en el txt
	public void entregarTxtGimnasio() {

		txtGimnasio.clear();

		for (gimnacios g : g) {

			String txt = g.EntregarNumeroGimnasio() + ";" + g.EntregarLiderGimnasio() + ";" + g.getEstado() + ";"
					+ g.entregarPokemon().size();

			for (pokemon p : g.entregarPokemon()) {
				txt += ";" + p.getNombre();
			}

			txtGimnasio.add(txt);
		}
	}

	// Método para entregar de jugador String y poder guardarlo en el txt
	public void entregarTxtJugador() {

		txtJugador.clear();

		String txt = prota.getJugador() + ";" + prota.getDerrotados();
		txtJugador.add(txt);

		for (pokemon p : prota.getListaPokemon()) {
			txt = p.getNombre() + ";" + p.estado();
			txtJugador.add(txt);
		}
	}

	public ArrayList<String> getTxtJugador() {
		return txtJugador;
	}

	public ArrayList<String> getTxtGimnasio() {
		return txtGimnasio;
	}

	// Método para que cuando se genere una partida nueva, no venga con partidas ya
	// ganadas
	public void setTxtGimnasioDefault() {

		txtGimnasio.clear();

		txtGimnasio.add("1;EmmaLaArdillaRabiosa;Sin derrotar;3;Minun;Plusle;Emolga");
		txtGimnasio.add("2;MartinNegro;Sin derrotar;3;MegaBlaziken;Centiskorch;Slugma");
		txtGimnasio.add("3;Ferran;Sin derrotar;4;Gengar;Reuniclus;Mewtwo;Reshiram");
		txtGimnasio.add("4;Branco;Sin derrotar;4;Minior;Dracovish;Porygon-Z;Hydrapple");
		txtGimnasio.add("5;Remi;Sin derrotar;5;Eevee;Scizor;Trevenant;Umbreon;Togekiss");
		txtGimnasio.add("6;Pruno;Sin derrotar;5;Infernape;Aggron;Tyranitar;Lucario;Aerodactyl");
		txtGimnasio.add("7;Dani;Sin derrotar;6;Mew;Swanna;Milotic;Dragapult;Lapras;Gigalith");
		txtGimnasio.add("8;Maxi;Sin derrotar;6;Chandelure;Decidueye;Froslass;Dragapult;Spiritomb;Sableye");
	}
	
	public void imprimirAlto() {
		
		String texto = "";
		
		for(AltosMandos a: a) {
			
			System.out.println(a);
			
		}
		
	}
	
	public void guardarAltoMando(AltosMandos al) {
		a.add(al);
	}

}
