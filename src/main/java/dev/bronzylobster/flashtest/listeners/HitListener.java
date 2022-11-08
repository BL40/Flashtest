package dev.bronzylobster.flashtest.listeners;

import dev.bronzylobster.flashtest.events.DamageEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class HitListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamaget(EntityDamageByEntityEvent event) {
        LivingEntity damager = (LivingEntity) event.getDamager();
        LivingEntity damagee = (LivingEntity) event.getEntity();
        double damage = event.getFinalDamage();
        DamageEvent event1 = new DamageEvent(damager, damagee, damage);
        event1.callEvent();
    }
}
