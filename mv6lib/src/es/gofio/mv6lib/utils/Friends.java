/* 
Copyright Ricardo Cruz Fernandez, 2009, http://www.piradoiv.com/

This file is part of mv6lib.

This software is licensed under LGPL, available at
http://www.gnu.org/licenses/lgpl.html

*/
package es.gofio.mv6lib.utils;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.gofio.mv6lib.Member;

public abstract class Friends {
	
	/**
	 * Usage example: Vector<Member> friends = Page.getFriendsOf("PiradoIV");
	 * @param nick The member we want to stalk.
	 * @return A Vector<Member> with the nick, online status and the url of the member's avatar.
	 */
	public static Vector<Member> getFriendsOf(String nick) {
		Vector<Member> members = new Vector<Member>();
		String page = Page.getPage("http://www.mediavida.com/id/" + nick + "/amigos");
		String regex = "<span class=\"imgmini\"><img src=\"(/img/users/avatar/[^\"]*)\" (class=\"online\")? width=\"32\" height=\"32\" alt=\"([^\"]*)\"></span>";
		Matcher m = Pattern.compile(regex).matcher(page);
		
		while(m.find()) {
			boolean connected;
			if(m.group(2) != null) {
				connected = true;
			} else {
				connected = false;
			}
			members.add(new Member(m.group(3), connected, m.group(1)));
		}
		
		return members;
	}
}
