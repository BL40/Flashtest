package dev.bronzylobster.flashtest;

import dev.bronzylobster.flashtest.listeners.CraftExecutor;
import dev.bronzylobster.flashtest.listeners.ItemListener;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Flashtest extends JavaPlugin {

    private CraftExecutor Executor;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new ItemListener(), this);

        Executor = new CraftExecutor();

        getCommand("create").setExecutor(Executor);

        File folder = new File("plugins/Flashtest");
        boolean  folderF = folder.mkdir();

        saveDefaultConfig();

        debugM(Component.text("folder.mkdir():" + folderF));
    }

    @Override
    public void onDisable() {
    }

    public void debugM(Component message) {
        if (getConfig().getBoolean("debug")) Bukkit.broadcast(message, Server.BROADCAST_CHANNEL_ADMINISTRATIVE);
    }
}
