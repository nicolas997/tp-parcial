package tp.model.client;

import tp.model.purchase.Purchase;
import tp.model.purchase.observer.PurchaseObserver;

public class ClientPurchaseObserver implements PurchaseObserver {

    Client client;

    public ClientPurchaseObserver(Client client) {
        this.client = client;
    }

    @Override
    public void onPurchaseCreation(Purchase purchase) {
        client.sendNotification("your purchase " + purchase.getId() + " was just created");
    }

    @Override
    public void onPurchaseConfirmation(Purchase purchase) {
        client.sendNotification("your purchase " + purchase.getId() + " was just confirmed");
    }

    @Override
    public void onPurchaseDispatch(Purchase purchase) {
        client.sendNotification("your purchase " + purchase.getId() + " was just dispatched with rider " + purchase.getAssignedRider().getFirstName());
    }

    @Override
    public void onPurchaseDelivery(Purchase purchase) {
        client.sendNotification("your purchase " + purchase.getId() + " was just delivered by rider " + purchase.getAssignedRider().getFirstName());
    }

    @Override
    public void checkPurchaseDetails(Purchase purchase){

        System.out.println(purchase.toString());
    }


    @Override
    public void suscribeClientToPurchase(Client client, Purchase purchase) {}

}
