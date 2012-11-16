package othellogame;

public abstract class Player {
	public static final int SIDE_BLACK = 0;
	public static final int SIDE_WHITE = 1;
	private int pside;
	
	public Player(int side){
		this.pside = side;
	}
	public abstract boolean turn(Board bd);
}
