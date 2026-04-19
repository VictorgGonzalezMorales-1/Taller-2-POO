package Taller2;

import java.io.*;
import java.util.ArrayList;

public class administrador {
	
	public static memoria M = new memoria();
	
	// Método generado para imprimir más rápido
	public static void P(String t) {
		System.out.println(t);
	}
	
	/*Método el cual se comunica con la clase jugador, crea el objeto y
	 *le dice a la clase memoria que lo almacene */
	public void crearJugador(String nombre) {
		
		jugador prota = new jugador(nombre);
		M.guardarJugador(prota);
		sobreescribirTexto("Registros.txt", M.getJugador().textoSobreescribir());
		
	}
	
	public void sobreescribirTexto(String archivo, ArrayList<String> nuevoTexto) {
		
		BufferedWriter Escritor;
		
		try {
			
			Escritor = new BufferedWriter(new FileWriter(archivo, false));
			
			for(String s: nuevoTexto) {
				
				Escritor.write(s);
				Escritor.newLine();
				
			}
			
			Escritor.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
