package de.virtualplayground.plugin.listener;

import de.virtualplayground.plugin.VirtualPlugin;
import de.virtualplayground.plugin.task.SavePlayerTask;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    private final VirtualPlugin plugin;

    public QuitListener(VirtualPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        final Player player = e.getPlayer();
        new SavePlayerTask(plugin, player.getUniqueId()).runTaskAsynchronously(plugin);
    }

}
