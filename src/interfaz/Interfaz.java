package interfaz;

<<<<<<< HEAD
import modelo.LogicGame;
=======
import modelo.Game;
import modelo.Game.EstadoCasillero;
import ranking.Ranking;
>>>>>>> pre-main

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
<<<<<<< HEAD

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
=======
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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Interfaz {

	private Recurso recurso;
	private JFrame frame;
	private JLabel[] titulo;
	private JLabel[][] tablero;
	private int posLetra = 0;
	private int posFila = 0;
	private int LEN_PALABRA;

	private Game game;

	protected JButton btnJugar;
	private JButton btnPlayFacil;
	private JButton btnPlayNormal;
	private JButton btnPlayDificil;

	private Toolkit miPantalla;
	private Image miIcono;

	private Ranking rkFacil;
	private Ranking rkNormal;
	private Ranking rkDificil;

>>>>>>> pre-main
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
<<<<<<< HEAD
		//Pantalla principal
		game = new LogicGame(5,"Menem");
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
=======
		// Pantalla principal
		LEN_PALABRA = 5;
		recurso = new Recurso();
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setTitle("w-UNGS-dle");
		miPantalla = Toolkit.getDefaultToolkit();
		miIcono = miPantalla.getImage("src/interfaz/icono.png");
		frame.setIconImage(miIcono);

		frame.setBounds(0, 0, 472, 632);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// title

		JLabel contador = new JLabel("00:00");
		contador.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		contador.setHorizontalAlignment(SwingConstants.CENTER);
		contador.setBounds(184, 511, 80, 25);
		frame.getContentPane().add(contador);

		// title
		limpiarPantalla();
		menuPrincipal();

		// keyListener
		addEventosDeTeclado();

		addEventosDeBtn();
	}

	private void addEventosDeTeclado() {
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (tablero == null || game.getIsOver())
					return;

				if (e.getKeyChar() == KeyEvent.VK_ESCAPE)
					System.exit(0);

				if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
					borrarLetra();
					return;
				}

				if (e.getKeyChar() == KeyEvent.VK_ENTER && posLetra >= LEN_PALABRA - 1
						&& tablero[posFila][LEN_PALABRA - 1].getText() != " ") {
>>>>>>> pre-main
					enviarPalabra();
					return;
				}

<<<<<<< HEAD
				if (!esTeclaValida(e) || posLetra > 4)
					return;
				
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
}

	
=======
				if (!game.esTeclaValida(e) || posLetra > LEN_PALABRA - 1)
					return;

				if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE && e.getKeyChar() != KeyEvent.VK_ENTER)
					colocarLetra(e);
			}
		});

	}

	private void addEventosDeBtn() {
		btnPlayFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LEN_PALABRA = 4;
				btnJugar.setText("Jugar en FÁCIL");
				btnJugar.setEnabled(true);
			}
		});

		btnPlayNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LEN_PALABRA = 5;
				btnJugar.setText("Jugar en NORMAL");
				btnJugar.setEnabled(true);
			}
		});

		btnPlayDificil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LEN_PALABRA = 6;
				btnJugar.setText("Jugar en DIFÍCIL");
				btnJugar.setEnabled(true);
			}
		});

		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarPantalla();
				game = new Game(LEN_PALABRA);
				tablero = new JLabel[6][LEN_PALABRA];
				recurso.crearTablero(frame, LEN_PALABRA, tablero);
			}
		});
	}

	private void menuPrincipal() {
		btnPlayFacil = new JButton("Facil");
		btnPlayNormal = new JButton("Normal");
		btnPlayDificil = new JButton("Dificil");
		btnJugar = new JButton("Jugar en ----");
		recurso.crearBtnFacil(frame, btnPlayFacil);
		recurso.crearBtnNormal(frame, btnPlayNormal);
		recurso.crearBtnDificil(frame, btnPlayDificil);
		recurso.crearBtnJugar(frame, btnJugar);
		crearRankings();
		updateFrame();
	}

	private void limpiarPantalla() {
		frame.getContentPane().removeAll();
		recurso.crearTitulo(frame, titulo);
		recurso.crearAnio(frame);
		recurso.crearLogo(frame);
		updateFrame();
	}

	private void crearRankings() {
		rkFacil = new Ranking("Facil");
		rkNormal = new Ranking("Normal");
		rkDificil = new Ranking("Dificil");
		recurso.crearRankings(frame, rkFacil, rkNormal, rkDificil);
	}

	private void borrarLetra() {
		if (posLetra > LEN_PALABRA) {
			posLetra = LEN_PALABRA - 1;
		}

		if (posLetra != 0 && tablero[posFila][posLetra].getText() == " ") {
			posLetra--;
		}

		tablero[posFila][posLetra].setText(" ");
	}

	private void enviarPalabra() {
		char[] palabraEnviada = new char[LEN_PALABRA];
		for (int i = 0; i < tablero[posFila].length; i++) {
			palabraEnviada[i] = tablero[posFila][i].getText().charAt(0);
		}

		if (!game.perteneceAlListado(palabraEnviada)) {
			JOptionPane.showMessageDialog(null, "No existe la palabra");
			return;
		}

		EstadoCasillero[] resultado = game.aciertosJugador(palabraEnviada);
		colorearLetras(resultado);

		if (game.terminarIntento(palabraEnviada)) {
			JOptionPane.showMessageDialog(null, "¡GANASTE!");
			pedirNombreJugador();
			return;
		}

		if (posFila == 5) {
			game.setIsOver();
			JOptionPane.showMessageDialog(null, "¡PERDISTE!");
			return;
		}
		posFila++;
		posLetra = 0;
	}

	private String pedirNombreJugador() {
		String nombre = JOptionPane.showInputDialog("INGRESE SU TAG");
		return nombre;
	}

	private void colocarLetra(KeyEvent e) {
		if (tablero[posFila][posLetra].getText() != " ")
			return;

		char letra = game.mayus(e.getKeyChar());
		tablero[posFila][posLetra].setText("" + letra);
		posLetra += posLetra != tablero[0].length - 1 ? 1 : 0;
	}

	private void colorearLetras(EstadoCasillero[] resultados) {
		for (int i = 0; i < tablero[0].length; i++) {
			if (resultados[i] == EstadoCasillero.verde)
				recurso.colorearVerde(tablero[posFila][i]);

			else if (resultados[i] == EstadoCasillero.amarillo)
				recurso.colorearAmarillo(tablero[posFila][i]);

			else if (resultados[i] == EstadoCasillero.gris)
				recurso.colorearGris(tablero[posFila][i]);
		}
	}

	private void updateFrame() {
		SwingUtilities.updateComponentTreeUI(frame);
		frame.setVisible(true);
		frame.toFront();
		frame.requestFocus();
	}
}
>>>>>>> pre-main
