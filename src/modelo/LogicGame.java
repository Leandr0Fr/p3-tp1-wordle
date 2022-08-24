package modelo;

import java.util.HashMap;
import java.util.Map;

public class LogicGame{
	private String palabraEnJuego = "menua";
	private Map<Character, Integer> letraYcantidad;
	private char[] palabraIngresada;
	private estadosLetra [] resultadoLetras;
	private enum estadosLetra{verde,amarillo,gris};

	public LogicGame(int tamanoPalabra) {
		this.palabraIngresada = new char[tamanoPalabra];
		this.resultadoLetras = new estadosLetra[tamanoPalabra];
			
		resetearPalabra();
		resetearLetraYCantidad();
	}
	//enter
	//boolean = otro enum
	public boolean terminarIntento(char[] palabra) {
		//falta la opción de que no sea una palabra
		verificarPalabra();
		aciertosJugador(palabra);
		for (estadosLetra estLet : resultadoLetras) {
			if (estLet != estadosLetra.verde) 
				break;
			//todo es verde
			return true;
		}
		
		//no acertó la palabra
		
		resetearPalabra();
		resetearLetraYCantidad();
		
		return false;
	}

	private estadosLetra aciertosJugador(char [] palabra) {
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
//		
//		if (palabra.charAt(index) == letra) {
//			letraYcantidad.put(letra, letraYcantidad.get(letra) - 1);
//			return estadosLetra.verde;
//		}
//		
//		if (!letraYcantidad.containsKey(letra) || letraYcantidad.get(letra) < 1) {
//			return estadosLetra.gris;
//		}
//		
//		letraYcantidad.put(letra, letraYcantidad.get(letra) - 1);
//		return estadosLetra.amarillo;
//	
		return null;
	}
	
	public estadosLetra[] getVerificacionPalabra() {
		return resultadoLetras; //devuelve puntero a objeto array
	}
	
	private void verificarPalabra() {
		//verifica si es una palabra que esta dentro del conjunto
	}
	
	private void resetearPalabra() {
		for (int i = 0; i < palabraIngresada.length; i++) {
			palabraIngresada[i] = ' ';
		}
	}

	private void resetearLetraYCantidad() {
		letraYcantidad = new HashMap<Character, Integer>();
		for (Character c : palabraEnJuego.toCharArray()) {
			int cantExistente = letraYcantidad.getOrDefault(c, 0);
			letraYcantidad.put(c, cantExistente + 1);
		}
	}

		
}
	