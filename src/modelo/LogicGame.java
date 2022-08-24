package modelo;

import java.util.Set;

public class LogicGame{
	private String palabra = "menua";
	private Set<Character> conjuntoLetras; 
	private char[] palabraIngresada;
	private estadosLetra [] verificacionPalabra;
	private int posicionCaracter = 0;
	private enum estadosLetra{verde,amarillo,gris};
	private int intentos = 6;
	public LogicGame(int tamanoPalabra) {
		this.palabraIngresada = new char[tamanoPalabra];
		this.verificacionPalabra = new estadosLetra[tamanoPalabra];
		for (int i = 0; i < palabra.length(); i++) {
			conjuntoLetras.add(palabra.charAt(i));
		}
		resetearPalabra();
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
	public boolean terminarIntento() {
		//switch
		for (int i = 0; i < palabraIngresada.length; i++) {
			if(!(palabraIngresada[i] == palabra.charAt(i))) {
				//no es todo verde
				//blanquear
				intentos--;
				return false;	
			}
		}	
		//todo es verde
		return true;
	}
	
	public estadosLetra[] getVerificacionPalabra() {
		return verificacionPalabra;
	}

	private void resetearPalabra() {
		for (int i = 0; i < palabraIngresada.length; i++) {
			palabraIngresada[i] = ' ';
		}
	}
	private void verificarPalabra() {
		for (int i = 0; i < palabraIngresada.length; i++) {
			if (palabraIngresada[i] == palabra.charAt(i)) {
				verificacionPalabra[i] = estadosLetra.verde;
			}
			else {
				if(conjuntoLetras.contains(palabra.charAt(i))) {
					verificacionPalabra[i] = estadosLetra.amarillo;
				}
				else {
					verificacionPalabra[i] = estadosLetra.gris;
				}
			}
		}
		
	}
}
	