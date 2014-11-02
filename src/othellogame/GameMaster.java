package othellogame;

public class GameMaster {
	private Player p1, p2;
	
	public GameMaster(){
		p1 = PlayerFactory.getPlayer(PlayerFactory.TYPE_CPU, Player.SIDE_BLACK);	
		p2 = PlayerFactory.getPlayer(PlayerFactory.TYPE_USER, Player.SIDE_WHITE);
	}
	public void start(){
		Board bd = new Board();
		
		bd.prepareBoard();

		p1.registBoard(bd);
		p2.registBoard(bd);
		
		boolean end_game = false;
		while(end_game == false){
			p1.turn();
			p2.turn();
		}
	}
}
