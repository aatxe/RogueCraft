package us.aaronweiss.roguecraft.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

/**
 * A listener to fix the AI for sneaking.
 * @author Aaron Weiss
 * @version 1.0
 */
public class SneakEntityListener implements Listener {
	@EventHandler
	public void fixEntityAI(EntityTargetEvent e) {
		try {
			Entity entity = e.getEntity();
			Player target = (Player) e.getTarget();
			double yaw = entity.getLocation().getYaw();
			double yaw2 = target.getLocation().getYaw();
			double thresh = 55.0;
			if (target.isSneaking()) {
				if (yaw < 0 && yaw2 > 0) {
					yaw += 360;
				} else if (yaw > 0 && yaw2 < 0) {
					yaw2 +=  360;
				}
				if (yaw > 360) {
					yaw %= 360;
				} else if (yaw2 > 360) {
					yaw2 %= 360;
				}
				if (Math.abs(yaw - yaw2) <= thresh) {
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
