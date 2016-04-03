package br.com.ok.util.security;

import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * The Class OKPasswordHandler.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
public final class OKPasswordHandler {

	/** The Constant CHIPER_ALGORITHM. */
	private static final String CHIPER_ALGORITHM = "AES";

	/** The Constant HASH_ALGORITHM. */
	private static final String HASH_ALGORITHM = "SHA-256";

	/** The Constant KEY -> OKOnlineLearning <-. */
	private static final byte[] KEY = { 79, 75, 79, 110, 108, 105, 110, 101, 76, 101, 97, 114, 110, 105, 110, 103 };

	/** The Constant DEFAULT_PASSWORD -> onknowledge <-. */
	private static final byte[] DEFAULT_PASSWORD = { 111, 110, 107, 110, 111, 119, 108, 101, 100, 103, 101 };

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(OKPasswordHandler.class.getName());

	/**
	 * Instantiates a new OK password handler.
	 */
	private OKPasswordHandler() {

	}

	/**
	 * Checks if is equal.
	 *
	 * @param passworda
	 *            the passworda
	 * @param passwordb
	 *            the passwordb
	 * @return true, if is equal
	 */
	public static boolean isEqual(String passworda, byte[] passwordb) {
		boolean equals = false;
		try {
			equals = MessageDigest.isEqual(OKPasswordHandler.doHash(passworda), passwordb);
		} catch (Exception e) {
			OKPasswordHandler.LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return equals;
	}

	/**
	 * Generate default password.
	 *
	 * @return the byte[]
	 */
	public static byte[] generateDefaultPassword() {
		try {
			return OKPasswordHandler.doHash(new String(OKPasswordHandler.DEFAULT_PASSWORD));
		} catch (Exception e) {
			OKPasswordHandler.LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Do hash.
	 *
	 * @param password
	 *            the password
	 * @return the byte[]
	 * @throws Exception
	 *             the exception
	 */
	public static byte[] doHash(String password) throws Exception {
		return MessageDigest.getInstance(OKPasswordHandler.HASH_ALGORITHM).digest(OKPasswordHandler.doCipher(password, Cipher.ENCRYPT_MODE));
	}

	/**
	 * Do cipher.
	 *
	 * @param password
	 *            the password
	 * @param mode
	 *            the mode
	 * @return the byte[]
	 * @throws Exception
	 *             the exception
	 */
	public static byte[] doCipher(String password, int mode) throws Exception {
		return OKPasswordHandler.generateCipher(mode).doFinal(password.getBytes());
	}

	/**
	 * Generate cipher.
	 *
	 * @param mode
	 *            the mode
	 * @return the cipher
	 * @throws Exception
	 *             the exception
	 */
	private static Cipher generateCipher(int mode) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(OKPasswordHandler.KEY, OKPasswordHandler.CHIPER_ALGORITHM);
		Cipher cipher = Cipher.getInstance(OKPasswordHandler.CHIPER_ALGORITHM);
		cipher.init(mode, secretKey);
		return cipher;
	}
}
