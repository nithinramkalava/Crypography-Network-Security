package practice;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Hash {
    public static void main(String[] args) throws Exception{
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update("VVIT".getBytes());
        byte[] hashDigest = messageDigest.digest();

        BigInteger hash = new BigInteger(1, hashDigest);

        System.out.println(hash.toString(16));
    }
}
