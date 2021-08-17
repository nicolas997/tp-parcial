package tp.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import tp.model.client.notification.NotificationChannel;
import tp.model.utils.Country;
import tp.model.utils.WithId;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Client implements WithId {

    String id;

    Country country;
    String email;
    String phoneNumber;

    Instant registrationDate;
    Instant lastPurchaseDate;

    NotificationChannel notificationChannel;

    public Client (Country country, String email, String phoneNumber, NotificationChannel notificationChannel) {
        this(UUID.randomUUID().toString(), country, email, phoneNumber, Instant.now(), null, notificationChannel);
    }

    public void sendNotification(String notificationContent) {
        notificationChannel.sendNotification(this, notificationContent);
    }

    public ClientPurchaseObserver getPurchaseObserver() {
        return new ClientPurchaseObserver(this);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Client :: ").append(id).append("\n")
                .append("Email :: ").append(email).append("\n")
                .append("Phone :: ").append(phoneNumber).append("\n")
                .append("Channel :: ").append(getNotificationChannel().getName()).append("\n")
                .append("Country :: ").append(country.name());


        return builder.toString();
    }

}
