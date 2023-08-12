import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        SecretKey symmetricKey = Cryptography.generateKey();
        IvParameterSpec iv = Cryptography.generateIv();

        // Takes input from the keyboard
        Scanner message = new Scanner(System.in);
        String plainText = message.nextLine();
        message.close();

        // Encrypt the message using the symmetric key
        byte[] cipherText = Cryptography.encrypt(plainText, symmetricKey, iv);

        System.out.println("The encrypted message is: " + cipherText);

        // Decrypt the encrypted message
        String decryptedText = Cryptography.decrypt(cipherText, symmetricKey, iv);

        System.out.println( "Your original message is: " + decryptedText);
    }

}