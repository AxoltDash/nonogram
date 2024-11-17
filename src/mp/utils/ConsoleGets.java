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
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.println(msg);
            if (scn.hasNextInt()) {
                val = scn.nextInt();
                // (-infinito, min) || (max, infinito)
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

    public static int[] getCoordinates(int size, String rowMsg, String colMsg, String error) {
        int x = getInt(rowMsg, error, 0, size);
        int y = getInt(colMsg, error, 0, size);
        return new int[] {x, y};
    }

    /**
    * Method to get an String value from the user.
    * 
    * @param msg Message to inform the user to enter a value.
    * @return An string value provided by the user.
    */
    public static String getString(String mensaje) {
        Scanner scn = new Scanner(System.in);
        System.out.println(mensaje);
        return scn.nextLine();
    }
}
