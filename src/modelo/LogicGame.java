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
		for (int i = 0; i < resultadoLetras.length; i++) {
			System.out.print(resultadoLetras[i]+"-");
		}
		System.out.println();
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
	private void aciertosJugador(char [] palabraIntento) {
		//prioridad verde
		//fijarse si se puede hacer algo tipo palabraIntento[i] == palabraEnJuego[i]
		for (int i = 0; i < palabraEnJuego.length(); i++) {
			for (int j = 0; j < palabraIntento.length; j++) {
				if (palabraIntento[i] == palabraEnJuego.charAt(j) && letraYcantidad.get(palabraIntento[i]) > 0 && resultadoLetras[i] != estadosLetra.verde) {
					letraYcantidad.put(palabraIntento[i], letraYcantidad.get(palabraIntento[i])-1);
					resultadoLetras[i] = estadosLetra.verde;
					
				}
			}
		}
		//amarillo y gris
		for (int i = 0; i < palabraIntento.length; i++) {
			if (letraYcantidad.containsKey(palabraIntento[i]) && letraYcantidad.get(palabraIntento[i]) > 0 && resultadoLetras[i] != estadosLetra.verde) {
				resultadoLetras[i] = estadosLetra.amarillo;
				letraYcantidad.put(palabraIntento[i], letraYcantidad.get(palabraIntento[i])-1);
			}
			else if(!letraYcantidad.containsKey(palabraIntento[i]) || resultadoLetras[i] != estadosLetra.verde && resultadoLetras[i] != estadosLetra.amarillo) {
				resultadoLetras[i] = estadosLetra.gris;
			}
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
	