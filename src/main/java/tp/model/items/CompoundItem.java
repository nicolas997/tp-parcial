package tp.model.items;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;

@AllArgsConstructor
public class CompoundItem extends Item {

    String id;

    Item baseItem;
    Item[] extras;

    public CompoundItem addExtra(Item extra) {
        Item[] newExtras = new Item[extras.length + 1];

        for (int i=0 ; i<extras.length ; i++) {
            newExtras[i] = extras[i];
        }

        newExtras[extras.length] = extra;

        this.extras = newExtras;
        return this;
    }

    public CompoundItem removeExtra(Item extra) {
        this.extras = (Item[]) Arrays.stream(extras).filter(i -> i.equals(extra)).toArray();
        return this;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        StringBuilder builder = new StringBuilder();

        builder.append(baseItem.getName());
        builder.append(" [ ");
        for (Item item : extras) {
            builder.append(item.getName()).append(" ");
        }
        builder.append("]");

        return builder.toString();
    }

    @Override
    public BigDecimal getPrice() {
        BigDecimal accumulator = baseItem.getPrice();

        for (Item item : extras) {
            accumulator = accumulator.add(item.getPrice());
        }

        return accumulator;
    }

    @Override
    public ItemType getType() {
        return ItemType.compound;
    }

    @Override
    public String toString() {
        return getName() + " " + getPrice();
    }

}
