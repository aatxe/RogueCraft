package us.aaronweiss.roguecraft;

import java.util.logging.Logger;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import us.aaronweiss.roguecraft.listeners.BackstabListener;
import us.aaronweiss.roguecraft.listeners.SneakEntityListener;

/**
 * RogueCraft: a Bukkit plugin to make stealth worthwhile.
 * @author Aaron Weiss
 * @version 1.2
 */
public class RogueCraft extends JavaPlugin {
	public final Logger logger = Logger.getLogger("Minecraft");
	private PluginDescriptionFile pdf;
	
	@Override
	public void onEnable() {
		pdf = this.getDescription();
		this.getServer().getPluginManager().registerEvents(new SneakEntityListener(), this);
		this.getServer().getPluginManager().registerEvents(new BackstabListener(), this);
		this.print("RogueCraft loaded.");
	}
	
	@Override
	public void onDisable() {
		this.print("RogueCraft disabled.");
	}
	
	public void print(String message) {
		this.logger.info("[" + pdf.getName() + "] " + message);
	}
}
