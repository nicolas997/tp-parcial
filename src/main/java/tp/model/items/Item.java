package tp.model.items;

import tp.model.utils.WithId;

import java.math.BigDecimal;

public abstract class Item implements WithId {

    public enum ItemType {
        base, compound
    }

    public abstract String getName();
    public abstract BigDecimal getPrice();
    public abstract ItemType getType();

}
