package com.garfiec.networkchat.server;
import com.garfiec.networkchat.common.Crypt_RSA;
import com.garfiec.networkchat.common.Crypt_RSA.Keys;
import java.util.ArrayList;
import java.math.BigInteger;

public class Chat_Server {
    public static void main(String[] args) {
        System.out.println("Running chat server");
        Chat_Server chat_server = new Chat_Server();
		// Create Crypto instance to encrypt and decrypt shit
		Crypt_RSA c = new Crypt_RSA();
		// Create public keys from p and q -- YOU MUST VERIFY THEY ARE PRIME BEFORE SENDING THEM
		Keys k = c.makeKeys(256201021L, 256203161L);
		// Public key is (e, n), Private is (d, n). Let's print them to find out what they are
		System.out.println(String.format("e:%d d:%d n:%d", k.e, k.d, k.n));
		// Let's encrypt our secret message using our public key (this method doesn't care about k.d)
		ArrayList<BigInteger> enc = c.encrypt("Meet me outside SCE at 10pm. I will be waiting for you, so please don't take long. hahahahahahaha here are some numbers too 53427593857 maybe a % and a \\ too. Can you handle newlines too?\nBet", k);
		// Now let's decrypt it!! This method doesn't care about k.e
		String dec = c.decrypt(enc, k);
		// Print what we encrypted. Hopefully it's the same thing
		System.out.println(dec);
    }
}
