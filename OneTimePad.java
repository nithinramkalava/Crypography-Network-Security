import java.util.Scanner;

public class OneTimePad {

    public static String stringEncryption(String text, String key) {
        char[] encryptedText = new char[text.length()];

        for(int i = 0; i < text.length(); i++){
            encryptedText[i] = (char) (((text.charAt(i) - 'a' + key.charAt(i) - 'a') % 26) + 'a');
        }

        return new String(encryptedText);
    }

    public static String stringDecryption(String text, String key) {
        char[] decryptedText = new char[text.length()];

        for(int i = 0; i < text.length(); i++){
            decryptedText[i] = (char) (((text.charAt(i) - 'a' - (key.charAt(i) - 'a') + 26) % 26) + 'a');
        }

        return new String(decryptedText);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter plain text: ");
        String plainText = sc.nextLine().toLowerCase();

        System.out.println("Enter Key (same length as plain text): ");
        String key = sc.nextLine().toLowerCase();
        sc.close();

        if (key.length() != plainText.length()) {
            System.out.println("Key length must match plaintext length.");
            return;
        }

        String encryptedText = stringEncryption(plainText, key);
        System.out.println("Cipher Text - " + encryptedText);
        System.out.println("Message - " + stringDecryption(encryptedText, key));
    }
}
