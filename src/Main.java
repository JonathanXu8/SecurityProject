import javafx.util.Pair;

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

        /*
        // Encrypt the message using the symmetric key
        Pair cipherText = SecretSigns.sendAndEncrypt(plainText, symmetricKey, iv);

        System.out.println("The encrypted message is: " + cipherText.getKey().toString());

        // Decrypt the encrypted message
        String decryptedText = SecretSigns.recieveAndDecrpt(cipherText, symmetricKey, iv);

        System.out.println( "Your original message is: " + decryptedText);

        System.out.println();
        */

        for(int i = 0; i < 100; i++){
            Pair encryptedPackage = SecretSigns.sendAndEncrypt(plainText, symmetricKey, iv);
            System.out.println("The encrypted message is: " + encryptedPackage.getKey().toString());
            String decryptedText = SecretSigns.recieveAndDecrpt(encryptedPackage, symmetricKey, iv);
            System.out.println( "Your original message is: " + decryptedText);

            System.out.println();

        }




    }

}