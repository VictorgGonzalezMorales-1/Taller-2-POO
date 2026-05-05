package Taller2;

//Importar librerias necesarias para el funcionamiento del Main
import java.util.ArrayList;

public class memoria {

	private jugador prota;
	private ArrayList<pokedex> p = new ArrayList<pokedex>();
	private ArrayList<Habitat> h = new ArrayList<Habitat>();

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

}
