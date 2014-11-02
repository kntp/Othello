package othellogame;

public class PlayerFactory {
	public static final int TYPE_CPU = 0;
	public static final int TYPE_USER = 1;

	public static Player getPlayer(int type, int side){
		Player pl;
		if(type == TYPE_CPU){
			pl = new CpuPlayer(side);
		}else{
			pl = new UserPlayer(side);
		}
		
		return pl;
	}
}
