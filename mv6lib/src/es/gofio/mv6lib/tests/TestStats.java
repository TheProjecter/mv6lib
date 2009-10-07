package es.gofio.mv6lib.tests;

import es.gofio.mv6lib.Stats;

public class TestStats {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stats mvs = new Stats();
		mvs.updateStats();
		System.out.println("Usuarios online: " + String.valueOf(mvs.getUsersOnline()));
		System.out.println("Miembros registrados: " + String.valueOf(mvs.getMembersCount()));
		System.out.println("Threads: " + String.valueOf(mvs.getThreadsCount()));
		System.out.println("Posts: " + String.valueOf(mvs.getPostsCount()));		
	}

}
