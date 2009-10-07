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
import es.gofio.mv6lib.utils.Timestamp;

/**
 * <p>Post<br />
 * (2/Oct/09)</p>
 * <p>
 * Example of usage:
 * </p>
 * <p><i>
 * Post p = new Post(38349, 2);<br />
 * System.out.println(p.getAuthor());
 * </i></p>
 * 
 * @author Ricardo 'PiradoIV' Cruz Fdez.
 * @author http://www.piradoiv.com/
 * @author piradoiv@gmail.com
 *
 */
public class Post {
	private int		_threadId;
	private int		_postId;
	private String	_threadPage;
	private int		_forumId;
	private long	_timestamp;
	private String	_author;
	private String	_post;
	
	/**
	 * Constructor of the class
	 * @param threadId The ID of the Thread.
	 * @param postId The ID of the Post.
	 */
	public Post(int threadId, int postId) {
		this._threadId = threadId;
		this._postId = postId;
	}
	
	/**
	 * Constructor of the class, can specify the String with the full content of the page, saving resources.
	 * @param threadId The ID of the Thread.
	 * @param postId The ID of the Post.
	 * @param page The full content of the Thread page.
	 */
	public Post(int threadId, int postId, String page) {
		this._threadId = threadId;
		this._postId = postId;
		this._threadPage = page;
	}
	
	/**
	 * Forces the object to reload the post.
	 */
	public void refreshPost() {
		if(this._threadPage == null) {
			setPage();
		}
		setForumId();
		setPost();
		setTimestamp();
		setAuthor();
	}
	
	/**
	 * @return Returns the ID of the Thread.
	 */
	public int getThreadId() { return this._threadId; }
	
	/**
	 * @return Returns the ID of the Post.
	 */
	public int getPostId() { return this._postId; }
	
	/**
	 * @return Returns the ID of the Forum.
	 */
	public int getForumId() { return this._forumId; }
	
	/**
	 * @return Returns the creation timestamp of the Post.
	 */
	public long getTimestamp() { return this._timestamp; }
	
	/**
	 * @return Returns the content of the Post.
	 */
	public String getPost() { return this._post; }
	
	/**
	 * @return Returns the content of the Post, without the most of the HTML code.
	 */
	public String getPostWithoutHtml() { return getCleanPost(); }
	
	/**
	 * @return Returns the ID of the Member.
	 */
	public String getAuthor() { return this._author; }
	
	private void setPage() {
		if(this._postId <= 30) {
			this._threadPage = Page.getPage("http://www.mediavida.com/foro/1/mv6lib-" + this._threadId);
		} else {
			int pagenumber = (int)Math.floor((this._postId / 30)) +1;
			System.out.println(pagenumber);
			this._threadPage = Page.getPage("http://www.mediavida.com/foro/1/mv6lib-" + this._threadId + "/" + pagenumber);
		}
	}
	
	private void setForumId() {
		String regex = " <a href=\"/foro/([0-9]+)\">";
		Matcher m = Pattern.compile(regex).matcher(this._threadPage);
		if(m.find()) {
			this._forumId = Integer.parseInt(m.group(1));
		}
	}
	
	private void setPost() {
		String regex = "<div id=\"cuerpo_" + this._postId + "\" class=\"cuerpo\">\t\t\t\t\t\t\t([^\t]+)\t\t\t\t\t\t\t\t\t</div>";
		Matcher m = Pattern.compile(regex).matcher(this._threadPage);
		if(m.find()) {
			this._post = m.group(1);
		}
	}
	
	private String getCleanPost() {
		String cleanedPost = this._post;
		
		String regex = "(<br />)";
		Matcher m = Pattern.compile(regex).matcher(cleanedPost);
		cleanedPost = m.replaceAll("\n");
		
		regex = "&quot;";
		m = Pattern.compile(regex).matcher(cleanedPost);
		cleanedPost = m.replaceAll("\"");
		
		regex = "<img .* src=\"([^\"]*)\">";
		m = Pattern.compile(regex).matcher(cleanedPost);
		if(m.find()) {
			cleanedPost = m.replaceAll(m.group(1));
		}
		
		regex = "<a href=\"http://([^\"]*)\".*</a>";
		m = Pattern.compile(regex).matcher(cleanedPost);
		if(m.find()) {
			cleanedPost = m.replaceAll("http://" + m.group(1));
		}
		
		regex = "<a href=\"#\".*Spoiler</a>";
		m = Pattern.compile(regex).matcher(cleanedPost);
		if(m.find()) {
			cleanedPost = m.replaceAll("Spoiler: ");
		}
		
		regex = "(<[^>]*>)";
		m = Pattern.compile(regex).matcher(cleanedPost);
		cleanedPost = m.replaceAll("");
		
		return cleanedPost;
	}
	
	private void setTimestamp() {
		String regex = "#" + this._postId + "</a> <span>([^<]*)</span></div>";
		Matcher m = Pattern.compile(regex).matcher(this._threadPage);
		if(m.find()) {
			this._timestamp = Timestamp.getTimestamp(m.group(1));
		}
	}
	
	private void setAuthor() {
		String regex = "<dt><a href=\"/id/([^\"]*)\"";
		Matcher m = Pattern.compile(regex).matcher(this._threadPage.substring(this._threadPage.indexOf("<div id=\"post" + this._postId + "\"")));
		if(m.find()) {
			this._author = m.group(1);
		}
	}
}