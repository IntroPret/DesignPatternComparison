package factory.patternApproach;

import factory.model.EmailNotification;
import factory.model.Notification;
import factory.model.PushNotification;
import factory.model.SMSNotification;

public class ConcreteNotificationFactory extends NotificationFactory {

	@Override
	public Notification createNotification(String type) {
		if (type.equalsIgnoreCase("EMAIL")) {
			return new EmailNotification();
		} else if (type.equalsIgnoreCase("SMS")) {
			return new SMSNotification();
		} else {
			return new PushNotification();
		}
	}

}
