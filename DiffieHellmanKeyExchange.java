import java.security.*;
import java.util.Base64;

import javax.crypto.KeyAgreement;

public class DiffieHellmanKeyExchange {
    public static void main(String[] args) {
        try {
            // Alice's key pair generation
            KeyPairGenerator aliceKeyGen = KeyPairGenerator.getInstance("DH");
            aliceKeyGen.initialize(2048);
            KeyPair aliceKeyPair = aliceKeyGen.generateKeyPair();

            // Bob's key pair generation
            KeyPairGenerator bobKeyGen = KeyPairGenerator.getInstance("DH");
            bobKeyGen.initialize(2048);
            KeyPair bobKeyPair = bobKeyGen.generateKeyPair();

            // Alice's public key
            PublicKey alicePublicKey = aliceKeyPair.getPublic();
            System.out.println("Alice's Public Key: " + Base64.getEncoder().encodeToString(alicePublicKey.getEncoded()));

            // Bob's public key
            PublicKey bobPublicKey = bobKeyPair.getPublic();
            System.out.println("Bob's Public Key: " + Base64.getEncoder().encodeToString(bobPublicKey.getEncoded()));

            // Alice's key agreement
            KeyAgreement aliceKeyAgree = KeyAgreement.getInstance("DH");
            aliceKeyAgree.init(aliceKeyPair.getPrivate());
            aliceKeyAgree.doPhase(bobPublicKey, true);
            byte[] aliceSharedSecret = aliceKeyAgree.generateSecret();

            // Bob's key agreement
            KeyAgreement bobKeyAgree = KeyAgreement.getInstance("DH");
            bobKeyAgree.init(bobKeyPair.getPrivate());
            bobKeyAgree.doPhase(alicePublicKey, true);
            byte[] bobSharedSecret = bobKeyAgree.generateSecret();

            // Compare shared secrets
            System.out.println("Alice's Shared Secret: " + Base64.getEncoder().encodeToString(aliceSharedSecret));
            System.out.println("Bob's Shared Secret: " + Base64.getEncoder().encodeToString(bobSharedSecret));

            // Verify if the shared secrets are the same
            if (MessageDigest.isEqual(aliceSharedSecret, bobSharedSecret)) {
                System.out.println("Shared secrets are the same.");
            } else {
                System.out.println("Shared secrets are not the same.");
            }

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}