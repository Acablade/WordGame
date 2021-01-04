package me.acablade.kelimeoyunu;

import org.bukkit.plugin.java.JavaPlugin;

public final class KelimeOyunu extends JavaPlugin {

    private static KelimeOyunu instance;

    /**
     * Pretty self explanatory
     * @return instance of this class
     */
    public static KelimeOyunu getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
