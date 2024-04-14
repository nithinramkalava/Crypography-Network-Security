import java.security.*;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.*;

public class PKCSv1_5 {
    public static void main(String[] args) throws NoSuchPaddingException {
        try {
            // Generate an RSA key pair
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            KeyPair keyPair = keyGen.generateKeyPair();

            // Get the public and private keys
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Encode the keys as Base64 strings
            String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());

            System.out.println("Public Key: " + publicKeyBase64);
            System.out.println("Private Key: " + privateKeyBase64);

            // Create a message to be encrypted
            System.out.println("Enter message:");
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine().toLowerCase();
            scanner.close();
            
            System.out.println("Original Message: " + message);

            // Encrypt the message using PKCS#1 v1.5 padding
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] cipherText = cipher.doFinal(message.getBytes());

            // Print the encrypted message in Base64 format
            String encryptedMessage = Base64.getEncoder().encodeToString(cipherText);
            System.out.println("Encrypted Message: " + encryptedMessage);

            // Decrypt the message using PKCS#1 v1.5 padding
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
            String decryptedMessage = new String(decryptedBytes);

            System.out.println("Decrypted Message: " + decryptedMessage);

        } catch (NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }
}