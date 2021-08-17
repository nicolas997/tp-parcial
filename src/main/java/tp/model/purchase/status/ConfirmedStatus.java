package tp.model.purchase.status;

import lombok.Data;
import tp.model.purchase.Purchase;
import tp.model.purchase.observer.PurchaseObserver;
import tp.model.rider.Rider;

import java.time.Instant;

@Data
public class ConfirmedStatus extends PurchaseStatus {

    public ConfirmedStatus(Purchase purchase) {
        this.purchase = purchase;
    }

    @Override
    public void confirm() {
        System.err.println("cannot confirm a confirmed purchase");
    }

    @Override
    public void dispatch(Rider rider) {
        purchase.setStatus(new DispatchedStatus(purchase));
        purchase.setDispatchDate(Instant.now());
        purchase.setAssignedRider(rider);

        for (PurchaseObserver observer : purchase.getObservers()) {
            observer.onPurchaseDispatch(purchase);
        }
    }

    @Override
    public void finishDelivery() {
        System.err.println("cannot confirm a non-delivered purchase");
    }

    @Override
    public PurchaseStatusName getName() {
        return PurchaseStatusName.confirmed;
    }
    
}
