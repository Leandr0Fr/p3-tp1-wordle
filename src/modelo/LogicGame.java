package modelo;

import java.util.HashMap;
import java.util.Map;

public class LogicGame{
	private String palabra = "menua";
	private Map<Character, Integer> letraYcantidad;
	private char[] palabraIngresada;
	private estadosLetra [] resultadoLetras;
	private int posicionCaracter = 0;
	private enum estadosLetra{verde,amarillo,gris};
	private int intentos = 6;

	public LogicGame(int tamanoPalabra) {
		this.palabraIngresada = new char[tamanoPalabra];
		this.resultadoLetras = new estadosLetra[tamanoPalabra];
			
		resetearPalabra();
		resetearLetraYCantidad();
	}
	public void colocarLetra(char letra) {
		//coloca la letra en la posicion y luego aumenta en uno posicionCaracter
		palabraIngresada[posicionCaracter++] = letra;	
	}
	//backspace
	public void eliminarLetra(){
		palabraIngresada[posicionCaracter--] = ' ';
	}
	//enter
	public boolean terminarIntento(char[] palabra) {
		verificarPalabra();
		
		for (estadosLetra estLet : resultadoLetras) {
			if (estLet != estadosLetra.verde) 
				break;
			//todo es verde
			return true;
		}
		
		//no acertó la palabra
		intentos--;
		resetearPalabra();
		resetearLetraYCantidad();
		
		return false;
	}
	

	public estadosLetra[] getVerificacionPalabra() {
		return resultadoLetras; //devuelve puntero a objeto array
	}

	private void resetearPalabra() {
		for (int i = 0; i < palabraIngresada.length; i++) {
			palabraIngresada[i] = ' ';
		}
	}

	private void resetearLetraYCantidad() {
		letraYcantidad = new HashMap<Character, Integer>();
		for (Character c : palabra.toCharArray()) {
			int cantExistente = letraYcantidad.getOrDefault(c, 0);
			letraYcantidad.put(c, cantExistente + 1);
		}
	}

	private void verificarPalabra() {
		for (int i = 0; i < palabraIngresada.length; i++) {
			char letra = palabraIngresada[i];
			resultadoLetras[i] = verifLetra(letra, i);
		}
	}
	
	private estadosLetra verifLetra(char letra, int index) {
		//prioridad verde
		if (palabra.charAt(index) == letra) {
			letraYcantidad.put(letra, letraYcantidad.get(letra) - 1);
			return estadosLetra.verde;
		}
		
		if (!letraYcantidad.containsKey(letra) || letraYcantidad.get(letra) < 1) {
			return estadosLetra.gris;
		}
		
		letraYcantidad.put(letra, letraYcantidad.get(letra) - 1);
		return estadosLetra.amarillo;
	}

		
}
		
	