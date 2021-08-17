package tp.model.items.factories;

import tp.model.items.CompoundItem;
import tp.model.items.Item;

import java.util.UUID;

public class CompoundItemFactory {

    public static CompoundItem build(Item baseItem) {
        return new CompoundItem(UUID.randomUUID().toString(), baseItem, new Item[]{});
    }

}
