package com.oopsfeedmecode.lib_using_slf4j_api.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.*;

class NewEncryptionDecryptionUtilTest {

    @Test
    @DisplayName("Test AES Key Generation")
    void testKeyGeneration() {
        SecretKey key = NewEncryptionDecryptionUtil.generateKey();
        assertNotNull(key, "Generated key should not be null");
        assertEquals("AES", key.getAlgorithm(), "Key algorithm should be AES");
    }

    @Test
    @DisplayName("Test Encryption & Decryption with Valid Input")
    void testEncryptDecryptSuccess() {
        SecretKey key = NewEncryptionDecryptionUtil.generateKey();
        String plaintext = "Hello from the JUnit test!";

        // Encrypt
        String encrypted = NewEncryptionDecryptionUtil.encrypt(plaintext, key);
        assertNotNull(encrypted, "Encrypted text should not be null");
        assertFalse(encrypted.isBlank(), "Encrypted text should not be blank");

        // Decrypt
        String decrypted = NewEncryptionDecryptionUtil.decrypt(encrypted, key);
        assertNotNull(decrypted, "Decrypted text should not be null");
        assertEquals(plaintext, decrypted, "Decrypted text should match the original plaintext");
    }

    @Test
    @DisplayName("Test Encryption with Empty Plaintext")
    void testEncryptEmptyPlaintext() {
        SecretKey key = NewEncryptionDecryptionUtil.generateKey();
        String emptyPlaintext = "";

        String encrypted = NewEncryptionDecryptionUtil.encrypt(emptyPlaintext, key);
        assertEquals("", encrypted, "Encryption of empty plaintext should return an empty string");
    }

    @Test
    @DisplayName("Test Decryption with Empty Ciphertext")
    void testDecryptEmptyCiphertext() {
        SecretKey key = NewEncryptionDecryptionUtil.generateKey();
        String emptyCiphertext = "";

        String decrypted = NewEncryptionDecryptionUtil.decrypt(emptyCiphertext, key);
        assertEquals("", decrypted, "Decryption of empty ciphertext should return an empty string");
    }

    @Test
    @DisplayName("Test Decryption with Invalid or Tampered Ciphertext")
    void testDecryptInvalidCiphertext() {
        SecretKey key = NewEncryptionDecryptionUtil.generateKey();
        // This ciphertext is random or tampered (not valid AES-GCM)
        String invalidCiphertext = "NotAValidCiphertext123";

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> NewEncryptionDecryptionUtil.decrypt(invalidCiphertext, key),
                "Expected decrypt to throw RuntimeException for invalid ciphertext"
        );

        String message = exception.getMessage();
        assertTrue(message.contains("Decryption error"), "Exception message should indicate decryption error");
    }
}