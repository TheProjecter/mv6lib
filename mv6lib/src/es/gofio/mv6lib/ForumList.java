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
 * <p>ForumList<br />
 * (3/Oct/09)</p>
 * <p>
 * This class is used to get a Vector of Forum classes. Excample of use:
 * </p>
 * <p><i>
 * Vector<Forum> forums = new Stats();<br />
 * System.out.println(stats.getUsersOnline());
 * </i></p>
 * 
 * @author Ricardo 'PiradoIV' Cruz Fdez.
 * @author http://www.piradoiv.com/
 * @author piradoiv@gmail.com
 *
 */
public class ForumList {
	private Vector<Forum>	_forumList;
	private String			_page;
	
	public ForumList() {
		this._forumList = new Vector<Forum>();
	}
	
	/**
	 * @return Returns a vector with the forum list.
	 */
	public Vector<Forum> getForumList() {
		return this._forumList;
	}
	
	/**
	 * Forces the class to refresh the list of forums.
	 */
	public void refreshForumList() {
		this._page = Page.getPage("http://www.mediavida.com/foro/");
		updateForumList();
	}
	
	private void updateForumList() {
		String regex = "<a href=['|\"]/foro/([0-9]+)['|\"]( class=\"hb\")?>([^<]*)</a>";
		Matcher m = Pattern.compile(regex).matcher(this._page);
		while(m.find()) {
			this._forumList.add(new Forum(Integer.parseInt(m.group(1)), m.group(3)));
		}
	}
}
