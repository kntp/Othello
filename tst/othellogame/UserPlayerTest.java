package othellogame;

import junit.framework.TestCase;

public class UserPlayerTest extends TestCase {

	public void testUP(){
		Board bd = new Board();
		UserPlayer up = new UserPlayer(Player.SIDE_BLACK);
		
		bd.initBoard();
		up.turn(bd);
		
	}
}
