package modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class terminarIntentoTest {
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

}
