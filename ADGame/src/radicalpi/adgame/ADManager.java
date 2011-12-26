package radicalpi.adgame;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class ADManager {
	private static Map<Player, ADPlayer> players = new HashMap<Player, ADPlayer>();
	
	static boolean playerJoin(Player player, PlayerTeam team)
	{
		if (!isPlaying(player))
		{
			ADPlayer gamePlayer = new ADPlayer(player, team);
			players.put(player, gamePlayer);
			return true;
		} else {
			return false;
		}
	}
	
	static boolean isPlaying(Player player)
	{
		return players.containsKey(player);
	}
}
