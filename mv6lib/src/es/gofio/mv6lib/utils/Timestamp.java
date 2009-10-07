package es.gofio.mv6lib.utils;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Timestamp {
	public static long getTimestamp(String string) {
		Pattern p = Pattern.compile("([0-9]{1,2}) ([a-z]{3})[ ]*([0-9]*), ([0-9]{2}):([0-9]{2})");
		Matcher m = p.matcher(string);
		if(m.find()) {
			int date = Integer.parseInt(m.group(1));
			int month = 0;
			if(m.group(2).equals("ene")) {
				month = Calendar.JANUARY;
			} else if(m.group(2).equals("feb")) {
				month = Calendar.FEBRUARY;
			} else if(m.group(2).equals("mar")) {
				month = Calendar.MARCH;
			} else if(m.group(2).equals("abr")) {
				month = Calendar.APRIL;
			} else if(m.group(2).equals("may")) {
				month = Calendar.MAY;
			} else if(m.group(2).equals("jun")) {
				month = Calendar.JUNE;
			} else if(m.group(2).equals("jul")) {
				month = Calendar.JULY;
			} else if(m.group(2).equals("ago")) {
				month = Calendar.AUGUST;
			} else if(m.group(2).equals("sep")) {
				month = Calendar.SEPTEMBER;
			} else if(m.group(2).equals("oct")) {
				month = Calendar.OCTOBER;
			} else if(m.group(2).equals("nov")) {
				month = Calendar.NOVEMBER;
			} else if(m.group(2).equals("dic")) {
				month = Calendar.DECEMBER;
			}
			
			int year = 0;
			if(m.group(3).length() == 0) {
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(System.currentTimeMillis());
				year = c.get(Calendar.YEAR);
				year = 2009;
			} else {
				year = 2000 + Integer.valueOf(m.group(3));
			}
			
			int hourOfDay = Integer.valueOf(m.group(4));
			int minute = Integer.valueOf(m.group(5));
			
			Calendar c = Calendar.getInstance();
			c.set(year, month, date, hourOfDay, minute);
			
			return c.getTimeInMillis();		
		}
		
		p = Pattern.compile("([ayer|hoy]), ([0-9]{2}):([0-9]{2})");
		m = p.matcher(string);

		if(m.find()) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(System.currentTimeMillis());
			c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(m.group(2)));
			c.set(Calendar.MINUTE, Integer.parseInt(m.group(3)));
			c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) - 1);
			if(m.group(1).equals("ayer")) {
				c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) - 1);
			}
			return c.getTimeInMillis();
		}
		
		return 0;
	}
}
