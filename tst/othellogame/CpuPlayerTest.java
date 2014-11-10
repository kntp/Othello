package othellogame;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import junit.framework.TestCase;

public class CpuPlayerTest {

	@Test
	public void testTurn(){
		assertTrue(true);
	}
	
	@Ignore("not yet")
	@Test
	public void testCP(){
		Board bd = new Board();
		CpuPlayer cp1 = new CpuPlayer(Player.SIDE_BLACK);
		CpuPlayer cp2 = new CpuPlayer(Player.SIDE_WHITE);
		boolean res = true, res1, res2;
		
		cp1.registBoard(bd);
		cp2.registBoard(bd);
		
		bd.prepareBoard();
		
		while(res == true){
			res1 = cp1.turn();
			res2 = cp2.turn();
			
			if((res1 == false) && (res2 == false)){
				res = false;
			}else {
				res = true;
			}
		}
		assertTrue(true);
	}
}
