package radicalpi.spplus;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class GameStart implements Runnable {

	Player _player = null;
	Server _server = null;
	int _time;
	
	/** Initialize the GameStart task
	 * @param pl The player initializing the countdown
	 * @param time The time remaining on the countdown
	 */
	public GameStart(Player pl, int time)
	{
		_server = pl.getServer();
		_player = pl;
		_time = (time < 0) ? 0 : time;
	}
	
	/** Sends the countdown messages and begins the game
	 * 
	 */
	@Override
	public void run() {
		if (_time > 0)
		{
			_server.broadcastMessage(ChatColor.GREEN + "[SPLEEF] " + _time + "...");
		} else {
			_server.broadcastMessage(ChatColor.GREEN + "[SPLEEF] Starting Spleef game!");
			_server.dispatchCommand(_player, "spleef reset game");
			_server.dispatchCommand(_player, "spleef start game");
		}
	}
	
}
