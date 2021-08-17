package tp.persistence.inmemory;

import tp.model.items.Item;
import tp.persistence.BasicDAO;
import tp.persistence.repositories.item.BaseItemDAO;
import tp.persistence.repositories.item.CompoundItemDAO;
import tp.persistence.repositories.item.ItemDAO;

import java.util.Optional;

public class ItemIMDAO implements ItemDAO {

    public BaseItemDAO baseItemDAO;
    public CompoundItemDAO compoundItemDAO;

    public ItemIMDAO(BaseItemDAO baseItemDAO, CompoundItemDAO compoundItemDAO) {
        this.baseItemDAO = baseItemDAO;
        this.compoundItemDAO = compoundItemDAO;
    }

    public BasicDAO getDAO(Item.ItemType type) {
        switch (type) {
            case base: return baseItemDAO;
            case compound: return compoundItemDAO;
        }
        return null;
    }

    @Override
    public Optional<Item> findByIdAndType(String id, Item.ItemType type) {
        return getDAO(type).findById(id);
    }

    @Override
    public Optional<Item> update(Item object) {
        return getDAO(object.getType()).update(object);
    }

    @Override
    public Optional<Item> insert(Item object) {
        return getDAO(object.getType()).insert(object);
    }

    @Override
    public Optional<Item> deleteById(Item object) {
        return getDAO(object.getType()).deleteById(object.getId().toString());
    }

}
