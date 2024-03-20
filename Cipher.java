import java.util.HashSet;
import java.util.Scanner;

public class Cipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select Cipher:");
        System.out.println("1. Caesar Cipher");
        System.out.println("2. Substitution Cipher");
        int choice = scanner.nextInt();

        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                caesarCipher(scanner);
                break;
            case 2:
                substitutionCipher(scanner);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void caesarCipher(Scanner scanner) {
        System.out.println("Enter message:");
        String message = scanner.nextLine().toLowerCase();

        System.out.println("Enter shift value:");
        int shift = scanner.nextInt() % 26;

        System.out.println("Encrypt (E) or Decrypt (D)?");
        char action = scanner.next().toUpperCase().charAt(0);

        if (action == 'D') shift = 26 - shift;

        String result = "";
        for (char c : message.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                int newPosition = (c - 'a' + shift) % 26;
                result += (char) ('a' + newPosition);
            } 
            else {
                result += c;
            }
        }

        if (action == 'E') {
            System.out.println("Encrypted message: " + result);
        } else if (action == 'D') {
            System.out.println("Decrypted message: " + result);
        } else {
            System.out.println("Invalid action!");
        }
    }

    private static void substitutionCipher(Scanner scanner) {
        System.out.println("Enter message:");
        String message = scanner.nextLine().toLowerCase();

        System.out.println("Enter substitution key (same length as alphabet):");
        String key = scanner.nextLine();

        if (key.length() != 26) {
            System.out.println("Invalid key length! Must be 26 characters.");
            return;
        }

        HashSet<Character> keySet = new HashSet<>();
        for (char c : key.toCharArray())
            keySet.add(c);
        
        if (keySet.size() != 26) {
            System.out.println("Invalid key! Must be 26 characters with unique characters.");
            return;          
        }

        System.out.println("Encrypt (E) or Decrypt (D)?");
        char action = scanner.next().toUpperCase().charAt(0);

        String result = "";
        for (char c : message.toCharArray()) {
            int index = c - 'a';
            if (index > -1 && index < 26) {
                char newChar = (action == 'E') ? key.charAt(index) : (char) ('a' + key.indexOf(c));
                result += newChar;
            } else {
                result += c;
            }
        }

        if (action == 'E') {
            System.out.println("Encrypted message: " + result);
        } else if (action == 'D') {
            System.out.println("Decrypted message: " + result);
        } else {
            System.out.println("Invalid action!");
        }
    }
}
