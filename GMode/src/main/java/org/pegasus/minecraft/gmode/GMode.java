package org.pegasus.minecraft.gmode;

// Testing auto-load of plugins

import java.util.logging.Logger;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class GMode extends JavaPlugin {
	
	Logger log = Logger.getLogger("Minecraft");
	
	private PluginDescriptionFile pdf;

	public void onDisable() {
		log.info("[" + pdf.getName() + "] Disabled");
	}

	public void onEnable() {
		pdf = getDescription();

		log.info("[" + pdf.getName() + "] Initialized v" + pdf.getVersion());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		Player player = null;
		String subCmd = null;
		
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		
		if (args.length == 0)
		{
			sender.sendMessage(pdf.getName() + " v" + pdf.getVersion());
			sender.sendMessage(pdf.getDescription());
			return true;
		}
		
		if (args.length >= 1)
		{
			if (player == null)
			{
				sender.sendMessage("[" + pdf.getName() + "] This command cannot be run from console");
				return true;
			}
			
			subCmd = args[0];
			
			if (subCmd.equalsIgnoreCase("creative") || subCmd.equalsIgnoreCase("c"))
			{
				player.setGameMode(GameMode.CREATIVE);
				return true;
			} else if (subCmd.equalsIgnoreCase("survival") || subCmd.equalsIgnoreCase("s"))
			{
				player.setGameMode(GameMode.SURVIVAL);
				return true;
			}
		}
		
		return false;
	}
}
