package es.gofio.mv6lib.tests;

import java.util.Vector;

import es.gofio.mv6lib.Forum;
import es.gofio.mv6lib.Singleton;

public class Tests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Tests();
	}
	
	public Tests() {
		long startTime = System.currentTimeMillis();
		
		Vector<Forum> f = Singleton.getInstance().getForumList();
		System.out.println(f);
		
		System.out.println("\nTiempo empleado: " + (System.currentTimeMillis() - startTime) + "ms");
	}
}