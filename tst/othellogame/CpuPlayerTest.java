package othellogame;

import junit.framework.TestCase;

public class CpuPlayerTest extends TestCase {

	public void testCP(){
		Board bd = new Board();
		CpuPlayer cp = new CpuPlayer(Player.SIDE_BLACK);
		
		bd.initBoard();
		cp.turn(bd);
		
	}
}
