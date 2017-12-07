package com.garfiec.networkchat.client.util;

public class Chat_Client_Config {
    // Storage for settings related to client program
    public String   server_ip   = "127.0.0.1";
    public int      port        = 9000;

    public long cipher_p;
    public long cipher_q;

    public String user_name = "Guest";

    public Chat_Client_Config() {
        // Generate random primes as default
        setCipherPrimes(0, 0);
    }

    // Todo
    public boolean setCipherPrimes(long p, long q) {
        // Check if p, q = 0
        if (p == 0 && q == 0) {
            // Todo: Generate primes
            // https://crypto.stackexchange.com/questions/71/how-can-i-generate-large-prime-numbers-for-rsa
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

        // Todo: Check if numbers are prime


        // Valid input. Success
        this.cipher_p = p;
        this.cipher_q = q;

        return true;
    }
}
