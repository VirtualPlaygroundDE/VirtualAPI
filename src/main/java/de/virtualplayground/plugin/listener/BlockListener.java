package de.virtualplayground.plugin.listener;

import de.tr7zw.nbtapi.NBTBlock;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import de.virtualplayground.api.VirtualAPI;
import de.virtualplayground.lib.item.CustomItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        ReadWriteNBT nbt = new NBTBlock(e.getBlock()).getData();
        if (nbt.hasTag("custom_id")) {
            e.setDropItems(false);
            e.setExpToDrop(0);
            e.getBlock().getLocation().getWorld().dropItem(
                    e.getBlock().getLocation(), VirtualAPI.getInstance().getItemManager().getItem(nbt.getString("custom_id")).build()
            );
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (VirtualAPI.getInstance().getItemManager().isCustomItem(e.getItemInHand())) {
            CustomItem customItem = VirtualAPI.getInstance().getItemManager().getItem(e.getItemInHand());
            ReadWriteNBT nbt = new NBTBlock(e.getBlock()).getData();
            nbt.setString("custom_id", customItem.getId());
        }
    }

}
