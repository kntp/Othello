package othellogame;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class BoardTest {

	@Test
	public void testBoardInit(){
		Board bd = new Board();
		int x, y;

		for(x = 0; x < 8; x++){
			for(y = 0; y < 8; y++){
				assertEquals(bd.getVal(x,y),Board.COLOR_NONE);
			}
		}

		bd.putPiece(0, 0, Board.COLOR_BLACK);
		bd.putPiece(7, 7, Board.COLOR_WHITE);
		bd.initBoard();
		assertEquals(bd.getVal(3, 3),Board.COLOR_WHITE);
		assertEquals(bd.getVal(4, 4),Board.COLOR_WHITE);
		assertEquals(bd.getVal(3, 4),Board.COLOR_BLACK);
		assertEquals(bd.getVal(4, 3),Board.COLOR_BLACK);
		assertEquals(bd.getVal(0, 0),Board.COLOR_NONE);
		assertEquals(bd.getVal(7, 7),Board.COLOR_NONE);

	}

	@Test
	public void testGetval(){
		Board bd = new Board();

		assertEquals(bd.getVal(-1, -1),-1);
		assertEquals(bd.getVal(7, 7),Board.COLOR_NONE);
		assertEquals(bd.getVal(7, 8),-1);
		assertEquals(bd.getVal(8, 7),-1);
		assertEquals(bd.getVal(8, 8),-1);

	}

	@Test
	public void testPutpieces(){
		Board bd = new Board();
		int x,y;

		bd.initBoard();
		for(x = 0; x < Board.BOARD_SIZE; x++){
			for(y = 0; y < Board.BOARD_SIZE; y++){
				if(((x == 3) && (y == 4))||((x == 4) && (y == 3))||((x == 3) && (y == 3))||((x == 4) && (y == 4))){
					assertFalse(bd.putPiece(x, y, Board.COLOR_BLACK));
					assertEquals(bd.getVal(3, 4),Board.COLOR_BLACK);
					assertEquals(bd.getVal(4, 3),Board.COLOR_BLACK);
					assertEquals(bd.getVal(3, 3),Board.COLOR_WHITE);
					assertEquals(bd.getVal(4, 4),Board.COLOR_WHITE);
				}else{
					bd.putPiece(x, y, Board.COLOR_BLACK);
					assertEquals(bd.getVal(x, y),Board.COLOR_BLACK);
				}
			}
		}
		/*bd.show();*/
		bd.initBoard();
		for(x = 0; x < Board.BOARD_SIZE; x++){
			for(y = 0; y < Board.BOARD_SIZE; y++){
				if(((x == 3) && (y == 4))||((x == 4) && (y == 3))||((x == 3) && (y == 3))||((x == 4) && (y == 4))){
					assertFalse(bd.putPiece(x, y, Board.COLOR_WHITE));
					assertEquals(bd.getVal(3, 4),Board.COLOR_BLACK);
					assertEquals(bd.getVal(4, 3),Board.COLOR_BLACK);
					assertEquals(bd.getVal(3, 3),Board.COLOR_WHITE);
					assertEquals(bd.getVal(4, 4),Board.COLOR_WHITE);
				}else{
					assertTrue(bd.putPiece(x, y, Board.COLOR_WHITE));
					assertEquals(bd.getVal(x, y), Board.COLOR_WHITE);
				}
			}
		}
		/*bd.show();*/
		bd.initBoard();
		assertFalse(bd.putPiece(0, 8, Board.COLOR_BLACK));
		assertFalse(bd.putPiece(8, 0, Board.COLOR_BLACK));
		/*bd.show();*/
		bd.initBoard();
		assertFalse(bd.putPiece(0, 8, Board.COLOR_WHITE));
		assertFalse(bd.putPiece(8, 0, Board.COLOR_WHITE));
	}

	@Test
	public void testIsExist(){
		Board bd = new Board();

		bd.initBoard();

		/* test corners */
		assertFalse(bd.isExist(0, 0));
		assertFalse(bd.isExist(7, 0));
		assertFalse(bd.isExist(0, 7));
		assertFalse(bd.isExist(7, 7));

		/* test out of range */
		assertFalse(bd.isExist(-1, -1));
	
		/* test center */
		assertTrue(bd.isExist(3, 3));
		assertTrue(bd.isExist(3, 4));
		assertTrue(bd.isExist(4, 3));
		assertTrue(bd.isExist(4, 4));
		bd.tableClear(); /* now, clean up the board */
		assertFalse(bd.isExist(3, 3));
		assertFalse(bd.isExist(3, 4));
		assertFalse(bd.isExist(4, 3));
		assertFalse(bd.isExist(4, 4));
		
		/* at random */
		assertFalse(bd.isExist(5, 1));
		bd.putPiece(5, 1, Board.COLOR_BLACK);
		assertTrue(bd.isExist(5, 1));
		
		assertFalse(bd.isExist(2, 6));
		bd.putPiece(2, 6, Board.COLOR_WHITE);
		assertTrue(bd.isExist(2, 6));
	}

	@Ignore("not ready yet")
	@Test
	public void testIsPuttable(){
		Board bd = new Board();

		bd.initBoard();
		assertFalse(bd.isPuttable(0, 0, Board.COLOR_BLACK));
		assertFalse(bd.isPuttable(0, 0, Board.COLOR_WHITE));

		assertFalse(bd.isPuttable(0, 8, Board.COLOR_BLACK));
		assertFalse(bd.isPuttable(8, 0, Board.COLOR_BLACK));
		assertFalse(bd.isPuttable(0, 8, Board.COLOR_WHITE));
		assertFalse(bd.isPuttable(8, 0, Board.COLOR_WHITE));

		assertFalse(bd.isPuttable(2, 2, Board.COLOR_BLACK));
		assertFalse(bd.isPuttable(2, 3, Board.COLOR_BLACK));
		assertTrue(bd.isPuttable(2, 4, Board.COLOR_BLACK));
		assertFalse(bd.isPuttable(2, 5, Board.COLOR_BLACK));

		assertFalse(bd.isPuttable(2, 2, Board.COLOR_WHITE));
		assertTrue(bd.isPuttable(2, 3, Board.COLOR_WHITE));
		assertFalse(bd.isPuttable(2, 4, Board.COLOR_WHITE));
		assertFalse(bd.isPuttable(2, 5, Board.COLOR_WHITE));

		assertFalse(bd.isPuttable(3, 2, Board.COLOR_BLACK));
		assertFalse(bd.isPuttable(3, 3, Board.COLOR_BLACK));
		assertFalse(bd.isPuttable(3, 4, Board.COLOR_BLACK));
		assertTrue(bd.isPuttable(3, 5, Board.COLOR_BLACK));

		assertTrue(bd.isPuttable(3, 2, Board.COLOR_WHITE));
		assertFalse(bd.isPuttable(3, 3, Board.COLOR_WHITE));
		assertFalse(bd.isPuttable(3, 4, Board.COLOR_WHITE));
		assertFalse(bd.isPuttable(3, 5, Board.COLOR_WHITE));

		assertTrue(bd.isPuttable(4, 2, Board.COLOR_BLACK));
		assertFalse(bd.isPuttable(4, 3, Board.COLOR_BLACK));
		assertFalse(bd.isPuttable(4, 4, Board.COLOR_BLACK));
		assertFalse(bd.isPuttable(4, 5, Board.COLOR_BLACK));

		assertTrue(bd.isPuttable(4, 2, Board.COLOR_WHITE));
		assertFalse(bd.isPuttable(4, 3, Board.COLOR_WHITE));
		assertFalse(bd.isPuttable(4, 4, Board.COLOR_WHITE));
		assertFalse(bd.isPuttable(4, 5, Board.COLOR_WHITE));

		assertFalse(bd.isPuttable(5, 2, Board.COLOR_BLACK));
		assertTrue(bd.isPuttable(5, 3, Board.COLOR_BLACK));
		assertFalse(bd.isPuttable(5, 4, Board.COLOR_BLACK));
		assertFalse(bd.isPuttable(5, 5, Board.COLOR_BLACK));

		assertFalse(bd.isPuttable(5, 2, Board.COLOR_WHITE));
		assertTrue(bd.isPuttable(5, 3, Board.COLOR_WHITE));
		assertFalse(bd.isPuttable(5, 4, Board.COLOR_WHITE));
		assertFalse(bd.isPuttable(5, 5, Board.COLOR_WHITE));

		bd.tableClear();

		bd.putPiece(3, 3, Board.COLOR_BLACK);

}

	@Ignore
	@Test
	public void testShow(){
		Board bd = new Board();

		bd.show();
		bd.initBoard();
		bd.show();
	}

	@Test
	public void testGetBoardData(){
		Board bd = new Board();
		String src = new String();
		String dst = new String();

		/* test clear state */
		src =	"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000";
		dst = bd.getBoardData();

		assertTrue(src.equals(dst));
		
		/* test 1 piece */
		bd.tableClear();
		bd.putPiece(0, 0, Board.COLOR_BLACK);

		src =	"10000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000";
		dst = bd.getBoardData();
		assertTrue(src.equals(dst));		
		
		/* test initial state */
		bd.initBoard();

		src =	"00000000" +
				"00000000" +
				"00000000" +
				"00021000" +
				"00012000" +
				"00000000" +
				"00000000" +
				"00000000";
		dst = bd.getBoardData();
		assertTrue(src.equals(dst));
		
		/* test 1 piece */
		bd.tableClear();
		bd.putPiece(1, 0, Board.COLOR_WHITE);

		src =	"02000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000";
		dst = bd.getBoardData();
		assertTrue(src.equals(dst));		
	}
}
