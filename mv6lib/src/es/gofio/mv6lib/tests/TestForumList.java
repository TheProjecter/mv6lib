package es.gofio.mv6lib.tests;

import java.util.Vector;

import es.gofio.mv6lib.Forum;
import es.gofio.mv6lib.ForumList;

public class TestForumList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ForumList fl = new ForumList();
		fl.refreshForumList();
		Vector<Forum> f = fl.getForumList();
		for(int i = 0; i < f.size(); i++) {
			Forum cf = (Forum)f.get(i);
			System.out.println(cf.getId() + ": " + cf.getForumName());
		}
	}

}
