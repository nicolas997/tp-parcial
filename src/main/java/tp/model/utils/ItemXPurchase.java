package tp.model.utils;

import lombok.AllArgsConstructor;
import tp.model.items.Item;
import tp.model.purchase.Purchase;

@AllArgsConstructor
public class ItemXPurchase {

    String id;
    Item item;
    Purchase purchase;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

}
