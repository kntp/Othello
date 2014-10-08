package othellogame;

import org.junit.Test;

import junit.framework.TestCase;

public class GameMasterTest extends TestCase {

	@Test
	public void testGM(){
		Board bd = new Board();
		Player p1 = new UserPlayer(Player.SIDE_BLACK);
		Player p2 = new CpuPlayer(Player.SIDE_WHITE);
		GameMaster gm = new GameMaster(); 
	
		bd.initBoard();
		
	}
}
