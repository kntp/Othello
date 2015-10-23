package othellogame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	private Board bd;

	@Before
	public void doBefore() {
		bd = new Board();
	}

	@After
	public void doAfter() {
	}

	@Test
	public void testBoardInit(){
		int x, y;

		for(x = 0; x < 8; x++){
			for(y = 0; y < 8; y++){
				assertEquals(bd.getVal(x,y),Board.COLOR_NONE);
			}
		}

		bd.putPiece(0, 0, Board.COLOR_BLACK);
		bd.putPiece(7, 7, Board.COLOR_WHITE);
		bd.prepareBoard();
		assertEquals(bd.getVal(3, 3),Board.COLOR_WHITE);
		assertEquals(bd.getVal(4, 4),Board.COLOR_WHITE);
		assertEquals(bd.getVal(3, 4),Board.COLOR_BLACK);
		assertEquals(bd.getVal(4, 3),Board.COLOR_BLACK);
		assertEquals(bd.getVal(0, 0),Board.COLOR_NONE);
		assertEquals(bd.getVal(7, 7),Board.COLOR_NONE);

	}

	@Test
	public void testGetval(){
		assertEquals(bd.getVal(-1, -1),-1);
		assertEquals(bd.getVal(7, 7),Board.COLOR_NONE);
		assertEquals(bd.getVal(7, 8),-1);
		assertEquals(bd.getVal(8, 7),-1);
		assertEquals(bd.getVal(8, 8),-1);
	}

	private boolean is_allvalid(int color)
	{
		int x,y;
		boolean piece = true;

		bd.clearTable();

		for(y = 0; y < Board.BOARD_SIZE; y++){
			for(x = 0; x < Board.BOARD_SIZE; x++){
				bd.putPiece(x, y, color);
				if(bd.getVal(x, y) != color) {
					piece = false;
				}
			}
		}

		return piece;
	}

	@Test
	public void testPutPiece(){

		/* put black test */
		assertTrue(is_allvalid(Board.COLOR_BLACK));

		/* put white test*/
		assertTrue(is_allvalid(Board.COLOR_WHITE));

		/* out of range test*/
		bd.prepareBoard();
		assertFalse(bd.putPiece(0, 8, Board.COLOR_BLACK));
		assertFalse(bd.putPiece(8, 0, Board.COLOR_BLACK));

		bd.prepareBoard();
		assertFalse(bd.putPiece(0, 8, Board.COLOR_WHITE));
		assertFalse(bd.putPiece(8, 0, Board.COLOR_WHITE));

		/* put fail test */
		bd.prepareBoard();
		assertFalse(bd.putPiece(3, 3, Board.COLOR_WHITE));
		assertFalse(bd.putPiece(3, 4, Board.COLOR_WHITE));
		assertFalse(bd.putPiece(4, 3, Board.COLOR_WHITE));
		assertFalse(bd.putPiece(4, 4, Board.COLOR_WHITE));

	}

	@Test
	public void testIsExist(){
		bd.prepareBoard();

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
		bd.clearTable(); /* now, clean up the board */
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

//	@Ignore("not ready yet")
	@Test
	public void testCanPutXDir(){

		/* clean test */
		bd.clearTable();
		for(int y = 0; y < Board.BOARD_SIZE; y++) {
			for(int x = 0; x < Board.BOARD_SIZE; x++) {
				assertFalse(bd.canPut(x, y, Board.COLOR_BLACK));
				assertFalse(bd.canPut(x, y, Board.COLOR_WHITE));
			}
		}

		/* E direction */
		/* ..Xo*... */
		bd.clearTable();
		bd.putPiece(3, 3, Board.COLOR_WHITE);
		bd.putPiece(4, 3, Board.COLOR_BLACK);
		assertTrue(bd.canPut(2, 3, Board.COLOR_BLACK));
//		bd.showBoard();

		/* ..Xo.... */
		bd.clearTable();
		bd.putPiece(3, 3, Board.COLOR_WHITE);
		assertFalse(bd.canPut(2, 3, Board.COLOR_BLACK));
//		bd.showBoard();

		/* ..Xooooo */
		bd.clearTable();
		bd.putPiece(3, 3, Board.COLOR_WHITE);
		bd.putPiece(4, 3, Board.COLOR_WHITE);
		bd.putPiece(5, 3, Board.COLOR_WHITE);
		bd.putPiece(6, 3, Board.COLOR_WHITE);
		bd.putPiece(7, 3, Board.COLOR_WHITE);
		assertFalse(bd.canPut(2, 3, Board.COLOR_BLACK));
//		bd.showBoard();

		/* ..Xoooo* */
		bd.clearTable();
		bd.putPiece(3, 3, Board.COLOR_WHITE);
		bd.putPiece(4, 3, Board.COLOR_WHITE);
		bd.putPiece(5, 3, Board.COLOR_WHITE);
		bd.putPiece(6, 3, Board.COLOR_WHITE);
		bd.putPiece(7, 3, Board.COLOR_BLACK);
		assertTrue(bd.canPut(2, 3, Board.COLOR_BLACK));
//		bd.showBoard();

		/* ..Xooo*o */
		bd.clearTable();
		bd.putPiece(3, 3, Board.COLOR_WHITE);
		bd.putPiece(4, 3, Board.COLOR_WHITE);
		bd.putPiece(5, 3, Board.COLOR_WHITE);
		bd.putPiece(6, 3, Board.COLOR_BLACK);
		bd.putPiece(7, 3, Board.COLOR_WHITE);
		assertTrue(bd.canPut(2, 3, Board.COLOR_BLACK));
//		bd.showBoard();

		/* ..Xo.o*o */
		bd.clearTable();
		bd.putPiece(3, 3, Board.COLOR_WHITE);
//		bd.putPiece(4, 3, Board.COLOR_WHITE);
		bd.putPiece(5, 3, Board.COLOR_WHITE);
		bd.putPiece(6, 3, Board.COLOR_BLACK);
		bd.putPiece(7, 3, Board.COLOR_WHITE);
		assertFalse(bd.canPut(2, 3, Board.COLOR_BLACK));
//		bd.showBoard();

		/* W direction */
		/* ....*X.. */
		bd.clearTable();
		bd.putPiece(4, 3, Board.COLOR_BLACK);
		assertFalse(bd.canPut(5, 3, Board.COLOR_WHITE));
//		bd.showBoard();

		/* ...o*X.. */
		bd.clearTable();
		bd.putPiece(3, 3, Board.COLOR_WHITE);
		bd.putPiece(4, 3, Board.COLOR_BLACK);
		assertTrue(bd.canPut(5, 3, Board.COLOR_WHITE));
//		bd.showBoard();

		/* o****X.. */
		bd.clearTable();
		bd.putPiece(0, 3, Board.COLOR_WHITE);
		bd.putPiece(1, 3, Board.COLOR_BLACK);
		bd.putPiece(2, 3, Board.COLOR_BLACK);
		bd.putPiece(3, 3, Board.COLOR_BLACK);
		bd.putPiece(4, 3, Board.COLOR_BLACK);
		assertTrue(bd.canPut(5, 3, Board.COLOR_WHITE));
//		bd.showBoard();

		/* *****X.. */
		bd.clearTable();
		bd.putPiece(0, 3, Board.COLOR_BLACK);
		bd.putPiece(1, 3, Board.COLOR_BLACK);
		bd.putPiece(2, 3, Board.COLOR_BLACK);
		bd.putPiece(3, 3, Board.COLOR_BLACK);
		bd.putPiece(4, 3, Board.COLOR_BLACK);
		assertFalse(bd.canPut(5, 3, Board.COLOR_WHITE));
//		bd.showBoard();

		/* *o***X.. */
		bd.clearTable();
		bd.putPiece(0, 3, Board.COLOR_BLACK);
		bd.putPiece(1, 3, Board.COLOR_WHITE);
		bd.putPiece(2, 3, Board.COLOR_BLACK);
		bd.putPiece(3, 3, Board.COLOR_BLACK);
		bd.putPiece(4, 3, Board.COLOR_BLACK);
		assertTrue(bd.canPut(5, 3, Board.COLOR_WHITE));
//		bd.showBoard();

		/* *o*.*X.. */
		bd.clearTable();
		bd.putPiece(0, 3, Board.COLOR_BLACK);
		bd.putPiece(1, 3, Board.COLOR_WHITE);
		bd.putPiece(2, 3, Board.COLOR_BLACK);
//		bd.putPiece(3, 3, Board.COLOR_BLACK);
		bd.putPiece(4, 3, Board.COLOR_BLACK);
		assertFalse(bd.canPut(5, 3, Board.COLOR_WHITE));
//		bd.showBoard();

	}

	@Test
	public void testCanPutYDir(){

		/* S direction */
		/* ..Xo*... */
		bd.clearTable();
		bd.putPiece(3, 3, Board.COLOR_WHITE);
		bd.putPiece(3, 4, Board.COLOR_BLACK);
		assertTrue(bd.canPut(3, 2, Board.COLOR_BLACK));
//		bd.showBoard();

		/* ..Xooo*o */
		bd.clearTable();
		bd.putPiece(3, 3, Board.COLOR_WHITE);
		bd.putPiece(3, 4, Board.COLOR_WHITE);
		bd.putPiece(3, 5, Board.COLOR_WHITE);
		bd.putPiece(3, 6, Board.COLOR_BLACK);
		bd.putPiece(3, 7, Board.COLOR_WHITE);
		assertTrue(bd.canPut(3, 2, Board.COLOR_BLACK));
//		bd.showBoard();

		/* ..Xo.o*o */
		bd.clearTable();
		bd.putPiece(3, 3, Board.COLOR_WHITE);
//		bd.putPiece(3, 4, Board.COLOR_WHITE);
		bd.putPiece(3, 5, Board.COLOR_WHITE);
		bd.putPiece(3, 6, Board.COLOR_BLACK);
		bd.putPiece(3, 7, Board.COLOR_WHITE);
		assertFalse(bd.canPut(3, 2, Board.COLOR_BLACK));
//		bd.showBoard();

		/* N direction */
		/* ...o*X.. */
		bd.clearTable();
		bd.putPiece(3, 3, Board.COLOR_WHITE);
		bd.putPiece(3, 4, Board.COLOR_BLACK);
		assertTrue(bd.canPut(3, 5, Board.COLOR_WHITE));
//		bd.showBoard();

		/* *****X.. */
		bd.clearTable();
		bd.putPiece(3, 0, Board.COLOR_BLACK);
		bd.putPiece(3, 1, Board.COLOR_BLACK);
		bd.putPiece(3, 2, Board.COLOR_BLACK);
		bd.putPiece(3, 3, Board.COLOR_BLACK);
		bd.putPiece(3, 4, Board.COLOR_BLACK);
		assertFalse(bd.canPut(3, 5, Board.COLOR_WHITE));
//		bd.showBoard();

		/* *o*.*X.. */
		bd.clearTable();
		bd.putPiece(3, 0, Board.COLOR_BLACK);
		bd.putPiece(3, 1, Board.COLOR_WHITE);
		bd.putPiece(3, 2, Board.COLOR_BLACK);
//		bd.putPiece(3, 3, Board.COLOR_BLACK);
		bd.putPiece(3, 4, Board.COLOR_BLACK);
		assertFalse(bd.canPut(3, 5, Board.COLOR_WHITE));
//		bd.showBoard();

	}
	
	@Test
	public void testCanPutDiagonalDir(){

		/* SE direction */
		/* ..Xo*... */
		bd.clearTable();
		bd.putPiece(3, 3, Board.COLOR_WHITE);
		bd.putPiece(4, 4, Board.COLOR_BLACK);
		assertTrue(bd.canPut(2, 2, Board.COLOR_BLACK));
//		bd.showBoard();

		/* Xoo*o */
		bd.clearTable();
		bd.putPiece(1, 4, Board.COLOR_WHITE);
		bd.putPiece(2, 5, Board.COLOR_WHITE);
		bd.putPiece(3, 6, Board.COLOR_BLACK);
		bd.putPiece(4, 7, Board.COLOR_WHITE);
		assertTrue(bd.canPut(0, 3, Board.COLOR_BLACK));
//		bd.showBoard();

		/* Xoo.o */
		bd.clearTable();
		bd.putPiece(1, 4, Board.COLOR_WHITE);
		bd.putPiece(2, 5, Board.COLOR_WHITE);
//		bd.putPiece(3, 6, Board.COLOR_BLACK);
		bd.putPiece(4, 7, Board.COLOR_WHITE);
		assertFalse(bd.canPut(0, 3, Board.COLOR_BLACK));
//		bd.showBoard();

		/* SW direction */
		/* ...o*X.. */
		bd.clearTable();
		bd.putPiece(4, 1, Board.COLOR_BLACK);
		bd.putPiece(3, 2, Board.COLOR_WHITE);
		assertTrue(bd.canPut(5, 0, Board.COLOR_WHITE));
//		bd.showBoard();

		/* *****X.. */
		bd.clearTable();
		bd.putPiece(4, 1, Board.COLOR_BLACK);
		bd.putPiece(3, 2, Board.COLOR_BLACK);
		bd.putPiece(2, 3, Board.COLOR_BLACK);
		bd.putPiece(1, 4, Board.COLOR_BLACK);
		bd.putPiece(0, 5, Board.COLOR_BLACK);
		assertFalse(bd.canPut(5, 0, Board.COLOR_WHITE));
//		bd.showBoard();

		/* o.***X.. */
		bd.clearTable();
		bd.putPiece(4, 1, Board.COLOR_BLACK);
		bd.putPiece(3, 2, Board.COLOR_BLACK);
		bd.putPiece(2, 3, Board.COLOR_BLACK);
//		bd.putPiece(1, 4, Board.COLOR_BLACK);
		bd.putPiece(0, 5, Board.COLOR_WHITE);
		assertFalse(bd.canPut(5, 0, Board.COLOR_WHITE));
//		bd.showBoard();

		/* NW direction */
		/* ..Xo*... */
		bd.clearTable();
		bd.putPiece(6, 2, Board.COLOR_WHITE);
		bd.putPiece(5, 1, Board.COLOR_BLACK);
		assertTrue(bd.canPut(7, 3, Board.COLOR_BLACK));
//		bd.showBoard();

		/* Xo*o */
		bd.clearTable();
		bd.putPiece(6, 2, Board.COLOR_WHITE);
		bd.putPiece(5, 1, Board.COLOR_BLACK);
		bd.putPiece(4, 0, Board.COLOR_WHITE);
		assertTrue(bd.canPut(7, 3, Board.COLOR_BLACK));
//		bd.showBoard();

		/* Xoo. */
		bd.clearTable();
		bd.putPiece(6, 2, Board.COLOR_WHITE);
		bd.putPiece(5, 1, Board.COLOR_WHITE);
//		bd.putPiece(4, 0, Board.COLOR_WHITE);
		assertFalse(bd.canPut(7, 3, Board.COLOR_BLACK));
//		bd.showBoard();

		/* NE direction */
		/* .o*X... */
		bd.clearTable();
		bd.putPiece(3, 5, Board.COLOR_BLACK);
		bd.putPiece(4, 4, Board.COLOR_WHITE);
		assertTrue(bd.canPut(2, 6, Board.COLOR_WHITE));
//		bd.showBoard();

		/* *****X.. */
		bd.clearTable();
		bd.putPiece(3, 5, Board.COLOR_BLACK);
		bd.putPiece(4, 4, Board.COLOR_BLACK);
		bd.putPiece(5, 3, Board.COLOR_BLACK);
		bd.putPiece(6, 2, Board.COLOR_BLACK);
		bd.putPiece(7, 1, Board.COLOR_BLACK);
		assertFalse(bd.canPut(2, 6, Board.COLOR_WHITE));
//		bd.showBoard();

		/* o****X.. */
		bd.clearTable();
		bd.putPiece(3, 5, Board.COLOR_BLACK);
		bd.putPiece(4, 4, Board.COLOR_BLACK);
		bd.putPiece(5, 3, Board.COLOR_BLACK);
		bd.putPiece(6, 2, Board.COLOR_BLACK);
		bd.putPiece(7, 1, Board.COLOR_WHITE);
		assertTrue(bd.canPut(2, 6, Board.COLOR_WHITE));
//		bd.showBoard();

	}
	
	@Test
	public void testSetBoardData(){
		String src = new String();
		
		bd.clearTable();
		src =	"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"0000000";
		assertFalse(bd.setBoardData(src));
		
		bd.clearTable();
		src =	"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000";
		assertTrue(bd.setBoardData(src));
		
		bd.clearTable();
		src =	"10000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000" +
				"00000000";
		assertTrue(bd.setBoardData(src));
		assertEquals(Board.COLOR_BLACK, bd.getVal(0, 0));
		
		bd.clearTable();
		src =	"20000001" +
				"01020111" +
				"00021000" +
				"00022100" +
				"01010000" +
				"00000000" +
				"00200000" +
				"00000002";
		assertTrue(bd.setBoardData(src));
		assertEquals(Board.COLOR_WHITE, bd.getVal(0, 0));
		assertEquals(Board.COLOR_WHITE, bd.getVal(3, 1));
		assertEquals(Board.COLOR_WHITE, bd.getVal(3, 2));
		assertEquals(Board.COLOR_WHITE, bd.getVal(3, 3));
		assertEquals(Board.COLOR_WHITE, bd.getVal(4, 3));
		assertEquals(Board.COLOR_WHITE, bd.getVal(2, 6));
		assertEquals(Board.COLOR_WHITE, bd.getVal(7, 7));

		assertEquals(Board.COLOR_BLACK, bd.getVal(7, 0));
		assertEquals(Board.COLOR_BLACK, bd.getVal(1, 1));
		assertEquals(Board.COLOR_BLACK, bd.getVal(5, 1));
		assertEquals(Board.COLOR_BLACK, bd.getVal(6, 1));
		assertEquals(Board.COLOR_BLACK, bd.getVal(7, 1));
		assertEquals(Board.COLOR_BLACK, bd.getVal(4, 2));
		assertEquals(Board.COLOR_BLACK, bd.getVal(5, 3));
		assertEquals(Board.COLOR_BLACK, bd.getVal(1, 4));
		assertEquals(Board.COLOR_BLACK, bd.getVal(3, 4));
		
	}
	
	@Test
	public void testPutAndTurn(){
		String src = new String();

		bd.clearTable();
		bd.prepareBoard();
		src =	"00000000" +
				"00000000" +
				"00000000" +
				"00021000" +
				"00012000" +
				"00000000" +
				"00000000" +
				"00000000";
		assertEquals(src, bd.getBoardData());

		assertTrue(bd.putAndTurn(2, 3, Board.COLOR_BLACK));
//		bd.showBoard();
		src =	"00000000" +
				"00000000" +
				"00000000" +
				"00111000" +
				"00012000" +
				"00000000" +
				"00000000" +
				"00000000";
		assertEquals(src, bd.getBoardData());
		
		assertTrue(bd.putAndTurn(2, 2, Board.COLOR_WHITE));
//		bd.showBoard();
		src =	"00000000" +
				"00000000" +
				"00200000" +
				"00121000" +
				"00012000" +
				"00000000" +
				"00000000" +
				"00000000";
		assertEquals(src, bd.getBoardData());
		
		assertTrue(bd.putAndTurn(2, 1, Board.COLOR_BLACK));
//		bd.showBoard();
		src =	"00000000" +
				"00100000" +
				"00100000" +
				"00121000" +
				"00012000" +
				"00000000" +
				"00000000" +
				"00000000";
		assertEquals(src, bd.getBoardData());
		
	}
	
	@Test
	public void testPutAndTurn2(){
		String score = "F5D6C5F4D7C6C7F6D3B5C4C3E6B6A6A4A5A7G5B3F7B4F3E3E2E8C8G4H3D2G3E1B2E7A2H6D8B8F2F8G7G6G8H8H7H5D1F1G1C1B1C2A3A1H4H2B7G2H1";
		repeat_score(score,false);
		score = "F5F6E6F4E3C5C4E7C6E2F2D2F3G4G5D6D7H5G3D3C3C2H6H4D1H7F8F1E1H3G1D8B3C8G2C7F7G6E8G8B7B8H2H1G7A8B6A5A7A6B5A4B4";
		repeat_score(score,false);
		score = "F5D6C5F4E3C6D3F6E6D7E7C3C4C7D8C8F7F8B8D2B6F3C2E8G8B3G5B4A3A5D1E2E1B5A6A4G4B7A8A7B2G6H5A1A2B1C1H7G7F1F2H3H4G1H2G3G2H1H6H8";
		repeat_score(score, false);
	
	}

	private void repeat_score(String score, boolean show) {
		int cur_turn = Board.COLOR_BLACK;
		
		bd.clearTable();
		bd.prepareBoard();
		
		int cur_x,cur_y;
		for(int cnt = 0; cnt < (score.length()/2); cnt++){
			cur_x = convert_index(score.charAt(cnt * 2));
			cur_y = convert_index(score.charAt(cnt * 2 + 1));
			assertTrue(bd.putAndTurn(cur_x, cur_y, cur_turn));
			if(cur_turn == Board.COLOR_BLACK){
				cur_turn = Board.COLOR_WHITE;
			}else{
				cur_turn = Board.COLOR_BLACK;
			}
			if(show == true){
				bd.showBoard();
			}
		}
	}

	private int convert_index(char cur_char) {
		int ret_index = 0;
		
		switch(cur_char) {
		case '1':
		case 'A':
			ret_index = 0;
			break;
		case '2':
		case 'B':
			ret_index = 1;
			break;
		case '3':
		case 'C':
			ret_index = 2;
			break;
		case '4':
		case 'D':
			ret_index = 3;
			break;
		case '5':
		case 'E':
			ret_index = 4;
			break;
		case '6':
		case 'F':
			ret_index = 5;
			break;
		case '7':
		case 'G':
			ret_index = 6;
			break;
		case '8':
		case 'H':
			ret_index = 7;
			break;
		default:
			assert(false);
			break;
		}
		
		return ret_index;
	}
}
