package com.guest.book.util;

import java.time.ZonedDateTime;
import java.util.Date;

public class DBDateIssueFix {

	public static void main(String[] args) {
		
		

ZonedDateTime dbdate = null;
String requireddate = null;
Date d= new Date();	
ZonedDateTimeConverter time = new ZonedDateTimeConverter();
	
dbdate = DateAndTimeZoned.toZonedDateTime(d);//database date from resultset

if (dbdate != null) {
					requireddate = time.convertToDatabaseColumn(dbdate);
					;
				} else {
					Date date = new java.util.Date();
					requireddate = date.toGMTString();
				}
				
				
				
				
				requireddate = time.convertToDatabaseColumn(dbdate);
					String date = DateSuffixUtil.convertDate(requireddate);
	}
}
