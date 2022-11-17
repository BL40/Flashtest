package dev.bronzylobster.flashtest.listeners;

import dev.bronzylobster.flashtest.Flashtest;
import dev.bronzylobster.flashtest.util.DataBase;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CraftExecutor implements CommandExecutor {

    private DataBase db;
    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args) {
        Player player = null;
        {
            try {
                db = new DataBase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (sender instanceof Player) player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("create")) {
            if (player == null) {
                sender.sendMessage("This command no for console");
            } else {
                EntityEquipment equipment = player.getEquipment();
                ItemStack item = equipment.getItemInMainHand();
                int itemID = 0;
                String type = null;
                String owner = player.getName();
                if (   (item.getType().equals(Material.NETHERITE_AXE) ||
                        item.getType().equals(Material.NETHERITE_HOE) ||
                        item.getType().equals(Material.NETHERITE_SWORD) ||
                        item.getType().equals(Material.NETHERITE_SHOVEL) ||
                        item.getType().equals(Material.NETHERITE_PICKAXE))) {
                    ItemMeta meta = item.getItemMeta();
                    List<Component> lore = meta.hasLore() ? meta.lore() : new ArrayList<>();
                    if (lore.isEmpty()) {
                        switch (item.getType()) {
                            case NETHERITE_AXE:
                                itemID = db.getID("axe");
                                type = "axe";
                                lore.add(0, Component.text("Wood Blocks: 0"));
                                break;
                            case NETHERITE_SWORD:
                                itemID = db.getID("sword");
                                type = "sword";
                                lore.add(0, Component.text("Kills: 0"));
                                break;
                            case NETHERITE_HOE:
                                itemID = db.getID("hoe");
                                type = "hoe";
                                lore.add(0, Component.text("Plowed: 0"));
                                break;
                            case NETHERITE_SHOVEL:
                                itemID = db.getID("shovel");
                                type = "shovel";
                                lore.add(0, Component.text("Blocks: 0"));
                                break;
                            case NETHERITE_PICKAXE:
                                itemID = db.getID("pickaxe");
                                type = "pickaxe";
                                lore.add(0, Component.text("Blocks: 0"));
                                break;
                        }
                        lore.add(1, Component.text("Item ID: " + itemID++));
                        lore.add(2, Component.text("Owner: " + owner));
                    }
                    meta.lore(lore);
                    item.setItemMeta(meta);
                    db.setID(type, itemID);
                    db.add(type, String.valueOf(lore.get(1)), 0, owner);
                } else {
                    player.sendMessage("It is not netherite");
                }
            }
            return true;
        }
        return false;
    }
}
