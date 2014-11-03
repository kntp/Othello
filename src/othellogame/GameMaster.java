package othellogame;

public class GameMaster {
	private Player player1, player2;
	
	public GameMaster(){
		player1 = PlayerFactory.getPlayer(PlayerFactory.TYPE_CPU, Player.SIDE_BLACK);	
		player2 = PlayerFactory.getPlayer(PlayerFactory.TYPE_USER, Player.SIDE_WHITE);
	}
	public GameMaster(Player p1, Player p2) {
		this.player1 = p1;
		this.player2 = p2;
	}
	
	public void start(){
		Board bd = new Board();
		
		bd.prepareBoard();

		player1.registBoard(bd);
		player2.registBoard(bd);
		
		boolean end_game = false;
		while(end_game == false){
			player1.turn();
			player2.turn();
			end_game = true;
		}
	}
}
