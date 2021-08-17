package tp.model.purchase.status;

import lombok.Data;
import tp.model.purchase.Purchase;
import tp.model.rider.Rider;

@Data
public class DeliveredStatus extends PurchaseStatus {

    public DeliveredStatus(Purchase purchase) {
        this.purchase = purchase;
    }

    @Override
    public void confirm() {
        System.err.println("cannot confirm a completed purchase");
    }

    @Override
    public void dispatch(Rider rider) {
        System.err.println("cannot dispatch a completed purchase");
    }

    @Override
    public void finishDelivery() {
        System.err.println("cannot finish a completed purchase");
    }

    @Override
    public PurchaseStatusName getName() {
        return PurchaseStatusName.delivered;
    }

}
