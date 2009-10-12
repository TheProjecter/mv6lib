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
	private Vector<String>	_page = new Vector<String>();
	private Vector<Forum> _forums = new Vector<Forum>();
	
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
	 * @return This method returns a Vector<Forum> with id, name, threads counter and posts counter.
	 */
	public Vector<Forum> getForumsWithCounters() {
		if(this._forums.size() == 0) {
//			updateStats();
		}
		return _forums;
	}
	
	/**
	 * This method tries to connect to the website and update all of the stats.
	 */
	public void updateStats() {
		updatePage();
		updateTimestamp();
		updateUsersOnline();
		updateMembersCount();
		updateThreadsCounters();
	}
	
	private void updatePage() {
		this._page.clear();
		this._page.add(Page.getPage("http://www.mediavida.com/foro/"));
		String regex = "<a href='/foro/([^']*)' class=\"hb\">[^<]*</a><br><span>[^<]*</span>		<!-- subforos -->";
		Matcher m = Pattern.compile(regex).matcher(this._page.firstElement());
		
		while(m.find()) {
			this._page.add(Page.getPage("http://www.mediavida.com/foro/" + m.group(1)));
		}
	}
	
	private void updateTimestamp() {
		_timestamp = System.currentTimeMillis();
	}
	
	private boolean updateUsersOnline() {
		Matcher m = Pattern.compile("OnLine: ([0-9]+)").matcher(this._page.get(1));
		if(m.find()) {
			this._usersOnline = Integer.valueOf(m.group(1));
			return true;
		} else {
			return false;
		}
	}
	
	private boolean updateMembersCount() {
		Matcher m = Pattern.compile("Miembros: ([0-9]+)").matcher(this._page.get(1));
		if(m.find()) {
			this._members = Integer.valueOf(m.group(1));
			return true;
		} else {
			return false;
		}
	}
	
	private void updateThreadsCounters() {
		_forums.clear();
		for(int i = 0; i < this._page.size(); i++) {
//			String regex = "<a href='/foro/([0-9]*)' class=\"hb\">([^<]*)</a><br><span>[^<]*</span>\t</td>\t \t<td class=\"alt center\">([0-9]*)</td>\t<td class=\"last center\">([0-9]*)</td>";
			String regex = "<a href='/foro/([0-9]*)' class=\"hb\">([^<]*)</a><br><span>[^<]*</span>(\t\t[^\t]*\t[^\t]*\t[^\t]*)?\t</td>\t \t<td class=\"alt center\">([0-9]*)</td>\t<td class=\"last center\">([0-9]*)</td>";
			Matcher m = Pattern.compile(regex).matcher(this._page.get(i));
			while(m.find()) {
				Forum f = new Forum(Integer.parseInt(m.group(1)), m.group(2));
				f.setThreadsCounter(Integer.parseInt(m.group(4)));
				f.setPostsCounter(Integer.parseInt(m.group(5)));
				_forums.add(f);
			}
			regex = "<strong><a href='/foro/([0-9]*)'>([^<]*)</a></strong><br><span>[^<]*</span></td>\t\t<td class=\"alt\">([0-9]*)</td>\t\t<td class=\"last\">([0-9]*)</td></tr>";
			m = Pattern.compile(regex).matcher(this._page.get(i));
			while(m.find()) {
				Forum f = new Forum(Integer.parseInt(m.group(1)), m.group(2));
				f.setThreadsCounter(Integer.parseInt(m.group(3)));
				f.setPostsCounter(Integer.parseInt(m.group(4)));
				_forums.add(f);
			}
		}
		
		int totalThreadsCounter = 0;
		int totalPostsCounter = 0;
		for(int i = 0; i < this._forums.size(); i++) {
			totalThreadsCounter += _forums.get(i).getThreadsCounter();
			totalPostsCounter += _forums.get(i).getPostsCounter();
		}
		
		this._threads = totalThreadsCounter;
		this._posts = totalPostsCounter;
	}
}