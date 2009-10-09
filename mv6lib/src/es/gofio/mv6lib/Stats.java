/* 
Copyright Ricardo Cruz Fernandez, 2009, http://www.piradoiv.com/

This file is part of mv6lib.

This software is licensed under LGPL, available at
http://www.gnu.org/licenses/lgpl.html

*/
package es.gofio.mv6lib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.gofio.mv6lib.utils.Page;

/**
 * <p>Stats<br />
 * (29/Sep/09)</p>
 * <p>
 * Example of usage:
 * </p>
 * <p><i>
 * Stats stats = new Stats();<br />
 * System.out.println(stats.getUsersOnline());
 * </i></p>
 * 
 * @author Ricardo 'PiradoIV' Cruz Fdez.
 * @author http://www.piradoiv.com/
 * @author piradoiv@gmail.com
 *
 */
public class Stats {
	private long	_timestamp;
	private int		_usersOnline;
	private int		_members;
	private int		_threads;
	private int		_posts;
	private String	_page;
	
	/**
	 * Constructor of the class, you must call the updateStats() method to retrieve the stats.
	 * @see updateStats()
	 */
	public Stats() {
		//updateStats();
	}
	
	/**
	 * This method returns the timestamp of the stats.
	 * @return long with timestamp in milliseconds.
	 */
	public long getTimestamp() {
		if(this._page == null) {
			updateStats();
		}
		return _timestamp;
	}
	
	/**
	 * Method for get the current number of online users.
	 * @return int with the current number of online users.
	 */
	public int getUsersOnline() {
		if(this._page == null) {
			updateStats();
		}
		return _usersOnline;
	}
	
	/**
	 * Method for get the current number of registered users in the page.
	 * @return int with the current number of registered users.
	 */
	public int getMembersCount() {
		if(this._page == null) {
			updateStats();
		}
		return _members;
	}
	
	/**
	 * This method counts the quantity of threads created in the page.
	 * @return int with the quantity of threads.
	 */
	public int getThreadsCount() {
		if(this._page == null) {
			updateStats();
		}
		return _threads;
	}
	
	/**
	 * This method counts the quantity of posts (threads + replies) created in the page.
	 * @return int with the quantity of posts.
	 */
	public int getPostsCount() {
		if(this._page == null) {
			updateStats();
		}
		return _posts;
	}
	
	/**
	 * This method tries to connect to the website and update all of the stats.
	 */
	public void updateStats() {
		updatePage();
		updateTimestamp();
		updateUsersOnline();
		updateMembersCount();
		updateThreadsCount();
		updatePostsCount();
	}
	
	private void updatePage() {
		this._page = Page.getPage("http://www.mediavida.com/foro/");
	}
	
	private void updateTimestamp() {
		_timestamp = System.currentTimeMillis();
	}
	
	private boolean updateUsersOnline() {
		Matcher m = getMatcher("OnLine: ([0-9]+)");
		if(m.find()) {
			this._usersOnline = Integer.valueOf(m.group(1));
			return true;
		} else {
			return false;
		}
	}
	
	private boolean updateMembersCount() {
		Matcher m = getMatcher("Miembros: ([0-9]+)");
		if(m.find()) {
			this._members = Integer.valueOf(m.group(1));
			return true;
		} else {
			return false;
		}
	}
	
	private void updateThreadsCount() {
		Matcher m = getMatcher("<td class=\"alt center\">([0-9]+)</td>");
		int i = 0;
		while(m.find()) {
			i = i + Integer.valueOf(m.group(1));
		}
		this._threads = 0;
		this._threads = i;
	}
	
	private void updatePostsCount() {
		Matcher m = getMatcher("<td class=\"last center\">([0-9]+)</td");
		int i = 0;
		while(m.find()) {
			i = i + Integer.valueOf(m.group(1));
		}
		this._posts = 0;
		this._posts = i;		
	}
	
	private Matcher getMatcher(String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(this._page);
		return m;
	}
}