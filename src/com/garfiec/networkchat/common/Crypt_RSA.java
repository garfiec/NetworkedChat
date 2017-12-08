package com.garfiec.networkchat.common;
import java.lang.Math;
import java.util.ArrayList;
import java.lang.Long;
import java.math.BigInteger;
import java.io.Serializable;

public class Crypt_RSA implements Serializable {

	public class Keys implements Serializable {
		public long e;
		public long d;
		public long n;
		public boolean valid;

		public Keys(long e, long d, long n, boolean valid) {
			this.e = e;
			this.d = d;
			this.n = n;
			this.valid = valid;
		}
	}
	public Crypt_RSA() {
	}

	public long gcd(long p, long q) {
		if (q == 0) return p;
		return gcd(q, p%q);
	}

	public Keys makeKeys(long p, long q) {
		long N = p * q;
		long r = (p-1) * (q-1);
		if (N <= Math.pow(128, 4)) {
			//return new Keys(-1, -1, -1, false);
		}

		long e;
		for (e = 2; e < N; ++e) {
			if (gcd(e, r) == 1) break;			
		}
		if (e == N) {
			return new Keys(-1, -1, -1, false);
		}
		long k;
		for (k = 1; k < N; ++k) {
			if (((1+k*r) % e) == 0) break;
		}
		if (k == N) {
			return new Keys(-1, -1, -1, false);
		}
		long d = (1 + k * r) / e;
		return new Keys(e, d, N, true);
	}

	public ArrayList<BigInteger> encrypt(String data, Keys key) {
		int len = data.length();
		int len2 = ((len % 4) == 0) ? len : (len + (4-(len % 4)));
		char chars[] = new char[len2];
		char ourData[] = data.toCharArray();
		for (int i = 0; i < ourData.length; ++i) {
			chars[i] = ourData[i];
		}
		if (len%4 != 0) {
			for (int i = ourData.length; i < len2; ++i) {
				// please keep this comment. Uncomment if encryption fails
				//System.out.println(String.format("Setting %d.. Length is %d and len2 is %d", i, len, len2));
				chars[i] = 0;
			}
		}
		int k = len2/4;
		ArrayList<BigInteger> result = new ArrayList<BigInteger>();
		for (int i = 0; i < k; ++i) {
			BigInteger r = BigInteger.valueOf(0);
			for (int j = 0; j < 4; ++j) {
				int index = i*4+j;
				BigInteger single = BigInteger.valueOf((long)chars[index]);
				BigInteger multiplicand = BigInteger.valueOf((long)Math.pow(128, j));
				r = r.add(single.multiply(multiplicand));
			}
			result.add(r.modPow(BigInteger.valueOf(key.e), BigInteger.valueOf(key.n)));
		}
		return result;
	}

	public String decrypt(ArrayList<BigInteger> dataOrig, Keys key) {
		ArrayList<BigInteger> data = new ArrayList<BigInteger>();
		for (BigInteger l: dataOrig) {
			BigInteger result = l.modPow(BigInteger.valueOf(key.d), BigInteger.valueOf(key.n));
			data.add(result);
			// Keep this comment please. Uncomment if error in decryption happens
			//System.out.println(String.format("%d turning into %d", l, result));
		}
		int len = data.size();
		char chars[] = new char[len*4+1];
		for (int i = 0; i < len; ++i) {
			BigInteger l = data.get(i);
			for (int j = 4; j > 0; --j) {
				BigInteger k = l.shiftRight(7*(j-1)).and(BigInteger.valueOf(0x7F));
				long c = k.longValue();
				// Keep this comment please. Uncomment if error in decryption happens
				//System.out.println(String.format("Turning %d into %c by shifting %d", l, (char)c, (8*(j-1))));
				chars[(i*4)+(j-1)] = (char)c;
			}
		}
		chars[len*4] = 0;
		return new String(chars);
	}
}
