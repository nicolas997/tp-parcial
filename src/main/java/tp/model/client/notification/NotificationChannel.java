package tp.model.client.notification;

import tp.model.client.Client;

public abstract class NotificationChannel {

    public enum NotificationChannelName {
        email, phone
    }

    public abstract void sendNotification(Client client, String notificationContent);
    public abstract NotificationChannelName getName();

    public static NotificationChannel fromName(NotificationChannelName name) {
        switch (name) {
            case email: return EmailNotificationChannel.getInstance();
            case phone: return PhoneNotificationChannel.getInstance();
        }
        return null;
    }

}
