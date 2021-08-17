package tp.persistence.inmemory;

import tp.model.items.BaseItem;
import tp.persistence.repositories.item.BaseItemDAO;

public class BaseItemIMDAO extends BasicInMemoryRepository<BaseItem> implements BaseItemDAO {
}
