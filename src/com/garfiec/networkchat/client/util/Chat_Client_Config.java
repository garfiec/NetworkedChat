package com.garfiec.networkchat.client.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Chat_Client_Config {
    // Storage for settings related to client program
    public String   server_ip   = "127.0.0.1";
    public int      port        = 9000;

    public long cipher_p;
    public long cipher_q;

    public String user_name = "Guest";

    private ArrayList<Integer> prime_list;

    public Chat_Client_Config() {
        // Generate random primes as default
        setCipherPrimes(0, 0);
        primalityTest(101);
    }

    // Todo
    public boolean setCipherPrimes(long p, long q) {
        // Check if p, q = 0
        if (p == 0 && q == 0) {
            // Todo: Generate primes
            // https://crypto.stackexchange.com/questions/71/how-can-i-generate-large-prime-numbers-for-rsa
//            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("/com/garfiec/common/etc/primes.rsc");
//            System.out.println(stream != null);

            return true;
        }

        // Check if p = q
        if (p == q) {
            return false;
        }

        // Check if p, q > 0
        if (p <= 0 || q <= 0) {
            return false;
        }

        // Check if p*q <= 128^4
        long N = p*q;
        if (N <= Math.pow(128, 4)) {
            return false;
        }

        // Check if numbers are prime
        if (!primalityTest(p) || !primalityTest(q)) {
            return false;
        }

        // Valid input. Success
        this.cipher_p = p;
        this.cipher_q = q;

        return true;
    }

    private boolean primalityTest(long num) {
        // Using trial division primality test specified below
        // https://en.wikipedia.org/wiki/Primality_test

        // We know that it shouldn't be divisible by 2
        if (num % 2 == 0) {
            return false;
        }

        long divisorMax = (long) Math.sqrt(num);

        // Since we already checked divisibility by 2, we an skip even divisors
        for (int i = 3; i <= divisorMax; i+= 2) {
            if (num % i == 0) {
                // Factor found
                return false;
            }
        }

        return true;
    }
}
