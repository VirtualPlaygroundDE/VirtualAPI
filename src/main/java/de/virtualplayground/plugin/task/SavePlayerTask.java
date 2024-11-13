package de.virtualplayground.plugin.task;

import de.virtualplayground.api.VirtualAPI;
import de.virtualplayground.plugin.VirtualPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class SavePlayerTask extends BukkitRunnable {

    private final VirtualPlugin plugin;
    private final UUID uuid;

    public SavePlayerTask(VirtualPlugin plugin, UUID uuid) {
        this.plugin = plugin;
        this.uuid = uuid;
    }

    @Override
    public void run() {
        plugin.getCursor().savePlayer(uuid);
        VirtualAPI.getInstance().getPlayerManager().getOnlinePlayers().remove(uuid);
    }
}
