package tp.model.purchase;

import lombok.AllArgsConstructor;
import lombok.Data;
import tp.model.client.Client;
import tp.model.items.Item;
import tp.model.purchase.observer.PurchaseObserver;
import tp.model.purchase.status.PendingStatus;
import tp.model.purchase.status.PurchaseStatus;
import tp.model.rider.Rider;
import tp.model.utils.WithId;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Purchase implements PurchaseOperations, WithId {

    String id;

    Client client;

    Item[] items;
    BigDecimal deliveryPrice;

    Instant creationDate;
    Instant confirmationDate;
    Instant dispatchDate;
    Instant deliveryDate;

    PurchaseStatus status;

    PurchaseObserver[] observers;

    Rider assignedRider;

    public Purchase (Client client, Item[] items) {
        this(client, items, BigDecimal.ZERO, new PurchaseObserver[]{});
    }

    public Purchase (Client client, Item[] items, BigDecimal deliveryPrice, PurchaseObserver[] observers) {
        this(UUID.randomUUID().toString(), client, items, deliveryPrice, Instant.now(),
                null, null, null, null, observers, null);

        this.status = new PendingStatus(this);
        this.client.setLastPurchaseDate(Instant.now());
        this.addObserver(client.getPurchaseObserver());

        for (PurchaseObserver observer : this.observers) {
            observer.onPurchaseCreation(this);
        }
    }

    public void addObserver(PurchaseObserver observer) {
        PurchaseObserver[] newObservers = new PurchaseObserver[observers.length + 1];

        for (int i=0 ; i<observers.length ; i++) {
            newObservers[i] = observers[i];
        }
        newObservers[observers.length] = observer;

        this.observers = newObservers;
    }

    public BigDecimal getItemsPrice() {
        BigDecimal accumulator = BigDecimal.ZERO;

        for (Item item : items) {
            accumulator = accumulator.add(item.getPrice());
        }

        return accumulator;
    }

    public BigDecimal getTotalPrice() {
        return getItemsPrice().add(deliveryPrice);
    }

    public PurchaseStatus.PurchaseStatusName getStatusName() {
        return status.getName();
    }

    @Override
    public void confirm() {
        status.confirm();
    }

    @Override
    public void dispatch(Rider rider) {
        status.dispatch(rider);
    }

    @Override
    public void finishDelivery() {
        status.finishDelivery();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Purchase :: ").append(id).append("\n")
                .append("Client :: ").append(client.getId()).append("\n")
                .append("Assigned Rider :: ").append(assignedRider != null ? assignedRider.getId() : "---").append("\n")
                .append("Items :: (").append(items.length).append(")\n");

        for (Item item : items) {
            builder.append(item.toString()).append("\n");
        }

        builder.append("Items Price :: ").append(getItemsPrice()).append("\n")
                .append("Delivery Price :: ").append(getDeliveryPrice()).append("\n")
                .append("Price :: ").append(getTotalPrice()).append("\n")
                .append("Status :: ").append(getStatusName());

        return builder.toString();
    }

}
