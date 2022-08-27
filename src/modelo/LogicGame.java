package modelo;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LogicGame{
	private String palabraEnJuego = "menua";
	private Map<Character, Integer> letraYcantidad;
	private Set<String> listadoDePalabras;
	private estadosLetra [] resultadoLetras;
	private enum estadosLetra{verde,amarillo,gris,vacio};


	public LogicGame(int tamanoPalabra) {
		setearLetraYCantidad();
		setearResultadosLetras();
	}
	public boolean terminarIntento(char[] palabra) {
		for (estadosLetra estLet : resultadoLetras) {
			if (estLet != estadosLetra.verde) {
				setearLetraYCantidad();
				setearResultadosLetras();
				return false;
			}
		}
		//acerto la palabra
		return true;
	}
	public estadosLetra[] aciertosJugador(char [] palabraIntento) {
		//prioridad verde
		for (int i = 0; i < palabraEnJuego.length(); i++) {
			if (palabraIntento[i] == palabraEnJuego.charAt(i)) {
				resultadoLetras[i] = estadosLetra.verde;
				letraYcantidad.put(palabraIntento[i], letraYcantidad.get(palabraIntento[i])-1);
			}
		}
		//amarillo y gris
		for (int i = 0; i < palabraEnJuego.length(); i++) {
			if (palabraIntento[i] != palabraEnJuego.charAt(i) && letraYcantidad.containsKey(palabraIntento[i]) && letraYcantidad.get(palabraIntento[i]) > 0 ) {
				resultadoLetras[i] = estadosLetra.amarillo;
				letraYcantidad.put(palabraIntento[i], letraYcantidad.get(palabraIntento[i])-1);
			}
			else if(palabraIntento[i] != palabraEnJuego.charAt(i) && !letraYcantidad.containsKey(palabraIntento[i]) ||(palabraIntento[i] != palabraEnJuego.charAt(i) && letraYcantidad.get(palabraIntento[i]) == 0) ){
				resultadoLetras[i] = estadosLetra.gris;
			}
		}
		return this.resultadoLetras;
	}
	
	public boolean perteneceAlListado(char[] palabra) {
		StringBuilder palabraFormada = new StringBuilder();
		for (int i = 0; i < palabra.length; i++) {
			palabraFormada.append(palabra[i]);
		}
		if(listadoDePalabras.contains(palabraFormada)) {
			return true;
		}return false;
	}
	
	private void verificarPalabra() {
		//verifica si es una palabra que esta dentro del conjunto
	}

	private void setearResultadosLetras() {
		this.resultadoLetras = new estadosLetra[palabraEnJuego.length()];
		for (int i = 0; i < resultadoLetras.length; i++) {
			resultadoLetras[i] = estadosLetra.vacio;
		}
	}


	public estadosLetra[] getVerificacionPalabra() {
		return resultadoLetras; //devuelve puntero a objeto array
	}


	private void setearLetraYCantidad() {
		letraYcantidad = new HashMap<Character, Integer>();
		for (Character c : palabraEnJuego.toCharArray()) {
			int cantExistente = letraYcantidad.getOrDefault(c, 0);
			letraYcantidad.put(c, cantExistente + 1);
		}
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
		
}
		
	