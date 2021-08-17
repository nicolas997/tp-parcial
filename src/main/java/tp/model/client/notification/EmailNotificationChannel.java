package tp.model.client.notification;

import tp.model.client.Client;

public class EmailNotificationChannel extends NotificationChannel {

    private final static EmailNotificationChannel channel = new EmailNotificationChannel();
    public static EmailNotificationChannel getInstance() {
        return channel;
    }

    @Override
    public void sendNotification(Client client, String notificationContent) {
        if (client.getEmail() == null) return;
        System.out.println("sending email to " + client.getEmail() + " with content " + notificationContent);
    }

    @Override
    public NotificationChannelName getName() {
        return NotificationChannelName.email;
    }
}
