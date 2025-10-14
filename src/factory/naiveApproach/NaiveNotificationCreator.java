package factory.naiveApproach;

import factory.model.Notification;
import factory.model.EmailNotification;
import factory.model.PushNotification;
import factory.model.SMSNotification;

public class NaiveNotificationCreator {
	
	public static Notification createNotification(String type) {
        if (type.equalsIgnoreCase("EMAIL")) {
        	return new EmailNotification();
        } else if (type.equalsIgnoreCase("SMS")) {
        	return new SMSNotification();
        } else {
        	return new PushNotification();
        }
    }
	
}
