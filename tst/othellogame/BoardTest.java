package othellogame;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	@Test
	public void testBoardInit(){
		Board bd = new Board();
		int x, y;
		
		for(x = 0; x < 8; x++){
			for(y = 0; y < 8; y++){
				assertEquals(bd.getval(x,y),Board.COLOR_NONE);
			}
		}

		bd.putpiece(0, 0, Board.COLOR_BLACK);
		bd.putpiece(7, 7, Board.COLOR_WHITE);
		bd.initBoard();
		assertEquals(bd.getval(3, 3),Board.COLOR_WHITE);
		assertEquals(bd.getval(4, 4),Board.COLOR_WHITE);
		assertEquals(bd.getval(3, 4),Board.COLOR_BLACK);
		assertEquals(bd.getval(4, 3),Board.COLOR_BLACK);
		assertEquals(bd.getval(0, 0),Board.COLOR_NONE);
		assertEquals(bd.getval(7, 7),Board.COLOR_NONE);
		
	}
	
	@Test
	public void testGetval(){
		Board bd = new Board();
		
		assertEquals(bd.getval(-1, -1),-1);
		assertEquals(bd.getval(7, 7),Board.COLOR_NONE);
		assertEquals(bd.getval(7, 8),-1);
		assertEquals(bd.getval(8, 7),-1);
		assertEquals(bd.getval(8, 8),-1);

	}

	@Test
	public void testPutpieces(){
		Board bd = new Board();
		int x,y;
		
		bd.initBoard();
		for(x = 0; x < Board.BOARD_SIZE; x++){
			for(y = 0; y < Board.BOARD_SIZE; y++){
				if(((x == 3) && (y == 4))||((x == 4) && (y == 3))||((x == 3) && (y == 3))||((x == 4) && (y == 4))){
					assertFalse(bd.putpiece(x, y, Board.COLOR_BLACK));
					assertEquals(bd.getval(3, 4),Board.COLOR_BLACK);
					assertEquals(bd.getval(4, 3),Board.COLOR_BLACK);
					assertEquals(bd.getval(3, 3),Board.COLOR_WHITE);
					assertEquals(bd.getval(4, 4),Board.COLOR_WHITE);
				}else{
					bd.putpiece(x, y, Board.COLOR_BLACK);
					assertEquals(bd.getval(x, y),Board.COLOR_BLACK);
				}
			}
		}
		bd.show();
		bd.initBoard();
		for(x = 0; x < Board.BOARD_SIZE; x++){
			for(y = 0; y < Board.BOARD_SIZE; y++){
				if(((x == 3) && (y == 4))||((x == 4) && (y == 3))||((x == 3) && (y == 3))||((x == 4) && (y == 4))){
					assertFalse(bd.putpiece(x, y, Board.COLOR_WHITE));
					assertEquals(bd.getval(3, 4),Board.COLOR_BLACK);
					assertEquals(bd.getval(4, 3),Board.COLOR_BLACK);
					assertEquals(bd.getval(3, 3),Board.COLOR_WHITE);
					assertEquals(bd.getval(4, 4),Board.COLOR_WHITE);
				}else{
					assertTrue(bd.putpiece(x, y, Board.COLOR_WHITE));
					assertEquals(bd.getval(x, y), Board.COLOR_WHITE);
				}
			}
		}
		bd.show();
		bd.initBoard();
		assertFalse(bd.putpiece(0, 8, Board.COLOR_BLACK));
		assertFalse(bd.putpiece(8, 0, Board.COLOR_BLACK));
		bd.show();
		bd.initBoard();
		assertFalse(bd.putpiece(0, 8, Board.COLOR_WHITE));
		assertFalse(bd.putpiece(8, 0, Board.COLOR_WHITE));
	}
	
	@Test
	public void testCheckLocation(){
		Board bd = new Board();
		
		bd.initBoard();
		assertFalse(bd.checklocation(0, 0, Board.COLOR_BLACK));
		assertFalse(bd.checklocation(0, 0, Board.COLOR_WHITE));

		assertFalse(bd.checklocation(0, 8, Board.COLOR_BLACK));
		assertFalse(bd.checklocation(8, 0, Board.COLOR_BLACK));
		assertFalse(bd.checklocation(0, 8, Board.COLOR_WHITE));
		assertFalse(bd.checklocation(8, 0, Board.COLOR_WHITE));
		
		assertFalse(bd.checklocation(2, 2, Board.COLOR_BLACK));
		assertFalse(bd.checklocation(2, 3, Board.COLOR_BLACK));
		assertTrue(bd.checklocation(2, 4, Board.COLOR_BLACK));
		assertFalse(bd.checklocation(2, 5, Board.COLOR_BLACK));

		assertFalse(bd.checklocation(2, 2, Board.COLOR_WHITE));
		assertTrue(bd.checklocation(2, 3, Board.COLOR_WHITE));
		assertFalse(bd.checklocation(2, 4, Board.COLOR_WHITE));
		assertFalse(bd.checklocation(2, 5, Board.COLOR_WHITE));

		assertFalse(bd.checklocation(3, 2, Board.COLOR_BLACK));
		assertFalse(bd.checklocation(3, 3, Board.COLOR_BLACK));
		assertFalse(bd.checklocation(3, 4, Board.COLOR_BLACK));
		assertTrue(bd.checklocation(3, 5, Board.COLOR_BLACK));

		assertTrue(bd.checklocation(3, 2, Board.COLOR_WHITE));
		assertFalse(bd.checklocation(3, 3, Board.COLOR_WHITE));
		assertFalse(bd.checklocation(3, 4, Board.COLOR_WHITE));
		assertFalse(bd.checklocation(3, 5, Board.COLOR_WHITE));

		assertTrue(bd.checklocation(4, 2, Board.COLOR_BLACK));
		assertFalse(bd.checklocation(4, 3, Board.COLOR_BLACK));
		assertFalse(bd.checklocation(4, 4, Board.COLOR_BLACK));
		assertFalse(bd.checklocation(4, 5, Board.COLOR_BLACK));

		assertTrue(bd.checklocation(4, 2, Board.COLOR_WHITE));
		assertFalse(bd.checklocation(4, 3, Board.COLOR_WHITE));
		assertFalse(bd.checklocation(4, 4, Board.COLOR_WHITE));
		assertFalse(bd.checklocation(4, 5, Board.COLOR_WHITE));

		assertFalse(bd.checklocation(5, 2, Board.COLOR_BLACK));
		assertTrue(bd.checklocation(5, 3, Board.COLOR_BLACK));
		assertFalse(bd.checklocation(5, 4, Board.COLOR_BLACK));
		assertFalse(bd.checklocation(5, 5, Board.COLOR_BLACK));

		assertFalse(bd.checklocation(5, 2, Board.COLOR_WHITE));
		assertTrue(bd.checklocation(5, 3, Board.COLOR_WHITE));
		assertFalse(bd.checklocation(5, 4, Board.COLOR_WHITE));
		assertFalse(bd.checklocation(5, 5, Board.COLOR_WHITE));
		
		bd.teClear();
		
		bd.putpiece(3, 3, Board.COLOR_BLACK);

}

	@Test
	public void testShow(){
		Board bd = new Board();
		
		bd.show();
		bd.initBoard();
		bd.show();
	}
}
