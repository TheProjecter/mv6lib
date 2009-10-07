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
	public String getForumName() { return this._name; }
	
	/**
	 * @return Returns a Vector of the last 30 (more or less) threads.
	 */
	public Vector<Thread> getLastThreads() { return this._lastThreads; }
	
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
		// <a id='a363482' class="hb" title='ÀCu‡ndo podremos crear grupos?'
		String regex = "<a id='a([0-9]*)' class=\"hb\" title='([^\']*)'";
		Matcher m = Pattern.compile(regex).matcher(this._page);
		while(m.find()) {
//			System.out.println(m.group(1) + " " + m.group(2));
			_lastThreads.add(new Thread(Integer.parseInt(m.group(1)), m.group(2)));
		}
	}
}
