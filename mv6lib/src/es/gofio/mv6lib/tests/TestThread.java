package es.gofio.mv6lib.tests;

import java.util.Calendar;

import es.gofio.mv6lib.Thread;

public class TestThread {
	public static void main(String args[]) {
		Thread t = new Thread(367319);
		t.refreshThread();
		System.out.println("ID: " + t.getId());
		System.out.println("Faves: " + t.getFavouritesCount());
		System.out.println("ForumId: " + t.getForumId());
		System.out.println("Replies: " + t.getRepliesCount());
		System.out.println("Page count: " + t.getPageCount());

		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(t.getTimestamp());
		System.out.println("Timestamp: " + c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.DAY_OF_MONTH) + " @" + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE));
		System.out.println("Title: " + "\"" + t.getTitle() + "\"");
		System.out.println("Author: " + t.getAuthor());
	}
}
