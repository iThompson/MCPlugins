package radicalpi.adgame;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class ADManager {
	private static Map<Player, ADPlayer> players = new HashMap<Player, ADPlayer>();
	
	static boolean playerJoin(Player player, PlayerTeam team)
	{
		if (!isPlaying(player))
		{
			ADPlayer gamePlayer = new ADPlayer(player, team);
			player.setGameMode(GameMode.SURVIVAL);
			if (player.getGameMode() != GameMode.SURVIVAL) {
				// Some other plugin blocked the mode change
				return false;
			}
			players.put(player, gamePlayer);
			return true;
		} else {
			return false;
		}
	}
	
	static void playerLeave(ADPlayer gamePlayer)
	{
		playerLeave(gamePlayer.getPlayer());
	}
	
	static void playerLeave(Player player)
	{
		ADPlayer gamePlayer = players.get(player);
		if (gamePlayer != null)
		{ // If NULL, the player wasn't in the list
			gamePlayer.restoreLastMode();
			players.remove(player);
		}
	}
	
	static boolean isPlaying(Player player)
	{
		return players.containsKey(player);
	}
}
