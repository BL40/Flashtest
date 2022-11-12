package dev.bronzylobster.flashtest.listeners;

import dev.bronzylobster.flashtest.events.DamageEvent;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if (damager instanceof Player) {
            LivingEntity livingDamager = (LivingEntity) event.getDamager();
            LivingEntity damagee = (LivingEntity) event.getEntity();
            double damage = event.getFinalDamage();
            EntityEquipment equipment = livingDamager.getEquipment();
            ItemStack item = equipment.getItemInMainHand();
            if (    item.getType().equals(Material.DIAMOND_SWORD) ||
                    item.getType().equals(Material.GOLDEN_SWORD) ||
                    item.getType().equals(Material.NETHERITE_SWORD)||
                    item.getType().equals(Material.IRON_SWORD) ||
                    item.getType().equals(Material.WOODEN_SWORD) ||
                    item.getType().equals(Material.STONE_SWORD)) {
                DamageEvent event1 = new DamageEvent(livingDamager, damagee, damage);
                event1.callEvent();
            }
        }
    }
}
