/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.util.encoders.Base64;

/**
 *
 * @author toze
 */
public class CryptAES {

    /*
    @see http://stackoverflow.com/questions/15554296/simple-java-aes-encrypt-decrypt-example
     */
    public static String encrypt(String key, String value) {
        if (value.length() >= 4) {
            try {
                key = hashFromString(key);
                String initVector = key.substring(0, 16);
                key = key.substring(48, 64);
                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes());
                SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
                byte[] encrypted = cipher.doFinal(value.getBytes());
                return Base64.toBase64String(encrypted);
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
            }
        }
        return null;
    }

    public static String decrypt(String key, String encrypted) {
        if (encrypted.length() >= 4) {
            try {
                key = hashFromString(key);
                String initVector = key.substring(0, 16);
                key = key.substring(48, 64);
                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes());
                SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
                byte[] original = cipher.doFinal(Base64.decode(encrypted));
                return new String(original);
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
            }
        }
        return null;
    }

    private static String hashFromString(String entra) {
        String retorna;
        int read;
        byte[] buffer = new byte[8192];
        InputStream stream = new ByteArrayInputStream(entra.getBytes(StandardCharsets.UTF_8));
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            while ((read = stream.read(buffer)) > 0) {
                digest.update(buffer, 0, read);
            }
            byte[] hash = digest.digest();
            BigInteger bigInt = new BigInteger(1, hash);
            retorna = bigInt.toString(16);
            while (retorna.length() < 32) {
                retorna = "0" + retorna;
            }
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace(System.err);
            return null;
        }

        return retorna;
    }
}
