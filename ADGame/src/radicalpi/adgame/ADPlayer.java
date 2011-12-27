package radicalpi.adgame;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class ADPlayer {
	private Player player;
	
	private PlayerTeam team;
	
	private GameMode lastMode;
	
	public ADPlayer(Player _player, PlayerTeam _team)
	{
		player = _player;
		team = _team;
		lastMode = _player.getGameMode();
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public PlayerTeam getTeam()
	{
		return team;
	}
	
	public void restoreLastMode()
	{
		player.setGameMode(lastMode);
	}
}
