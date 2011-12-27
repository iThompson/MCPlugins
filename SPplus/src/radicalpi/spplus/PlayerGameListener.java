package radicalpi.spplus;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerTeleportEvent;

import shadowmax507.spleefextreme.GameManager;

public class PlayerGameListener extends PlayerListener {
	
	/** The Player is automatically teleported at the end of the game.
	 *  Listens for the teleport and updates the player appropriately
	 *  
	 *  @param event The teleportation event
	 */
	@Override
	public void onPlayerTeleport(PlayerTeleportEvent event)
	{
		Player player = event.getPlayer();
		
		if (SPPlayerManager.isPlayingGame(player) && !event.isCancelled())
		{
			// Check if this is the entry teleport
			if (event.getTo().equals(GameManager.getGame("game").getTp("join")) || event.getTo().equals(GameManager.getGame("game2").getTp("join")))
			{
				return;
			}
			
			SPPlayerManager.endPlayerGame(player, SPPlayerManager.getCurrentGame(player));
		}
	}
	
	/** Watch for and block attempts to change game mode if needed
	 * 
	 * @param event The Game Mode Change event
	 */
	@Override
	public void onPlayerGameModeChange(PlayerGameModeChangeEvent event)
	{	
		Player player = event.getPlayer();
		if (SPPlayerManager.isPlayingGame(player))
		{
			event.setCancelled(true);
			player.sendMessage(ChatColor.RED + "Command aborted: You can't change modes during a Spleef game");
		}
	}
}
