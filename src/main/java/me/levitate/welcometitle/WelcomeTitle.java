package me.levitate.welcometitle;

import de.exlll.configlib.ConfigLib;
import de.exlll.configlib.NameFormatters;
import de.exlll.configlib.YamlConfigurationProperties;
import de.exlll.configlib.YamlConfigurations;
import me.levitate.welcometitle.config.Config;
import me.levitate.welcometitle.listener.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.file.Path;

public final class WelcomeTitle extends JavaPlugin {
    private Config config;
    final Path configPath = new File(getDataFolder(), "config.yml").toPath();

    @Override
    public void onEnable() {
        // Load the config
        this.reloadConfig();

        // Register the event
        new PlayerListener(this, config);
    }

    public void reloadConfig() {
        // Set configuration properties
        final YamlConfigurationProperties properties = ConfigLib.BUKKIT_DEFAULT_PROPERTIES.toBuilder()
                .header(
                        """
                        The configuration of the WelcomeTitle plugin.
                        Author: Levitate
                        """
                )
                .setNameFormatter(NameFormatters.LOWER_UNDERSCORE)
                .setFieldFilter(field -> !field.getName().startsWith("internal"))
                .build();

        // Load the configuration
        this.config = YamlConfigurations.update(
                configPath,
                Config.class,
                properties
        );
    }
}
