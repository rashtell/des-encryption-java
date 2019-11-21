package com.rashtell;

/**
 *
 */

/**
 * @author rAsHtElL
 *
 */

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


 class Encrypt {

    private static Cipher ecipher;
    private static Cipher dcipher;

    private static SecretKey key;

    /**
     *
     */
    public Encrypt() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            //generate secret key using DES algorithm
            key = KeyGenerator.getInstance("DES").generateKey();

            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");

            //Initialize the ciphers with the given key
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);

            String info = "Thi is a classified message !";
            String encrypted = ecrypt(info);
            String decrypted = decrypt(encrypted);

            System.out.println(encrypted);
            System.out.println(decrypted);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("No Such Algorithm: "+e.getMessage());
        }catch (NoSuchPaddingException e) {
            System.out.println("No Such Padding: "+e.getMessage());
        }catch (InvalidKeyException e) {
            System.out.println("Invalid Key: "+e.getMessage());
            return;
        }

    }

    private static String ecrypt(String info) {
        try {
            // encode the string into a sequence of bytes using the named charset

            // storing the result into a new byte array

            byte[] utf8 = info.getBytes("UTF8");
            byte[] enc = ecipher.doFinal(utf8);

            // encode to base64
            enc = BASE64EncoderStream.encode(enc);

            return new String(enc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String decrypt(String encrypted) {
        try {
            // decode with base64 to get bytes
            byte[] dec = BASE64DecoderStream.decode(encrypted.getBytes());

            byte[] utf8 = dcipher.doFinal(dec);

            // create new string based on the specified charset
            return new String(utf8, "UTF8");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
