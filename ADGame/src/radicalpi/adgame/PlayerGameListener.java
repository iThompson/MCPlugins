package radicalpi.adgame;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerListener;

public class PlayerGameListener extends PlayerListener {

	/** Monitor GameMode changes, block if necessary
	 * @see org.bukkit.event.player.PlayerListener#onPlayerGameModeChange(org.bukkit.event.player.PlayerGameModeChangeEvent)
	 */
	@Override
	public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
		Player pl = event.getPlayer();
		if (ADManager.isPlaying(pl)) {
			event.setCancelled(true);
			pl.sendMessage(ChatColor.RED + "[ADGame] You cannot change GameMode while ingame");
		}
	}

}
