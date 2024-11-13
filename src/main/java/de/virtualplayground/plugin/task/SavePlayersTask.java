package de.virtualplayground.plugin.task;

import de.virtualplayground.api.VirtualAPI;
import de.virtualplayground.plugin.VirtualPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SavePlayersTask extends BukkitRunnable {

    private final VirtualPlugin plugin;

    public SavePlayersTask(VirtualPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        VirtualAPI.getInstance().getPlayerManager().getOnlinePlayers().forEach((uuid, virtualPlayer) -> {
            plugin.getCursor().savePlayer(virtualPlayer);
        });
    }
}
