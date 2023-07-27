package the.aes;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class Main {

    private static final String ALGORITHM = "AES";
    private static User user = new User(Page.MENU, null, null);


    public static void main(String[] args) throws Exception {
        while (user.getPage() == Page.MENU) {
            start();
        }
    }

    public static void start() throws Exception {
        System.out.println("--------------------- MENU ---------------------");
        System.out.println("Decrypt - Here you can decrypt some text by key");
        System.out.println("Encrypt - Here you can encode your text by key");
        System.out.println("Exit - For Exit the program");
        System.out.println("Please select:");

        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();

        switch (str.toLowerCase()) {
            case "decrypt" -> decrypt();
            case "encrypt" -> encrypt();
            case "exit" -> Runtime.getRuntime().exit(1);
        }

    }

    public static void createKey() {


        System.out.println("KEY: ");
    }

    public static void encrypt() throws Exception {
        user.setPage(Page.ENCRYPT);

        System.out.println("Your key: ");

        Scanner scanner = new Scanner(System.in);
        String stringKey = scanner.nextLine();

        SecretKey secretKey = generateKeyFromString(stringKey);
        user.setEncryptKey(secretKey);

        System.out.println("Text to encrypt:");

        String text = scanner.nextLine();
        byte[] cipherText = encrypt(text, secretKey);

        System.out.println("TEXT: " + Base64.getEncoder().encodeToString(cipherText));
        user.setPage(Page.MENU);
    }

    public static void decrypt() {
        user.setPage(Page.DECRYPT);

        System.out.println("Your key: ");

        Scanner scanner = new Scanner(System.in);
        String stringKey = scanner.nextLine().trim();


        SecretKey secretKey = generateKeyFromString(stringKey);
        user.setDecryptedKey(secretKey);

        System.out.println("Text to decrypt: ");

        String text = scanner.nextLine().trim();
        try {
            System.out.println("--------------------------------");
            System.out.println("TEXT: " + decryptText(text, secretKey));
        } catch (Exception e) {
            System.out.println("Something went wrong!");
            e.printStackTrace();
            user.setPage(Page.MENU);
            user.setDecryptedKey(null);
        }

        user.setPage(Page.MENU);
    }

    public static SecretKey generateKeyFromString(String keyString) {
        byte[] keyBytes = Base64.getDecoder().decode(keyString);
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    public static byte[] encrypt(String plainText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(plainText.getBytes());
    }

    public static String decryptText(String encryptedText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] result = cipher.doFinal(encryptedBytes);
        return new String(result);
    }

    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

}