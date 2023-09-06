import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        SecretKey key = Cryptography.generateKey();

        byte[] tempKey = key.getEncoded();

        key = new SecretKeySpec(tempKey, 0, tempKey.length, "AES");

        IvParameterSpec iv = Cryptography.generateIv();

        Scanner message = new Scanner(System.in);
        String plainText = message.nextLine();

        for(int i = 0; i < 100; i++) {

            System.out.println("Plain Text: " + plainText);

            byte[] cypherText = SecretSigns.encrypt(plainText, key, iv);

            System.out.print("Cypher Text: ");
            for(int j = 0; j < cypherText.length; j++){
                System.out.print(cypherText[j] + " ");
            }
            System.out.println();
            System.out.println("Cypher Text Length: " + cypherText.length);

            String decryptedText = SecretSigns.decrypt(cypherText, key, iv);

            System.out.println("Decrypted Text: " + decryptedText);

            System.out.println();
        }

    }

}