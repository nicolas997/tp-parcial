package tp.model.items.factories;

import tp.model.items.BaseItem;

import java.math.BigDecimal;
import java.util.UUID;

public class BaseItemFactory {

    public static BaseItem build(String name, long price) {
        return new BaseItem(UUID.randomUUID().toString(), name, BigDecimal.valueOf(price), null);
    }

}
