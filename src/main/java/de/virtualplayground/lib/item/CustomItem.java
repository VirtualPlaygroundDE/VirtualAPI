package de.virtualplayground.lib.item;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import javax.annotation.Nonnull;
import java.util.UUID;

public class CustomItem {

    private final String id;
    private final ItemBuilder itemBuilder;
    private Recipe recipe;

    private boolean stackable = true;

    public CustomItem(@Nonnull String id, @Nonnull ItemBuilder itemBuilder) {
        this.id = id;
        this.itemBuilder = itemBuilder;
    }

    public void setStackable(boolean enabled) {
        this.stackable = enabled;
    }

    public CustomItem setShapedRecipe(String[] shape, char ingredientChar, Material ingredientMaterial) {
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey("vp", id), itemBuilder.build());
        shapedRecipe.shape(shape);
        shapedRecipe.setIngredient(ingredientChar, ingredientMaterial);
        this.recipe = shapedRecipe;
        return this;
    }

    public CustomItem setShapelessRecipe(Material... ingredients) {
        ShapelessRecipe shapelessRecipe = new ShapelessRecipe(new NamespacedKey("vp", id), itemBuilder.build());
        for (Material ingredient : ingredients) {
            shapelessRecipe.addIngredient(ingredient);
        }
        this.recipe = shapelessRecipe;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public ItemBuilder getBuilder() {
        return this.itemBuilder;
    }

    public ItemStack build() {
        ItemStack itemStack = itemBuilder.build();
        NBT.modify(itemStack, nbt -> {
            nbt.setString("custom_id", id);
            if (!stackable) {
                nbt.setUUID("custom_uuid", UUID.randomUUID());
            }
        });
        return itemStack;
    }

    public boolean equals(ItemStack itemStack) {
        String customId = NBT.get(itemStack, nbt -> (String) nbt.getString("custom_id"));
        return customId.equals(id);
    }

}
