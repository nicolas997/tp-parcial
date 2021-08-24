package tp.model.purchase.status;

import lombok.Data;
import tp.model.purchase.Purchase;
import tp.model.purchase.observer.PurchaseObserver;
import tp.model.rider.Rider;

import java.time.Instant;

@Data
public class DispatchedStatus extends PurchaseStatus {

    public DispatchedStatus(Purchase purchase) {
        this.purchase = purchase;
    }
    public DispatchedStatus() {    }

    @Override
    public void confirm() {
        System.err.println("cannot confirm a dispatched purchase");
    }

    @Override
    public void dispatch(Rider rider) {
        System.err.println("cannot dispatch a dispatched purchase");
    }

    @Override
    public void finishDelivery() {
        purchase.setStatus(new DeliveredStatus(purchase));
        purchase.setDeliveryDate(Instant.now());

        for (PurchaseObserver observer : purchase.getObservers()) {
            observer.onPurchaseDelivery(purchase);
        }
    }

    @Override
    public PurchaseStatusName getName() {
        return PurchaseStatusName.dispatched;
    }

}
