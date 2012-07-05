package us.aaronweiss.roguecraft.listeners;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

/**
 * A listener to deal additional damage with backstabs.
 * @author Aaron Weiss
 * @version 1.2
 */
public class BackstabListener implements Listener {
	@EventHandler
	public void backstab(EntityDamageByEntityEvent e) {
		try {
			Player attacker = (Player) e.getDamager();
			LivingEntity attacked = (LivingEntity) e.getEntity();
			Location loc = attacker.getLocation();
			Location col = attacked.getLocation();
			Vector dir = attacked.getLocation().getDirection().normalize();
			if (attacker.isSneaking()) {
				double a = dir.getX();
				double c = dir.getZ();
				double d = -(a * col.getX() + c * col.getZ()) / Math.sqrt(a*a + c*c);
				if ((a * loc.getX()) + (c * loc.getZ()) + d < 0) {
					attacked.damage(e.getDamage() * 2);
					attacker.giveExp((int) Math.round(attacked.getMaxHealth() * 0.5));
				}
			}
		} catch (ClassCastException ex) {
			return;
		}
	}
}
