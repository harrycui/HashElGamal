package test;


import java.math.BigInteger;

import core.HECiphertext;
import core.KeyPair;
import core.Parameters;
import core.HashElGamal;

public class HETest {
	
	public static void main(String[] args) {
		
		Parameters param = new Parameters();
		
		KeyPair keys = HashElGamal.genKeyPair(param);
		
		String msg = "1357924680";
		
		System.out.println("msg = " + msg);
		
		HECiphertext cipher = HashElGamal.encrypt(param, keys.getPk(), msg);
		
		System.out.println("After decryption:\nmsg = " + HashElGamal.decrypt(param, keys.getSk(), cipher));
		
		String mask = "1234";
		
		System.out.println("\nAdd mask in plaintext domain:\nmasked_msg = " + (new BigInteger(msg).xor(new BigInteger(mask))));
		
		System.out.println("\nAdd mask in ciphertext domain:");
		
		HECiphertext newCipher = HashElGamal.addMask(cipher, mask);
		
		String decMsg = HashElGamal.decrypt(param, keys.getSk(), newCipher);

		System.out.println("After decryption:\nmasked_msg = " + decMsg);
		
		String unmaskedMsg = (new BigInteger(decMsg).xor(new BigInteger(mask))).toString();
		
		System.out.println("Unmasking:\nmsg = " + unmaskedMsg);
		
		System.out.println("Correct or not? " + msg.equals(unmaskedMsg));
	}
}
