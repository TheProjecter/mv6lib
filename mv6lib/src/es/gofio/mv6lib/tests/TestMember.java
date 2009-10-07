package es.gofio.mv6lib.tests;

import java.util.Calendar;
import java.util.Vector;

import es.gofio.mv6lib.Member;

public class TestMember {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Member m = new Member("PiradoIV");
		m.refreshMember();
		
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(m.getRegisteredTimestamp());
		
		Vector<String> lastVisitors = m.getLastVisitors();
		
		System.out.println("ID: " + m.getId());
		System.out.println("Nick: " + m.getNick());
		System.out.println("Is connected: " + m.isConnected());
		System.out.println("Avatar: " + m.getAvatar());
		System.out.println("Photo: " + m.getPhoto());
		System.out.println("Firmas: " + m.getSignaturesCount());
		System.out.println("Edad: " + m.getAge());
		System.out.println("Ciudad: " + m.getCity());
		System.out.println("Nombre: " + m.getName());
		System.out.println("Posts: " + m.getPosts());
		System.out.println("Visits: " + m.getVisits());
		System.out.println("Registered: " + c.getTime().toString());
		System.out.print("Last visitors:");
		for(int i = 0; i < lastVisitors.size(); i++) {
			if(i > 0) { System.out.print(","); }
			System.out.print(" " + lastVisitors.get(i));
		}
		System.out.print(".\n");
	}

}
