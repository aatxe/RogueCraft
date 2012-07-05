package us.aaronweiss.roguecraft.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.util.Vector;

/**
 * A listener to fix the AI for sneaking.
 * @author Aaron Weiss
 * @version 1.2
 */
public class SneakEntityListener implements Listener {
	@EventHandler
	public void fixEntityAI(EntityTargetEvent e) {
		try {
			Entity entity = e.getEntity();
			Player target = (Player) e.getTarget();
			Location loc = target.getLocation();
			Location col = entity.getLocation();
			Vector dir = entity.getLocation().getDirection().normalize();
			if (target.isSneaking()) {
				double a = dir.getX();
				double c = dir.getZ();
				double d = -(a * col.getX() + c * col.getZ()) / Math.sqrt(a*a + c*c);
				if ((a * loc.getX()) + (c * loc.getZ()) + d < 0) {
					e.setCancelled(true);
				}
			}
		} catch (ClassCastException ex) {
			return;
		} catch (NullPointerException ex) {
			return;
		}
	}
}
