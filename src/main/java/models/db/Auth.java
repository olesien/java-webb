package models.db;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Auth {
    private static final int SALT_LENGTH = 16;
    public static String encrypt(String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        try {
            //Skapa en instans av SHA-256 hash-funktionen
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            //Pour salt into wounds
            digest.update(salt);

            //Röka lite hash
            byte[] hashedPassword = digest.digest(password.getBytes());
            return  byteArrayToHex(hashedPassword) +  byteArrayToHex(salt);

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static boolean matches (String password, String hashedPassword) {
        String passwordHash = hashedPassword.substring(0, 64);
        String salt = hashedPassword.substring(64);
        byte[] saltBytes = hexStringToByeArray(salt);

        try {
            //Skapa en instans av SHA-256 hash-funktionen
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            //Pour salt into wounds
            digest.update(saltBytes);

            //Röka lite hash
            byte[] hashedInputPassword = digest.digest(password.getBytes());
            String hexStringPass = byteArrayToHex(hashedInputPassword);

            return hexStringPass.equals(passwordHash);

            //return Arrays.toString(hashedPassword) + Arrays.toString(salt);

        } catch (NoSuchAlgorithmException e) {
            return false;
        }

    }

    private static byte[] hexStringToByeArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}