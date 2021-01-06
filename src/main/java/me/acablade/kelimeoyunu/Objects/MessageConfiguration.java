package me.acablade.kelimeoyunu.Objects;

import me.acablade.kelimeoyunu.KelimeOyunu;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessageConfiguration {

    private static FileConfiguration customConfig;

    public static FileConfiguration getCustomConfig() {
        return customConfig;
    }

    public static void createCustomConfig() {
        File customConfigFile = new File(KelimeOyunu.getInstance().getDataFolder(), "messages.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            KelimeOyunu.getInstance().saveResource("messages.yml", false);
        }

        customConfig= new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


}
