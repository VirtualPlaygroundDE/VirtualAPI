package de.virtualplayground.plugin.command;

import de.virtualplayground.lib.command.BaseCommand;
import de.virtualplayground.plugin.gui.ItemsGui;

public class ItemsCommand extends BaseCommand {

    private final ItemsGui gui = new ItemsGui();

    public ItemsCommand() {

        super("customitems");

        command.executesPlayer((player, args) -> {
                gui.open(player);
        });

    }

}
