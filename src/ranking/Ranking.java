package ranking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ranking {
	private List <Jugador> ranking;
	private String [] rankingOrdenado; 
	private String dificultad;
	public Ranking(String dificultad) {
		this.dificultad = dificultad;
		this.ranking = new ArrayList<>();
		cargarRanking();
	}
	private void cargarRanking() {
		File archivoRanking = new File("Ranking"+dificultad+".rk");
		try {
			Scanner palabras = new Scanner(archivoRanking);
			while (palabras.hasNext()) {
				String linea = palabras.next().toString();
				if (linea.isEmpty()) {
					break;
				}
				Pattern patternNombre = Pattern.compile("[a-zA-Z]{1,3}");
				Matcher matcherNombre = patternNombre.matcher(linea);
				Pattern patternTiempo = Pattern.compile("\\d{2}");
				Matcher matcherTiempo = patternTiempo.matcher(linea);
				String nombre = "";
				String minutos= "";
				String segundos= "";
				if (matcherNombre.find()) {
					nombre = matcherNombre.group().toString();
				}
				if (matcherTiempo.find()) {
					minutos = matcherTiempo.group().toString();
				}
				//selecciona el siguiente elemento del grupo
				if (matcherTiempo.find()) {
					segundos = matcherTiempo.group().toString();
				}
				ranking.add(new Jugador(nombre.toUpperCase(),Integer.parseInt(minutos),Integer.parseInt(segundos)));
			}
			palabras.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ordenarRanking();
	}
	public String [] obtenerRanking() {
		ordenarRanking();
		return this.rankingOrdenado;
	}
	private void ordenarRanking() {
		this.rankingOrdenado= new String [5];
		Collections.sort(ranking);
		Collections.reverse(ranking);
		if (ranking.isEmpty()) {
			return;
		}

		for (int i = 0; i < rankingOrdenado.length; i++) {
			if (ranking.get(i) == null) {
				rankingOrdenado[i] = "";
			}
			rankingOrdenado[i] = ranking.get(i).getNombre() + "---" + ranking.get(i).obtenerTiempo();
		}
	}
	
	public void agregarPuntaje(String nombre, int min, int seg) {
		ranking.add(new Jugador(nombre, min, seg));
		ordenarRanking();
		actualizarRanking();
	}
	private void actualizarRanking() {
		File archivoRanking = new File("Ranking"+dificultad+".rk");
		archivoRanking.delete();
		File nuevoRanking = new File("Ranking"+dificultad+".rk");
		try {
			FileWriter fw = new FileWriter(nuevoRanking, true);
			fw.write(ranking.get(0).getNombre()+"---"+ranking.get(0).obtenerTiempo());
			for (int i = 1; i < rankingOrdenado.length; i++) {
				fw.write("\n"+ranking.get(i).getNombre()+"---"+ranking.get(i).obtenerTiempo());
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Ranking rankingNormal = new Ranking("Normal");
		String [] r = rankingNormal.obtenerRanking();
		for (String string : r) {
			System.out.println(string);
		}
		System.out.println();
		rankingNormal.agregarPuntaje("LEO", 0, 1);
		String [] r2 = rankingNormal.obtenerRanking();
		for (String string : r2) {
			System.out.println(string);
		}
	}
}