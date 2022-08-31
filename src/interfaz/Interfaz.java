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
import javax.swing.Timer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Interfaz {

	private JFrame frame;
	private JLabel[] titulo;
	private JLabel[][] tablero; 
	private int posLetra = 0;
	private int posFila = 0;
	private int LEN_PALABRA;
	private LogicGame game;
	private enum estadosLetra{verde,amarillo,gris};
	private Timer tiempo;
	private int centesimasDeSegundo = 0;
	private int segundo = 0;
	private int minuto = 0;
	

	private Color VERDE = new Color(106, 170, 100);
	private Color AMARILLO = new Color(201, 180, 88);
	private Color GRIS = new Color(120, 124, 126);
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
		LEN_PALABRA = 5;
		game = new LogicGame(LEN_PALABRA);
		frame = new JFrame();
		Toolkit miPantalla = Toolkit.getDefaultToolkit();	
		Image miIcono = miPantalla.getImage("src/interfaz/icono.png");
		
		frame.setIconImage(miIcono);
		// (x,y, 64 * length word, 64 * 6 + 8 * 5 + 64 * 3)
		frame.setBounds(0, 0, 400, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//title
		crearTitulo();
		
		JLabel contador = new JLabel("00:00");
		contador.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		contador.setHorizontalAlignment(SwingConstants.CENTER);
		contador.setBounds(150, 525, 80, 25);
		frame.getContentPane().add(contador);
		
		//Start button && actions	
		JButton btnStartGame = new JButton("START!");
		btnStartGame.setFont(new Font("Consolas", Font.PLAIN, 11));

		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tiempo.start();
				crearTablero(5);
				btnStartGame.setEnabled(false);
				btnStartGame.setVisible(false);
				updateFrame();
			}
		});

		btnStartGame.setBounds(206, 273, 89, 23);
		frame.getContentPane().add(btnStartGame);
		updateFrame();
		
		//keyListener
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
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
		ActionListener accion = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				centesimasDeSegundo++;
				if(centesimasDeSegundo==60) {
					segundo++;
					centesimasDeSegundo=0;
				}
				if(segundo==60) {
					minuto++;
					segundo=0;
					JOptionPane.showMessageDialog(null, "¡GANASTE!");
					JOptionPane.showInputDialog(null, "INGRESE SU NOMBRE");
				}
				if(minuto==60) {
					minuto=0;
				}
				actualizarContador(contador);
			}
		};
		tiempo = new Timer(10, accion);
		
	}
	
	private void actualizarContador(JLabel contador) {
		System.out.println("centesimas: "+ centesimasDeSegundo);
		String texto = (minuto <= 9?"0":"") + minuto + ":" + (segundo <= 9?"0":"")+ segundo;
		contador.setText(texto);
	}

	private void crearTablero(int tamanoPalabra){
		int x = 16;
		int y = 72;
		int CENTER = 0;
		tablero = new JLabel[6][tamanoPalabra];
		for (int f = 0; f < 6; f++) {
			for (int c = 0; c <tamanoPalabra ; c++) {
				tablero[f][c] = new JLabel();
				tablero[f][c].setFont(new Font("Source Code Pro", Font.PLAIN, 64));
				tablero[f][c].setText(" ");
				tablero[f][c].setBackground(Color.WHITE);
				tablero[f][c].setOpaque(true);
				tablero[f][c].setHorizontalAlignment(CENTER);
				tablero[f][c].setBounds(x,y,64,64);
				frame.getContentPane().add(tablero[f][c]);
				x+= 72;
			}
			y+= 72;
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
		celda.setForeground(Color.white);
	}
	
	private void updateFrame() {
		SwingUtilities.updateComponentTreeUI(frame);
		frame.setVisible(true);
		frame.toFront();
		frame.requestFocus();
	}
}
