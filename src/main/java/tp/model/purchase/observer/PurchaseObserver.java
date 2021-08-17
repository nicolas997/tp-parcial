package tp.model.purchase.observer;

import tp.model.purchase.Purchase;

public interface PurchaseObserver {

    public void onPurchaseCreation(Purchase purchase);
    public void onPurchaseConfirmation(Purchase purchase);
    public void onPurchaseDispatch(Purchase purchase);
    public void onPurchaseDelivery(Purchase purchase);

}
