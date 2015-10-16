package core;


import java.math.BigInteger;

import core.HECiphertext;
import core.KeyPair;
import core.Parameters;

public class HashElGamal {

	public static KeyPair genKeyPair(Parameters param) {

		BigInteger sk = BigInteger.valueOf(param.rand.nextLong()).mod(param.biP);

		BigInteger pk = param.biG.modPow(sk, param.biP);

		return new KeyPair(sk, pk);
	}

	public static HECiphertext encrypt(Parameters param, BigInteger pk, String msg) {

		BigInteger r = (BigInteger.valueOf(param.rand.nextLong())).mod(param.biP);

		BigInteger u = param.biG.modPow(r, param.biP);

		BigInteger v = BigInteger.valueOf(HashFunction.SHA256ToUnsignedInt(pk.modPow(r, param.biP).toString())).xor(new BigInteger(msg));

		return new HECiphertext(u, v);
	}

	public static String decrypt(Parameters param, BigInteger sk, HECiphertext ciphertext) {

		return BigInteger.valueOf(HashFunction.SHA256ToUnsignedInt(ciphertext.getU().modPow(sk, param.biP).toString())).xor(ciphertext.getV()).toString();
	}

	public static HECiphertext addMask(HECiphertext c, String mask) {

		return new HECiphertext(c.getU(), c.getV().xor(new BigInteger(mask)));
	}
}
