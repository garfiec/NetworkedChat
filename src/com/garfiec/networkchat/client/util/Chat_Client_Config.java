package com.garfiec.networkchat.client.util;

import com.garfiec.networkchat.common.Crypt_RSA;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Chat_Client_Config {
    // Storage for settings related to client program
    public String   server_ip   = "127.0.0.1";
    public int      port        = 9000;

    public long cipher_p = 0;
    public long cipher_q = 0;

    public Crypt_RSA.Keys cipher_key;

    public String user_name = "Guest";

    private ArrayList<Long> prime_list;

    public Chat_Client_Config() {
        // Generate random primes as default
        prime_list = new ArrayList<>();
        readPrimesFile();
        setCipherPrimes(0, 0);
    }

    // Todo
    public boolean setCipherPrimes(long p, long q) {
        // Check if p, q = 0
        if (p == 0 && q == 0) {
            this.cipher_p = prime_list.get(ThreadLocalRandom.current().nextInt(0, prime_list.size()));
            do {
                this.cipher_q = prime_list.get(ThreadLocalRandom.current().nextInt(0, prime_list.size()));
            } while (this.cipher_p == this.cipher_q);

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

    private void readPrimesFile() {
        InputStream stream = this.getClass().getResourceAsStream("/com/garfiec/networkchat/common/etc/primes.rsc");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "utf-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                prime_list.add(Long.parseLong(line));
            }
        }
        catch (Exception e) {
        }
    }
}
