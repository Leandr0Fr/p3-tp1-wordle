package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javax.swing.Timer;

public class Game {
	private String palabraEnJuego = "";
	private Map<Character, Integer> letraYcantidad;
	private List<String> listadoDePalabras;
	private EstadoCasillero[] aciertosJugador;
	private boolean isOver;
	private Dificultad dificultad;
	private Timer tiempo;
	private int centesimasDeSegundo = 0;

	private int segundo = 0;
	private int minuto = 0;

	public enum EstadoCasillero {
		verde, amarillo, gris, vacio
	};

	public enum Dificultad {
		facil, normal, dificil
	};

	public Game(int tamanoPalabra, boolean isEnglish) {
		this.listadoDePalabras = new LinkedList<String>();
		cualDificultad(tamanoPalabra);
		obtenerConjuntoDePalabras(dificultad, isEnglish);
		seleccionarPalabra();
		setearLetraYCantidad();
		setearResultadosLetras();
		isOver = false;
		System.out.println(palabraEnJuego);
		iniciarTiempo();
	}

	private void cualDificultad(int len) {
		this.dificultad = (len == 4 ? Dificultad.facil : len == 5 ? Dificultad.normal : Dificultad.dificil);
	}

	public boolean terminarIntento() {
		for (EstadoCasillero estLet : aciertosJugador) {
			if (estLet != EstadoCasillero.verde) {
				setearLetraYCantidad();
				setearResultadosLetras();
				return false;
			}
		}
		// acerto la palabra
		tiempo.stop();
		setIsOver();
		return true;
	}

	public EstadoCasillero[] obtenerAciertos(char[] palabraIntento) {
		// prioridad verde
		for (int i = 0; i < palabraEnJuego.length(); i++) {
			if (palabraIntento[i] == palabraEnJuego.charAt(i)) {
				aciertosJugador[i] = EstadoCasillero.verde;
				letraYcantidad.put(palabraIntento[i], letraYcantidad.get(palabraIntento[i]) - 1);
			}
		}
		// amarillo y gris
		for (int i = 0; i < palabraEnJuego.length(); i++) {
			if (palabraIntento[i] != palabraEnJuego.charAt(i) && letraYcantidad.containsKey(palabraIntento[i])
					&& letraYcantidad.get(palabraIntento[i]) > 0) {
				aciertosJugador[i] = EstadoCasillero.amarillo;
				letraYcantidad.put(palabraIntento[i], letraYcantidad.get(palabraIntento[i]) - 1);
			} else if (palabraIntento[i] != palabraEnJuego.charAt(i) && !letraYcantidad.containsKey(palabraIntento[i])
					|| (palabraIntento[i] != palabraEnJuego.charAt(i) && letraYcantidad.get(palabraIntento[i]) == 0)) {
				aciertosJugador[i] = EstadoCasillero.gris;
			}
		}
		return this.aciertosJugador;
	}

	public boolean perteneceAlListado(char[] palabra) {
		StringBuilder palabraFormada = new StringBuilder();
		for (int i = 0; i < palabra.length; i++) {
			palabraFormada.append(palabra[i]);
		}
		return listadoDePalabras.contains(palabraFormada.toString());
	}

	public boolean esTeclaValida(KeyEvent e) {
		// ascii 65 - 90 (209 = Ñ | 241 ñ) 97 - 122
		return e.getKeyChar() == 10 || e.getKeyChar() == 8 || e.getKeyChar() == 209 || e.getKeyChar() == 241
				|| e.getKeyChar() >= 65 && e.getKeyChar() <= 90 || e.getKeyChar() >= 97 && e.getKeyChar() <= 122;
	}

	public boolean esLetraValida(char letra) {
		return letra == 209 || letra == 241 || letra >= 65 && letra <= 90 || letra >= 97 && letra <= 122;
	}

	public char mayus(char letra) {
		if (letra >= 97 && letra <= 122 || letra == 241)
			letra = (char) (letra - 32);
		return letra;
	}

	private void iniciarTiempo() {
		ActionListener accion = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				centesimasDeSegundo++;
				if (centesimasDeSegundo == 60) {
					segundo++;
					centesimasDeSegundo = 0;
				}
				if (segundo == 60) {
					minuto++;
					segundo = 0;
				}
				if (minuto == 60) {
					minuto = 0;
				}
			}
		};
		tiempo = new Timer(10, accion);
		tiempo.start();
	}

	private void obtenerConjuntoDePalabras(Dificultad dificultad, boolean ingles) {
		StringBuilder ruta = new StringBuilder(Game.class.getResource("").getPath());
		ruta.append((ingles) ? "ingles" : "espanol");
		if (dificultad == Dificultad.facil) {
			ruta.append("Facil.wd");
		} else if (dificultad == Dificultad.normal) {
			ruta.append("Normal.wd");
		} else {
			ruta.append("Dificil.wd");
		}
		// leer contenido
		File archivoPalabras = new File(ruta.toString());
		try {
			Scanner palabras = new Scanner(archivoPalabras);
			while (palabras.hasNext()) {
				listadoDePalabras.add(palabras.next().toString().toUpperCase());
			}
			palabras.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void seleccionarPalabra() {
		Random random = new Random();
		this.palabraEnJuego = listadoDePalabras.get(random.nextInt(listadoDePalabras.size() - 1));
	}

	private void setearLetraYCantidad() {
		letraYcantidad = new HashMap<Character, Integer>();
		palabraEnJuego = palabraEnJuego.toUpperCase();
		for (Character c : palabraEnJuego.toCharArray()) {
			int cantExistente = letraYcantidad.getOrDefault(c, 0);
			letraYcantidad.put(c, cantExistente + 1);
		}
	}

	private void setearResultadosLetras() {
		this.aciertosJugador = new EstadoCasillero[palabraEnJuego.length()];
		for (int i = 0; i < aciertosJugador.length; i++) {
			aciertosJugador[i] = EstadoCasillero.vacio;
		}
	}

	public boolean getIsOver() {
		return isOver;
	}

	public void setIsOver() {
		isOver = true;
	}

	public int getMinuto() {
		return minuto;
	}

	public int getSegundo() {
		return segundo;
	}

	public Dificultad getDificultad() {
		return dificultad;
	}

}