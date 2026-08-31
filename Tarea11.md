# Práctica: Desglose de Billetes en Java

## Introducción
En esta práctica exploraremos el uso de estructuras de control, específicamente ciclos y condicionales en Java, para resolver un problema cotidiano de lógica matemática: calcular el desglose de una cantidad de dinero en billetes de diferentes denominaciones.

## Resumen de la práctica
El objetivo principal de este ejercicio es desarrollar un algoritmo que determine la menor cantidad de billetes necesarios para cubrir un monto específico (en este caso, $385). Para lograrlo, recorreremos un arreglo que contiene las denominaciones disponibles, aplicando la división entera para contar cuántos billetes entregar y la operación módulo (residuo) para actualizar la cantidad sobrante de forma iterativa.

## Código Fuente
A continuación se presenta el código en Java, estructurado y comentado explicando el funcionamiento lógico:

```java
/*
 * Práctica de desglose de moneda
 * Clase: Tarea11
 */
package tarea.pkg1.pkg1;

/**
 * @author gerar
 */
public class Tarea11 {

    public static void main(String[] args) {
        int cantidad = 385; // Cantidad de dinero a desglosar (puedes cambiarla para probar)
        int[] denominaciones = {100, 50, 20, 10, 5}; // Billetes disponibles ordenados de mayor a menor
        
        System.out.println("Desglose de $" + cantidad + " usando ciclos:");
        
        // Recorremos el arreglo de denominaciones de una por una
        for (int i = 0; i < denominaciones.length; i++) {
            int billeteActual = denominaciones[i];
            
            // Si la cantidad es mayor o igual al billete, podemos dar cambio con él
            if (cantidad >= billeteActual) {
                int cuantosBilletes = cantidad / billeteActual; // Cuántos billetes caben (división entera)
                cantidad = cantidad % billeteActual;            // Cuánto sobra (el residuo)
                
                System.out.println(cuantosBilletes + " billete(s) de $" + billeteActual);
            }
        }
        
        // Por si queda un sobrante menor a 5 (nuestra denominación más baja)
        if (cantidad > 0) {
            System.out.println("Sobra: $" + cantidad + " (No hay denominaciones para esto)");
        }
    }
}