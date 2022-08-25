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
		System.out.println(palabraEnJuego);
		System.out.println(palabra);
		for (int i = 0; i < resultadoLetras.length; i++) {
			System.out.print(resultadoLetras[i]+"-");
		}
		System.out.println();
		System.out.println(letraYcantidad);
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
	