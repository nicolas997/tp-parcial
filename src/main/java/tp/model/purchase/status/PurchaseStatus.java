package tp.model.purchase.status;

import lombok.Data;
import tp.model.purchase.Purchase;
import tp.model.purchase.PurchaseOperations;
import tp.model.rider.Rider;

@Data
public abstract class PurchaseStatus implements PurchaseOperations {

    public enum PurchaseStatusName {
        pending, confirmed, dispatched, delivered
    }

    Purchase purchase;
    public abstract PurchaseStatusName getName();

}
