package othellogame;

public class CpuPlayer extends Player {

	public CpuPlayer(int type){
		super(type);
	}
	@Override
	public boolean turn() {
		for(int y = 0; y < Board.BOARD_SIZE; y++){
			for(int x = 0; x < Board.BOARD_SIZE; x++){
				if(this.bd.canPut(x, y, GameMaster.stoneColor(this.pside)) == true) {
					this.bd.putAndTurn(x, y, GameMaster.stoneColor(this.pside));
					this.bd.showBoard();
					return true;
				}
			}
		}
		return false;
	}

}
