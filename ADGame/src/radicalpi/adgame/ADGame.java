/**
 * 
 */
package radicalpi.adgame;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Ian Thompson
 *
 */
public class ADGame extends JavaPlugin {
	
	private Logger log = Logger.getLogger("Minecraft");
	
	private PluginDescriptionFile pdf;
	
	PlayerGameListener gameListener = null;
	PlayerBlockListener blockListener = null;

	/**
	 * Cleanly shuts down the AD Game plugin
	 * @see org.bukkit.plugin.Plugin#onDisable()
	 */
	@Override
	public void onDisable() {
		
		log.info("[" + pdf.getName() + "] disabled!");
	}

	/**
	 * Performs initialization of the AD Game plugin
	 * @see org.bukkit.plugin.Plugin#onEnable()
	 */
	@Override
	public void onEnable() {
		pdf = getDescription();

		gameListener = new PlayerGameListener();
		blockListener = new PlayerBlockListener();
		
		log.info("[" + pdf.getName() + "] v" + pdf.getVersion() + " enabled!");
	}

}
