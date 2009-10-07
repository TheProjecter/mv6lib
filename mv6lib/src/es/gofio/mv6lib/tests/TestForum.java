package es.gofio.mv6lib.tests;

import java.util.Vector;

import es.gofio.mv6lib.Forum;
import es.gofio.mv6lib.Thread;

public class TestForum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Forum f = new Forum(4);
		f.refreshForum();
		System.out.println(f.getForumName());
		System.out.println(f.getId());
		Vector<Thread> vf = f.getLastThreads();
		for(int i = 0; i < vf.size(); i++) {
			Thread t = vf.get(i);
			System.out.println(t.getTitle());
		}
	}

}
