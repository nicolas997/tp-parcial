package tp.model.purchase.observer;

import tp.model.purchase.Purchase;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class StatisticsPurchaseObserver implements PurchaseObserver {

    private final static StatisticsPurchaseObserver observer = new StatisticsPurchaseObserver();
    public static StatisticsPurchaseObserver getInstance() {
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

}
