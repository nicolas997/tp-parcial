package tp.model.purchase.status;

import lombok.Data;
import tp.model.purchase.Purchase;
import tp.model.purchase.observer.PurchaseObserver;
import tp.model.rider.Rider;

import java.time.Instant;

@Data
public class PendingStatus extends PurchaseStatus {

    public PendingStatus(Purchase purchase) {
        this.purchase = purchase;
    }
    public PendingStatus() {    }


    @Override
    public void confirm() {
        purchase.setStatus(new ConfirmedStatus(purchase));
        purchase.setConfirmationDate(Instant.now());

        for (PurchaseObserver observer : purchase.getObservers()) {
            observer.onPurchaseConfirmation(purchase);
        }
    }

    @Override
    public void dispatch(Rider rider) {
        System.err.println("cannot dispatch a non-confirmed purchase");
    }

    @Override
    public void finishDelivery() {
        System.err.println("cannot finish a non-dispatched purchase");
    }

    @Override
    public PurchaseStatusName getName() {
        return PurchaseStatusName.pending;
    }

}
