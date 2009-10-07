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
import es.gofio.mv6lib.utils.Timestamp;

/**
 * <p>Member<br />
 * (1/Oct/09)</p>
 * <p>
 * Example of usage:
 * </p>
 * <p><i>
 * Member m = new Member("PiradoIV");<br />
 * System.out.println(m.isConnected());
 * </i></p>
 * 
 * @author Ricardo 'PiradoIV' Cruz Fdez.
 * @author http://www.piradoiv.com/
 * @author piradoiv@gmail.com
 *
 */
public class Member {
	private String			_id;
	private String			_nick;
	private boolean			_connected;
	private String			_avatar;
	private String			_photo;
	private int				_signatures;
	private int				_posts;
	private int				_visits;
	private int				_age;
	private String			_name;
	private String			_city;
	private long			_registeredTimestamp;
	private Vector<String>	_lastVisitors;
	
	private String			_page;
	
	/**
	 * Constructor of the class. You must call the refreshMember() method to fetch the values of the member.
	 * @param id The ID of the member.
	 * @see refreshMember()
	 */
	public Member(String id) {
		this._id = id;
		this._nick = id;
	}
	
	/**
	 * Constructor of the class. You must call the refreshMember() method to fetch the values of the member except if you want to see the online status & avatar of the member.
	 * @param id The ID of the member.
	 * @param isConnected Specifies if the member is currently online.
	 * @param avatar Set the member's avatar.
	 */
	public Member(String id, boolean isConnected, String avatar) {
		this._id = id;
		this._nick = id;
		this._connected = isConnected;
		this._avatar = avatar;
	}
	
	/**
	 * @return Returns the ID of the member.
	 */
	public String getId() { return this._id; }
	
	/**
	 * @return Returns the nick of the member (same as the ID).
	 */
	public String getNick() { return this._nick; }
	
	/**
	 * @return Returns true if the member is online.
	 */
	public boolean isConnected() { return this._connected; }
	
	/**
	 * @return Returns the full url of the member's avatar.
	 */
	public String getAvatar() { return this._avatar; }
	
	/**
	 * @return Returns the full url of the member's photo.
	 */
	public String getPhoto() { return this._photo; }
	
	/**
	 * @return Returns the current counter of the member's signatures.
	 */
	public int getSignaturesCount() { return this._signatures; }
	
	/**
	 * @return Returns the quantity of posts the member wrote.
	 */
	public int getPosts() {
		return this._posts;
	}
	
	/**
	 * @return Returns the visits quantity of the member.
	 */
	public int getVisits() {
		return this._visits;
	}
	
	/**
	 * @return Returns the age of the member.
	 */
	public int getAge() {
		return this._age;
	}
	
	/**
	 * @return Returns the name of the member.
	 */
	public String getName() {
		return this._name;
	}
	
	/**
	 * @return Returns where the member lives.
	 */
	public String getCity() {
		return this._city;
	}
	
	/**
	 * @return Returns the timestamp of the member's register.
	 */
	public long getRegisteredTimestamp() {
		return this._registeredTimestamp;
	}
	
	/**
	 * @return Returns a Vector<String> of the last visitors of the member.
	 */
	public Vector<String> getLastVisitors() {
		return this._lastVisitors;
	}
	
	/**
	 * Forces the object to retrieve updated information of the member.
	 */
	public void refreshMember() {
		updatePage();
		setNick();
		checkIfIsConnected();
		setAvatar();
		setPhoto();
		setSignatures();
		setPosts();
		setVisits();
		setAge();
		setName();
		setCity();
		setMemberSince();
		setLastVisits();
	}
	
	/*
	 * Aqu’ empieza el c—digo privado que no necesitan ver los usuarios de mv6lib.
	 * 
	 * TODO Documentar el c—digo. 
	 */	
	
	private void setLastVisits() {
		Matcher m = getMatcher("\t\t\t\t<strong>([^<]+)</strong>", this._page);
		Vector<String> v = new Vector<String>();
		while(m.find()) {
			v.add(m.group(1));
		}
		this._lastVisitors = v;
	}
	
	private void setSignatures() {
		Matcher m = getMatcher("firmas\">([0-9]*) firmas</a>", this._page);
		if(m.find()) {
			this._signatures = Integer.parseInt(m.group(1));
		}
	}
	
	private void setVisits() {
		Matcher m = getMatcher("([0-9]*) visitas", this._page);
		if(m.find()) {
			this._visits = Integer.parseInt(m.group(1));
		}
	}
	
	private void setAge() {
		Matcher m = getMatcher("\t([0-9]*) a", this._page);
		if(m.find()) {
			this._age = Integer.parseInt(m.group(1).trim());
		}
	}
	
	private void setName() {
		Matcher m = getMatcher("<p><strong>([^<]*)</strong>,", this._page);
		if(m.find()) {
			this._name = m.group(1);
		}
	}
	
	private void setCity() {
		Matcher m = getMatcher("os, ([^<]*)</p>", this._page);
		if(m.find()) {
			this._city = m.group(1).trim();
		}
	}
	
	private void setPosts() {
		Matcher m = getMatcher("([0-9]*) posts", this._page);
		if(m.find()) {
			this._posts = Integer.parseInt(m.group(1));
		}
	}
	
	private void setNick() {
		Matcher m = getMatcher("<title>Perfil de ([^&]*)&bull;", this._page);
		if(m.find()) {
			this._nick = m.group(1).trim();
		}
	}
	
	private void setAvatar() {
		Matcher m = getMatcher("<div class=\"avatarwrap\"><img src=\"([^\"]*)\"></div>", this._page);
		if(m.find()) {
			this._avatar = "http://www.mediavida.com" + m.group(1);
		}
	}
	
	private void setPhoto() {
		Matcher m = getMatcher("<a href=\"(/img/users/galeria/[^\"]+)\" id=\"fotoperfil\">", this._page);
		if(m.find()) {
			this._photo = "http://www.mediavida.com" + m.group(1);
		}
	}
	
	private void checkIfIsConnected() {
		Matcher m = getMatcher("visto hace ", this._page);
		if(m.find()) {
			this._connected = false;
		} else {
			this._connected = true;
		}
	}
	
	private void setMemberSince() {
		Matcher m;
		m = getMatcher("miembro desde ([^\t]+)\t", this._page);
		if(m.find()) {
			this._registeredTimestamp = Timestamp.getTimestamp(m.group(1));
		}
	}	
	
	private void updatePage() {
		this._page = Page.getPage("http://www.mediavida.com/id/" + this._id);
	}
		
	private Matcher getMatcher(String regex, String page) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(page);
		return m;
	}	
	
}
