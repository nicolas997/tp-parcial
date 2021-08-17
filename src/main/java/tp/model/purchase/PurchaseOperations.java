package tp.model.purchase;

import tp.model.rider.Rider;

public interface PurchaseOperations {

    public void confirm();
    public void dispatch(Rider rider);
    public void finishDelivery();

}
