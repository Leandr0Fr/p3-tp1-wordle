package interfaz;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import ranking.Ranking;

public class Recurso {

	private Font fuenteSmall = new Font("Consolas", Font.PLAIN, 12);
	private Font fuenteMedium = new Font("Consolas", Font.PLAIN, 28);
	private Font fuenteBig = new Font("Arial", Font.PLAIN, 60);
	private Font fuenteRK = new Font("Consolas", Font.BOLD, 20);

	private Color VERDE = new Color(106, 170, 100);
	private Color AMARILLO = new Color(201, 180, 88);
	private Color GRIS = new Color(120, 124, 126);
	private Color BORDER_COLOUR = new Color(120, 120, 120);

	protected JButton crearBtnFacil() {
		JButton btnPlayFacil = new JButton();
		btnPlayFacil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPlayFacil.setFont(fuenteSmall);
		btnPlayFacil.setBorderPainted(true);
		btnPlayFacil.setFocusPainted(false);
		btnPlayFacil.setContentAreaFilled(true);
		btnPlayFacil.setBackground(Color.WHITE);
		btnPlayFacil.setBounds(20, 133, 126, 37);
		return btnPlayFacil;
	}

	protected JButton crearBtnNormal() {
		JButton btnPlayNormal = new JButton();
		btnPlayNormal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPlayNormal.setFont(fuenteSmall);
		btnPlayNormal.setBorderPainted(true);
		btnPlayNormal.setFocusPainted(false);
		btnPlayNormal.setContentAreaFilled(true);
		btnPlayNormal.setBackground(Color.WHITE);
		btnPlayNormal.setBounds(164, 133, 126, 37);
		return btnPlayNormal;
	}

	protected JButton crearBtnDificil(){
		JButton btnPlayDificil = new JButton();
		btnPlayDificil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPlayDificil.setFont(fuenteSmall);
		btnPlayDificil.setBorderPainted(true);
		btnPlayDificil.setFocusPainted(false);
		btnPlayDificil.setContentAreaFilled(true);
		btnPlayDificil.setBackground(Color.WHITE);
		btnPlayDificil.setBounds(306, 133, 126, 37);
		return btnPlayDificil;
	}

	protected JButton crearBtnJugar() {
		JButton btnJugar= new JButton();
		btnJugar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnJugar.setFont(fuenteSmall);
		btnJugar.setBorderPainted(true);
		btnJugar.setFocusPainted(false);
		btnJugar.setContentAreaFilled(true);
		btnJugar.setBackground(Color.WHITE);
		btnJugar.setEnabled(false);
		btnJugar.setBounds(124, 380, 209, 37);
		return btnJugar;
	}

	protected JLabel[][] crearTablero(int LEN_PALABRA, JPanel mainContainer) {
		JLabel[][] tablero = new JLabel[6][LEN_PALABRA];
		int x = 16 + 36 * (6 % LEN_PALABRA);
		int y = 72;
		for (int f = 0; f < 6; f++) {
			for (int c = 0; c < LEN_PALABRA; c++) {
				tablero[f][c] = new JLabel();
				tablero[f][c].setFont(fuenteBig);
				tablero[f][c].setText(" ");
				tablero[f][c].setBackground(Color.WHITE);
				tablero[f][c].setOpaque(true);
				tablero[f][c].setHorizontalAlignment(SwingConstants.CENTER);
				tablero[f][c].setVerticalAlignment(SwingConstants.TOP);
				tablero[f][c].setBorder(new LineBorder(BORDER_COLOUR, 1));
				tablero[f][c].setBounds(x, y, 64, 64);
				mainContainer.add(tablero[f][c]);
				x += 72;
			}
			y += 72;
			x = 16 + 36 * (6 % LEN_PALABRA);
		}
		return tablero;
	}

	protected void crearAnio(JPanel mainContainer) {
		JLabel lblAnio = new JLabel("2022");
		lblAnio.setFont(fuenteSmall);
		lblAnio.setBounds(415, 568, 31, 14);
		mainContainer.add(lblAnio);
	}

	protected void crearLogo(JPanel mainContainer) {
		JEditorPane ungsLogo = new JEditorPane();
		ungsLogo.setEditable(false);
		ungsLogo.setForeground(new Color(0, 128, 128));
		ungsLogo.setFont(new Font("Consolas", Font.PLAIN, 1));
		ungsLogo.setText(
				"                                           ~~~         ~~                                           \n"
						+ "                                    ~  7?JY5PP7       ?P5YJ?7  ~                                    \n"
						+ "                                  ?YPB#&@@@@@B        B@@@@@&#BPY? ~                                \n"
						+ "                            ~ ?YG#@@@@@@@@@@@J~      ?@@@@@@@@@@@@#GY7 ~                            \n"
						+ "                         ~ ?5#@@@@@@@@@@@@@@#        G@@@@@@@@@@@@@@@&B57                           \n"
						+ "                       ~7YB@@@@@@@@@@@@@@@@@Y~      7&@@@@@@@@@@@@@@@@@@&GJ ~                       \n"
						+ "                      75&@@@@@@@@@@@@@@@@@@&7      ~P@@@@@@@@@@@@@@@@@@@@@@#Y                       \n"
						+ "                     5&@@@@@@@@@@@@@@@@@@@@P~      7&@@@@@@#BGB&@@@@@@@@@@@@@#J                     \n"
						+ "                  ~J#@@@@@@@@@@@@@@@@@@@@@&7      ~5@@@@@#J    75@@@@@@@@@@@@@@G7                   \n"
						+ "                  5@@@@@@@@@@@@@@@@@@@@@@@G        #@@@@&?~     ~G@@@@@@@@@@@@@@#J~                 \n"
						+ "                 G@@@@@@@@@@@@@@@@@@@@@@@@?       J@@@@@G~       B@@@@@@@@@@@@@@@@Y                 \n"
						+ "                G@@@@@@@@@@@@@@@@@@@@@@@@B        B@@@@@?       J@@@@@@@@@@@@@@@@@@Y~               \n"
						+ "               P@@@@@@@@@@@@@@@@@@@@@@@@@J       ?@@@@@B        B@@@@@@@@@@@@@@@@@@@J               \n"
						+ "              Y@@@@@@@@@@@@@@@@@@@@@@@@@#        G@@@@@J       ?@@@@@@@@@@@@@@@@@@@@&7              \n"
						+ "              #@@@@@@@@@@@@@@@@@@@@@@@@@Y~      7&@@@@#       ~G@@@@@@@@@@@@@@@@@@@@@P              \n"
						+ "             Y@@@@@@@@@@@@@@@@@@@@@@@@@&7      ~P@@@@@Y       7&@@@@@@@@@@@@@@@@@@@@@&7             \n"
						+ "            ~G@@@@@@@@@@@@@@@@@@@@@@@@@5~      7&@@@@#       ~5@@@@@@@@@@@@@@@@@@@@@@@Y~            \n"
						+ "             B@@@@@@@@@@@@@@@@@@@@@@@@&7      ~5@@@@@5       7&@@@@@@@@@@@@@@@@@@@@@@@P~            \n"
						+ "             #@@@@@@@@@@@@@@@@@@@@@@@@G        #@@@@&7      ~Y@@@@@@@@@@@@@@@@@@@@@@@@G             \n"
						+ "             B@@@@@@@@@@@@@@@@@@@@@@@@?       Y@@@@@P~       #@@@@@@@@@@@@@@@@@@@@@@@@G~            \n"
						+ "            ~P@@@@@@@@@@@@@@@@@@@@@@@B        B@@@@@?       J@@@@@@@@@@@@@@@@@@@@@@@@@5~            \n"
						+ "             J@@@@@@@@@@@@@@@@@@@@@@@J       J@@@@@G        B@@@@@@@@@@@@@@@@@@@@@@@@@?             \n"
						+ "              B@@@@@@@@@@@@@@@@@@@@@#        G@@@@@J       ?@@@@@@@@@@@@@@@@@@@@@@@@@B              \n"
						+ "              J@@@@@@@@@@@@@@@@@@@@@Y       ?&@@@@#        G@@@@@@@@@@@@@@@@@@@@@@@@&?              \n"
						+ "               5@@@@@@@@@@@@@@@@@@@&       ~P@@@@@Y~      7&@@@@@@@@@@@@@@@@@@@@@@@@5~              \n"
						+ "                P@@@@@@@@@@@@@@@@@@5~      7&@@@@#       ~P@@@@@@@@@@@@@@@@@@@@@@@@P                \n"
						+ "                 P@@@@@@@@@@@@@@@@&7      ~5@@@@@5~      7&@@@@@@@@@@@@@@@@@@@@@@@P                 \n"
						+ "                  5&@@@@@@@@@@@@@@&7~      #@@@@&7      ~Y@@@@@@@@@@@@@@@@@@@@@@&Y                  \n"
						+ "                  ~?B@@@@@@@@@@@@@@#J7  7JB@@@@@P~       #@@@@@@@@@@@@@@@@@@@@@B?~                  \n"
						+ "                     5&@@@@@@@@@@@@@@&##&@@@@@@@?       J@@@@@@@@@@@@@@@@@@@@#Y                     \n"
						+ "                      75&@@@@@@@@@@@@@@@@@@@@@@G        B@@@@@@@@@@@@@@@@@@#57~                     \n"
						+ "                       ~75#@@@@@@@@@@@@@@@@@@@@J       ?@@@@@@@@@@@@@@@@@BY ~                       \n"
						+ "                         ~ ?P#@@@@@@@@@@@@@@@@B        G@@@@@@@@@@@@@@B5?                           \n"
						+ "                            ~ ?5B&@@@@@@@@@@@@Y~      7&@@@@@@@@@@#GY? ~                            \n"
						+ "                               ~ 7J5G#&@@@@@@#        P@@@@@&#BPY?                                  \n"
						+ "                                   ~  77JY5PGY        PP5YJ?7  ~                                    \n"
						+ "                                          ~~~          ~~                                           \n"
						+ "");
		ungsLogo.setBounds(0, 511, 111, 82);
		ungsLogo.setFocusable(false);
		mainContainer.add(ungsLogo);
	}

	protected void crearTitulo(JPanel mainContainer, JLabel[] titulo) {
		int x = 88;
		int y = 12;
		char[] gameTitleChars = { 'w', 'U', 'N', 'G', 'S', 'd', 'l', 'e' };
		int LEN_TITLE_CHARS = gameTitleChars.length;
		titulo = new JLabel[LEN_TITLE_CHARS];

		for (int i = 0; i < LEN_TITLE_CHARS; i++) {
			titulo[i] = new JLabel();
			titulo[i].setFont(fuenteMedium);
			titulo[i].setText(" ");
			titulo[i].setBackground(Color.WHITE);
			titulo[i].setOpaque(true);
			titulo[i].setHorizontalAlignment(SwingConstants.CENTER);
			titulo[i].setVerticalAlignment(SwingConstants.TOP);
			titulo[i].setBounds(x, y, 32, 32);
			titulo[i].setText("" + gameTitleChars[i]);
			titulo[i].setBorder(new LineBorder(BORDER_COLOUR, 1));
			mainContainer.add(titulo[i]);
			x += 36;
		}

	}

	protected void crearRankings(JPanel mainContainer, Ranking rkFacil, Ranking rkNormal, Ranking rkDificil) {
		JTextPane recordsFacil = new JTextPane();
		recordsFacil.setEditable(false);
		recordsFacil.setFont(fuenteRK);
		recordsFacil.setBackground(new Color(204, 204, 204));
		recordsFacil.setBounds(20, 181, 128, 126);

		String[] rkFacilString = rkFacil.obtenerRanking();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < rkFacilString.length - 1; i++) {
			sb.append(recordsFacil.getText());
			sb.append(rkFacilString[i]);
			sb.append('\n');
		}
		sb.append(rkFacilString[rkFacilString.length - 1]);
		recordsFacil.setText(sb.toString());
		mainContainer.add(recordsFacil);

		JTextPane recordsNormal = new JTextPane();
		recordsNormal.setEditable(false);
		recordsNormal.setFont(fuenteRK);
		recordsNormal.setBackground(new Color(204, 204, 204));
		recordsNormal.setBounds(164, 183, 128, 126);

		String[] rkNormalString = rkNormal.obtenerRanking();
		sb = new StringBuilder();

		for (int i = 0; i < rkNormalString.length - 1; i++) {
			sb.append(recordsNormal.getText());
			sb.append(rkNormalString[i]);
			sb.append('\n');
		}
		sb.append(rkNormalString[rkNormalString.length - 1]);
		recordsNormal.setText(sb.toString());
		mainContainer.add(recordsNormal);

		JTextPane recordsDificil = new JTextPane();
		recordsDificil.setEditable(false);
		recordsDificil.setFont(fuenteRK);
		recordsDificil.setBackground(new Color(204, 204, 204));
		recordsDificil.setBounds(306, 181, 128, 126);

		String[] rkDificilString = rkDificil.obtenerRanking();
		sb = new StringBuilder();

		for (int i = 0; i < rkDificilString.length - 1; i++) {
			sb.append(recordsDificil.getText());
			sb.append(rkDificilString[i]);
			sb.append('\n');
		}
		sb.append(rkDificilString[rkDificilString.length - 1]);
		recordsDificil.setText(sb.toString());

		mainContainer.add(recordsDificil);
	}

	protected void colorearVerde(JLabel celda) {
		celda.setBackground(VERDE);
		colorearTextoBlanco(celda);
	}

	protected void colorearAmarillo(JLabel celda) {
		celda.setBackground(AMARILLO);
		colorearTextoBlanco(celda);
	}

	protected void colorearGris(JLabel celda) {
		celda.setBackground(GRIS);
		colorearTextoBlanco(celda);
	}

	protected void colorearTextoBlanco(JLabel celda) {
		celda.setForeground(Color.WHITE);
	}

	protected JButton crearBtnEsp() {
		JButton btnEsp = new JButton();
		btnEsp.setText("C A S T E L L A N O");
		btnEsp.setFont(fuenteSmall);
		btnEsp.setIcon(new ImageIcon(Interfaz.class.getResource("/interfaz/ar.png")));
		btnEsp.setIconTextGap(20);
		btnEsp.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnEsp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEsp.setHorizontalAlignment(SwingConstants.LEFT);
		btnEsp.setBorderPainted(true);
		btnEsp.setFocusPainted(false);
		btnEsp.setContentAreaFilled(true);
		btnEsp.setBackground(Color.WHITE);
		btnEsp.setBounds(115, 156, 230, 60);
		return btnEsp;
	}

	protected JButton crearBtnEng() {
		JButton btnEng = new JButton();
		btnEng.setText("   E N G L I S H");
		btnEng.setFont(fuenteSmall);
		btnEng.setIcon(new ImageIcon(Interfaz.class.getResource("/interfaz/gb.png")));
		btnEng.setIconTextGap(20);
		btnEng.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnEng.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEng.setHorizontalAlignment(SwingConstants.LEFT);
		btnEng.setBorderPainted(true);
		btnEng.setFocusPainted(false);
		btnEng.setContentAreaFilled(true);
		btnEng.setBackground(Color.WHITE);
		btnEng.setBounds(115, 320, 230, 60);
		return btnEng;
	}

	protected JPanel crearTagContainer(JLabel lblIngreseTag, JLabel lblEnviarTag) {
		JPanel tagContainer = new JPanel();
		tagContainer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tagContainer.setBounds(93, 181, 233, 160);
		tagContainer.setLayout(null);
		tagContainer.setVisible(false);
		lblIngreseTag.setBounds(10, 11, 227, 14);
		tagContainer.add(lblIngreseTag);
		lblEnviarTag.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnviarTag.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEnviarTag.setBackground(Color.WHITE);
		lblEnviarTag.setOpaque(true);
		lblEnviarTag.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblEnviarTag.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEnviarTag.setBounds(73, 116, 76, 33);
		lblEnviarTag.setEnabled(false);
		tagContainer.add(lblEnviarTag);
		return tagContainer;
	}

	protected void mostrarTagContainer(JPanel tagContainer, JLabel[] tagJugador) {
		for (int i = 0; i < tagJugador.length; i++) {
			tagJugador[i] = new JLabel(" ");
			tagJugador[i].setOpaque(true);
			tagJugador[i].setHorizontalAlignment(SwingConstants.CENTER);
			tagJugador[i].setVerticalAlignment(SwingConstants.TOP);
			tagJugador[i].setFont(fuenteBig);
			tagJugador[i].setBackground(Color.WHITE);
			tagJugador[i].setBounds(10 + i * 72, 34, 64, 64);
			tagContainer.add(tagJugador[i]);
		}

		tagContainer.setVisible(true);
		tagContainer.requestFocus();
	}

}
