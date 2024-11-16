package mp.utils;
import java.util.Scanner;

public class ConsoleGets {
    /**
    * Method to get an integer value with a minimum and maximum from the user.
    * 
    * @param msg Message to inform the user to enter a value.
    * @param min Minimum value that the integer entered by the user should be.
    * @param max Maximum value that the integer entered by the user should be.
    * @param error Message to print in case an undesired value is given.
    * @return An integer value provided by the user.
    */
    public static int getInt(String msg, String error, int min, int max) {
        int val;
        try (Scanner scn = new Scanner(System.in)) {
            while (true) {
                System.out.println(msg);
                if (scn.hasNextInt()) {
                    val = scn.nextInt();
                    // (-infinit, min) || (max, infinit)
                    if (val < min || max < val) {
                        System.out.println(error);
                    } else {
                        return val;
                    }
                } else {
                    scn.next();
                    System.out.println(error);
                }
            }
        }
    }

    /**
    * Method to get an integer value from the user.
    * 
    * @param msg Message to inform the user to enter a value.
    * @return An integer value provided by the user.
    */
    public static String getString(String mensaje) {
        try (Scanner scn = new Scanner(System.in)) {
            System.out.println(mensaje);
            return scn.nextLine();
        }
    }
}
