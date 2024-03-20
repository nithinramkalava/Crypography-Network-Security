import java.math.BigInteger;
import java.security.*;
import java.util.*;

public class CryptoHashFunctions {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);

        // 1. Get user input for data and algorithm
        System.out.println("Enter data to be hashed:");
        String data = scanner.nextLine();
        System.out.println("Choose hashing algorithm (SHA-256, SHA-512, MD5):");
        String algorithm = scanner.nextLine().toUpperCase();

        // 2. Validate algorithm choice

        HashSet<String> algorithms = new HashSet<>();
        algorithms.add("SHA-256");
        algorithms.add("MD5");
        algorithms.add("SHA-512");

        if (!algorithms.contains(algorithm)){
            System.out.println("Invalid algorithm choice. Please choose SHA-256, SHA-512 or MD5.");
            scanner.close();
            return;
        }

        // 3. Generate hash
        String hash = generateHash(data, algorithm);

        // 4. Demonstrate applications
        System.out.println("\nHash generated using " + algorithm + ":");
        System.out.println(hash);

        scanner.close();
    }

    public static String generateHash(String data, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(data.getBytes());
        byte[] hashBytes = digest.digest();
        BigInteger hashInt = new BigInteger(1, hashBytes);
        return hashInt.toString(16);
    }
}

