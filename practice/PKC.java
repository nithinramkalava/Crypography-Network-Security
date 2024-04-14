package practice;
import java.security.*;
import java.util.*;
import javax.crypto.*;

public class PKC {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        System.out.println("Private Key: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        System.out.println("Public key: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Message: ");
        String message = sc.nextLine();
        sc.close();
        
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedMessage = cipher.doFinal(message.getBytes());
        String encryptedString = Base64.getEncoder().encodeToString(encryptedMessage);

        System.out.println("Encrypted Message: " + encryptedString);

        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessage = cipher.doFinal(Base64.getDecoder().decode(encryptedString));
        String decryptedString = new String(decryptedMessage);

        System.err.println("Decrypted Message: " + decryptedString);
    }
}
