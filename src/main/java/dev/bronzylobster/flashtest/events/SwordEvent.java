package dev.bronzylobster.flashtest.events;

import dev.bronzylobster.flashtest.util.DataBase;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SwordEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private DataBase db;
    {
        try {
          db = new DataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SwordEvent(LivingEntity damager, LivingEntity damagee, double damage) {
        EntityEquipment equipment = damager.getEquipment();
        ItemStack item = equipment.getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            List<Component> lore = meta.hasLore() ? meta.lore() : new ArrayList<>();
            if (damage >= damagee.getHealth()) {
                if (!(lore.isEmpty())) {
                    int x = db.getA("sword", String.valueOf(lore.get(1)));
                    db.set("sword", String.valueOf(lore.get(1)), ++x);
                    lore.set(0, Component.text("Kills: " + x));
                }
                meta.lore(lore);
                item.setItemMeta(meta);
            }
        }
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
