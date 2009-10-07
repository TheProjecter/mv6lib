package es.gofio.mv6lib.tests;

import es.gofio.mv6lib.Post;

public class TestPost {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Post p = new Post(351952, 4);
		p.refreshPost();
		System.out.println("Foro: " + p.getForumId());
		System.out.println("Pid: " + p.getPostId());
		System.out.println("Tid: " + p.getThreadId());
		System.out.println("Timestamp: " + p.getTimestamp());
		System.out.println("Author: " + p.getAuthor());
		System.out.println("Post: " + p.getPost());
		System.out.println("Post limpio: " + p.getPostWithoutHtml());
	}

}
