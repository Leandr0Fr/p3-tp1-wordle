package modelo;

import java.util.HashMap;
import java.util.Map;

public class LogicGame{
	private String palabraEnJuego = "menua";
	private Map<Character, Integer> letraYcantidad;
	private estadosLetra [] resultadoLetras;
	private enum estadosLetra{verde,amarillo,gris,vacio};

	public LogicGame(int tamanoPalabra) {
		resetearLetraYCantidad();
		resetearResultadosLetras();
	}
	//enter
	public boolean terminarIntento(char[] palabra) {
		aciertosJugador(palabra);
		for (estadosLetra estLet : resultadoLetras) {
			if (estLet != estadosLetra.verde) {
				resetearLetraYCantidad();
				resetearResultadosLetras();
				return false;
			}
		}
		//acerto la palabra
		return true;
	}
	private void aciertosJugador(char [] palabra) {
		//prioridad verde
		for (int i = 0; i < palabra.length; i++) {
			for (int j = 0; j < palabra.length; j++) {
				if (palabraEnJuego.charAt(i) == palabra[j]) {
					letraYcantidad.get(palabra[j]--);
					resultadoLetras[j] = estadosLetra.verde;
				}
			}
		}
		//amarillo y gris
		for (int i = 0; i < palabra.length; i++) {
			if (letraYcantidad.containsKey(palabra[i])) {
				resultadoLetras[i] = estadosLetra.amarillo;
			}
			resultadoLetras[i] = estadosLetra.gris;
		}
	}
	
	public estadosLetra[] getVerificacionPalabra() {
		return resultadoLetras;
	}
	
	private void verificarPalabra() {
		//verifica si es una palabra que esta dentro del conjunto
	}

	private void resetearLetraYCantidad() {
		this.letraYcantidad = new HashMap<Character, Integer>();
		for (Character c : palabraEnJuego.toCharArray()) {
			int cantExistente = letraYcantidad.getOrDefault(c, 0);
			letraYcantidad.put(c, cantExistente + 1);
		}
	}	
	private void resetearResultadosLetras() {
		this.resultadoLetras = new estadosLetra[palabraEnJuego.length()];
		for (int i = 0; i < resultadoLetras.length; i++) {
			resultadoLetras[i] = estadosLetra.vacio;
		}
	}
}
	