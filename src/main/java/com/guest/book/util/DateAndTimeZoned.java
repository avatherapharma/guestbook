package com.guest.book.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateAndTimeZoned {
	static Logger logger = LoggerFactory.getLogger(DateAndTimeZoned.class);

	public static void main(String[] args) {

	}

	public static ZonedDateTime toZonedDateTime(Date utilDate) {
		if (utilDate == null) {
			return null;
		}
		final ZoneId systemDefault = ZoneId.systemDefault();
		return ZonedDateTime.ofInstant(utilDate.toInstant(), systemDefault);
	}

	public static String getDateToString(Date date) {
		DateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		String dateString = null;
		if (date != null) {
			dateString = sdf.format(date);
		}

		return dateString;

	}

	public static Date getStringtoUtilDate(String input) {
		Date date = null;
		if ("" != input && input != null && input.length() == 8 && input.matches("^([0-9\\s])+$")) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy", Locale.ENGLISH);
			LocalDate dateTime = LocalDate.parse(input, formatter);
			date = Date.from(dateTime.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		return date;
	}

	public static String getStringtoPattern(Date date) {
		DateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		String dateString = null;
		if (date != null) {
			dateString = sdf.format(date);
		}

		return dateString;

	}

	/*
	 * public static String ZonedTimeAndDate( Date d) {
	 * 
	 * return formatedDateTime;
	 * 
	 * }
	 */
}