package tp.model.purchase.observer;

import tp.model.client.Client;
import tp.model.purchase.Purchase;

public class DummyPurchaseObserver implements PurchaseObserver {

    private final static DummyPurchaseObserver observer = new DummyPurchaseObserver();
    public static DummyPurchaseObserver getInstance() {
        return observer;
    }

    @Override
    public void onPurchaseCreation(Purchase purchase) {
        System.out.println("purchase " + purchase.getId() + " was just created");
    }

    @Override
    public void onPurchaseConfirmation(Purchase purchase) {
        System.out.println("purchase " + purchase.getId() + " was just confirmed");
    }

    @Override
    public void onPurchaseDispatch(Purchase purchase) {
        System.out.println("purchase " + purchase.getId() + " was just dispatched");
    }

    @Override
    public void onPurchaseDelivery(Purchase purchase) {
        System.out.println("purchase " + purchase.getId() + " was just delivered");
    }

    @Override
    public void checkPurchaseDetails(Purchase purchase) {

        System.out.println(purchase.toString());
    }
    @Override
    public void suscribeClientToPurchase(Client client, Purchase purchase) {

        purchase.addObserver(client.getPurchaseObserver());
    }

}
