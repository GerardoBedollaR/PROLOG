# Práctica: Cálculo de Factorial 

## Introducción
En esta práctica exploraremos el paradigma de la programación recursiva en Java. La recursividad es una técnica fundamental donde un método se llama a sí mismo para resolver un problema, dividiéndolo en subproblemas más pequeños del mismo tipo. Todo método recursivo requiere un "caso base" para detener la ejecución y un "caso recursivo" para continuar el ciclo.

## Resumen de la práctica
El objetivo de este ejercicio es implementar un algoritmo matemático utilizando recursividad para calcular el factorial de un número entero. En matemáticas, el factorial representa el producto de todos los números enteros positivos desde 1 hasta dicho número. Mediante un caso base (para 0 y 1) y un caso recursivo (multiplicar el número por el factorial de su anterior), el programa determinará el resultado correcto para el valor asignado (ej. 5! = 120).

## Código Fuente
A continuación se presenta el código en Java, estructurado y comentado explicando el funcionamiento lógico:

```java
/*
 * Práctica de cálculo de Factorial Recursivo
 * Clase: Practica3_2
 */
package practica3_2;

/**
 * @author gerar
 */
public class Practica3_2 {

   public static void main(String[] args) {
        int n = 5; // Número del que queremos calcular el factorial (ej. 5! = 120)
        System.out.println("El factorial de " + n + " es: " + calcular(n));
    }

    public static int calcular(int n) {
        // Caso base: el factorial de 0 y 1 siempre es 1. Esto detiene la cadena de multiplicaciones.
        if (n == 0 || n == 1) return 1;
        
        // Caso recursivo: el número actual 'n' multiplicado por el resultado de la función evaluando a su anterior (n - 1).
        return n * calcular(n - 1);
    }
    
}