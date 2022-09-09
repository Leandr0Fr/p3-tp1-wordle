package interfaz;

import ranking.Ranking;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import modelo.Game;
import modelo.Game.Dificultad;
import modelo.Game.EstadoCasillero;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Interfaz {

	private Recurso recurso;
	private JFrame frame;
	private JPanel mainContainer;
	private JLabel[] titulo;
	private JLabel[][] tablero;
	private int posLetra = 0;
	private int posFila = 0;
	private int LEN_PALABRA;

	private JButton btnEsp;
	private JButton btnEng;
	private boolean isEnglish;

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

	private JPanel tagContainer;
	private JLabel lblIngreseTag;
	private JLabel[] tagJugador;
	private JLabel lblEnviarTag;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.mainContainer.setVisible(true);
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
		mainContainer = new JPanel();
		frame.setResizable(false);
		frame.setTitle("w-UNGS-dle");
		miPantalla = Toolkit.getDefaultToolkit();
		miIcono = miPantalla.getImage("src/interfaz/icono.png");
		frame.setIconImage(miIcono);

		frame.setBounds(0, 0, 472, 632);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		tagContainer = new JPanel();
		tagContainer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tagContainer.setBounds(93, 181, 233, 160);
		tagContainer.setLayout(null);
		tagContainer.setVisible(false);
		frame.getContentPane().add(tagContainer);

		lblIngreseTag = new JLabel(isEnglish ? "Enter your TAG: " : "Ingrese su TAG: ");
		lblIngreseTag.setBounds(10, 11, 227, 14);
		tagContainer.add(lblIngreseTag);

		lblEnviarTag = new JLabel(isEnglish ? "SUBMIT" : "ENVIAR");
		lblEnviarTag.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (game.getDificultad() == Dificultad.facil) {
					rkFacil.agregarPuntaje(pedirNombreJugador(), game.getMinuto(), game.getSegundo());

				} else if (game.getDificultad() == Dificultad.normal) {
					rkNormal.agregarPuntaje(pedirNombreJugador(), game.getMinuto(), game.getSegundo());

				} else
					rkDificil.agregarPuntaje(pedirNombreJugador(), game.getMinuto(), game.getSegundo());

				tagContainer.setVisible(false);
			}
		});

		lblEnviarTag.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnviarTag.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEnviarTag.setBackground(Color.WHITE);
		lblEnviarTag.setOpaque(true);
		lblEnviarTag.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblEnviarTag.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEnviarTag.setBounds(73, 116, 76, 33);
		lblEnviarTag.setEnabled(false);
		tagContainer.add(lblEnviarTag);

		mainContainer.setBounds(0, 0, 456, 593);
		frame.getContentPane().add(mainContainer);
		mainContainer.setLayout(null);
		mainContainer.setBackground(Color.WHITE);

		// title
		limpiarPantalla();
		menuIdioma();

		// keyListener
		addEventosDeTeclado();

	}

	private void menuIdioma() {
		btnEsp = new JButton("C A S T E L L A N O");
		recurso.crearBtnEsp(mainContainer, btnEsp);
		btnEsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEnglish = false;
				limpiarPantalla();
				menuPrincipal();
			}
		});

		btnEng = new JButton("     E N G L I S H");
		recurso.crearBtnEng(mainContainer, btnEng);
		btnEng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEnglish = true;
				limpiarPantalla();
				menuPrincipal();
			}
		});
	}

	private void addEventosDeTeclado() {
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("" + e.getKeyChar());
				if (tagJugador != null && tagContainer.isVisible() && game.esLetraValida(e.getKeyChar())) {
					colocarLetraEnTag(e);
				}

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
				btnJugar.setText(isEnglish ? "Play on EASY" : "Jugar en FÁCIL");
				btnJugar.setEnabled(true);
			}
		});

		btnPlayNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LEN_PALABRA = 5;
				btnJugar.setText(isEnglish ? "Play on NORMAL" : "Jugar en NORMAL");
				btnJugar.setEnabled(true);
			}
		});

		btnPlayDificil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LEN_PALABRA = 6;
				btnJugar.setText(isEnglish ? "Play on HARD" : "Jugar en DIFÍCIL");
				btnJugar.setEnabled(true);
			}
		});

		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarPantalla();
				game = new Game(LEN_PALABRA, isEnglish);
				tablero = new JLabel[6][LEN_PALABRA];
				recurso.crearTablero(mainContainer, LEN_PALABRA, tablero);
			}
		});
	}

	private void menuPrincipal() {
		btnPlayFacil = new JButton(isEnglish ? "Easy" : "Facil");
		btnPlayNormal = new JButton("Normal");
		btnPlayDificil = new JButton(isEnglish ? "Hard" : "Dificil");
		btnJugar = new JButton(isEnglish ? "Play on ----" : "Jugar en ----");
		recurso.crearBtnFacil(mainContainer, btnPlayFacil);
		recurso.crearBtnNormal(mainContainer, btnPlayNormal);
		recurso.crearBtnDificil(mainContainer, btnPlayDificil);
		recurso.crearBtnJugar(mainContainer, btnJugar);
		addEventosDeBtn();
		crearRankings();
		updateFrame();
	}

	private void limpiarPantalla() {
		mainContainer.removeAll();
		recurso.crearTitulo(mainContainer, titulo);
		recurso.crearAnio(mainContainer);
		recurso.crearLogo(mainContainer);
		updateFrame();
	}

	private void crearRankings() {
		rkFacil = new Ranking("Facil");
		rkNormal = new Ranking("Normal");
		rkDificil = new Ranking("Dificil");
		recurso.crearRankings(mainContainer, rkFacil, rkNormal, rkDificil);
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
			JOptionPane.showMessageDialog(null, isEnglish ? "¡WINNER!" : "¡GANASTE!");
			mostrarTagContainer();
		}

		if (posFila == 5) {
			game.setIsOver();
			JOptionPane.showMessageDialog(null, isEnglish ? "¡YOU LOST!" : "¡PERDISTE!");
			return;
		}
		posFila++;
		posLetra = 0;
	}

	private void mostrarTagContainer() {
		tagJugador = new JLabel[3];
		for (int i = 0; i < tagJugador.length; i++) {
			tagJugador[i] = new JLabel(" ");
			tagJugador[i].setOpaque(true);
			tagJugador[i].setHorizontalAlignment(SwingConstants.CENTER);
			tagJugador[i].setFont(new Font("Consolas", Font.PLAIN, 60));
			tagJugador[i].setBackground(Color.WHITE);
			tagJugador[i].setBounds(10 + i * 72, 24, 64, 64);
			tagContainer.add(tagJugador[i]);
		}

		tagContainer.setVisible(true);
		tagContainer.requestFocus();
		updateFrame();
	}

	private void colocarLetraEnTag(KeyEvent e) {
		for (int i = 0; i < tagJugador.length; i++) {

			if (i == tagJugador.length - 1)
				lblEnviarTag.setEnabled(true);

			if (tagJugador[i].getText() == " ") {
				char letraMayus = game.mayus(e.getKeyChar());
				tagJugador[i].setText("" + letraMayus);
				break;
			}
		}

	}

	private String pedirNombreJugador() {
		StringBuilder sb = new StringBuilder();
		for (JLabel c : tagJugador)
			sb.append(c.getText());

		return sb.toString();
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
		// 7495769 Codigo seguimiento pami
	}
}