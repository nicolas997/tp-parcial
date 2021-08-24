package tp.model.purchase.observer;

import tp.model.client.Client;
import tp.model.purchase.Purchase;

public interface PurchaseObserver {

    public void onPurchaseCreation(Purchase purchase);
    public void onPurchaseConfirmation(Purchase purchase);
    public void onPurchaseDispatch(Purchase purchase);
    public void onPurchaseDelivery(Purchase purchase);
    public void checkPurchaseDetails(Purchase purchase);
    public void suscribeClientToPurchase(Client client, Purchase purchase);

}
