import javafx.util.Pair;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class SecretSigns {

    public static Pair sendAndEncrypt(String s, SecretKey key, IvParameterSpec iv) throws Exception{
        String multiKey = generateMutiKey(128);

        byte[] cypherText = Cryptography.encrypt(s + multiKey, key, iv);
        byte[] encryptedKey = Cryptography.encrypt(multiKey, key, iv);

        return new Pair(cypherText, encryptedKey);

    }

    public static String recieveAndDecrpt(Pair pair, SecretKey key, IvParameterSpec iv ) throws Exception{
        byte[] cypherText = (byte[]) pair.getKey();
        byte[] encryptedKey = (byte[]) pair.getValue();

        String multiKey = Cryptography.decrypt(encryptedKey, key, iv);
        int bytes = multiKey.length();

        String s = Cryptography.decrypt(cypherText, key, iv);
        s = s.substring(0, s.length() - bytes);

        return s;

    }


    public static String generateMutiKey(int bytes){

        String key = "";

        for(int i = 0; i < bytes; i++){
            int temp = (int) (Math.random() * (127 - 33) + 33);
            key += (char) temp;
        }

        return key;
    }










}
