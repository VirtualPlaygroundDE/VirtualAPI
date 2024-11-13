package de.virtualplayground.plugin;

import de.virtualplayground.api.VirtualAPI;
import de.virtualplayground.lib.gui.GuiListener;
import de.virtualplayground.lib.lang.Language;
import de.virtualplayground.plugin.command.CoinsCommand;
import de.virtualplayground.plugin.config.MainConfig;
import de.virtualplayground.plugin.listener.JoinListener;
import de.virtualplayground.plugin.listener.QuitListener;
import de.virtualplayground.plugin.sql.SQLCursor;
import de.virtualplayground.plugin.task.SavePlayersTask;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class VirtualPlugin extends JavaPlugin {

    private final Language language = new Language(this);
    private final MainConfig mainConfig = new MainConfig(this);

    private SQLCursor cursor;

    @Override
    public void onLoad() {
        VirtualAPI.getInstance().onLoad(this);
    }

    @Override
    public void onEnable() {

        language.init();
        mainConfig.init();

        cursor = new SQLCursor(this);
        cursor.connect();
        cursor.createTables();

        registerEvents(getServer().getPluginManager());
        registerCommands();
    }

    @Override
    public void onDisable() {

        new SavePlayersTask(this).run();

        cursor.disconnect();
        VirtualAPI.getInstance().onDisable();
    }

    private void registerEvents(final PluginManager pluginManager) {
        pluginManager.registerEvents(new GuiListener(), this);
        pluginManager.registerEvents(new JoinListener(this), this);
        pluginManager.registerEvents(new QuitListener(this), this);
    }

    private void registerCommands() {
        new CoinsCommand(this).register();
    }

    public Language getLanguage() {
        return this.language;
    }

    public MainConfig getMainConfig() {
        return this.mainConfig;
    }

    public SQLCursor getCursor() {
        return this.cursor;
    }
}
