package de.virtualplayground.api.item;

import de.tr7zw.nbtapi.NBT;
import de.virtualplayground.lib.item.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomItemManager {

    private final HashSet<CustomItem> customItems = new HashSet<>();

    public void register(CustomItem item) {
        customItems.add(item);
        if (item.getRecipe() != null) {
            Bukkit.addRecipe(item.getRecipe());
        }
    }

    public boolean isCustomItem(ItemStack itemStack) {
        AtomicBoolean result = new AtomicBoolean(false);
        NBT.get(itemStack, nbt -> {
            if (nbt.hasTag("custom_id")) {
                result.set(true);
            }
        });
        return result.get();
    }

    public CustomItem getItem(String id) {
        for (CustomItem customItem : customItems) {
            if (customItem.getId().equals(id)) {
                return customItem;
            }
        }
        return null;
    }

    public CustomItem getItem(ItemStack itemStack) {
        for (CustomItem customItem : customItems) {
            if (customItem.equals(itemStack)) {
                return customItem;
            }
        }
        return null;
    }

    public HashSet<CustomItem> getCustomItems() {
        return customItems;
    }

}
