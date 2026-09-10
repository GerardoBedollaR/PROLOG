# Llenado de matriz con teselias

## Introducción
En esta práctica exploraremos la resolución de problemas matemáticos y espaciales utilizando el paradigma de la programación recursiva en Java[cite: 4]. La recursividad es una técnica fundamental donde un método se llama a sí mismo para resolver un problema, dividiéndolo en subproblemas más pequeños del mismo tipo[cite: 4]. Todo método recursivo requiere un "caso base" para detener la ejecución y un "caso recursivo" para continuar el ciclo[cite: 4].

## Resumen de la práctica
El objetivo de este programa es resolver el problema matemático conocido como el Teorema de Teselación de Golomb en una matriz de 8x8, utilizando la estrategia de "Divide y Vencerás". El algoritmo llenará la cuadrícula con piezas en forma de "L" (teselas de 3 cubitos), garantizando que las piezas no se superpongan y respetando la restricción de dejar exactamente un campo en blanco. Para lograrlo, el método dividirá iterativamente el tablero principal en 4 cuadrantes más pequeños hasta resolver todo el espacio de forma automática.

## Código Fuente
A continuación se presenta el código en Java, estructurado y comentado para explicar detalladamente el llenado de la matriz, la lógica de los cuadrantes y la pieza central:

```java
public class TeseladoRecursivo {
    private static int[][] matriz = new int[8][8];
    private static int numeroTesela = 1;

    public static void main(String[] args) {
        // Marcamos la celda (0,0) como el campo que quedará en blanco (-1)
        int filaVacia = 0, colVacia = 0;
        matriz[filaVacia][colVacia] = -1; 
        
        System.out.println("Llenado de matriz 8x8 con teselas en 'L':\n");
        // Llamada inicial para una matriz de 8x8
        llenarMatriz(8, 0, 0, filaVacia, colVacia);
        imprimirMatriz();
    }

    public static void llenarMatriz(int tamano, int filaInicio, int colInicio, int filaFaltante, int colFaltante) {
        // Caso base: un área de 1x1 no necesita teselas, esto detiene la recursividad
        if (tamano == 1) return;

        // Dividimos el problema en subproblemas (4 cuadrantes)
        int mitad = tamano / 2;
        int centroFila = filaInicio + mitad;
        int centroCol = colInicio + mitad;
        int teselaActual = numeroTesela++;

        // Cuadrante 1: Superior Izquierdo
        if (filaFaltante < centroFila && colFaltante < centroCol) {
            // Si el espacio faltante/vacío ya está aquí, evaluamos este cuadrante tal cual
            llenarMatriz(mitad, filaInicio, colInicio, filaFaltante, colFaltante);
        } else {
            // Si no está, pintamos un cubo de la tesela en la esquina central y lo evaluamos
            matriz[centroFila - 1][centroCol - 1] = teselaActual;
            llenarMatriz(mitad, filaInicio, colInicio, centroFila - 1, centroCol - 1);
        }

        // Cuadrante 2: Superior Derecho
        if (filaFaltante < centroFila && colFaltante >= centroCol) {
            llenarMatriz(mitad, filaInicio, centroCol, filaFaltante, colFaltante);
        } else {
            matriz[centroFila - 1][centroCol] = teselaActual;
            llenarMatriz(mitad, filaInicio, centroCol, centroFila - 1, centroCol);
        }

        // Cuadrante 3: Inferior Izquierdo
        if (filaFaltante >= centroFila && colFaltante < centroCol) {
            llenarMatriz(mitad, centroFila, colInicio, filaFaltante, colFaltante);
        } else {
            matriz[centroFila][centroCol - 1] = teselaActual;
            llenarMatriz(mitad, centroFila, colInicio, centroFila, centroCol - 1);
        }

        // Cuadrante 4: Inferior Derecho
        if (filaFaltante >= centroFila && colFaltante >= centroCol) {
            llenarMatriz(mitad, centroFila, centroCol, filaFaltante, colFaltante);
        } else {
            matriz[centroFila][centroCol] = teselaActual;
            llenarMatriz(mitad, centroFila, centroCol, centroFila, centroCol);
        }
    }

    public static void imprimirMatriz() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (matriz[i][j] == -1) {
                    System.out.printf("%4s", "X"); // La celda vacía se imprime como X
                } else {
                    System.out.printf("%4d", matriz[i][j]);
                }
            }
            System.out.println();
        }
    }
}