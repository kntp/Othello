package othellogame;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class GameMasterTest{

	@Ignore("not yet")
	@Test
	public void testGM(){
		Player p1 = new UserPlayer(Player.SIDE_BLACK);
		Player p2 = new CpuPlayer(Player.SIDE_WHITE);
		GameMaster gm = new GameMaster(p1, p2); 
	
		assertTrue(true);
		gm.start();
		assertTrue(false);
	}
}
