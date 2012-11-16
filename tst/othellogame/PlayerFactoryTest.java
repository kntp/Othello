package othellogame;

import junit.framework.TestCase;

public class PlayerFactoryTest extends TestCase {
	public void testFact(){
		Player p;
		PlayerFactory pf = new PlayerFactory();
		
		p = pf.getPlayer(0, 0);
	}
}
