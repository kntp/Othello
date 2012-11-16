package othellogame;

public class PlayerFactory {

	public static Player getPlayer(int type, int side){
		Player pl;
		if(type == 0){
			pl = new CpuPlayer(side);
		}else{
			pl = new UserPlayer(side);
		}
		
		return pl;
	}
}
