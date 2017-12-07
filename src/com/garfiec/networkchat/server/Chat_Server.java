package com.garfiec.networkchat.server;
import com.garfiec.networkchat.common.Crypt_RSA;
import com.garfiec.networkchat.common.Crypt_RSA.Keys;
import java.util.ArrayList;

public class Chat_Server {
    public static void main(String[] args) {
        System.out.println("Running chat server");
        Chat_Server chat_server = new Chat_Server();
		Crypt_RSA c = new Crypt_RSA();
		Keys k = c.makeKeys(13, 7);
		System.out.println(String.format("e:%d d:%d n:%d", k.e, k.d, k.n));
		ArrayList<Long> enc = c.encrypt("Meet me outside SCE at 10pm.", k);
		for (Long l: enc) {
			System.out.println(l);
		}
		String dec = c.decrypt(enc, k);
		System.out.println(dec);
    }
}
