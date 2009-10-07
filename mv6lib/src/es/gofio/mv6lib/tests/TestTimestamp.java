package es.gofio.mv6lib.tests;

import java.util.Calendar;

import es.gofio.mv6lib.utils.Timestamp;

public class TestTimestamp {
	public static void main(String[] args) {
		long t = Timestamp.getTimestamp("25 mar 08, 13:24");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(t);
		System.out.println(c.getTime().toString());
	}
}
