package com.garfiec.networkchat.common;
import java.lang.Math;
import java.util.ArrayList;
import java.lang.Long;

public class Crypt_RSA {

	public class Keys {
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

	public ArrayList<Long> encrypt(String data, Keys key) {
		int len = data.length();
		int len2 = ((len % 4) == 0) ? len : (len + (4-(len % 4)));
		char chars[] = new char[len2];
		chars = data.toCharArray();
		for (int i = len-1; i < len2; ++i) {
			chars[i] = 0;
		}
		int k = len2/4;
		ArrayList<Long> result = new ArrayList<Long>();
		for (int i = 0; i < k; ++i) {
			Long r = (long)0;
			for (int j = 0; j < 4; ++j) {
				int index = i*4+j;
				r += (long)(((long)chars[index])*Math.pow(128, j));
			}
			result.add(r);
		}
		return result;
	}

	public String decrypt(ArrayList<Long> data, Keys key) {
		int len = data.size();
		char chars[] = new char[len*4+1];
		for (int i = 0; i < len; ++i) {
			long l = data.get(i);
			for (int j = 4; j > 0; --j) {
				long k = l >> (7*(j-1));
				k = k & 0x7F;
				System.out.println(String.format("Turning %d into %c by shifting %d", l, (char)k, (8*(j-1))));
				chars[(i*4)+(j-1)] = (char)k;
			}
		}
		chars[len*4] = 0;
		return new String(chars);
	}
}
