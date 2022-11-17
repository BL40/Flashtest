package dev.bronzylobster.flashtest.listeners;

import dev.bronzylobster.flashtest.events.SwordEvent;
import dev.bronzylobster.flashtest.util.DataBase;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemListener implements Listener {

    private DataBase db;
    @EventHandler(priority = EventPriority.HIGH)
    public void onSwordUsed(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();

        if (damager instanceof Player) {
            {
                try {
                    db = new DataBase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            LivingEntity livingDamager = (LivingEntity) event.getDamager();
            String owner = livingDamager.getName();
            LivingEntity damagee = (LivingEntity) event.getEntity();

            double damage = event.getFinalDamage();

            EntityEquipment equipment = livingDamager.getEquipment();
            ItemStack item = equipment.getItemInMainHand();
            ItemMeta meta = item.getItemMeta();
            List<Component> lore = meta.hasLore() ? meta.lore() : new ArrayList<>();
            if (!(lore.isEmpty())) {
                if (owner.equalsIgnoreCase(db.getN("sword", String.valueOf(lore.get(1))))) {
                    SwordEvent event1 = new SwordEvent(livingDamager, damagee, damage);
                    event1.callEvent();
                } else {
                    event.setCancelled(true);
                    livingDamager.sendMessage(Component.text("You are not sword owner"));
                }
            }
        }
    }
}
