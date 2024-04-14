package practice;
import java.util.*;
import java.security.*;
import javax.crypto.*;

public class DiffeHellman {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator aliceKeysGen = KeyPairGenerator.getInstance("DH");
        KeyPair aliceKeys = aliceKeysGen.generateKeyPair();

        KeyPairGenerator bobKeysGen = KeyPairGenerator.getInstance("DH");
        KeyPair bobKeys = bobKeysGen.generateKeyPair();

        KeyAgreement aliceAgreement = KeyAgreement.getInstance("DH");
        aliceAgreement.init(aliceKeys.getPrivate());
        aliceAgreement.doPhase(bobKeys.getPublic(), true);
        byte[] aliceSharedSecret = aliceAgreement.generateSecret();

        KeyAgreement bobKeyAgreement = KeyAgreement.getInstance("DH");
        bobKeyAgreement.init(bobKeys.getPrivate());
        bobKeyAgreement.doPhase(aliceKeys.getPublic(), true);
        byte[] bobSharedSecret = bobKeyAgreement.generateSecret();

        String aliceSharedSecretString = Base64.getEncoder().encodeToString(aliceSharedSecret);
        String bobSharedSecretString = Base64.getEncoder().encodeToString(bobSharedSecret);

        System.out.println(aliceSharedSecretString.equals(bobSharedSecretString));

    }
}
