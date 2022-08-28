package interfaz;

import modelo.LogicGame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class Interfaz {

	private JFrame frame;
	private JLabel[] titulo;
	private JLabel[][] tablero; 
	private int posLetra = 0;
	private int posFila = 0;
	private int LEN_PALABRA;
	private LogicGame game;
	private enum estadosLetra{verde,amarillo,gris};


	private Toolkit miPantalla;	
	private Image miIcono;

	private Color VERDE = new Color(106, 170, 100);
	private Color AMARILLO = new Color(201, 180, 88);
	private Color GRIS = new Color(120, 124, 126);
	
	private Font fuenteSourceCodeSmall = new Font("Source Code Pro", Font.PLAIN, 16);
	private Font fuenteSourceCodeMedium = new Font("Source Code Pro", Font.PLAIN, 32);
	private Font fuenteSourceCodeBig = new Font("Source Code Pro", Font.PLAIN, 64);

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
		LEN_PALABRA = 6;
		game = new LogicGame(LEN_PALABRA);
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("w-UNGS-dle");
		miPantalla = Toolkit.getDefaultToolkit();	
		miIcono = miPantalla.getImage("src/interfaz/icono.png");
		frame.setIconImage(miIcono);

		frame.setBounds(0, 0, 472, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//title
		crearTitulo();

		//Start button
		JButton btnStartGame = new JButton("START!");
		btnStartGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStartGame.setFont(fuenteSourceCodeSmall);
		btnStartGame.setBorderPainted(true);
		btnStartGame.setFocusPainted(false);
		btnStartGame.setContentAreaFilled(true);
		btnStartGame.setBackground(Color.WHITE);

		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearTablero(LEN_PALABRA);
				btnStartGame.setEnabled(false);
				btnStartGame.setVisible(false);
				updateFrame();
			}
		});

		btnStartGame.setBounds(128, 221, 126, 37);
		frame.getContentPane().add(btnStartGame);
		updateFrame();
		//
		
		
		
		//keyListener
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ESCAPE) 
					System.exit(0);
				
				if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) { 
					borrarLetra();
					return;
				}
				
				if (e.getKeyChar() == KeyEvent.VK_ENTER && posLetra >= LEN_PALABRA-1 && tablero[posFila][LEN_PALABRA-1].getText() != " ") {
					enviarPalabra();
					return;
				}

				if (!game.esTeclaValida(e) || posLetra > LEN_PALABRA-1)
					return;
				
				if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE && e.getKeyChar() != KeyEvent.VK_ENTER)
					colocarLetra(e);
			}
		});
		
	}

	private void crearTablero(int tamanoPalabra){
		int x = 16 + 36 * (6%LEN_PALABRA);
		int y = 72;
		int CENTER = 0;
		tablero = new JLabel[6][tamanoPalabra];
		for (int f = 0; f < 6; f++) {
			for (int c = 0; c <tamanoPalabra ; c++) {
				tablero[f][c] = new JLabel();
				tablero[f][c].setFont(fuenteSourceCodeBig);
				tablero[f][c].setText(" ");
				tablero[f][c].setBackground(Color.WHITE);
				tablero[f][c].setOpaque(true);
				tablero[f][c].setHorizontalAlignment(CENTER);
				System.out.println(6%LEN_PALABRA);
				tablero[f][c].setBounds(x,y,64,64);
				frame.getContentPane().add(tablero[f][c]);
				x+= 72;
			}
			y+= 72;
			x = 16 + 36 * (6%LEN_PALABRA);			
		}
	}

	private void crearTitulo(){
		int x = 88;
		int y = 20;
		int CENTER = 0;
		char[] gameTitleChars = {'w', 'U', 'N', 'G', 'S', 'd', 'l', 'e'};
		int LEN_TITLE_CHARS = gameTitleChars.length;
		titulo = new JLabel[LEN_TITLE_CHARS];

		for (int i = 0; i < LEN_TITLE_CHARS; i++) {
			titulo[i] = new JLabel();
			titulo[i].setFont(fuenteSourceCodeMedium);
			titulo[i].setText(" ");
			titulo[i].setBackground(Color.WHITE);
			titulo[i].setOpaque(true);
			titulo[i].setHorizontalAlignment(CENTER);
			titulo[i].setBounds(x,y,32,32);
			titulo[i].setText("" + gameTitleChars[i]);
			frame.getContentPane().add(titulo[i]);
			x += 36;
		}
		
	}

	private void borrarLetra() {
		if (posLetra > LEN_PALABRA){
			posLetra = LEN_PALABRA-1;
		}
		
		if (posLetra != 0 && tablero[posFila][posLetra].getText() == " " ) {
			posLetra--;
		}

		tablero[posFila][posLetra].setText(" ");
	}
	
	private void enviarPalabra() {
		char[] palabraEnviada = new char[LEN_PALABRA];
		for (int i = 0; i < tablero[posFila].length; i++) {
			palabraEnviada[i] = tablero[posFila][i].getText().charAt(0);
		}
		
		// ### PRUEBA COLOREAR
		estadosLetra[] resultado = {estadosLetra.amarillo, estadosLetra.amarillo, estadosLetra.gris, estadosLetra.verde, estadosLetra.verde, estadosLetra.gris};
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
		
		char letra = game.mayus(e.getKeyChar());
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
		celda.setBackground(VERDE);
		colorearTextoBlanco(celda);
	}

	private void colorearAmarillo(JLabel celda) {
		celda.setBackground(AMARILLO);
		colorearTextoBlanco(celda);
	}

	private void colorearGris(JLabel celda) {
		celda.setBackground(GRIS);
		colorearTextoBlanco(celda);
	}
	
	private void colorearTextoBlanco(JLabel celda) {
		celda.setForeground(Color.WHITE);
	}
	
	private void updateFrame() {
		SwingUtilities.updateComponentTreeUI(frame);
		frame.setVisible(true);
		frame.toFront();
		frame.requestFocus();
	}
}