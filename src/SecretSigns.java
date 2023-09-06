import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SecretSigns {

    public static byte[] encrypt(String text, SecretKey key, IvParameterSpec iv) throws Exception {
        SecretKey innerKey = Cryptography.generateKey();

        byte[] cypherText = join(innerKey.getEncoded(), Cryptography.encrypt(text, innerKey, iv));

        cypherText =   Cryptography.encrypt(cypherText, key, iv);

        return cypherText;

    }

    public static String decrypt(byte[] cypherText, SecretKey key, IvParameterSpec iv) throws Exception {
        byte[] decryptedText = Cryptography.decrypt(cypherText, key, iv);

        byte[] innerKeyByteArray = Arrays.copyOfRange(decryptedText, 0, 32);
        SecretKey innerKey = new SecretKeySpec(innerKeyByteArray, 0, innerKeyByteArray.length, "AES");

        decryptedText = Arrays.copyOfRange(decryptedText, 32, decryptedText.length);

        decryptedText = Cryptography.decrypt(decryptedText, innerKey, iv);

        return new String(decryptedText);

    }

    public static byte[] join(byte[] arr1, byte[] arr2){
        byte[] temp = new byte[arr1.length + arr2.length];

        for(int i = 0; i < temp.length; i++){
            if(i < arr1.length){
                temp[i] = arr1[i];
            } else {
                temp[i] = arr2[i - arr1.length];
            }
        }

        return temp;
    }


}
