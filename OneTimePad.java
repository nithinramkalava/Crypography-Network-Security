import java.util.Scanner;

public class OneTimePad {

    public static String stringEncryption(String text, String key) {
        String cipherText = "";

        // Initialize cipher array of key length
        // which stores the sum of corresponding no.'s
        // of plainText and key.
        int cipher[] = new int[key.length()];

        for (int i = 0; i < key.length(); i++) {
            cipher[i] = text.charAt(i) - 'A' + key.charAt(i) - 'A';
        }

        // If the sum is greater than 25
        // subtract 26 from it
        // and store that resulting value
        for (int i = 0; i < key.length(); i++) {
            if (cipher[i] > 25) {
                cipher[i] = cipher[i] - 26;
            }
        }

        // Converting the no.'s into integers

        // Convert these integers to corresponding
        // characters and add them up to cipherText
        for (int i = 0; i < key.length(); i++) {
            int x = cipher[i] + 'A';
            cipherText += (char) x;
        }

        // Returning the cipherText
        return cipherText;
    }

    public static String stringDecryption(String s, String key) {
        // Initializing plain text
        String plainText = "";

        // Initializing integer array of key length
        // which stores difference
        // of corresponding no.'s of
        // each character of cipherText and key
        int plain[] = new int[key.length()];

        // Running for loop for each character
        // subtracting and storing in the array
        for (int i = 0; i < key.length(); i++) {
            plain[i] = s.charAt(i) - 'A' - (key.charAt(i) - 'A');
        }

        // If the difference is less than 0
        // add 26 and store it in the array.
        for (int i = 0; i < key.length(); i++) {
            if (plain[i] < 0) {
                plain[i] = plain[i] + 26;
            }
        }

        // Converting int to corresponding char
        // add them up to plainText
        for (int i = 0; i < key.length(); i++) {
            int x = plain[i] + 'A';
            plainText += (char) x;
        }

        // Returning plainText
        return plainText;
    }

    // Method 3
    // Main driver method
    public static void main(String[] args) {
        // Declaring plain text

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter plain text: ");
        String plainText = sc.nextLine();

        // Declaring key
        System.out.println("Enter Key (same lenght as plain text): ");
        String key = sc.nextLine();
        sc.close();

        if (key.length() != plainText.length()) return;

        // Converting plain text to toUpperCase
        // function call to stringEncryption
        // with plainText and key as parameters
        String encryptedText = stringEncryption(
                plainText.toUpperCase(),
                key.toUpperCase());

        // Printing cipher Text
        System.out.println("Cipher Text - " + encryptedText);

        // Calling above method to stringDecryption
        // with encryptedText and key as parameters
        System.out.println(
                "Message - " + stringDecryption(encryptedText, key.toUpperCase()));
    }
}
