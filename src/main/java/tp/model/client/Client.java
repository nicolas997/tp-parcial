package tp.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import tp.external.google.GoogleApiClient;
import tp.external.google.GoogleGeocodeResult;
import tp.model.client.notification.NotificationChannel;
import tp.model.purchase.Purchase;
import tp.model.purchase.observer.DummyPurchaseObserver;
import tp.model.utils.Country;
import tp.model.utils.WithId;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Client implements WithId {

    String id;
    String name;

    Country country;
    String email;
    String password;
    String phoneNumber;

    Instant registrationDate;
    Instant lastPurchaseDate;

    NotificationChannel notificationChannel;

    String displayableAddress;
    Float addressLatitude;
    Float addressLongitude;

    public Client (String name, Country country, String email, String password, String phoneNumber, NotificationChannel notificationChannel, String address) {
        this(UUID.randomUUID().toString(), name, country, email, password, phoneNumber, Instant.now(), null,
                notificationChannel, null, null, null);

        GoogleGeocodeResult geocodeResult = GoogleApiClient.getInstance().geocodeAddress(address);
        if (geocodeResult == null) {
            // no se pudo encontrar la direccion
            throw new RuntimeException("address " + address + " was not found, try again");
        } else {
            this.displayableAddress = geocodeResult.getFormattedAddress();
            this.addressLatitude    = geocodeResult.getLatitude();
            this.addressLongitude   = geocodeResult.getLongitude();
        }
    }

    public Client (String name, Country country, String email, String password, String phoneNumber, NotificationChannel notificationChannel) {
        this(UUID.randomUUID().toString(), name, country, email, password, phoneNumber, Instant.now(), null,
                notificationChannel, null, null, null);
    }

    public Client (String id){
        this.id = id;
    }

    public void sendNotification(String notificationContent) {
        notificationChannel.sendNotification(this, notificationContent);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Client :: ").append(id).append("\n")
                .append("Name :: ").append(name).append("\n")
                .append("Email :: ").append(email).append("\n")
                .append("Phone :: ").append(phoneNumber).append("\n")
                .append("Channel :: ").append(getNotificationChannel().getName()).append("\n")
                .append("Country :: ").append(country.name()).append("\n")
                .append("Address :: ").append(displayableAddress)
                    .append(" [")
                    .append(addressLatitude).append("; ").append(addressLongitude).append("]");


        return builder.toString();
    }

    public ClientPurchaseObserver getPurchaseObserver() {
        return new ClientPurchaseObserver(this);
    }

}
