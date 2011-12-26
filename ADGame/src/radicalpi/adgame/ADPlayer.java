package radicalpi.adgame;

import org.bukkit.entity.Player;

public class ADPlayer {
	private Player player;
	
	private PlayerTeam team;
	
	public ADPlayer(Player _player, PlayerTeam _team)
	{
		player = _player;
		team = _team;
	}
	
	public PlayerTeam getTeam()
	{
		return team;
	}
}
