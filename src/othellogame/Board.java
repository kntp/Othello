package othellogame;

public class Board {
	/* piece color */
	public static final int COLOR_NONE = 0;
	public static final int COLOR_BLACK = 1;
	public static final int COLOR_WHITE = 2;
	public static final int BOARD_SIZE = 8;
	public static final int NUM_OF_CELLS = BOARD_SIZE * BOARD_SIZE;
	/* search direction */
	private static final int DIRECTION_N = 0;
	private static final int DIRECTION_NE = 1;
	private static final int DIRECTION_E = 2;
	private static final int DIRECTION_SE = 3;
	private static final int DIRECTION_S = 4;
	private static final int DIRECTION_SW = 5;
	private static final int DIRECTION_W = 6;
	private static final int DIRECTION_NW = 7;
	/* table on board */
	private int[][] table = new int[BOARD_SIZE][BOARD_SIZE];

	private void clear_board(){
		int x, y;

		for(y = 0; y < BOARD_SIZE; y++){
			for(x = 0; x < BOARD_SIZE; x++){
				table[x][y] = COLOR_NONE;
			}
		}

		return;
	}

	private boolean is_range_valid(int x, int y){
		if(x < 0 || y < 0 || x > BOARD_SIZE - 1 || y > BOARD_SIZE - 1){
			return false;
		}
		
		return true;
	}
	
	private boolean put_one(int x, int y, int color){
		boolean ret = true;

		if(is_range_valid(x, y) == false) {
			ret = false;
		}else if((table[x][y] == COLOR_BLACK)||(table[x][y] == COLOR_WHITE)){
			ret = false;
		}else{
			table[x][y] = color;
		}

		return ret;
	}
	
	/**
	 *　コンストラクタ
	 */
	public Board(){
		clear_board();
	}

	/**
	 *　板の上をゲーム開始の状態にする
	 */
	public void prepareBoard(){
		clear_board();

		table[3][3] = table[4][4] = COLOR_WHITE;	//initial white piece
		table[3][4] = table[4][3] = COLOR_BLACK;	//initial black piece
	}

	public void clearTable(){
		clear_board();
	}

	/**
	 *　石を置く
	 * @param x x座標
	 * @param y y座標
	 * @param color 置く石の色
	 * @return 置ける　true　置けない　false
	 */
	public boolean putPiece(int x, int y, int color){
		return put_one(x,y,color);
	}

	/**
	 * @param x
	 * @param y
	 * @param color
	 * @return
	 */
	public boolean putPieceAndTurn(int x, int y, int color){
		return put_one(x,y,color);
	}

	/**
	 *　値を調べる
	 * @param x x座標
	 * @param y y座標
	 * @return 値
	 */
	public int getVal(int x, int y) {
		return get_val(x, y);
	}

	private int get_val(int x, int y) {
		int ret;

		if(is_range_valid(x, y) == false) {
			ret = -1;
		}else {
			ret = table[x][y];
		}

		return ret;
	}

	/**
	 *　石が置けるか調べる
	 * @param x x座標
	 * @param y y座標
	 * @param color 置く石の色
	 * @return 置ける　true　置けない　false
	 */
	public boolean canPut(int x, int y, int color){
		return can_put(x, y, color);
	}

	private boolean can_put(int x, int y, int color) {
		if(is_exist(x, y) == true) {
			return false;
		}
		
		if((dirCheck(x, y, color, DIRECTION_N)==true)||
			(dirCheck(x, y, color, DIRECTION_E)==true)||
			(dirCheck(x, y, color, DIRECTION_W)==true)||
			(dirCheck(x, y, color, DIRECTION_S)==true)||
			(dirCheck(x, y, color, DIRECTION_SE)==true)||
			(dirCheck(x, y, color, DIRECTION_SW)==true)||
			(dirCheck(x, y, color, DIRECTION_NW)==true)||
			(dirCheck(x, y, color, DIRECTION_NE)==true)){
			return true;
		}

		return false;
	}

	private boolean dirCheck(int x, int y, int color, int direction) {
		final int STATE_INITIAL = 0;
		final int STATE_FINDNEXT = 1;
		final int STATE_CANPLACE = 2;
		final int STATE_CANNOTPLACE = 3;
		int max_cnt, x_step, y_step;
		int state = STATE_INITIAL;
		
		/* decide check range */
		switch (direction) {
		case DIRECTION_E:
			max_cnt = BOARD_SIZE - x;
			x_step = 1;
			y_step = 0;
			break;
		case DIRECTION_W:
			max_cnt = x + 1;
			x_step = -1;
			y_step = 0;
			break;
		case DIRECTION_S:
			max_cnt = BOARD_SIZE - y;
			x_step = 0;
			y_step = 1;
			break;
		case DIRECTION_N:
			max_cnt = y + 1;
			x_step = 0;
			y_step = -1;
			break;
		case DIRECTION_SE:
			max_cnt = Math.min(BOARD_SIZE - x, BOARD_SIZE - y);
			x_step = 1;
			y_step = 1;
			break;
		case DIRECTION_SW:
			max_cnt = Math.min(x + 1, BOARD_SIZE - y);
			x_step = -1;
			y_step = 1;
			break;
		case DIRECTION_NW:
			max_cnt = Math.min(x + 1, y + 1);
			x_step = -1;
			y_step = -1;
			break;
		case DIRECTION_NE:
			max_cnt = Math.min(BOARD_SIZE - x, y + 1);
			x_step = 1;
			y_step = -1;
			break;
		default:
			max_cnt = 0;
			x_step = 0;
			y_step = 0;
			break;
		}
		
		/* search pieces in the line while moving state */
		int cur_x_pos = x;
		int cur_y_pos = y;
		for(int cnt = 0; cnt < max_cnt; cnt++){
			switch(state){
			case STATE_INITIAL:	
				if(cnt == 1 && table[cur_x_pos][cur_y_pos] == oposite_color(color)) {
					state = STATE_FINDNEXT;
				}
				break;
			case STATE_FINDNEXT:
				if(table[cur_x_pos][cur_y_pos] == color){
					state = STATE_CANPLACE;
				}else if(table[cur_x_pos][cur_y_pos] == oposite_color(color)){
					state = STATE_FINDNEXT;
				}else {
					state = STATE_CANNOTPLACE;
				}
				break;
			case STATE_CANPLACE:
			default:
				break;
			}
			cur_x_pos += x_step;
			cur_y_pos += y_step;
		}
		
		if(state == STATE_CANPLACE) {
			return true;
		}
		return false;
	}

	private static int oposite_color(int color) {
		int ret_color;
		
		switch(color) {
		case Board.COLOR_BLACK:
			ret_color = Board.COLOR_WHITE;
			break;
		case Board.COLOR_WHITE:
			ret_color = Board.COLOR_BLACK;
			break;
		default:
			ret_color = Board.COLOR_NONE;
			break;
		}

		return ret_color;
	}

	/**
	 *　以下はデバッグ用
	 */
	public void showBoard(){
		int x, y;

//		System.out.println("  abcdefgh");
		System.out.println("  01234567");
		System.out.println(" +--------+");
		for(y = 0; y < BOARD_SIZE; y++){
//			System.out.print(y+1 + "|");
			System.out.print(y + "|");
			for(x = 0; x < BOARD_SIZE; x++){
				switch(table[x][y]){
				case COLOR_WHITE:
					System.out.print("o");
					break;
				case COLOR_BLACK:
					System.out.print("*");
					break;
				case COLOR_NONE:
					System.out.print(".");
					break;
				default:
					System.out.print("#");
					break;
				}
			}
			System.out.print("|\n");
		}
		System.out.println(" +--------+");
		System.out.println("\n");
	}

	/**
	 * その場所に石が置かれているか調べる
	 * @param x x軸
	 * @param y y軸
	 * @return true 石がある false 石がない
	 */
	public boolean isExist(int x, int y) {
		return is_exist(x, y);
	}

	private boolean is_exist(int x, int y) {
		if(is_range_valid(x, y) == false) {
			return false;
		}

		if((table[x][y] == COLOR_BLACK)||(table[x][y] == COLOR_WHITE)) {
			return true;
		}else {
			return false;
		}
	}

	public String getBoardData() {
		String boardState = new String();
		int x, y;
		
		for(y = 0; y < BOARD_SIZE; y++){
			for(x = 0; x < BOARD_SIZE; x++){
				switch(table[x][y]) {
				case COLOR_NONE:
					boardState += "0";
					break;
				case COLOR_BLACK:
					boardState += "1";
					break;
				case COLOR_WHITE:
					boardState += "2";
					break;
				default:
					boardState += "X";
					break;
				}
			}
		}
		
		return boardState;
	}

	public boolean setBoardData(String src) {
		if(src.length() != NUM_OF_CELLS){
			return false;
		}
		
		int x, y;
		for(y = 0; y < BOARD_SIZE; y++){
			for(x = 0; x < BOARD_SIZE; x++){
				table[x][y] = Integer.parseInt("" + src.charAt(y * BOARD_SIZE + x));
			}
		}

		return true;
	}
}

