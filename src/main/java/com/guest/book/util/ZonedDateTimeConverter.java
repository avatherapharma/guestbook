package com.guest.book.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, String> {
	private static final String DATE_FORMAT = "dd-MMM-yyyy ,hh:mm:ss a ";

	@Override
	public String convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
		return DateTimeFormatter.ofPattern(DATE_FORMAT).format(zonedDateTime);
	}

	@Override
	public ZonedDateTime convertToEntityAttribute(String dateAsString) {
		return ZonedDateTime.parse(dateAsString, DateTimeFormatter.ofPattern(DATE_FORMAT));
	}
}