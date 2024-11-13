package de.virtualplayground.api;

import de.virtualplayground.api.player.VirtualPlayerManager;
import de.virtualplayground.plugin.VirtualPlugin;

import javax.annotation.Nonnull;

public class VirtualAPI {

    private static VirtualAPI instance;

    private VirtualPlugin plugin;
    private VirtualPlayerManager playerManager;

    private VirtualAPI() {}

    public void onLoad(@Nonnull VirtualPlugin virtualPlugin) {
        this.plugin = virtualPlugin;
        this.playerManager = new VirtualPlayerManager(virtualPlugin);
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

}
