/* 
Copyright Ricardo Cruz Fernandez, 2009, http://www.piradoiv.com/

This file is part of mv6lib.

This software is licensed under LGPL, available at
http://www.gnu.org/licenses/lgpl.html

*/
package es.gofio.mv6lib;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.gofio.mv6lib.utils.Page;

/**
 * <p>Forum<br />
 * (2/Oct/09)</p>
 * <p>
 * Example of usage:
 * </p>
 * <p><i>
 * Forum f = new Forum(3);<br />
 * System.out.println(f.getForumName());
 * </i></p>
 * 
 * @author Ricardo 'PiradoIV' Cruz Fdez.
 * @author http://www.piradoiv.com/
 * @author piradoiv@gmail.com
 *
 */
public class Forum {
	private int				_id;
	private String			_name;
	private String			_page;
	private Vector<Thread>	_lastThreads = new Vector<Thread>();
	private int				_postsCounter;
	private int				_threadsCounter;
	
	/**
	 * Constructor of the class.
	 * @param id The id of the forum.
	 */
	public Forum(int id) {
		this._id = id;
	}
	
	/**
	 * Constructor of the class.
	 * @param id The id of the forum.
	 * @param name The name of the forum.
	 */
	public Forum(int id, String name) {
		this._id = id;
		this._name = name;
	}
	
	/**
	 * @return Returns the ID of the forum.
	 */
	public int getId() { return this._id; }
	
	/**
	 * @return Returns the name of the forum.
	 */
	public String getForumName() {
		if(this._name == null) {
			refreshForum();
		}
		return this._name;
	}
	
	/**
	 * @return Returns a Vector of the last 30 (more or less) threads.
	 */
	public Vector<Thread> getLastThreads() {
		if(this._page == null) {
			refreshForum();
		}
		return this._lastThreads;
	}
	
	/**
	 * @return Returns the counter of threads
	 */
	public int getThreadsCounter() {
		if(this._threadsCounter == -1) {
//			refreshForum();
		}
		return this._threadsCounter;
	}
	
	/**
	 * @return Returns the counter of posts
	 */
	public int getPostsCounter() {
		if(this._postsCounter == -1) {
//			refreshForum();
		}
		return this._postsCounter;
	}
	
	/**
	 * Forces the class to refresh the content.
	 */
	public void refreshForum() {
		this._page = Page.getPage("http://www.mediavida.com/foro/" + this._id);
		setName();
		setLastThreads();
	}
	
	private void setName() {
		String regex = "<h1>(.*)</h1>";
		Matcher m = Pattern.compile(regex).matcher(this._page);
		if(m.find()) {
			this._name = m.group(1);
		}
	}
	
	private void setLastThreads() {
		String regex = "<a id='a([0-9]*)' class=\"hb\" title='([^\']*)'";
		Matcher m = Pattern.compile(regex).matcher(this._page);
		while(m.find()) {
			_lastThreads.add(new Thread(Integer.parseInt(m.group(1)), m.group(2)));
		}
	}
	
	public void setPostsCounter(int counter) {
		this._postsCounter = counter;
	}
	
	public void setThreadsCounter(int counter) {
		this._threadsCounter = counter;
	}
}
