package es.gofio.mv6lib.tests;

import java.util.Vector;

import es.gofio.mv6lib.Post;
import es.gofio.mv6lib.Thread;
import es.gofio.mv6lib.utils.Page;

public class Tests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Tests();
	}
	
	public Tests() {
		Vector<Post> p = new Thread(351952).getPosts(25, 35);
		for(int i = 0; i < p.size(); i++) {
			System.out.println(p.get(i).getThreadId() + " #" + p.get(i).getPostId() + " por " + p.get(i).getAuthor());
		}
	}
}
