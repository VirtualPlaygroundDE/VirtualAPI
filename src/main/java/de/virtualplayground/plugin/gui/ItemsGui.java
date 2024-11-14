package de.virtualplayground.plugin.gui;

import de.virtualplayground.api.VirtualAPI;
import de.virtualplayground.lib.gui.Gui;
import de.virtualplayground.lib.gui.GuiIcon;
import net.kyori.adventure.text.Component;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.concurrent.atomic.AtomicInteger;

public class ItemsGui extends Gui {

    public ItemsGui() {
        super(Component.text("Items"), 6);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {

        AtomicInteger slot = new AtomicInteger();

        VirtualAPI.getInstance().getItemManager().getCustomItems().forEach(customItem -> {
            setItem(slot.get(), new GuiIcon(customItem.build()).onClick(e -> {
                e.getWhoClicked().getInventory().addItem(customItem.build());
            }));
            slot.getAndIncrement();
        });

        update();
    }

    @Override
    public void onClose(InventoryCloseEvent event) {

    }
}
