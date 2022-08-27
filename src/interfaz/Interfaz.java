package interfaz;

import modelo.LogicGame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

<<<<<<< HEAD
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
=======
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
>>>>>>> branch-gaston

public class Interfaz {

	private JFrame frame;
<<<<<<< HEAD
	private JLabel [][] tablero; 
	private int posLetra = 0;
	private int posFila = 0;
	private LogicGame game;
=======
	private JLabel[] titulo;
	private JLabel[][] tablero; 
	private int posLetra = 0;
	private int posFila = 0;
	private LogicGame game;
	private enum estadosLetra{verde,amarillo,gris};

	private Color verde = new Color(106, 170, 100);
	private Color amarillo = new Color(201, 180, 88);
	private Color gris = new Color(120, 124, 126);
>>>>>>> branch-gaston
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Pantalla principal
		game = new LogicGame(5);
		frame = new JFrame();
<<<<<<< HEAD
		frame.setBounds(100, 100, 524, 574);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//tablero	
		crearTablero(5);
=======
		// (x,y, 64 * length word, 64 * 6 + 8 * 5 + 64 * 3)
		frame.setBounds(0, 0, 400, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//title
		crearTitulo();
		

		//Start button && actions	
		JButton btnStartGame = new JButton("START!");
		btnStartGame.setFont(new Font("Consolas", Font.PLAIN, 11));

		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearTablero(5);
				btnStartGame.setEnabled(false);
				btnStartGame.setVisible(false);
				updateFrame();
			}
		});

		btnStartGame.setBounds(206, 273, 89, 23);
		frame.getContentPane().add(btnStartGame);
		
>>>>>>> branch-gaston
		
		//keyListener
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
<<<<<<< HEAD
				System.out.println("Posicion letra: " + posLetra);
				if (e.getKeyChar() == 8) { 
					System.out.println("AAAAAAAAAAAAAAAAAaaa");
=======
				if (e.getKeyChar() == 8) { 
>>>>>>> branch-gaston
					borrarLetra();
					return;
				}
				
				if (e.getKeyChar() == 10 && posLetra >= 4) {
					enviarPalabra();
					return;
				}

				if (!esTeclaValida(e) || posLetra > 4)
					return;
				
<<<<<<< HEAD
				System.out.println(0 + e.getKeyChar());
				if (e.getKeyChar() != 8 && e.getKeyChar() != 10)
					colocarLetra(e);
				
				
			}

			private void borrarLetra() {
				System.out.println("ESTOY ACAAAA");
				
				if (posLetra > 5){
					posLetra = 4;
				}
				
				if (posLetra != 0 && tablero[posFila][posLetra].getText() == " " ) {
					posLetra--;
				}
				tablero[posFila][posLetra].setText(" ");
			}
			
			private void enviarPalabra() {
				System.out.println("Su Uber flash está en camino!");
				char[] palabraEnviada = new char[5];
				for (int i = 0; i < tablero[posFila].length; i++) {
					palabraEnviada[i] = tablero[posFila][i].getText().charAt(0);
				}
				if (game.terminarIntento(palabraEnviada)) {
					//GANASTE!!!
				}
				if (posFila == 5) {
					//perdiste
					return;
				}
				posFila++;
				posLetra = 0;
				
			}

			private boolean esTeclaValida(KeyEvent e) {
				//ascii 65 - 90 (209 = Ñ | 241 ñ) 97 - 122
				return e.getKeyChar() == 10 || e.getKeyChar() == 8 || e.getKeyChar() == 209 || e.getKeyChar() == 241 ||
					  e.getKeyChar() >= 65 && e.getKeyChar() <= 90
					  || e.getKeyChar() >= 97 && e.getKeyChar() <= 122;
			}
			
			private void colocarLetra(KeyEvent e) {
				
				if (tablero[posFila][posLetra].getText() != " ")
					return;
				
				char letra = mayus(e.getKeyChar());
				tablero[posFila][posLetra].setText("" + letra);
				posLetra += posLetra != tablero[0].length - 1 ? 1 : 0;
				
			}

			private char mayus(char letra) {
				if (letra >= 97 && letra <= 122 || letra == 241)
					letra = (char) (letra - 32);
				return letra;
			}
			
			
		});
	
		
=======
				if (e.getKeyChar() != 8 && e.getKeyChar() != 10)
					colocarLetra(e);
			}

		});
>>>>>>> branch-gaston
		
	}

	private void crearTablero(int tamanoPalabra){
<<<<<<< HEAD
		int x = 60;
		int y = 30;
=======
		int x = 16;
		int y = 72;
>>>>>>> branch-gaston
		int CENTER = 0;
		tablero = new JLabel[6][tamanoPalabra];
		for (int f = 0; f < 6; f++) {
			for (int c = 0; c <tamanoPalabra ; c++) {
				tablero[f][c] = new JLabel();
<<<<<<< HEAD
				tablero[f][c].setFont(new Font("System", Font.PLAIN, 64));
=======
				tablero[f][c].setFont(new Font("Source Code Pro", Font.PLAIN, 64));
>>>>>>> branch-gaston
				tablero[f][c].setText(" ");
				tablero[f][c].setBackground(Color.WHITE);
				tablero[f][c].setOpaque(true);
				tablero[f][c].setHorizontalAlignment(CENTER);
				tablero[f][c].setBounds(x,y,64,64);
				frame.getContentPane().add(tablero[f][c]);
				x+= 72;
			}
			y+= 72;
<<<<<<< HEAD
			x = 60;			
		}
=======
			x = 16;			
		}
	}

	private void crearTitulo(){
		int x = 38;
		int y = 20;
		int CENTER = 0;
		char[] gameTitleChars = {'w', 'U', 'N', 'G', 'S', 'd', 'l', 'e'};
		int LEN_TITLE_CHARS = gameTitleChars.length;
		titulo = new JLabel[LEN_TITLE_CHARS];

		for (int i = 0; i < LEN_TITLE_CHARS; i++) {
			titulo[i] = new JLabel();
			titulo[i].setFont(new Font("Source Code Pro", Font.PLAIN, 32));
			titulo[i].setText(" ");
			titulo[i].setBackground(Color.WHITE);
			titulo[i].setOpaque(true);
			titulo[i].setHorizontalAlignment(CENTER);
			titulo[i].setBounds(x,y,32,32);
			titulo[i].setText("" + gameTitleChars[i]);
			frame.getContentPane().add(titulo[i]);
			x += 40;
		}
		
	}

	private void borrarLetra() {
		System.out.println("ESTOY ACAAAA");
		
		if (posLetra > 5){
			posLetra = 4;
		}
		
		if (posLetra != 0 && tablero[posFila][posLetra].getText() == " " ) {
			posLetra--;
		}

		tablero[posFila][posLetra].setText(" ");
	}
	
	private void enviarPalabra() {
		char[] palabraEnviada = new char[5];
		for (int i = 0; i < tablero[posFila].length; i++) {
			palabraEnviada[i] = tablero[posFila][i].getText().charAt(0);
		}
		
		// ### PRUEBA COLOREAR
		estadosLetra[] resultado = {estadosLetra.amarillo, estadosLetra.amarillo, estadosLetra.gris, estadosLetra.verde, estadosLetra.verde};
		colorearLetras(resultado);
		
		// ### PRUEBA COLOREAR

		if (game.terminarIntento(palabraEnviada)) {
			//GANASTE!!!
		}
		if (posFila == 5) {
			//perdiste
			return;
		}
		posFila++;
		posLetra = 0;
		
	}
	
	private void colocarLetra(KeyEvent e) {
		if (tablero[posFila][posLetra].getText() != " ")
			return;
		
		char letra = mayus(e.getKeyChar());
		tablero[posFila][posLetra].setText("" + letra);
		posLetra += posLetra != tablero[0].length - 1 ? 1 : 0;
		
	}
	
	private void colorearLetras(estadosLetra[] resultados){
		for (int i = 0; i < tablero[0].length; i++) {
			
			if (resultados[i] == estadosLetra.verde)
				colorearVerde(tablero[posFila][i]);

			else if (resultados[i] == estadosLetra.amarillo)
				colorearAmarillo(tablero[posFila][i]);

			else if (resultados[i] == estadosLetra.gris)
				colorearGris(tablero[posFila][i]);
			
		}
		
	}

	private void colorearVerde(JLabel celda) {
		celda.setBackground(verde);
		colorearTextoBlanco(celda);
	}

	private void colorearAmarillo(JLabel celda) {
		celda.setBackground(amarillo);
		colorearTextoBlanco(celda);
	}

	private void colorearGris(JLabel celda) {
		celda.setBackground(gris);
		colorearTextoBlanco(celda);
	}
	
	private void colorearTextoBlanco(JLabel celda) {
		celda.setForeground(Color.white);
	}

	private boolean esTeclaValida(KeyEvent e) {
		//ascii 65 - 90 (209 = Ñ | 241 ñ) 97 - 122
		return e.getKeyChar() == 10 || e.getKeyChar() == 8 || e.getKeyChar() == 209 || e.getKeyChar() == 241 ||
			  e.getKeyChar() >= 65 && e.getKeyChar() <= 90
			  || e.getKeyChar() >= 97 && e.getKeyChar() <= 122;
	}

	private char mayus(char letra) {
		if (letra >= 97 && letra <= 122 || letra == 241)
			letra = (char) (letra - 32);
		return letra;
	}
	
	private void updateFrame() {
		SwingUtilities.updateComponentTreeUI(frame);
		frame.setVisible(true);
		frame.toFront();
		frame.requestFocus();
>>>>>>> branch-gaston
	}
}
