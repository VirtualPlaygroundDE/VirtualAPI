package de.virtualplayground.plugin.sql;

import de.virtualplayground.api.VirtualAPI;
import de.virtualplayground.api.player.VirtualPlayer;
import de.virtualplayground.lib.sql.*;
import de.virtualplayground.plugin.VirtualPlugin;

import java.util.UUID;

public class SQLCursor extends SQLAdapter {

    private final VirtualPlugin plugin;

    public SQLCursor(VirtualPlugin plugin) {
        super(plugin.getMainConfig().getAuth());
        this.plugin = plugin;
    }

    public void createTables() {

        // Create table: players
        SQLTable playersTable = new SQLTable("vp_players");

        playersTable.addColumn(new SQLColumn("uuid", SQLDataType.VARCHAR).setNotNull(true).setParameter(36));
        playersTable.addColumn(new SQLColumn("coins", SQLDataType.INT).setDefaultValue(plugin.getMainConfig().getStartBalance()));
        playersTable.setPrimary("uuid");

        addTable(playersTable);
    }

    public void register(UUID uuid) {
        if (exists("vp_players", uuid)) {
            VirtualPlayer virtualPlayer = new VirtualPlayer(uuid, plugin.getMainConfig().getStartBalance());
            virtualPlayer.setCoins(getInt("vp_players", uuid, "coins"));
            VirtualAPI.getInstance().getPlayerManager().getOnlinePlayers().put(uuid, virtualPlayer);
        } else {
            insert("vp_players", uuid);
            VirtualAPI.getInstance().getPlayerManager().getOnlinePlayers().put(uuid, new VirtualPlayer(uuid, plugin.getMainConfig().getStartBalance()));
        }

    }

    public void savePlayer(UUID uuid) {
        VirtualPlayer virtualPlayer = VirtualAPI.getInstance().getPlayerManager().getPlayer(uuid);
        savePlayer(virtualPlayer);
    }

    public void savePlayer(VirtualPlayer virtualPlayer) {
        update("vp_players", virtualPlayer.getUniqueId(), "coins",  virtualPlayer.getCoins());
    }

    @Override
    public void onConnect() {
        plugin.getServer().getLogger().info("MySQL-Datenbank erfolgreich verbunden.");
    }

    @Override
    public void onDisconnect() {
        plugin.getServer().getLogger().info("MySQL-Verbindung erfolgreich geschlossen.");
    }
}
