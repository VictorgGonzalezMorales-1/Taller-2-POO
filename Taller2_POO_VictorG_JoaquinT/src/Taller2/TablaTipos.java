package Taller2;

public class TablaTipos {

	// Matriz de efectividad
	private static final double[][] EFECTIVIDAD = {
			// NOR FUE AGU PLA ELE HIE LUC VEN TIE VOL PSI BIC ROC FAN DRA ACE SIN HAD
			{ 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 0.0, 1.0, 0.5, 1.0, 1.0 }, // NORMAL
			{ 1.0, 0.5, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 1.0 }, // FUEGO
			{ 1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 1.0, 1.0 }, // AGUA
			{ 1.0, 0.5, 2.0, 0.5, 1.0, 1.0, 1.0, 0.5, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 0.5, 0.5, 1.0, 1.0 }, // PLANTA
			{ 1.0, 1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0 }, // ELECTRICO
			{ 1.0, 0.5, 0.5, 2.0, 1.0, 0.5, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0 }, // HIELO
			{ 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 0.5, 0.5, 0.5, 2.0, 0.0, 1.0, 2.0, 2.0, 0.5 }, // LUCHA
			{ 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 0.0, 1.0, 2.0 }, // VENENO
			{ 1.0, 2.0, 1.0, 0.5, 2.0, 1.0, 1.0, 2.0, 1.0, 0.0, 1.0, 0.5, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0 }, // TIERRA
			{ 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 0.5, 1.0, 1.0 }, // VOLADOR
			{ 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.5, 0.0, 1.0 }, // PSIQUICO
			{ 1.0, 0.5, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5, 1.0, 0.5, 2.0, 1.0, 1.0, 0.5, 1.0, 0.5, 2.0, 0.5 }, // BICHO
			{ 1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0 }, // ROCA
			{ 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 1.0 }, // FANTASMA
			{ 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.0 }, // DRAGON
			{ 1.0, 0.5, 0.5, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 1.0, 2.0 }, // ACERO
			{ 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5 }, // SINIESTRO
			{ 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 2.0, 1.0 }  // HADA
	};

	// Arreglo que contiene todos los tipos en el mismo orden que la matriz
	public static final String[] tiposEnOrden = {
			"Normal", "Fuego", "Agua", "Planta", "Electrico", "Hielo",
			"Lucha", "Veneno", "Tierra", "Volador", "Psiquico",
			"Bicho", "Roca", "Fantasma", "Dragon", "Acero",
			"Siniestro", "Hada"
	};

	// Método generado para encontrar la posición del tipo en el arreglo
	public int encontrarIndice(String tipo) {

		try {

			if (tipo == null) {
				return -1;
			}

			for (int a = 0; a < tiposEnOrden.length; a++) {

				if (tiposEnOrden[a].equals(tipo)) {
					return a;
				}

			}

			return -1;

		} catch (Exception e) {
			System.out.println("Error al encontrar índice del tipo: " + e.getMessage());
			return -1;
		}
	}

	// Método para entregar la efectividad usando índices
	public double entregarEfectividad(int jugador, int oponente) {

		try {

			if (jugador < 0 || jugador >= EFECTIVIDAD.length) {
				System.out.println("Índice de tipo del jugador inválido.");
				return 1.0;
			}

			if (oponente < 0 || oponente >= EFECTIVIDAD[0].length) {
				System.out.println("Índice de tipo del oponente inválido.");
				return 1.0;
			}

			return EFECTIVIDAD[jugador][oponente];

		} catch (Exception e) {
			System.out.println("Error al entregar efectividad de tipos: " + e.getMessage());
			return 1.0;
		}
	}

	// Método para entregar la efectividad directamente usando los tipos en texto
	public double entregarEfectividadPorTipo(String tipoJugador, String tipoOponente) {

		try {

			int indiceJugador = encontrarIndice(tipoJugador);
			int indiceOponente = encontrarIndice(tipoOponente);

			return entregarEfectividad(indiceJugador, indiceOponente);

		} catch (Exception e) {
			System.out.println("Error al entregar efectividad por tipo: " + e.getMessage());
			return 1.0;
		}
	}

	// Método para verificar si un tipo existe dentro del arreglo
	public boolean existeTipo(String tipo) {

		try {

			if (tipo == null) {
				return false;
			}

			for (int a = 0; a < tiposEnOrden.length; a++) {

				if (tiposEnOrden[a].equals(tipo)) {
					return true;
				}

			}

			return false;

		} catch (Exception e) {
			System.out.println("Error al verificar tipo: " + e.getMessage());
			return false;
		}
	}
}