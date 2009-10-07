package es.gofio.mv6lib.tests;

import java.util.Vector;

import es.gofio.mv6lib.Member;
import es.gofio.mv6lib.utils.Friends;

public class TestFriends {

	public static void main(String[] args) {
		Vector<Member> friends = Friends.getFriendsOf("PiradoIV");
		for(int i = 0; i < friends.size(); i++) {
			System.out.println(friends.get(i).getNick() + " " + friends.get(i).isConnected() + " " + friends.get(i).getAvatar());
		}
	}

}
