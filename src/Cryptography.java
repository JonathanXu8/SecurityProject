import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
public class Cryptography {

    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
        keygenerator.init(128);
        return keygenerator.generateKey();
    }

    public static IvParameterSpec generateIv() {
        byte[] initializationVector = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);
        return new IvParameterSpec(initializationVector);
    }

    public static byte[] encrypt(String input, SecretKey key, IvParameterSpec iv)
            throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        return cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String decrypt(byte[] cipherText, SecretKey key, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(cipherText);
        return new String(plainText);
    }

}