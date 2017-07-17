package rsagenerator;

import java.math.BigInteger;
import java.util.Random;

public class RSAImpl {
	private int bitlength = 1024;

	/*
	 * Generating public and private keys using RSA algorithm
	 */

	public void generateRSA(int p, int q, int r) {
		Random random = new Random();
		BigInteger a = BigInteger.valueOf(p);
		BigInteger b = BigInteger.valueOf(q);
		BigInteger c = BigInteger.valueOf(r);
		BigInteger phi = a.subtract(BigInteger.ONE).multiply(b.subtract(BigInteger.ONE));

		c = BigInteger.probablePrime(bitlength / 2, random);

		do {
			c.add(BigInteger.ONE);
		} while (phi.gcd(c).compareTo(BigInteger.ONE) > 0 && c.compareTo(phi) < 0);

		BigInteger d = c.modInverse(phi);
		System.out.println("public key : " + c);
		System.out.println("private key : " + d);
	}
}
