package othellogame;

import org.junit.Test;

import junit.framework.TestCase;

public class UserPlayerTest extends TestCase {

	@Test
	public void testUP(){
		Board bd = new Board();
		UserPlayer up = new UserPlayer(Player.SIDE_BLACK);
		
		bd.prepareBoard();
		up.turn(bd);
		
	}
}
