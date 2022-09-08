package ranking;

public class Jugador implements Comparable<Jugador>{
	private String nombre;
	private int minJugados;
	private int segJugados;
	
	public Jugador(String nombre, int min, int seg) {
		this.nombre = nombre;
		this.minJugados = min;
		this.segJugados = seg;
	}
	//devuelve el tiempo jugado en formato reloj. Ej: 12:33.
	public String obtenerTiempo() {
		StringBuilder minYSeg = new StringBuilder();
		minYSeg.append(convertirTiempoAString(this.minJugados));
		minYSeg.append(":");
		minYSeg.append(convertirTiempoAString(this.segJugados));
		return minYSeg.toString();
	}	
	public String getNombre() {
		return nombre;
	}
	
	private String concatenarTiempos(int min, int seg) {
		String minutos = convertirTiempoAString(min);
		String segundos = convertirTiempoAString(seg);
		StringBuilder minYSeg = new StringBuilder();
		minYSeg.append(minutos);
		minYSeg.append(segundos);
		return minYSeg.toString();
	}
	private String convertirTiempoAString(int t) {
		StringBuilder tiempo = new StringBuilder();
		tiempo.append(dosDigitos(t) ?""+t: "0"+t);
		return tiempo.toString();
	}
	private boolean dosDigitos(int numero) {
		return numero >= 10;
	}
	private int tiempoTotal() {
		return Integer.parseInt(concatenarTiempos(minJugados, segJugados));
	}
	
	@Override
	public int compareTo(Jugador o) {
		return tiempoTotal() < o.tiempoTotal() ? 1: tiempoTotal() > o.tiempoTotal()? -1 : 0;
	}
}
	