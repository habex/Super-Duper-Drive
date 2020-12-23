package salt_hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SaltExample {
    public static void main(String[] args) {
        // A static String for the example
        String passwordToHash = "myPassword";

        // Create a salt
        byte[] salt = createSalt();

        // Create a hash
        String securePassword = get_SecurePassword(passwordToHash, salt);
        System.out.println("SHA-256_Salt : " + securePassword);

        String securePassword_MD5_Salt = get_SecurePassword_MD5_Salt(passwordToHash, salt);
        System.out.println("MD5_Salt : " + securePassword_MD5_Salt);
        String securePassword_MD5 = get_SecurePassword_MD5(passwordToHash);
        System.out.println("MD5 only : " + securePassword_MD5);
        System.out.println(" securePassword == securePassword_MD5_Salt : "+securePassword.equals(securePassword_MD5_Salt));
    }

    // Method to generate a Salt
    private static byte[] createSalt() {
        SecureRandom secureRandom = new SecureRandom();

        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    // Method to generate the hash.
    // It takes a password and the Salt as input arguments
    private static String get_SecurePassword(String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0Xff) + 0X100, 16).substring(1));
            }
            generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static String get_SecurePassword_MD5_Salt(String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0Xff) + 0X100, 16).substring(1));
            }
            generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static String get_SecurePassword_MD5(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0Xff) + 0X100, 16).substring(1));
            }
            generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
