package de.virtualplayground.plugin.task;

import de.virtualplayground.plugin.VirtualPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class RegisterPlayerTask extends BukkitRunnable {

    private final VirtualPlugin plugin;
    private final UUID uuid;

    public RegisterPlayerTask(VirtualPlugin plugin, UUID uuid) {
        this.plugin = plugin;
        this.uuid = uuid;
    }

    @Override
    public void run() {
        plugin.getCursor().register(uuid);
    }

}
