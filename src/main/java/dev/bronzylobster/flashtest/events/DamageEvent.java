package dev.bronzylobster.flashtest.events;

import dev.bronzylobster.flashtest.util.DataBase;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DamageEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private DataBase db;

    {
        try {
          db = new DataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DamageEvent(LivingEntity damager, LivingEntity damagee, double damage) {
        EntityEquipment equipment = damager.getEquipment();
        String nick = damager.getName();

        if (equipment instanceof PlayerInventory) {
            equipment.getItemInMainHand();
        } else {
            equipment.getItemInMainHand();
        }
        ItemStack item = equipment.getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            List<Component> lore = meta.hasLore() ? meta.lore() : new ArrayList<>();
            if (damage >= damagee.getHealth()) {
                if (lore.isEmpty()) {
                    int itemID = db.getID("sword");
                    lore.add(0, Component.text("Kills count: 1"));
                    lore.add(1, Component.text("Item ID: " + itemID++));
                    db.setID("sword", itemID);
                    db.add("sword", String.valueOf(lore.get(1)), 1, nick);
                } else {
                    int x = db.getA("sword", String.valueOf(lore.get(1)));
                    db.set("sword", String.valueOf(lore.get(1)), ++x);
                    lore.set(0, Component.text("Kills count: " + x));
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
