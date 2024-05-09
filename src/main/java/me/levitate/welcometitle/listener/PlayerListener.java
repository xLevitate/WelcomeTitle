package me.levitate.welcometitle.listener;

import me.levitate.welcometitle.config.Config;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.time.Duration;

public class PlayerListener implements Listener {
    private final Config config;

    public PlayerListener(Plugin plugin, Config config) {
        this.config = config;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (!player.isOnline())
            return;

        // Show the title for the player.
        this.showTitle(player);
    }

    /**
     * Show the title to the player using values from the config.
     * @param player The player to show the title for.
     */
    private void showTitle(Player player) {
        final Component titleText = MiniMessage.miniMessage().deserialize(config.getTitle());
        final Component subtitleText = MiniMessage.miniMessage().deserialize(config.getSubtitle());

        final Title.Times titleTime = Title.Times.times(
                Duration.ofMillis(config.getFadeInDuration()),
                Duration.ofMillis(config.getStayDuration()),
                Duration.ofMillis(config.getFadeOutDuration())
        );

        final Title title = Title.title(titleText, subtitleText, titleTime);
        player.showTitle(title);
    }
}
