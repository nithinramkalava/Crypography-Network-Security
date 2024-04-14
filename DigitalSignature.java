import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class DigitalSignature {
    public static void main(String[] args) {
        try {
            // Generate a key pair for the digital signature
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // Create a message to be signed
            System.out.println("Enter message:");
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine().toLowerCase();
            scanner.close();

            System.out.println("Original Message: " + message);

            // Sign the message
            Signature signature = Signature.getInstance("SHA256withDSA");
            signature.initSign(privateKey);
            byte[] messageBytes = message.getBytes();
            signature.update(messageBytes);
            byte[] digitalSignature = signature.sign();

            // Print the digital signature
            String base64Signature = Base64.getEncoder().encodeToString(digitalSignature);
            System.out.println("Digital Signature: " + base64Signature);

            // Verify the digital signature
            Signature verifier = Signature.getInstance("SHA256withDSA");
            verifier.initVerify(publicKey);
            verifier.update(messageBytes);
            boolean isValid = verifier.verify(digitalSignature);

            System.out.println("Is the digital signature valid? " + isValid);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
    }
}