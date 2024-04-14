import java.security.Key;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;

public class MessageAuthenticationCodes {
   public static void main(String args[]) throws Exception{
      //Creating a KeyGenerator object
      KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
      Key key = keyGen.generateKey();     

      //Creating a Mac object
      Mac mac = Mac.getInstance("HmacSHA256");

      //Initializing the Mac object
      mac.init(key);

      //Computing the Mac
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter Message: ");
      String msg = scanner.nextLine();
      scanner.close();
      byte[] bytes = msg.getBytes();      
      byte[] macResult = mac.doFinal(bytes);

      System.out.println("Mac result:");
      System.out.println(Base64.getEncoder().encodeToString(macResult));     
   }
}