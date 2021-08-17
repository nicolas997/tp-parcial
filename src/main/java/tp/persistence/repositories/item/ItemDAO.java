package tp.persistence.repositories.item;

import tp.model.items.Item;

import java.util.Optional;

public interface ItemDAO {

    public Optional<Item> findByIdAndType(String id, Item.ItemType type);
    public Optional<Item> update(Item object);
    public Optional<Item> insert(Item object);
    public Optional<Item> deleteById(Item object);

}
