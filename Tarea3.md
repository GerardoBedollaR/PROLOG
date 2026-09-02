# Práctica: Número de Fibonachi

## Introducción
La recursividad es un paradigma de programación donde un método se llama a sí mismo para resolver un problema, dividiéndolo en subproblemas más pequeños del mismo tipo. Todo método recursivo requiere de un "caso base" para detener la ejecución y de un "caso recursivo" para continuar el ciclo[cite: 1].

## Resumen de la práctica
El objetivo de este programa es calcular la sucesión de Fibonacci en una posición específica (en este caso, la posición 10). Se implementará un algoritmo recursivo que, mediante un caso base para los valores 0 y 1, y un caso recursivo que suma las dos posiciones anteriores, determinará el número correspondiente en la serie matemática[cite: 1].

## Código Fuente
A continuación se presenta el código en Java, comentado para explicar su funcionamiento lógico:

```java
package practica3;

/**
 * @author gerar
 */
public class Practica3 {

    public static void main(String[] args) {
        int n = 10; // Posición en la serie que queremos calcular
        System.out.println("El numero de Fibonacci en la posicion " + n + " es: " + calcular(n));
    }

    public static int calcular(int n) {
        // Caso base: Si el valor solicitado es 0 o 1, la función devuelve el mismo número[cite: 1].
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        // Caso recursivo: La función se llama a sí misma para sumar las dos posiciones anteriores en la sucesión[cite: 1].
        return calcular(n - 1) + calcular(n - 2);
    }
}