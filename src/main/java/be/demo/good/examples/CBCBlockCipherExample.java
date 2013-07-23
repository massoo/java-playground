package be.demo.good.examples;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import be.demo.utility.RandomGenerator;

/*
 * Example's original source:
 * http://www.scottjjohnson.com/blog/AesWithCbcExample.java
 */
public class CBCBlockCipherExample {

	public static void main(String[] args) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, NoSuchProviderException {

		String message1 = "This string contains a secret message.";
		String message2 = "This string contains a secret message.";
		
		SecureRandom secureRandom1 = SecureRandom.getInstance("SHA1PRNG", "SUN");
		secureRandom1.nextBytes(new byte[20]);
		
		// generate a key
		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		keygen.init(128); // To use 256 bit keys, you need the
							// "unlimited strength" encryption policy files from
							// Sun.

		byte[] key = keygen.generateKey().getEncoded();
		SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

		// build the initialization vector. This example is all zeros, but it
		// could be any value or generated using a random number generator.
		byte[] iv1 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		IvParameterSpec ivspec1 = new IvParameterSpec(iv1);

		// initialize the cipher for encrypt mode
		Cipher cipher1 = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher1.init(Cipher.ENCRYPT_MODE, skeySpec, ivspec1);

		// reinitialize the cipher for decryption
		byte[] encrypted1 = cipher1.doFinal(message1.getBytes());
		System.out.println("Ciphertext1: " + hexEncode(encrypted1) + "\n");
		
		cipher1.init(Cipher.DECRYPT_MODE, skeySpec, ivspec1);

		byte[] decrypted1 = cipher1.doFinal(encrypted1);
		System.out.println("Plaintext1: " + new String(decrypted1) + "\n");

		
		byte[] iv2 = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		IvParameterSpec ivspec2 = new IvParameterSpec(iv2);
		
		// initialize the cipher for encrypt mode
		Cipher cipher2 = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher2.init(Cipher.ENCRYPT_MODE, skeySpec, ivspec2);
		
		byte[] encrypted2 = cipher2.doFinal(message2.getBytes());
		System.out.println("Ciphertext2: " + hexEncode(encrypted2) + "\n");

		// reinitialize the cipher for decryption
		cipher2.init(Cipher.DECRYPT_MODE, skeySpec, ivspec2);
		
		byte[] decrypted2 = cipher2.doFinal(encrypted2);
		System.out.println("Plaintext2: " + new String(decrypted2) + "\n");
	}

	/**
	 * Hex encodes a byte array. <BR>
	 * Returns an empty string if the input array is null or empty.
	 * 
	 * @param input
	 *            bytes to encode
	 * @return string containing hex representation of input byte array
	 */
	public static String hexEncode(byte[] input) {
		if (input == null || input.length == 0) {
			return "";
		}

		int inputLength = input.length;
		StringBuilder output = new StringBuilder(inputLength * 2);

		for (int i = 0; i < inputLength; i++) {
			int next = input[i] & 0xff;
			if (next < 0x10) {
				output.append("0");
			}

			output.append(Integer.toHexString(next));
		}

		return output.toString();
	}

}
