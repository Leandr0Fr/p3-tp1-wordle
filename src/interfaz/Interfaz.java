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
		int posLetra = 0;
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
		frame = new JFrame();
		frame.setBounds(100, 100, 524, 574);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//tablero	
		crearTablero(5);
		
		//keyListener
		int posIntento = 0;
		
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!esTeclaValida(e))
					return;
				
				System.out.println(0 + e.getKeyChar());
				tablero[0][posLetra++].setText("" + e.getKeyChar());

			}

			private boolean esTeclaValida(KeyEvent e) {
				//ascii 65 - 90 (209 = Ñ | 241 ñ) 97 - 122
				return e.getKeyChar() == 209 || e.getKeyChar() == 241 ||
					  e.getKeyChar() >= 65 && e.getKeyChar() <= 90
					  || e.getKeyChar() >= 97 && e.getKeyChar() <= 122;
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
				tablero[f][c].setFont(new Font("Source Core Pro", Font.PLAIN, 64));
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
}
