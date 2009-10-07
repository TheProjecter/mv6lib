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
String page = Page.getPage("http://www.mediavida.com/foro/9/java-mv6lib-accede-mv6-desde-tu-aplicacion-367539");
Vector<Post> posts = new Vector<Post>();
for(int i = 1; i <= 14; i++) {
	posts.add(new Post(367539, i, page));
}
		
for(int i = 0; i < posts.size(); i++) {
	Post postActual = posts.get(i);
	postActual.refreshPost(); // Como ya le hemos dado la p‡gina, no la vuelve a cargar
	System.out.println("#" + postActual.getPostId() + " " +postActual.getAuthor());
}
	}
}
