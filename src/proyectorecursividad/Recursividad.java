package proyectorecursividad;

import java.util.Random;

/**
 *
 * @author Carlos Contreras
 * @materia Estructura de Datos
 * @tema Proyecto de Recursividad
 * @facilitador Clemente García Gerardo
 */
public class Recursividad {

    private final static String MENSAJE_ERROR = "\nERROR: No se ha generado ninguna matriz. "
            + "Asegúrese de generarla antes con la opción 1 del menú.\n";

    private final static String[] UNIDADES = {"un ", "dos ", "tres ",
        "cuatro ", "cinco ", "seis ", "siete ", "ocho ", "nueve ", "diez ",
        "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
        "diecisiete ", "dieciocho ", "diecinueve", "veinte ", "veintiuno ",
        "veintidos ", "veintitres ", "veinticuatro ", "veinticinco ",
        "veintiseis ", "veintisiete ", "veintiocho ", "veintinueve "};
    private final static String[] DECENAS = {"treinta ", "cuarenta ",
        "cincuenta ", "sesenta ", "setenta ", "ochenta ", "noventa "};
    private final static String[] CENTENAS = {"ciento ", "doscientos ",
        "trecientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
        "setecientos ", "ochocientos ", "novecientos "};

    public static int[][] generarMatriz() {
        Random random = new Random();
        int[][] matriz = new int[5][5];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                matriz[i][j] = random.nextInt(100) + 1;
            }
        }
        return matriz;
    }

    public static void mostrarMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static boolean estaGenerada(int[][] matriz) {
        if (matriz == null) {
            System.out.println(MENSAJE_ERROR);
            return false;
        }
        return true;
    }

    // 1. Desarrolle un método recursivo que calcule la suma de los valores contenidos en un renglón especificado.
    public static int sumaRenglon(int[][] matriz, int renglon, int indice) {
        if (indice == matriz[renglon].length - 1) {
            return matriz[renglon][indice];
        }
        return sumaRenglon(matriz, renglon, indice + 1) + matriz[renglon][indice];
    }

    // 2. Desarrolle un método recursivo que calcule la suma de los valores por cada renglón de la matriz.
//<editor-fold defaultstate="collapsed" desc="Borradores">
//    public static int[] sumaPorCadaRenglon(int[][] matriz, int[] sumatorias, int renglon) {
    // Caso base
//        if (renglon == matriz.length) {
//            return sumatorias;
//        }
//        sumatorias[renglon] = sumaRenglon(matriz, sumatorias, renglon);
// No me salía dando como parámetro la sumatorias (para irlo guardando), asi que usaré una variable estática
//</editor-fold>
    static int[] sumatorias;

    public static int[] sumaPorCadaRenglon(int[][] matriz, int renglon) {
        if (renglon == 0) { // Primer llamado, inicializa desde el renglón 0
            sumatorias = new int[matriz.length];
        }
        sumatorias[renglon] = sumaRenglon(matriz, renglon, 0);
        if (renglon == matriz.length - 1) { // Mismo caso que en la suma por renglón
            return sumatorias;
        }
        return sumaPorCadaRenglon(matriz, renglon + 1);
    }

    // 3. Desarrolle un algoritmo que asigne valores como muestra en la figura (archivo Word).
    public static int[][] asignarMatriz(int[][] matriz, int renglon, int columna) {
        // En el archivo vienen las condiciones para insertar, aquí se valida y se insertan valores
        // dependiendo estas. El Caso Base será cuando llegue al final de la matriz (ya rellenó todo).
        if (renglon == matriz.length || columna == matriz.length) {
            return matriz;
        }

        // 1. a los elementos de la diagonal principal. 
        // Cuando su renglon y columna son iguales (diag principal)
        if (renglon == columna) {
            matriz[renglon][columna] = 1;
        }

        // 2. a los elementos de la digonal secundaria (excepto intersección de 1 y 2).
        // Si son diferentes y su suma da 4, o sea length-1, inserta.
        if (renglon != columna && renglon + columna == matriz.length - 1) {
            matriz[renglon][columna] = 2;
        }

        // 3. a los elementos que están debajo de 1 y arriba de 2.
        // Se puede tomar de referencia el 4 que es el lenght-1 para que no sobrepase ese valor.
        // en estos el renglon es mayor que la columna siempre
        if (renglon + columna < matriz.length - 1 && renglon > columna) {
            matriz[renglon][columna] = 3;
        }

        // 4. a los elementos que están a la derecha de 1 e izquierda de 2.
        // En este caso renglon menor que columna
        if (renglon + columna < matriz.length - 1 && renglon < columna) {
            matriz[renglon][columna] = 4;
        }

        // 5. a los elementos que estan debajo de 2 y arriba de 1.
        // Renglon menor que columna pero sobrepasando el length-1
        if (renglon + columna > matriz.length - 1 && renglon < columna) {
            matriz[renglon][columna] = 5;
        }

        // 6. a los elementos que están a la derecha de 2 e izquierda de 1.
        // Igual pero renglon es mayor que columna
        if (renglon + columna > matriz.length - 1 && renglon > columna) {
            matriz[renglon][columna] = 6;
        }

        // Se recorre la fila cuando se llega al final de la columna
        if (renglon < matriz.length - 1 && columna == matriz.length - 1) {
//            return asignarMatriz(matriz, renglon + 1, columna);
            return asignarMatriz(matriz, renglon + 1, 0);
        }
        // Se recorre la columna
        if (columna < matriz.length) {
            return asignarMatriz(matriz, renglon, columna + 1);
        }
        return matriz;
    }

    /* 4. Desarrolle un método recursivo que reciba una cantidad entera y regrese su descripción en texto. 
    Ejemplo, recibe 1777217 (un millón setecientos setenta y siete mil dos cientos diecisitete) */
    public static String convertirAString(int numero) {
        String cadena = Integer.toString(numero); // Cast para trabajar con métodos de String con el mismo número
        while ((cadena.length() % 3) != 0) {  // Se completa si los primeros números no cierran en cantidades de 3
            cadena = "0" + cadena;
        }
        // Hago número la cadena con los 0's añadidos
        numero = Integer.parseInt(cadena);
        if (cadena.length() <= 3) {
            // Casos especiales
            if (numero == 0) {
                return "";
            }
            if (numero < 30) {
                return UNIDADES[numero - 1];
                // TODO: Validar caso si el último es 1, fuera "uno" en vez de "un".
            }
            if (numero < 100) {
                return DECENAS[Integer.parseInt(cadena.substring(1, 2)) - 3]
                        + "y "
                        + UNIDADES[Integer.parseInt(cadena.substring(2)) - 1];
            }
            if (numero == 100) {
                return "cien";
            }
            if (numero < 1000) {
                return CENTENAS[Integer.parseInt(cadena.substring(0, 1)) - 1]
                        + convertirAString(Integer.parseInt(cadena.substring(1)));
//                            + UNIDADES[Integer.parseInt(cadena.substring(1))];
            }
            return CENTENAS[Integer.parseInt(cadena.substring(0, 1)) - 1]
                    + DECENAS[Integer.parseInt(cadena.substring(1, 2)) - 2]
                    + "y "
                    + UNIDADES[Integer.parseInt(cadena.substring(2))];
        }

        // Imprimir nombre cantidades dependiendo de lo grande de la cantidad.
        if (numero > 0 && numero < 1000000) {
            return convertirAString(Integer.parseInt(cadena.substring(0, 3)))
                    + " mil "
                    + convertirAString(Integer.parseInt(cadena.substring(3)));
        }
        if (cadena.charAt(2) == '1' && numero < 2000000) {
            return convertirAString(Integer.parseInt(cadena.substring(0, 3)))
                    + " millon "
                    + convertirAString(Integer.parseInt(cadena.substring(3)));
        }
        if (numero > 0) {
            return convertirAString(Integer.parseInt(cadena.substring(0, 3)))
                    + " millones "
                    + convertirAString(Integer.parseInt(cadena.substring(3)));
        }
        return "";
    }
}
