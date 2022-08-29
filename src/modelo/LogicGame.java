package modelo;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class LogicGame{
	private String palabraEnJuego = "menem";
	private Map<Character, Integer> letraYcantidad;
	private Set<String> listadoDePalabras;
	private EstadoCasillero [] resultadoLetras;
	private enum EstadoCasillero{verde,amarillo,gris,vacio};
	private enum Dificultad{facil,normal,dificil};
	//modificar constructor para que tome la dificultad
	public LogicGame(int tamanoPalabra) {
		setearLetraYCantidad();
		setearResultadosLetras();
		obtenerConjuntoDePalabras(Dificultad.normal, false);
		this.listadoDePalabras = new HashSet<>();
	}
	
	public boolean terminarIntento(char[] palabra) {
		for (EstadoCasillero estLet : resultadoLetras) {
			if (estLet != EstadoCasillero.verde) {
				setearLetraYCantidad();
				setearResultadosLetras();
				return false;
			}
		}
		//acerto la palabra
		return true;
	}
	public EstadoCasillero[] aciertosJugador(char [] palabraIntento) {
		//prioridad verde
		for (int i = 0; i < palabraEnJuego.length(); i++) {
			if (palabraIntento[i] == palabraEnJuego.charAt(i)) {
				resultadoLetras[i] = EstadoCasillero.verde;
				letraYcantidad.put(palabraIntento[i], letraYcantidad.get(palabraIntento[i])-1);
			}
		}
		//amarillo y gris
		for (int i = 0; i < palabraEnJuego.length(); i++) {
			if (palabraIntento[i] != palabraEnJuego.charAt(i) && letraYcantidad.containsKey(palabraIntento[i]) && letraYcantidad.get(palabraIntento[i]) > 0 ) {
				resultadoLetras[i] = EstadoCasillero.amarillo;
				letraYcantidad.put(palabraIntento[i], letraYcantidad.get(palabraIntento[i])-1);
			}
			else if(palabraIntento[i] != palabraEnJuego.charAt(i) && !letraYcantidad.containsKey(palabraIntento[i]) ||(palabraIntento[i] != palabraEnJuego.charAt(i) && letraYcantidad.get(palabraIntento[i]) == 0) ){
				resultadoLetras[i] = EstadoCasillero.gris;
			}
		}
		return this.resultadoLetras;
	}
	
	
	public boolean perteneceAlListado(char[] palabra) {
		StringBuilder palabraFormada = new StringBuilder();
		for (int i = 0; i < palabra.length; i++) {
			palabraFormada.append(palabra[i]);
		}
		return listadoDePalabras.contains(palabraFormada.toString());
	}

	public boolean esTeclaValida(KeyEvent e) {
		//ascii 65 - 90 (209 = Ñ | 241 ñ) 97 - 122
		return e.getKeyChar() == 10 || e.getKeyChar() == 8 || e.getKeyChar() == 209 || e.getKeyChar() == 241 ||
			  e.getKeyChar() >= 65 && e.getKeyChar() <= 90
			  || e.getKeyChar() >= 97 && e.getKeyChar() <= 122;
	}
	public char mayus(char letra) {
		if (letra >= 97 && letra <= 122 || letra == 241)
			letra = (char) (letra - 32);
		return letra;
	}
	
	private void obtenerConjuntoDePalabras(Dificultad dificultad, boolean ingles) {
		StringBuilder ruta = new StringBuilder(LogicGame.class.getResource("").getPath());
		ruta.append((ingles)?"ingles":"espanol");
		if (dificultad == Dificultad.facil) {ruta.append("Facil.txt");}
		else if(dificultad == Dificultad.normal) {ruta.append("Normal.txt");}
		else {ruta.append("Dificil.txt");}
		//leer contenido
		File archivoPalabras = new File(ruta.toString());
		try {
			Scanner palabras = new Scanner(archivoPalabras);
			while (palabras.hasNext()) {
				listadoDePalabras.add(palabras.next().toString().toLowerCase());
			}
			palabras.close();
		} catch (FileNotFoundException e) {	
				e.printStackTrace();
			}	
	}
	private void seleccionarPalabra() {
		
	}
	private void setearLetraYCantidad() {
		letraYcantidad = new HashMap<Character, Integer>();
		for (Character c : palabraEnJuego.toCharArray()) {
			int cantExistente = letraYcantidad.getOrDefault(c, 0);
			letraYcantidad.put(c, cantExistente + 1);
		}
	}
	private void setearResultadosLetras() {
		this.resultadoLetras = new EstadoCasillero[palabraEnJuego.length()];
		for (int i = 0; i < resultadoLetras.length; i++) {
			resultadoLetras[i] = EstadoCasillero.vacio;
		}
	}
}