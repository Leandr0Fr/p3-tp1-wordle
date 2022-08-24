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

}
