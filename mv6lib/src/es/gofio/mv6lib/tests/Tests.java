package es.gofio.mv6lib.tests;

import java.util.Vector;

import es.gofio.mv6lib.Post;
import es.gofio.mv6lib.Singleton;
import es.gofio.mv6lib.Thread;

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
		
		Thread t = Singleton.getInstance().getThread(367662);
		Vector<Post> p = Singleton.getInstance().getPost(Integer.parseInt(t.getId()), 1, t.getRepliesCount());
		
		for(int i = 0; i < p.size(); i++) {
			System.out.println("#" + p.get(i).getPostId() + " por " + p.get(i).getAuthor());
		}
		
		System.out.println("\nTiempo empleado: " + (System.currentTimeMillis() - startTime) + "ms");
	}
}