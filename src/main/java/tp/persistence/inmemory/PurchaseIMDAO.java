package tp.persistence.inmemory;

import tp.model.purchase.Purchase;
import tp.persistence.repositories.purchase.PurchaseDAO;

public class PurchaseIMDAO extends BasicInMemoryRepository<Purchase> implements PurchaseDAO {

}
