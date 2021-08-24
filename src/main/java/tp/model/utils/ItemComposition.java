package tp.model.utils;

import lombok.AllArgsConstructor;
import tp.model.items.BaseItem;
import tp.model.items.CompoundItem;

@AllArgsConstructor
public class ItemComposition {

    String id;
    BaseItem baseItem;
    CompoundItem compoundItem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BaseItem getBaseItem() {
        return baseItem;
    }

    public void setBaseItem(BaseItem baseItem) {
        this.baseItem = baseItem;
    }

    public CompoundItem getCompoundItem() {
        return compoundItem;
    }

    public void setCompoundItem(CompoundItem compoundItem) {
        this.compoundItem = compoundItem;
    }

}
