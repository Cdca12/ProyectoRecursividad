package proyectorecursividad;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Carlos Contreras
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matriz = null;

        int opcion = 0;
        do {
            System.out.println("Menú Principal"
                    + "\n\n1. Generar una matriz aleatoria 5x5."
                    + "\n2. Mostrar matriz generada."
                    + "\n3. Calcular la suma de los valores contenidos en un renglón especificado."
                    + "\n4. Calcular la suma de los valores por cada renglón de la matriz."
                    + "\n5. Asignar valores a la matriz."
                    + "\n6. Obtener descripción de una cantidad entera."
                    + "\n7. Salir");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1: // Generar matriz para trabajar
                    matriz = Recursividad.generarMatriz();
                    System.out.println("\n¡La matriz se ha generado correctamente!"
                            + "\nSeleccione la opción 2 del menú para mostrarla.\n");
                    break;
                case 2: // Mostrar matriz generada.
                    if (Recursividad.estaGenerada(matriz)) {
                        System.out.println("\nMatriz generada para trabajar: ");
                        Recursividad.mostrarMatriz(matriz);
                        System.out.println("");
                    }
                    break;
                case 3: // Método 1
                    if (Recursividad.estaGenerada(matriz)) {
                        System.out.println("\nIngrese el número del renglón del que "
                                + "desea calcular la suma.\r");
                        /* Se pide el renglón mas no la posición, recordemos que los arreglos
                    en Java están basados en 0 por lo que se le resta 1. */
                        int renglon = scanner.nextInt() - 1;
                        System.out.println("Ingrese el índice a partir de cuál posición "
                                + "va a empezar a sumar.\r");
                        int indice = scanner.nextInt();
                        System.out.println("\nLa suma de los valores del renglón "
                                + renglon + " es igual a "
                                + Recursividad.sumaRenglon(matriz, renglon, indice) + "\n");
                    }
                    break;
                case 4: // Método 2
                    if (Recursividad.estaGenerada(matriz)) {
                        int[] sumatorias = Recursividad.sumaPorCadaRenglon(matriz, 0);
                        System.out.println("\nEl total de las sumas por renglones es:\n");
                        System.out.println(Arrays.toString(sumatorias));
                        System.out.println();
                    }
                    break;
                case 5: // Método 3
                    matriz = new int[5][5];
                    matriz = Recursividad.asignarMatriz(matriz, 0, 0);
                    System.out.println("\n¡Se le han asignado los valores a la matriz de acuerdo al ejercicio!"
                            + "\nSeleccione la opción 2 del menú para mostrarla.\n");
                    System.out.println("");
                    break;
                case 6: // Método 4
                    System.out.println("\nIngrese un número para convertirlo a cadena de texto: \r");
                    int numero = scanner.nextInt();
                    System.out.println("Cantidad en cadena: " + Recursividad.convertirAString(numero));
                    break;
                case 7: // Salir
                    System.out.println("\n¡Ha finalizado la ejecución del programa correctamente!\n");
                    break;
                default:
                    System.out.println("\nFavor de seleccionar una opción del menú.\n");
            }
        } while (opcion != 7);
    }
}
