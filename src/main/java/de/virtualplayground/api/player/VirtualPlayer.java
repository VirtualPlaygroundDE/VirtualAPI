package de.virtualplayground.api.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.UUID;

public class VirtualPlayer {

    private final UUID uuid;
    private int coins;

    public VirtualPlayer(@Nonnull UUID uuid, @Nonnegative int coins) {
        this.uuid = uuid;
        this.coins = coins;
    }

    public Player getBukkitPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public UUID getUniqueId() {
        return uuid;
    }

    public int getCoins() {
        return this.coins;
    }

    public void addCoins(@Nonnegative int amount) {
        this.coins = coins + amount;
    }

    public void removeCoins(@Nonnegative int amount) {
        this.coins = coins - amount;
    }

    public void setCoins(@Nonnegative int amount) {
        this.coins = amount;
    }

}
