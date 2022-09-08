package interfaz;

import modelo.Game;
import modelo.Game.EstadoCasillero;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import javax.swing.Timer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Cursor;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;

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
					enviarPalabra();
					return;
				}

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
		recurso.crearRankings(frame);

		updateFrame();
	}

	private void limpiarPantalla() {
		frame.getContentPane().removeAll();
		recurso.crearTitulo(frame, titulo);
		recurso.crearAnio(frame);
		recurso.crearLogo(frame);
		updateFrame();
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

		EstadoCasillero[] resultado = game.aciertosJugador(palabraEnviada);
		colorearLetras(resultado);

		if (game.terminarIntento(palabraEnviada)) {
			JOptionPane.showMessageDialog(null, "¡GANASTE!");
			pedirNombreJugador();
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