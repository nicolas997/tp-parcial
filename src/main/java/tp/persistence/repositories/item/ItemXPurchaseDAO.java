package tp.persistence.repositories.item;

import tp.model.utils.ItemComposition;
import tp.model.utils.ItemXPurchase;

import java.util.Collection;
import java.util.Optional;

public interface ItemXPurchaseDAO {
    public Collection<ItemXPurchase> findAll();
    public Collection<ItemXPurchase> findById(String id);
    public Optional<ItemXPurchase> update(ItemXPurchase object);
    public Optional<ItemXPurchase> insert(ItemXPurchase object);
    public void deleteById(String id);
}
