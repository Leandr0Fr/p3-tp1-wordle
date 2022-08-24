package interfaz;

import modelo.LogicGame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Interfaz {

	private JFrame frame;
	private JLabel [][] tablero; 
	private int posLetra = 0;
	private int posFila = 0;
	private LogicGame game;
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
		frame.setBounds(100, 100, 524, 574);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//tablero	
		crearTablero(5);
		
		//keyListener
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("Posicion letra: " + posLetra);
				if (e.getKeyChar() == 8) { 
					System.out.println("AAAAAAAAAAAAAAAAAaaa");
					borrarLetra();
					return;
				}
				
				if (e.getKeyChar() == 10 && posLetra >= 4) {
					enviarPalabra();
					return;
				}

				if (!esTeclaValida(e) || posLetra > 4)
					return;
				
				System.out.println(0 + e.getKeyChar());
				if (e.getKeyChar() != 8 && e.getKeyChar() != 10)
					colocarLetra(e);
			}

		});
	
		
		
	}

	private void crearTablero(int tamanoPalabra){
		int x = 60;
		int y = 30;
		int CENTER = 0;
		tablero = new JLabel[6][tamanoPalabra];
		for (int f = 0; f < 6; f++) {
			for (int c = 0; c <tamanoPalabra ; c++) {
				tablero[f][c] = new JLabel();
				tablero[f][c].setFont(new Font("System", Font.PLAIN, 64));
				tablero[f][c].setText(" ");
				tablero[f][c].setBackground(Color.WHITE);
				tablero[f][c].setOpaque(true);
				tablero[f][c].setHorizontalAlignment(CENTER);
				tablero[f][c].setBounds(x,y,64,64);
				frame.getContentPane().add(tablero[f][c]);
				x+= 72;
			}
			y+= 72;
			x = 60;			
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
	
}
