package factory.PatternApproach;

import factory.model.Notification;

public abstract class NotificationFactory {
	public abstract Notification createNotification(String type);
}
