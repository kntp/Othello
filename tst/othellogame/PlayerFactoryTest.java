package othellogame;

import org.junit.Test;

import junit.framework.TestCase;

public class PlayerFactoryTest extends TestCase {
	@Test
	public void testFact(){
		Player p;
		PlayerFactory pf = new PlayerFactory();
		
		p = pf.getPlayer(0, 0);
	}
}
