package com.brandonmdavis.api.utilities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Time {

	private static final String pattern = "yyyy-MM-dd hh:mm:ss";

	public static String getRelativeDate(int hoursAway, int minutesAway, int secondsAway) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, hoursAway);
		calendar.add(Calendar.MINUTE, minutesAway);
		calendar.add(Calendar.SECOND, secondsAway);
		Date date = calendar.getTime();

		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	public static Timestamp getTimestampFrom(String date) {
		return Timestamp.valueOf(date);
	}

	public static Timestamp getNow() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return getTimestampFrom(dateFormat.format(timestamp));
	}

	public static String getFileTime() {
		return getNow().toString().replace(":", "-");
	}

	public static String getTimeFromMin(int min) {
		long days = TimeUnit.MINUTES.toDays(min);
		long hours = TimeUnit.MINUTES.toHours(min - (days * 24));
		long dMin = min - (days * 24 * 60) - (hours * 60);
		String prettyTime = "";
		return prettyTime.concat(days + "d:").concat(hours + "h:").concat(dMin + "m");
	}
}

