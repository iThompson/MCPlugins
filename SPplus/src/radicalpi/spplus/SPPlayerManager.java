package radicalpi.spplus;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class SPPlayerManager {
	private static Map<Player, GameMode> _players = new HashMap<Player, GameMode>();
	private static boolean bRunningGame = false;
	
	public static boolean beginPlayerGame(Player player)
	{
		if (!bRunningGame)
		{
			GameMode mode = player.getGameMode();
			player.setGameMode(GameMode.SURVIVAL);
			if (player.getGameMode() != GameMode.SURVIVAL){
				// Someone else has locked the GameMode, we should abort
				player.sendMessage(ChatColor.RED + "You are already playing something else");
				return false;
			}
			_players.put(player, mode);
			player.sendMessage(ChatColor.RED + "Spleef mode initialized");
		} else {
			player.sendMessage(ChatColor.RED + "The Spleef game is already running!");
			return false;
		}
		return true;
	}
	
	/** Removes a Player from the list and restores their GameMode.
	 *  Also checks for endgame
	 * @param player The Player to remove
	 */
	public static void endPlayerGame(Player player)
	{
		if (isPlayingGame(player))
		{
			GameMode mode = _players.get(player);
			_players.remove(player);
			player.setGameMode(mode);
			player.sendMessage(ChatColor.RED + "Previous Game Mode restored");
			
			// _players should be empty when the game is over
			if (bRunningGame) bRunningGame = !_players.isEmpty();
		}
	}
	
	/** Checks whether a Player is registered for the game
	 * 
	 * @param player The Player to check
	 * @return true if the Player is registered
	 */
	public static boolean isPlayingGame(Player player)
	{
		return _players.containsKey(player);
	}
	
	/** Cancels the current game and restores GameMode on all registered players
	 * 
	 */
	public static void restoreAllPlayers()
	{
		for (Player player : _players.keySet()) {
			endPlayerGame(player);
		}
		_players.clear();
		bRunningGame = false;
	}
	
	/** Begin a game
	 * 
	 */
	public static void startGame()
	{
		bRunningGame = true;
	}
	
	/** Whether a game is currently running
	 * 
	 * @return true if a game is running
	 */
	public static boolean isRunningGame()
	{
		return bRunningGame;
	}
	
	/** Check if the game has players registered
	 * 
	 * @return true if there are players who have joined
	 */
	public static boolean hasPlayers()
	{
		return !_players.isEmpty();
	}
	
}
