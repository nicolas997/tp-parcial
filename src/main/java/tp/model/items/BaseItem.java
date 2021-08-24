package tp.model.items;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class BaseItem extends Item {

    String id;

    String name;
    BigDecimal price;

    CompoundItem compoundItem;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    public CompoundItem getCompoundItem() {
        return compoundItem;
    }

    @Override
    public ItemType getType() {
        return ItemType.base;
    }

    @Override
    public String toString() {
        return getName() + " " + price;
    }

}
