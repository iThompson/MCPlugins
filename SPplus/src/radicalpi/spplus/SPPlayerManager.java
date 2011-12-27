package radicalpi.spplus;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class SPPlayerManager {
	private static Map<Player, GameMode> _players = new HashMap<Player, GameMode>();
	private static Map<Player, GameMode> _players2 = new HashMap<Player, GameMode>();
	private static boolean bRunningGame = false;
	private static boolean bRunningGame2 = false;
	
	public static boolean beginPlayerGame(Player player, int gameNum)
	{
		if (gameNum == 1)
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
		} else if (gameNum == 2)
		{
			if (!bRunningGame2)
			{
				GameMode mode = player.getGameMode();
				player.setGameMode(GameMode.SURVIVAL);
				if (player.getGameMode() != GameMode.SURVIVAL){
					// Someone else has locked the GameMode, we should abort
					player.sendMessage(ChatColor.RED + "You are already playing something else");
					return false;
				}
				_players2.put(player, mode);
				player.sendMessage(ChatColor.RED + "Spleef mode initialized");
			} else {
				player.sendMessage(ChatColor.RED + "The Spleef game is already running!");
				return false;
			}
			return true;
		}
		return false;
	}
	
	/** Removes a Player from the list and restores their GameMode.
	 *  Also checks for endgame
	 * @param player The Player to remove
	 */
	public static void endPlayerGame(Player player, int gameNum)
	{
		if (gameNum == 1) 
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
		} else if (gameNum == 2)
		{
			if (isPlayingGame(player))
			{
				GameMode mode = _players2.get(player);
				_players2.remove(player);
				player.setGameMode(mode);
				player.sendMessage(ChatColor.RED + "Previous Game Mode restored");

				// _players should be empty when the game is over
				if (bRunningGame2) bRunningGame2 = !_players.isEmpty();
			}
		}
	}
	
	/** Checks whether a Player is registered for the game
	 * 
	 * @param player The Player to check
	 * @return true if the Player is registered
	 */
	public static boolean isPlayingGame(Player player)
	{
		return _players.containsKey(player) || _players2.containsKey(player);
	}
	
	/** Cancels the current game and restores GameMode on all registered players
	 * 
	 */
	public static void restoreAllPlayers(int gameNum)
	{
		if (gameNum == 1)
		{
			for (Player player : _players.keySet()) {
				endPlayerGame(player, gameNum);
			}
			_players.clear();
			bRunningGame = false;
		} else if (gameNum == 2)
		{
			for (Player player : _players2.keySet()) {
				endPlayerGame(player, gameNum);
			}
			_players2.clear();
			bRunningGame2 = false;
		}
	}
	
	/** Begin a game
	 * 
	 */
	public static void startGame(int gameNum)
	{
		if (gameNum == 1) bRunningGame = true;
		else if (gameNum == 2) bRunningGame2 = true;
	}
	
	/** Whether a game is currently running
	 * 
	 * @return true if a game is running
	 */
	public static boolean isRunningGame(int gameNum)
	{
		if (gameNum == 1) return bRunningGame;
		else if (gameNum == 2) return bRunningGame2;
		return false;
	}
	
	public static boolean isRunningGame()
	{
		return isRunningGame(1) || isRunningGame(2);
	}
	
	/** Check if the game has players registered
	 * 
	 * @return true if there are players who have joined
	 */
	public static boolean hasPlayers(int gameNum)
	{
		if (gameNum == 1) return !_players.isEmpty();
		else if (gameNum == 2) return !_players2.isEmpty();
		return false;
	}
	
	public static int getCurrentGame(Player player)
	{
		if (_players.containsKey(player))
		{
			return 1;
		} else if (_players2.containsKey(player))
		{
			return 2;
		} else {
			return 0;
		}
	}
	
}
