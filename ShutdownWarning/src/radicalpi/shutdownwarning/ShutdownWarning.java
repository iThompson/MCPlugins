package radicalpi.shutdownwarning;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class ShutdownWarning extends JavaPlugin {
	
	Logger log = Logger.getLogger("Minecraft");
	
	private PluginDescriptionFile pdf;

	@Override
	public void onDisable() {
		log.info("Stopping " + pdf.getFullName());
	}

	@Override
	public void onEnable() {
		pdf = getDescription();
		
		log.info("Starting " + pdf.getFullName());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		int time = 5;
		int i;
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!player.isOp()) {
				player.sendMessage(ChatColor.RED + "You cannot use this command!");
				return true;
			}
		}
		
		if (args.length == 1) {
			try {
				time = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				sender.sendMessage(ChatColor.RED + "That wasn't a proper int!");
				return true;
			}
		}
		
		getServer().broadcastMessage(ChatColor.RED + "[SERVER] Shutdown initiated");
		
		BukkitScheduler scheduler = getServer().getScheduler();
		
		for (i=1; time >= 0; time--, i++) {
			scheduler.scheduleSyncDelayedTask(this, new PlayerKick(getServer(), time), i * 20L);
		}
		// Shut down the server 5 seconds after the players are kicked
		scheduler.scheduleSyncDelayedTask(this, new ServerShutdown(getServer()), (i+5) * 20L);
		
		return true;
	}
}
