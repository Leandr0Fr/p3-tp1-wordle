package interfaz;

import ranking.Ranking;

import java.awt.Color;
import java.awt.EventQueue;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class Interfaz {

	private Recurso recurso;
	private JFrame frame;
	private JPanel mainContainer;
	private JLabel[] titulo;
	private JLabel[][] tablero;
	private int posLetra;
	private int posFila;
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

	public Interfaz() {
		initialize();
	}

	private void initialize() {
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
		crearTagContainer();
		mainContainer.setBounds(0, 0, 456, 593);
		frame.getContentPane().add(mainContainer);
		mainContainer.setLayout(null);
		mainContainer.setBackground(Color.WHITE);
		limpiarPantalla();
		menuIdioma();
		addEventosDeTeclado();

	}

	private void crearTagContainer() {
		tagContainer = new JPanel();
		lblIngreseTag = new JLabel();
		lblEnviarTag = new JLabel();
		recurso.crearTagContainer(frame, tagContainer, lblIngreseTag, lblEnviarTag);

		lblEnviarTag.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				enviarTag();
			}

		});
	}

	private void menuIdioma() {
		limpiarPantalla();
		posLetra = 0;
		posFila = 0;

		btnEsp = new JButton();
		recurso.crearBtnEsp(mainContainer, btnEsp);
		btnEsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEnglish = false;
				limpiarPantalla();
				menuPrincipal();
			}
		});

		btnEng = new JButton();
		recurso.crearBtnEng(mainContainer, btnEng);
		btnEng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEnglish = true;
				limpiarPantalla();
				menuPrincipal();
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

	private void mostrarBandera() {
		JLabel lblBanderaIdioma = new JLabel("");
		StringBuilder path = new StringBuilder();
		path.append("/interfaz/");
		path.append(isEnglish ? "gb" : "ar");
		path.append(".png");
		lblBanderaIdioma.setIcon(new ImageIcon(Interfaz.class.getResource(path.toString())));
		lblBanderaIdioma.setBounds(209, 539, 32, 32);
		mainContainer.add(lblBanderaIdioma);
	}

	private void crearRankings() {
		rkFacil = new Ranking("Facil");
		rkNormal = new Ranking("Normal");
		rkDificil = new Ranking("Dificil");
		recurso.crearRankings(mainContainer, rkFacil, rkNormal, rkDificil);
	}

	private void colocarLetra(KeyEvent e) {
		if (tablero[posFila][posLetra].getText() != " ")
			return;

		char letra = game.toMayus(e.getKeyChar());
		tablero[posFila][posLetra].setText("" + letra);
		posLetra += posLetra != tablero[0].length - 1 ? 1 : 0;
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
			JOptionPane.showMessageDialog(null, isEnglish ? "Word does not exists" : "No existe la palabra");
			return;
		}

		EstadoCasillero[] resultado = game.obtenerAciertos(palabraEnviada);
		colorearLetras(resultado);

		if (game.terminarIntento()) {
			JOptionPane.showMessageDialog(null, isEnglish ? "WINNER!" : "¡GANASTE!");
			mostrarTagContainer();
		}

		if (posFila == 5) {
			game.setIsOver();
			JOptionPane.showMessageDialog(null, isEnglish ? "YOU LOST!" : "¡PERDISTE!");
			return;
		}
		posFila++;
		posLetra = 0;
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

	private void mostrarTagContainer() {
		tagJugador = new JLabel[3];
		lblIngreseTag.setText(isEnglish ? "Enter your TAG: " : "Ingrese su TAG: ");
		lblEnviarTag.setText(isEnglish ? "SUBMIT" : "ENVIAR");
		recurso.mostrarTagContainer(tagContainer, tagJugador);
		updateFrame();
	}

	private void colocarLetraEnTag(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER && lblEnviarTag.isEnabled())
			enviarTag();

		if (!game.esLetraValida(e.getKeyChar()))
			return;

		for (int i = 0; i < tagJugador.length; i++) {

			if (i == tagJugador.length - 1)
				lblEnviarTag.setEnabled(true);

			if (tagJugador[i].getText() == " ") {
				char letraMayus = game.toMayus(e.getKeyChar());
				tagJugador[i].setText("" + letraMayus);
				break;
			}
		}
	}

	private void enviarTag() {
		if (game.getDificultad() == Dificultad.facil) {
			rkFacil.agregarPuntaje(pedirNombreJugador(), game.getMinuto(), game.getSegundo());

		} else if (game.getDificultad() == Dificultad.normal) {
			rkNormal.agregarPuntaje(pedirNombreJugador(), game.getMinuto(), game.getSegundo());

		} else
			rkDificil.agregarPuntaje(pedirNombreJugador(), game.getMinuto(), game.getSegundo());

		tagContainer.setVisible(false);
		menuIdioma();
	}

	private String pedirNombreJugador() {
		StringBuilder sb = new StringBuilder();
		for (JLabel c : tagJugador)
			sb.append(c.getText());

		return sb.toString();
	}

	private void addEventosDeTeclado() {
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (tagJugador != null && tagContainer.isVisible())
					colocarLetraEnTag(e);

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

	private void limpiarPantalla() {
		mainContainer.removeAll();
		recurso.crearTitulo(mainContainer, titulo);
		recurso.crearAnio(mainContainer);
		recurso.crearLogo(mainContainer);
		mostrarBandera();
		updateFrame();
	}

	private void updateFrame() {
		SwingUtilities.updateComponentTreeUI(frame);
		frame.setVisible(true);
		frame.toFront();
		frame.requestFocus();
	}
}