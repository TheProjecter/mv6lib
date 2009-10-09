package es.gofio.mv6lib.tests;

import es.gofio.mv6lib.Member;
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
		System.out.println(Singleton.getInstance().getMember("PiradoIV").getNick());
		System.out.println(Singleton.getInstance().getPost(367539,2).getAuthor());
	}
}
