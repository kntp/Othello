package othellogame;

public class Board {
	public static final int COLOR_NONE = 0;
	public static final int COLOR_BLACK = 1;
	public static final int COLOR_WHITE = 2;
	public static final int BOARD_SIZE = 8;
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
	 *　値を調べる
	 * @param x x座標
	 * @param y y座標
	 * @return 値
	 */
	public int getVal(int x, int y) {
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
	public boolean isPuttable(int x, int y, int side){
		if(is_range_valid(x, y) == false) {
			return false;
		}else {
			return true;
		}
	}

	/**
	 *　以下はデバッグ用
	 */
	public void showBoard(){
		int x, y;

		System.out.println("  abcdefgh");
		System.out.println(" +--------+");
		for(y = 0; y < BOARD_SIZE; y++){
			System.out.print(y+1 + "|");
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

	public boolean isExist(int x, int y) {
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
}
