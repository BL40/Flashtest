package dev.bronzylobster.flashtest;

import dev.bronzylobster.flashtest.listeners.HitListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File; 
import java.util.logging.Logger;

public final class Flashtest extends JavaPlugin {

    Logger log = Logger.getLogger("Minecraft");

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new HitListener(), this);
        File folder = new File("plugins/Flashtest");
        boolean fFlag = folder.mkdir();
        if (fFlag) log.fine("Folder created");
        else log.finer("Folder is exist");
    }

    @Override
    public void onDisable() {
    }

}
