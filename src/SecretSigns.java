import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class SecretSigns {

    public static byte[] encrypt(String text, SecretKey key, IvParameterSpec iv) throws Exception {
        SecretKey innerKey = Cryptography.generateKey();

        byte[] padding = generatePadding();

        byte[] cypherText = join(innerKey.getEncoded(), join(padding, Cryptography.encrypt( text, innerKey, iv)));

        cypherText = Cryptography.encrypt(cypherText, key, iv);

        return cypherText;

    }

    public static String decrypt(byte[] cypherText, SecretKey key, IvParameterSpec iv) throws Exception {
        byte[] decryptedText = Cryptography.decrypt(cypherText, key, iv);

        byte[] innerKeyByteArray = Arrays.copyOfRange(decryptedText, 0, 32); // 256 bit key =  32 bytes
        SecretKey innerKey = new SecretKeySpec(innerKeyByteArray, 0, innerKeyByteArray.length, "AES");

        decryptedText = Arrays.copyOfRange(decryptedText, 32 + decryptedText[32] + 128, decryptedText.length);

        decryptedText = Cryptography.decrypt(decryptedText, innerKey, iv);

        return new String(decryptedText);

    }

    public static byte[] generatePadding(){
        byte length = (byte) (Math.random() * (256) - 128);

        byte[] padding = new byte[length + 128];

        padding[0] = length;

        for(int i = 1; i < length + 128; i++){
            padding[i] = (byte) (Math.random() * (128 + 128) - 128);
        }

        return padding;

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
