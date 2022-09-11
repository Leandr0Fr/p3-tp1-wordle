package modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class terminarIntentoTest {
	@Test
	public void terminarIntentoTrueTest() {
		LogicGame game = new LogicGame(1,"menem");
		char [] palabraJugada = {'m','e','n','e','m'};
		assertTrue(game.terminarIntento(palabraJugada));
		
	}
	@Test
	public void terminarIntentoFalseTest() {
		LogicGame game = new LogicGame(1,"bahia");
		char [] palabraJugada = {'b','a','l','a','s'};
		assertFalse(game.terminarIntento(palabraJugada));
	}	
	@Test
	public void terminarIntentoLetraRepetidaTest() {
		LogicGame game = new LogicGame(1,"menem");
		char [] palabraJugada = {'e','e','e','e','e'};
		assertFalse(game.terminarIntento(palabraJugada));
	}
	@Test
	public void terminarIntentoInvertidoTest() {
		LogicGame game = new LogicGame(1,"plato");
		char [] palabraJugada = {'o','t','a','l','p'};
		assertFalse(game.terminarIntento(palabraJugada));
		
	}
	@Test
	public void terminarIntentoTodoAmarilloTest() {
		LogicGame game = new LogicGame(1,"gatos");
		char [] palabraJugada = {'o','g','a','s','t'};
		assertFalse(game.terminarIntento(palabraJugada));
	}
	@Test
	public void terminarIntentoPatron1Test() {
		//debe devolver -verde-verde-amarillo-gris-gris
		LogicGame game = new LogicGame(1,"gatos");
		char [] palabraJugada = {'g','a','o','q','q'};
		assertFalse(game.terminarIntento(palabraJugada));
	}
}
