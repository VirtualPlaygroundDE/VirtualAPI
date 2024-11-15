package de.virtualplayground.api;

import de.virtualplayground.api.item.CustomItemManager;
import de.virtualplayground.api.player.VirtualPlayerManager;
import de.virtualplayground.lib.config.LocationConfig;
import de.virtualplayground.plugin.VirtualPlugin;

import javax.annotation.Nonnull;

public class VirtualAPI {

    private static VirtualAPI instance;
    private VirtualPlugin plugin;

    private VirtualPlayerManager playerManager;
    private CustomItemManager itemManager;

    private VirtualAPI() {}

    public void onLoad(@Nonnull VirtualPlugin virtualPlugin) {
        this.plugin = virtualPlugin;
        this.playerManager = new VirtualPlayerManager(virtualPlugin);
        this.itemManager = new CustomItemManager();
    }

    public void onDisable() {
    }

    public static VirtualAPI getInstance() {
        if (instance == null) {
            instance = new VirtualAPI();
        }
        return instance;
    }

    public VirtualPlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public CustomItemManager getItemManager() {
        return this.itemManager;
    }

    public LocationConfig getLocationConfig() {
        return plugin.getLocationConfig();
    }

}
