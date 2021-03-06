package othellogame;

public abstract class Player {
	public static final int SIDE_BLACK = 0;
	public static final int SIDE_WHITE = 1;
	protected int pside;
	protected Board bd;
	
	public Player(int side){
		this.pside = side;
	}
	public abstract boolean turn();
	
	public void registBoard(Board bd){
		this.bd = bd;
	}
}
