package us.aaronweiss.roguecraft.listeners;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * A listener to deal additional damage with backstabs.
 * @author Aaron Weiss
 * @version 1.0
 */
public class BackstabListener implements Listener {
	@EventHandler
	public void backstab(EntityDamageByEntityEvent e) {
		try {
			Player attacker = (Player) e.getDamager();
			LivingEntity attacked = (LivingEntity) e.getEntity();
			double yaw = attacker.getLocation().getYaw();
			double yaw2 = attacked.getLocation().getYaw();
			double thresh = 55.0;
			if (attacker.isSneaking()) {
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
					attacked.damage(e.getDamage() * 2, e.getDamager());
					attacker.giveExp(attacked.getMaxHealth() * 2);
				}
			}
		} catch (ClassCastException ex) {
			return;
		}
	}
}
