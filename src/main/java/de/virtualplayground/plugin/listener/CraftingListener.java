package de.virtualplayground.plugin.listener;

import de.virtualplayground.api.VirtualAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class CraftingListener implements Listener {

    @EventHandler
    public void onPrepareCraft(PrepareItemCraftEvent event) {

        CraftingInventory inventory = event.getInventory();

        for (ItemStack item : inventory.getMatrix()) {
            if (item != null && VirtualAPI.getInstance().getItemManager().isCustomItem(item)) {
                inventory.setResult(null);
                return;
            }
        }
    }
}
