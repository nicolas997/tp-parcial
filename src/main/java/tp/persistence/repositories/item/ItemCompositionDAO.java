package tp.persistence.repositories.item;

import tp.model.items.Item;
import tp.model.utils.ItemComposition;

import java.util.Collection;
import java.util.Optional;

public interface ItemCompositionDAO {

    public Collection<ItemComposition> findAll();
    public Collection<ItemComposition> findById(String id);
    public Optional<ItemComposition> update(ItemComposition object);
    public Optional<ItemComposition> insert(ItemComposition object);
    public void deleteById(String id);
}
