/* 
Copyright Ricardo Cruz Fernandez, 2009, http://www.piradoiv.com/

This file is part of mv6lib.

This software is licensed under LGPL, available at
http://www.gnu.org/licenses/lgpl.html

*/
package es.gofio.mv6lib;

import java.util.Vector;

/**
 * <p>Singleton<br />
 * (9/Oct/09)</p>
 * 
 * @author Ricardo 'PiradoIV' Cruz Fdez.
 * @author http://www.piradoiv.com/
 * @author piradoiv@gmail.com
 *
 */
public class Singleton {
    private static Singleton INSTANCE = null;
 
    // Private constructor suppresses 
    private Singleton() {}
 
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciaci—n mœltiple 
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new Singleton();
        }
    }
 
    public static Singleton getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }
    
    public Member getMember(String nick) {
    	Member m = new Member(nick);
    	return m;
    }
    
    public Vector<Forum> getForumList() {
    	Vector<Forum> f = new ForumList().getForumList();
    	return f;
    }    
    
    public Thread getThread(int thread) {
    	Thread t = new Thread(thread);
    	return t;
    }
    
    public Post getPost(int thread, int postId) {
    	Post p = new Post(thread, postId);
    	return p;
    }
    
    public Vector<Post> getPost(int thread, int fromPostId, int toPostId) {
    	Vector<Post> p = new Thread(thread).getPosts(fromPostId, toPostId);
    	return p;
    }
    
    public Stats getStats() {
    	Stats s = new Stats();
    	return s;
    }
    
    public Forum getForum(int id) {
    	Forum f = new Forum(id);
    	return f;
    }
}
