package com.oopsfeedmecode.lib_using_slf4j_api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptionDecryptionUtil {

    private static final Logger LOGGER = LogManager.getLogger(EncryptionDecryptionUtil.class);
    private static final String AES_ALGORITHM = "AES";
    private static final String AES_GCM_NO_PADDING = "AES/GCM/NoPadding";
    private static final int AES_KEY_SIZE = 128; // in bits
    private static final int GCM_TAG_LENGTH = 128; // in bits
    private static final int IV_LENGTH_BYTES = 12; // 96 bits is typical for GCM

    /**
     * Generates a new AES SecretKey for encryption/decryption.
     *
     * @return A new randomly generated SecretKey.
     */
    public static SecretKey generateKey() {
        try {
            LOGGER.info("Generating AES key of size {} bits...", AES_KEY_SIZE);
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM);
            keyGenerator.init(AES_KEY_SIZE);
            SecretKey secretKey = keyGenerator.generateKey();
            LOGGER.debug("Successfully generated AES key: algorithm={}, format={}",
                    secretKey.getAlgorithm(), secretKey.getFormat());
            return secretKey;
        } catch (Exception e) {
            LOGGER.error("Error generating AES key: {}", e.getMessage(), e);
            throw new RuntimeException("Error generating AES key", e);
        }
    }

    /**
     * Encrypts the given plaintext using AES/GCM/NoPadding and returns the ciphertext
     * in Base64-encoded form, along with the IV appended to it.
     *
     * @param plaintext The data to encrypt.
     * @param secretKey The AES key for encryption.
     * @return Base64-encoded ciphertext with IV appended.
     */
    public static String encrypt(String plaintext, SecretKey secretKey) {
        LOGGER.info("Starting encryption process. Plaintext length: {}", plaintext.length());
        if (plaintext.isBlank()) {
            LOGGER.warn("Plaintext is empty. Returning empty ciphertext.");
            return "";
        }

        try {
            // Generate a random IV for GCM
            byte[] iv = new byte[IV_LENGTH_BYTES];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);

            // Initialize cipher for encryption
            Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);

            // Perform encryption
            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
            LOGGER.debug("Encryption successful. Encrypted byte length: {}", encryptedBytes.length);

            // Append IV to the beginning of the ciphertext so we can decrypt later
            byte[] encryptedWithIv = new byte[iv.length + encryptedBytes.length];
            System.arraycopy(iv, 0, encryptedWithIv, 0, iv.length);
            System.arraycopy(encryptedBytes, 0, encryptedWithIv, iv.length, encryptedBytes.length);

            // Base64-encode final result
            String encryptedBase64 = Base64.getEncoder().encodeToString(encryptedWithIv);
            LOGGER.info("Encryption complete. Returning Base64-encoded ciphertext.");
            return encryptedBase64;
        } catch (Exception e) {
            LOGGER.error("Error during encryption: {}", e.getMessage(), e);
            throw new RuntimeException("Encryption error", e);
        }
    }

    /**
     * Decrypts the given Base64-encoded ciphertext (with IV) using AES/GCM/NoPadding
     * and returns the original plaintext.
     *
     * @param encryptedBase64 The Base64-encoded ciphertext that includes the IV at the front.
     * @param secretKey       The AES key for decryption.
     * @return The original plaintext.
     */
    public static String decrypt(String encryptedBase64, SecretKey secretKey) {
        LOGGER.info("Starting decryption process. Ciphertext length: {}", encryptedBase64.length());
        if (encryptedBase64.isBlank()) {
            LOGGER.warn("Ciphertext is empty. Returning empty plaintext.");
            return "";
        }

        try {
            // Decode from Base64
            byte[] encryptedWithIv = Base64.getDecoder().decode(encryptedBase64);

            // Extract IV
            byte[] iv = new byte[IV_LENGTH_BYTES];
            byte[] encryptedBytes = new byte[encryptedWithIv.length - IV_LENGTH_BYTES];
            System.arraycopy(encryptedWithIv, 0, iv, 0, IV_LENGTH_BYTES);
            System.arraycopy(encryptedWithIv, IV_LENGTH_BYTES, encryptedBytes, 0,
                    encryptedWithIv.length - IV_LENGTH_BYTES);

            // Initialize cipher for decryption
            Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);

            // Perform decryption
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String decryptedText = new String(decryptedBytes);
            LOGGER.debug("Decryption successful. Decrypted text length: {}", decryptedText.length());

            LOGGER.info("Returning decrypted plaintext.");
            return decryptedText;
        } catch (Exception e) {
            LOGGER.error("Error during decryption: {}", e.getMessage(), e);
            throw new RuntimeException("Decryption error", e);
        }
    }
}