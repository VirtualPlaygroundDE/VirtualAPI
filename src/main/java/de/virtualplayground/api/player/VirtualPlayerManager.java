package de.virtualplayground.api.player;

import de.virtualplayground.plugin.VirtualPlugin;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.UUID;

public class VirtualPlayerManager {

    private final VirtualPlugin plugin;
    private final HashMap<UUID, VirtualPlayer> players = new HashMap<>();

    public VirtualPlayerManager(VirtualPlugin plugin) {
        this.plugin = plugin;
    }

    public VirtualPlayer getPlayer(@Nonnull Player player) {
        return getPlayer(player.getUniqueId());
    }

    public VirtualPlayer getPlayer(@Nonnull UUID uuid) {
        return this.players.get(uuid);
    }

    public HashMap<UUID, VirtualPlayer> getOnlinePlayers() {
        return players;
    }

    public void save(VirtualPlayer virtualPlayer) {
        plugin.getCursor().savePlayer(virtualPlayer);
    }

    public void save(UUID uuid) {
        plugin.getCursor().savePlayer(uuid);
    }
}
