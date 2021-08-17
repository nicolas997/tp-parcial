package tp.model.client.notification;

import tp.model.client.Client;

public class PhoneNotificationChannel extends NotificationChannel {

    private static final PhoneNotificationChannel channel = new PhoneNotificationChannel();
    public static PhoneNotificationChannel getInstance() {
        return channel;
    }

    @Override
    public void sendNotification(Client client, String notificationContent) {
        if (client.getPhoneNumber() == null) return;
        System.out.println("sending phone notification to " + client.getPhoneNumber() + " with content " + notificationContent);
    }

    @Override
    public NotificationChannelName getName() {
        return NotificationChannelName.phone;
    }
}
