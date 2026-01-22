package me.inotsleep.fishingspots.game;

import com.inotsleep.insutils.api.config.Path;
import com.inotsleep.insutils.spi.config.SerializableObject;
import org.bukkit.inventory.ItemStack;
import java.util.Base64;

public class DropItems extends SerializableObject {

    public transient ItemStack itemStack;

    @Path("item")
    public String item; // Base64

    @Path("min")
    public int min;

    @Path("max")
    public int max;

    public DropItems() {}

    public DropItems(ItemStack itemStack, int min, int max) {
        this.itemStack = itemStack;
        this.min = min;
        this.max = max;
    }

    @Override
    public void beforeSerialization() {
        if (min > max) {
            int t = min;
            min = max;
            max = t;
        }

        if (itemStack == null) {
            item = null;
            return;
        }

        item = Base64.getEncoder().encodeToString(itemStack.serializeAsBytes());

    }

    @Override
    public void afterDeserialization() {
        if (min > max) {
            int t = min;
            min = max;
            max = t;
        }

        if (item == null || item.isEmpty()) {
            itemStack = null;
            return;
        }

        byte[] data = Base64.getDecoder().decode(item);
        itemStack = ItemStack.deserializeBytes(data);
    }
}
