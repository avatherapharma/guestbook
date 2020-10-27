package com.guest.book.util;

@SuppressWarnings("unused")

public class DateSuffixUtil {
	private static String[] Months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
			"Dec" };

	public static String convertDate(String inputDate) {
		String[] splitteddate = inputDate.split("-");
		String inDate = splitteddate[0];
		String prefix = DateSuffixUtil.getDateSuffix(Integer.parseInt(inDate));
		String convertedDate = inputDate.replaceAll(inDate + "-", inDate + "" + prefix + "-");

		return convertedDate;
	}

	private static String getDateSuffix(int i) {
		switch (i) {
		case 1:
		case 21:
		case 31:
			return ("st");

		case 2:
		case 22:
			return ("nd");

		case 3:
		case 23:
			return ("rd");

		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 19:
		case 20:
		case 24:
		case 25:
		case 26:
		case 27:
		case 28:
		case 29:
		case 30:
			return ("th");
		default:
			return ("");
		}
	}

}
