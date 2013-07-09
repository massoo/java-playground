package be.demo.utility;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.SecretKey;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.crypto.CryptoHelper;
import org.owasp.esapi.errors.EncryptionException;

/*
 * Masterkey and salt generator from JavaEncrypt (ESAPI)
 */
public class MasterkeyGenerator {

	public static String generateMasterkey() throws NoSuchAlgorithmException, EncryptionException {
		String encryptAlgorithm = ESAPI.securityConfiguration().getEncryptionAlgorithm();
		int encryptionKeyLength = ESAPI.securityConfiguration().getEncryptionKeyLength();

		SecretKey secretKey = CryptoHelper.generateSecretKey(encryptAlgorithm, encryptionKeyLength);
        byte[] raw = secretKey.getEncoded();
        
        return ESAPI.encoder().encodeForBase64(raw, false); 
	}
	
	public static String generateMasterSalt() throws NoSuchAlgorithmException, EncryptionException {
		String randomAlgorithm = ESAPI.securityConfiguration().getRandomAlgorithm();
		SecureRandom random = SecureRandom.getInstance(randomAlgorithm);
		
        byte[] salt = new byte[20];	// Or 160-bits; big enough for SHA1, but not SHA-256 or SHA-512.
        random.nextBytes( salt );
                
        return ESAPI.encoder().encodeForBase64(salt, false);
	}
	
}
