package othellogame;

public class GameMaster {
	
	public void start(){
		Board bd = new Board();
		Player p1 = PlayerFactory.getPlayer(0, Player.SIDE_BLACK);	
		Player p2 = PlayerFactory.getPlayer(1, Player.SIDE_WHITE);	
		
		bd.prepareBoard();

		p1.registBoard(bd);
		p2.registBoard(bd);
	
	}
}
