package othellogame;

public class Board {
	public static final int COLOR_NONE = 0;
	public static final int COLOR_BLACK = 1;
	public static final int COLOR_WHITE = 2;
	public static final int BOARD_SIZE = 8;
	private int[][] cell = new int[BOARD_SIZE][BOARD_SIZE];

	private void clearBoard(){
		int x, y;
		
		for(x = 0; x < BOARD_SIZE; x++){
			for(y = 0; y < BOARD_SIZE; y++){
				cell[x][y] = COLOR_NONE;
			}
		}

		return;
	}
	
	public Board(){
		clearBoard();
	}

	public void initBoard(){
		clearBoard();
		
		cell[3][3] = cell[4][4] = COLOR_WHITE;	//initial white piece
		cell[3][4] = cell[4][3] = COLOR_BLACK;	//initial black piece
	}
	
	public void teClear(){
		clearBoard();
	}
	
	public boolean putpiece(int x, int y, int color){
		boolean ret = true;
		
		if((x >= BOARD_SIZE)||(y >= BOARD_SIZE)) {
			ret = false;
		}else if((cell[x][y] == COLOR_BLACK)||(cell[x][y] == COLOR_WHITE)){
			ret = false;
		}else{
			cell[x][y] = color;
		}
		
		return ret;
	}
	
	public int getval(int x, int y) {
		int ret;
		
		if(x < 0 || y < 0 || x > BOARD_SIZE - 1 || y > BOARD_SIZE - 1){
			ret = -1;
		}else {
			ret = cell[x][y];
		}
		
		return ret;
	}
	
	public boolean checklocation(int x, int y, int side){
		return false;
	}
	
	public void show(){
		int x, y;
		
		System.out.println("  abcdefgh");
		System.out.println(" +--------+");
		for(y = 0; y < BOARD_SIZE; y++){
			System.out.print(y+1 + "|");
			for(x = 0; x < BOARD_SIZE; x++){
				switch(cell[x][y]){
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

}
