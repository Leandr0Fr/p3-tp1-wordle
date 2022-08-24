package modelo;

import java.util.HashMap;
import java.util.Map;

public class LogicGame{
	private String palabraEnJuego;;
	private Map<Character, Integer> letraYcantidad;
	private estadosLetra [] resultadoLetras;
	private enum estadosLetra{verde,amarillo,gris,vacio};

	public LogicGame(int tamano,String palabraAJugar) {
		this.palabraEnJuego = palabraAJugar;
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
		boolean encontroLetra = false;
		//prioridad verde
		for (int i = 0; i < palabra.length; i++) {
			for (int j = 0; j < palabra.length; j++) {
				if (palabraEnJuego.charAt(i) == palabra[j] && !encontroLetra) {
					System.out.println(palabra[j]);
					letraYcantidad.get(palabra[j]);
					letraYcantidad.put(palabra[j], letraYcantidad.get(palabra[i])-1);
					resultadoLetras[i] = estadosLetra.verde;
					imprimir(resultadoLetras);
					encontroLetra = true;
				}
			}
			encontroLetra = false;
		}
		//amarillo y gris
		System.out.println("-----");
		for (int i = 0; i < palabra.length; i++) {
			System.out.println(palabra[i]);
			if (letraYcantidad.containsKey(palabra[i]) && letraYcantidad.get(palabra[i]) > 0) {
				resultadoLetras[i] = estadosLetra.amarillo;
			}
			if(!letraYcantidad.containsKey(palabra[i])) {
				resultadoLetras[i] = estadosLetra.gris;
			}
			imprimir(resultadoLetras);
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
	private void imprimir(estadosLetra [] arreglo) {
		for (int i = 0; i < arreglo.length; i++) {
			System.out.print(arreglo[i] + "-");
		}
		System.out.println();
	}
}
	