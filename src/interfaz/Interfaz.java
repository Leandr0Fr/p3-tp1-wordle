package interfaz;
import modelo.LogicGame;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Interfaz{

	private JFrame frame;
	private JLabel [][] listaLabels; 
	private KeyListener letra;
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addKeyListener(this.escucharTeclado());
		//tablero	
	
		
		
	}
	private void crearTablero(int tamanoPalabra){
		int x = 60;
		int y = 30;
		listaLabels = new JLabel[6][tamanoPalabra];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j <tamanoPalabra ; j++) {
				listaLabels[i][j] = new JLabel("_");
				listaLabels[i][j].setBounds(x,y,46,14);
				frame.getContentPane().add(listaLabels[i][j]);
				x+= 20;
			}
			y+= 20;
			x = 60;			
		}
	}
	//
	private KeyListener escucharTeclado() {
		this.letra = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println(e.getKeyChar());	
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
		};
		return letra;
	}
}
