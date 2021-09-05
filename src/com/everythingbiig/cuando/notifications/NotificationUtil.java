package com.everythingbiig.cuando.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.NotificationCompat;

public class NotificationUtil {

	public static void sendNotification(Context context, int icon, int ticker, int title, int text, PendingIntent intent, boolean autoCancel) {
		if(context == null){
			return;
		}
		Resources r = context.getResources();
		Notification notification = new NotificationCompat.Builder(context)
				.setTicker(r.getString(ticker))
				.setSmallIcon(icon)
				.setContentTitle(r.getString(title))
				.setContentText(r.getString(text))
				.setContentIntent(intent).
				setAutoCancel(autoCancel)
				.build();
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(0, notification);
	}

}
