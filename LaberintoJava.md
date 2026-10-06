# Práctica: Resolución de Laberinto con Recursividad y Backtracking en Java

## Introducción
En esta práctica exploramos el paradigma de la programación recursiva combinado con el algoritmo de *Backtracking* (vuelta atrás) en Java. La recursividad es una técnica fundamental donde un método se llama a sí mismo para resolver un problema dividiéndolo en subproblemas más pequeños[cite: 1]. En combinación con el backtracking, el programa explora caminos posibles en un espacio de búsqueda (como una matriz o cuadrícula) y retrocede de manera automática al topar con un obstáculo o callejón sin salida.

## Resumen de esta práctica
El objetivo de este programa es encontrar una ruta de salida en un laberinto representado por una matriz bidimensional. El algoritmo evaluará una casilla a la vez, marcándola como visitada y explorando recursivamente en cuatro direcciones (abajo, derecha, arriba e izquierda)[cite: 9]. Si el camino elegido no conduce a la salida (marcada con el número 9), el algoritmo desmarcará la casilla y retrocederá para intentar una dirección alternativa, garantizando encontrar la salida si existe una ruta disponible[cite: 9].

## Código Fuente
A continuación se presenta el código completo en Java, estructurado y comentado paso a paso:

```java
/*
 * Práctica: Resolución de Laberinto con Backtracking
 * Clase: LaberintoRecursivo
 */
public class LaberintoRecursivo {

    // Representación de la matriz:
    // 0 = Camino libre, 1 = Pared/Obstáculo, 9 = Salida, 2 = Casilla visitada
    static int[][] laberinto = {
        {0, 1, 0, 0, 0},
        {0, 1, 0, 1, 0},
        {0, 0, 0, 1, 9},
        {1, 1, 0, 1, 1},
        {0, 0, 0, 0, 0}
    };

    public static void main(String[] args) {
        // Iniciamos el recorrido en la coordenada superior izquierda (0,0)
        System.out.println("Buscando la salida del laberinto...\n");
        if (resolver(0, 0)) {
            System.out.println("¡Salida encontrada con éxito!");
        } else {
            System.out.println("No existe un camino posible hacia la salida.");
        }
    }

    public static boolean resolver(int x, int y) {
        // 1. CASOS BASE DE FALLO: Detener si estamos fuera de los límites del tablero
        if (x < 0 || y < 0 || x >= laberinto.length || y >= laberinto[0].length) {
            return false;
        }
        
        // Detener si es una pared (1) o una casilla ya visitada (2) para evitar bucles
        if (laberinto[x][y] == 1 || laberinto[x][y] == 2) {
            return false;
        }

        // 2. CASO BASE DE ÉXITO: Llegamos a la casilla meta (9)
        if (laberinto[x][y] == 9) {
            return true;
        }

        // 3. CASO RECURSIVO: Marcamos la casilla actual como visitada
        laberinto[x][y] = 2;

        // Intentamos avanzar recursivamente en las 4 direcciones posibles
        if (resolver(x + 1, y)) return true; // Abajo
        if (resolver(x, y + 1)) return true; // Derecha
        if (resolver(x - 1, y)) return true; // Arriba
        if (resolver(x, y - 1)) return true; // Izquierda

        // 4. BACKTRACKING: Si ninguna dirección funcionó, desmarcamos y retrocedemos
        laberinto[x][y] = 0;
        return false;
    }
}

## Conclusión
Este ejercicio demuestra la potencia de la recursividad combinada con Backtracking para resolver problemas de exploración de rutas[cite: 9]. En lugar de evaluar exhaustivamente cada combinación de forma manual, la pila de llamadas del sistema administra automáticamente el historial de movimiento[cite: 1, 9]. Al definir adecuadamente los casos base (límites de la matriz, paredes y condición de victoria), el programa garantiza prevenir bucles infinitos y retroceder de manera eficiente al encontrar un callejón sin salida[cite: 9].
