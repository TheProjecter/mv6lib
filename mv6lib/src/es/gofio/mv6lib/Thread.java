/* 
Copyright Ricardo Cruz Fernandez, 2009, http://www.piradoiv.com/

This file is part of mv6lib.

This software is licensed under LGPL, available at
http://www.gnu.org/licenses/lgpl.html

*/
package es.gofio.mv6lib;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.gofio.mv6lib.utils.Page;
import es.gofio.mv6lib.utils.Timestamp;

/**
 * <p>Thread<br />
 * (1/Oct/09)</p>
 * <p>
 * Example of usage:
 * </p>
 * <p><i>
 * Thread t = new Thread("http://www.mediavida.com/foro/8/spoilersois-unos-cansinosspoiler-364505");<br />
 * System.out.println(t.getFavouritesCount());
 * </i></p>
 * 
 * @author Ricardo 'PiradoIV' Cruz Fdez.
 * @author http://www.piradoiv.com/
 * @author piradoiv@gmail.com
 *
 */
public class Thread {
	private String		_id;
	private long		_timestamp;
	private int			_forumId;
	private String		_forumName;
	private String		_author;
	private String		_title;
	private int			_replies;
	private int			_favourites;
	@SuppressWarnings("unused")
	private String		_url;
	private int			_pageCount;
	
	@SuppressWarnings("unused")
	private String		_forumPage;
	private String		_threadPage;
	private String		_threadLastPage;
	
	/**
	 * Constructor of the class. Also tries to retrieve updated information via refreshThread().
	 * @param url The full url of the thread.
	 * @see Thread(int i) refreshThread()
	 * @deprecated
	 */
	public Thread(String url) {
		this._url = url;
		this.getIds();
		refreshThread();
	}
	
	/**
	 * The constructor of the class. You must also retrieve updated information via refreshThread().
	 * @param int The id of the thread.
	 * @since Release 1, build 3
	 * @see refreshThread()
	 */
	public Thread(int id) {
		this._id = "" + id;
		this._url = "http://www.mediavida.com/foro/1/mv6lib-" + id;
	}
	
	/**
	 * The constructor of the class. You must also retrieve updated information via refreshThread().
	 * @param id The id of the thread.
	 * @param name The name of the thread.
	 */
	public Thread(int id, String title) {
		this._id = "" + id;
		this._url = "http://www.mediavida.com/foro/1/mv6lib-" + id;
		this._title = title;
	}
	
	/**
	 * @return Returns the ID of the Thread.
	 */
	public String getId() { return this._id; }
	
	/** 
	 * @return Returns the timestamp (in milliseconds) of the thread's creation time.
	 */
	public long getTimestamp() { return this._timestamp; }
	
	/**
	 * @return Returns the ID of the parent forum.
	 */
	public int getForumId() { return this._forumId; }
	
	/**
	 * @return Get a Member class of the Author of the thread.
	 */
	public String getAuthor() { return this._author; }
	
	/**
	 * @return Returns the title of the thread.
	 */
	public String getTitle() { return this._title; }
	
	/**
	 * @return Returns the number of replies to the thread. Also is the id of the last post.
	 */
	public int getRepliesCount() { return this._replies; }
	
	/**
	 * @return Returns the number of people who added this thread to their favourites.
	 */
	public int getFavouritesCount() { return this._favourites; }
	
	/**
	 * @return Returns the number of pages of the thread.
	 */
	public int getPageCount() { return this._pageCount; }
	
	/**
	 * @return Returns the forum's name without the need of call the method Forum.getName()
	 */
	public String getForumName() { return this._forumName; }
	
	//public Category getCategory() { return this._category; }
	
	/**
	 * Forces the object to retrieve updated information of the thread.
	 */
	public void refreshThread() {
		updatePages();
		getIds();		
		updateReplies();
		updateTimestamp();
		updateCategory();
		updateAuthor();
		updateTitle();
		updateFavourites();
		updateForumName();
	}
	
	/*
	 * Aqu’ empieza el c—digo privado que no necesitan ver los usuarios de mv6lib.
	 * 
	 * TODO Documentar el c—digo. 
	 */
	
	private void updateCategory() { }		//TODO
	
	private void updateAuthor() {
		Matcher m = getMatcher("<dt><a href=\"/id/([^\"]+)\" style", this._threadPage);
		if(m.find()) {
			this._author = m.group(1);
		}
	}
	
	private void updateForumName() {
		String regex = "<a href=\"/foro/" + this._forumId + "\">(.*)</a>";
		Matcher m = Pattern.compile(regex).matcher(this._threadPage);
		if(m.find()) {
			this._forumName = m.group(1);
		}
	}
	
	private void updateTitle() {
		Matcher m = getMatcher("<title>([^&]*)&bull;", this._threadPage);
		if(m.find()) {
			this._title = m.group(1).trim();
		}
	}
	
	private void updatePagesCount() {
		Matcher m = getMatcher("class=\"last\">([0-9]*)</a>", this._threadPage);
		if(m.find()) {
			this._pageCount = Integer.parseInt(m.group(1));
		} else {
			this._pageCount = 1;
		}
	}
	
	private void updateReplies() {
		Matcher m = getMatcher("<div class=\"info\"><a href=\"#([0-9]*)\"", this._threadLastPage);
		int lastReply = 0;
		while(m.find() == true) {
			lastReply = Integer.parseInt(m.group(1));
		}
		this._replies = lastReply;
	}
	
	private void updateFavourites() {
		Matcher m = getMatcher("<div class=\"favcount\">([0-9]{1,})</div>", this._threadPage);
		if(m.find()) {
			this._favourites = Integer.parseInt(m.group(1));
		}
	}
	
	private void getIds() {
		Matcher m;
		m = getMatcher("\"/foro/([0-9]+)\">", this._threadPage);
		if(m.find()) {
			this._forumId = Integer.parseInt(m.group(1));
		}
	}
	
	private void updatePages() {
		try {
			this._threadPage = Page.getPage("http://www.mediavida.com/foro/1/mv6lib-" + this._id);
			updatePagesCount();
			if(this._pageCount > 1) {
				this._threadLastPage = Page.getPage(new URL("http://www.mediavida.com/foro/1/mv6lib-" + this._id + "/" + this._pageCount));
			} else {
				this._threadLastPage = this._threadPage;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateTimestamp() {
		Matcher m;
		m = getMatcher("#1</a> <span>[^0-9]*([^<]+)</span>", this._threadPage);
		if(m.find()) {
			this._timestamp = Timestamp.getTimestamp(m.group(1));
			return;
		}
		m = getMatcher("#1</a> <span>([^<]+)</span>", this._threadPage);
		if(m.find()) {
			this._timestamp = Timestamp.getTimestamp(m.group(1));
		}
	}

	private Matcher getMatcher(String regex, String page) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(page);
		return m;
	}	
}