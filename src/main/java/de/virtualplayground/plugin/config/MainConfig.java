package de.virtualplayground.plugin.config;

import de.virtualplayground.lib.config.ConfigHandler;
import de.virtualplayground.lib.sql.SQLAuth;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class MainConfig extends ConfigHandler {

    private SQLAuth auth;
    private int startBalance;

    public MainConfig(@NotNull JavaPlugin plugin) {
        super(plugin, "config");
    }

    @Override
    public void onLoad(FileConfiguration config) {

        // Load MySQL Configuration
        String host = config.getString("mysql.host");
        int port = config.getInt("mysql.port");
        String database = config.getString("mysql.database");
        String user = config.getString("mysql.user");
        String password = config.getString("mysql.password");

        this.auth = new SQLAuth(host, port, database, user, password);

        // Load Coins Configuration
        this.startBalance = config.getInt("coins.startBalance");
    }

    @Override
    public void onPreSave(FileConfiguration config) {

    }

    public SQLAuth getAuth() {
        return this.auth;
    }

    public int getStartBalance() {
        return this.startBalance;
    }

}
