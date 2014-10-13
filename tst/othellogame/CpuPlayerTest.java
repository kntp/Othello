package othellogame;

import org.junit.Test;

import junit.framework.TestCase;

public class CpuPlayerTest extends TestCase {

	@Test
	public void testCP(){
		Board bd = new Board();
		CpuPlayer cp = new CpuPlayer(Player.SIDE_BLACK);
		
		bd.prepareBoard();
		cp.turn(bd);
		
	}
}
