# Práctica: División por resta

## Introducción
En esta práctica continuaremos explorando el paradigma de la programación recursiva en Java para realizar operaciones aritméticas. La recursividad es una técnica donde un método se llama a sí mismo, dividiendo el problema en subproblemas más pequeños, y siempre requiere de un "caso base" para detener la ejecución y un "caso recursivo" para continuar el ciclo.

## Resumen de la práctica
El objetivo de este programa es implementar un algoritmo matemático que resuelva una división entera utilizando únicamente restas sucesivas[cite: 1]. El método contará recursivamente cuántas veces se puede restar el divisor del dividendo de manera reiterada[cite: 1]. Cuando el dividendo se vuelva menor que el divisor, el algoritmo se detendrá, habiendo calculado el cociente exacto[cite: 1].

## Código Fuente
A continuación se presenta el código en Java, estructurado y comentado para explicar su funcionamiento lógico:

```java
package practica3_3;

/**
 * @author gerar
 */
public class Practica3_3 {

    public static void main(String[] args) {
        int dividendo = 20; // Número a dividir
        int divisor = 4;    // Número por el cual se divide
        
        System.out.println(dividendo + " dividido entre " + divisor + " es igual a: " + calcular(dividendo, divisor));
    }

    public static int calcular(int dividendo, int divisor) {
        // Verificación de seguridad para evitar errores al intentar dividir por cero
        if (divisor == 0) {
            throw new ArithmeticException("No se puede dividir por cero");
        }
        
        // Caso base: Se activa cuando el dividendo es más pequeño que el divisor, indicando que el número ya no cabe y devolviendo 0[cite: 1].
        if (dividendo < divisor) return 0;
        
        // Caso recursivo: Cada vez que el divisor se puede restar exitosamente, la función cuenta un 1 para el cociente y vuelve a llamarse con el valor reducido[cite: 1].
        return 1 + calcular(dividendo - divisor, divisor);
    }
}