package de.virtualplayground.plugin.listener;

import de.virtualplayground.plugin.VirtualPlugin;
import de.virtualplayground.plugin.task.RegisterPlayerTask;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final VirtualPlugin plugin;

    public JoinListener(VirtualPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        new RegisterPlayerTask(plugin, player.getUniqueId()).runTaskAsynchronously(plugin);
    }

}
