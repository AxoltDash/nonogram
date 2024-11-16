package mp.utils;
import java.util.Scanner;

public class Gets {
    /**
    * Metodo para obtener un valor entero con un minimo y un maximo de parte del usuario.
    * 
    * @param mensaje Frase para dar a conocer al usuario que debe ingresar un valor
    * @param min Valor minimo que quieres que sea el entero que ingrese el usuario.
    * @param max Valor maximo que quieres que sea el entero que ingrese el usuario.
    * @param error Frase para imprimir en caso de que se de un valor no deseado.
    * @return Un valor entero proporcionado por el usuario.
    */
    public static int getInt(String mensaje, String error, int min, int max) {
        int val;
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.println(mensaje);
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


}
