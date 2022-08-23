package wordle;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Board {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Board window = new Board();
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
	public Board() {
		initialize();
		frame.setBounds(100, 100, 430, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// JEditorPane[] letters = new JEditorPane[5]; 
		JEditorPane[][] letters = new JEditorPane[6][5]; 
		
		for (int f = 0; f < letters.length; f++) {
			for (int c = 0; c < letters[0].length; c++) {
				letters[f][c] = new JEditorPane();
				letters[f][c].setFont(new Font("Source Code Pro", Font.PLAIN, 24));
				letters[f][c].setEditable(false);
				letters[f][c].setBounds(70 + 40 * (c+1), 60 + 40 * (f+1), 32, 32);
				frame.getContentPane().add(letters[f][c]);
			}
		}
			
		JEditorPane gameZone = new JEditorPane();
		gameZone.setFont(new Font("Source Code Pro", Font.PLAIN, 32));
		gameZone.setBackground(Color.DARK_GRAY);
		gameZone.setEditable(false);
		gameZone.setBounds(58, 52, 300, 320);
		frame.getContentPane().add(gameZone);

		gameZone.addKeyListener(new KeyAdapter() {

		int intento = 0;
		int posicionLetra = 0;
		JEditorPane selectedChar = null;

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(posicionLetra + "  PosLETRA");
				if (intento > 5)
					return;
				
				if (e.getKeyCode() != 10)

				selectedChar = letters[intento][posicionLetra];
				if (selectedChar != null && e.getKeyCode() == 8) {
					System.out.println("PosLetra antes de borrar: " + posicionLetra);
					selectedChar.setText("");
					posicionLetra += posicionLetra != 0 ? -1 : 0;
					selectedChar = letters[intento][posicionLetra];
					System.out.println("Posición letra borrar: " + posicionLetra);
					return;
				}
				
				if (posicionLetra >= 4) {
					if (selectedChar.getText() != "" && e.getKeyCode() == 10) {
						//FUNCION ENVIAR
						intento++;
						posicionLetra = 0;
					}
					return;
				}
				
				if (selectedChar.getText() != "") {
					if (posicionLetra <= 4) 
						selectedChar.setBackground(Color.white);
						posicionLetra++;
					selectedChar = letters[intento][posicionLetra];
				}
				
				System.out.println(e.getKeyCode());
				
				selectedChar.setBackground(Color.LIGHT_GRAY);
				
				if (e.getKeyCode() != 10)
					selectedChar.setText("" + e.getKeyChar());
				
				System.out.println(posicionLetra + "  PosLETRA POST");
				}
			});

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 481, 469);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
