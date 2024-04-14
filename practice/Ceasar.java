package practice;

import java.util.Scanner;

public class Ceasar {

    public static String encrypt(String message, int shift) {

        StringBuilder encryptedText = new StringBuilder();
        for(char letter: message.toCharArray()) {
            if (letter > 'a' - 1 && letter < 'z' + 1) {
                encryptedText.append((char)('a' + ((letter - 'a' + shift + 26) % 26)));
            } else encryptedText.append(letter);
        }

        return encryptedText.toString();
    }

    public static String decrypt(String cipherText, int shift) {
        return encrypt(cipherText, -shift);
    }
    public static void main(String[] args) {
        System.out.println("Enter Message: ");
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine().toLowerCase();
        System.out.println("Enter shift value: ");
        int shift = sc.nextInt() % 26;
        sc.close();

        String x = encrypt(message, shift);
        String y = decrypt(x, shift);
        System.out.println(x + "  " + y);
    }
}
